package com.webapp.storage;

import com.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {
    
    @Override
    public void save(Resume resume) {
    
    }
    
    @Override
    public void update(Resume resume) {
    
    }
    
    @Override
    public void delete(String uuid) {
    
    }
    
    @Override
    public void clear() {
    
    }
    
    @Override
    public Resume[] getAll() {
        return new Resume[0];
    }
    
    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}
