package com.webapp.storage;

import com.webapp.exception.ResumeExistException;
import com.webapp.exception.ResumeNotFoundException;
import com.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {
    
    @Override
    public void save(Resume resume) {
        int searchKey = getSearchKey(resume.getUuid());
        
        if (searchKey < 0) {
            saveResume(resume, searchKey);
        } else {
            throw new ResumeExistException(resume.getUuid());
        }
    }
    
    @Override
    public void update(Resume resume) {
        updateResume(resume, checkSearchKey(resume.getUuid()));
        System.out.println("\nThe resume with \"" + resume.getUuid() + "\" has been updated.");
    }
    
    @Override
    public void delete(String uuid) {
        deleteResume(checkSearchKey(uuid));
        System.out.println("\nThe resume with \"" + uuid + "\" has been removed from the database.");
    }
    
    @Override
    public Resume get(String uuid) {
        return getResume(checkSearchKey(uuid));
    }
    
    private int checkSearchKey(Object uuid) {
        int searchKey = getSearchKey(uuid);
        
        if (searchKey < 0) {
            throw new ResumeNotFoundException(uuid);
        }
        return searchKey;
    }
    
    protected abstract int getSearchKey(Object uuid);
    
    protected abstract void saveResume(Resume resume, int searchKey);
    
    protected abstract Resume getResume(Object searchKey);
    
    protected abstract void updateResume(Resume resume, int searchKey);
    
    protected abstract void deleteResume(Object searchKey);
}