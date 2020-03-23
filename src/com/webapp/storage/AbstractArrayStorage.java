package com.webapp.storage;

import com.webapp.exception.ResumeExistException;
import com.webapp.exception.ResumeNotFoundException;
import com.webapp.exception.StorageException;
import com.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    
    protected static final int STORAGE_LIMIT = 10_000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size;
    
    public int size() {
        return size;
    }
    
    public void save(Resume resume) {
        int index = getIndex(resume.getUuid());
        
        if (size < STORAGE_LIMIT) {
            if (index < 0) {
                placeResume(resume, index);
                size++;
                System.out.println("\nThe resume with \"" + resume.getUuid() + "\" has been saved in the database.");
            } else {
                throw new ResumeExistException(resume.getUuid());
            }
        } else {
            throw new StorageException("The storage is FULL, the resume with \"" + resume.getUuid() +
                "\" cannot be saved in the database.", resume.getUuid());
        }
    }
    
    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        
        if (index < 0) {
            throw new ResumeNotFoundException(resume.getUuid());
        } else {
            storage[index] = resume;
            System.out.println("\nThe resume with \"" + resume.getUuid() + "\" has been updated.");
        }
    }
    
    public void delete(String uuid) {
        int index = getIndex(uuid);
        
        if (index < 0) {
            throw new ResumeNotFoundException(uuid);
        } else {
            refillResume(index);
            storage[size - 1] = null;
            size--;
            System.out.println("\nThe resume with \"" + uuid + "\" has been removed from the database.");
        }
    }
    
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }
    
    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }
    
    public Resume get(String uuid) {
        int index = getIndex(uuid);
        
        if (index < 0) {
            throw new ResumeNotFoundException(uuid);
        }
        return storage[index];
    }
    
    protected abstract int getIndex(String uuid);
    
    protected abstract void placeResume(Resume resume, int index);
    
    protected abstract void refillResume(int index);
}
