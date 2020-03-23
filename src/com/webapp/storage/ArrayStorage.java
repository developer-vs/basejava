package com.webapp.storage;

import com.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {
    
    @Override
    protected int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
    
    @Override
    protected void placeResume(Resume resume, int index) {
        storage[size] = resume;
    }
    
    @Override
    protected void refillResume(int index) {
        storage[index] = storage[size - 1];
    }
}