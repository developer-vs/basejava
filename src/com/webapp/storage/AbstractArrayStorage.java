package com.webapp.storage;

import com.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    
    protected static final int STORAGE_LIMIT = 10_000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size;
    protected int index;
    
    public int size() {
        return size;
    }
    
    public void save(Resume resume) {
        index = getIndex(resume.getUuid());
        
        if (size < STORAGE_LIMIT) {
            if (index < 0) {
                placeResume(resume);
                size++;
                System.out.println("\nThe resume with \"" + resume.getUuid() + "\" has been saved in the database.");
            } else {
                System.out.println("\nThe resume with \"" + resume.getUuid() + "\" already exists in the database.");
            }
        } else {
            System.out.println("\nThe storage is FULL, the resume with \"" + resume.getUuid()
                + "\" cannot be saved in the database.");
        }
    }
    
    public void update(Resume resume) {
        index = getIndex(resume.getUuid());
        
        if (index < 0) {
            System.out.println("\nThe resume with \"" + resume.getUuid() + "\" does not exist in the database.");
        } else {
            storage[index] = resume;
            System.out.println("\nThe resume with \"" + resume.getUuid() + "\" has been updated.");
        }
    }
    
    public void delete(String uuid) {
        index = getIndex(uuid);
        
        if (index < 0) {
            System.out.println("\nThe resume with \"" + uuid + "\" does not exist in the database.");
        } else {
            refillResume();
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
        index = getIndex(uuid);
        
        if (index < 0) {
            System.out.println("\nThe resume with \"" + uuid + "\" does not exist in the database.");
            return null;
        }
        return storage[index];
    }
    
    protected abstract int getIndex(String uuid);
    
    protected abstract void placeResume(Resume resume);
    
    protected abstract void refillResume();
}
