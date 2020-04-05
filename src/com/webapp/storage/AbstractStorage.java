package com.webapp.storage;

import com.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {
    
    @Override
    public abstract void save(Resume resume);
    
    @Override
    public void update(Resume resume) {
    
    }
    
    @Override
    public Resume get(String uuid) {
        return null;
    }
    
    @Override
    public void delete(String uuid) {
    
    }
    
    @Override
    public abstract int size();
    
    @Override
    public void clear() {
    
    }
    
    /**
     * @return array, contains only Resumes in storage (without null)
     */
    @Override
    public abstract Resume[] getAll();
}
