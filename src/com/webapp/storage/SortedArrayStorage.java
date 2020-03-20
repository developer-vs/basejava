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
        int idx = -index - 1;
        System.arraycopy(storage, idx, storage, idx + 1, size);
        storage[idx] = resume;
    }
    
    @Override
    protected void refillResume() {
        System.arraycopy(storage, index + 1, storage, index, size - 1 - index);
    }
}