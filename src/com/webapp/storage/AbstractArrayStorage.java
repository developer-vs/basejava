package com.webapp.storage;

import com.webapp.exception.StorageException;
import com.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage {

    protected static final int STORAGE_LIMIT = 10_000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size;

    @Override
    public int size() {
        return size;
    }

    protected void saveResume(Resume resume, int index) {
        if (size < STORAGE_LIMIT) {
            placeResume(resume, index);
            size++;
            System.out.println("\nThe resume with \"" + resume.getUuid() + "\" has been saved in the database.");
        } else {
            throw new StorageException("The storage is FULL, the resume with \"" + resume.getUuid() +
                    "\" cannot be saved in the database.", resume.getUuid());
        }
    }

    @Override
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    @Override
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    @Override
    protected Resume getResume(String uuid, int index) {
        return storage[index];
    }

    @Override
    protected void updateResume(Resume resume, int index) {
        storage[index] = resume;
    }

    @Override
    protected void deleteResume(String uuid, int index) {
        refillResume(index);
        storage[size - 1] = null;
        size--;
    }

    protected abstract void refillResume(int index);

    protected abstract void placeResume(Resume resume, int index);
}
