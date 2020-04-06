package com.webapp.storage;

import com.webapp.exception.ResumeNotFoundException;
import com.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {
    
    @Override
    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        
        if (index < 0) {
            throw new ResumeNotFoundException(resume.getUuid());
        } else {
            updateResume(resume, index);
            System.out.println("\nThe resume with \"" + resume.getUuid() + "\" has been updated.");
        }
    }
    
    @Override
    public void delete(String uuid) {
        int index = getIndex(uuid);
        
        if (index < 0) {
            throw new ResumeNotFoundException(uuid);
        } else {
            deleteResume(index);
            System.out.println("\nThe resume with \"" + uuid + "\" has been removed from the database.");
        }
    }
    
    @Override
    public Resume get(String uuid) {
        int index = getIndex(uuid);
        
        if (index < 0) {
            throw new ResumeNotFoundException(uuid);
        }
        return getResume(index);
    }
    
    protected abstract int getIndex(String uuid);
    
    protected abstract Resume getResume(int index);
    
    protected abstract void updateResume(Resume resume, int index);
    
    protected abstract void deleteResume(int index);
}
