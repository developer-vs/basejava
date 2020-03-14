package com.webapp.storage;

import com.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {
    
    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
    
    @Override
    protected void placeResume(Resume resume) {
        System.arraycopy(storage, -index - 1, storage, -index, size);
        storage[-index - 1] = resume;
    }
    
    @Override
    protected void refillResume() {
        System.arraycopy(storage, index + 1, storage, index, size - 1 - index);
    }
}
