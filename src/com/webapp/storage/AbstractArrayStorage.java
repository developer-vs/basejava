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
    protected void saveResume(Resume resume) {
        if (size < STORAGE_LIMIT) {
            placeResume(resume);
            size++;
        } else {
            throw new StorageException(showMessageStorageFull(resume));
        }
    }

    @Override
    protected Resume getResume(Object index) {
        return storage[(int) index];
    }

    @Override
    protected void updateResume(Resume resume) {
        storage[updateResumeKey] = resume;
    }

    @Override
    protected void deleteResume(String uuid) {
        refillResume(checkSearchKey(uuid));
        storage[size - 1] = null;
        size--;
    }

    protected abstract void refillResume(int index);

    protected abstract void placeResume(Resume resume);
}