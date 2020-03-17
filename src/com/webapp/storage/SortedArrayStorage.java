package com.webapp.storage;

import com.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {
    
    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
    
    @Override
    protected void placeResume(Resume resume) {
        int ind = -index;
        System.arraycopy(storage, ind - 1, storage, ind, size);
        storage[ind - 1] = resume;
    }
    
    @Override
    protected void refillResume() {
        System.arraycopy(storage, index + 1, storage, index, size - 1 - index);
    }
}