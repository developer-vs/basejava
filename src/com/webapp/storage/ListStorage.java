package com.webapp.storage;

import com.webapp.exception.ResumeExistException;
import com.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {
    
    private List<Resume> storage = new ArrayList<>();
    
    @Override
    public void save(Resume resume) {
        int index = getIndex(resume.getUuid());
        
        if (index < 0) {
            storage.add(resume);
            System.out.println("\nThe resume with \"" + resume.getUuid() + "\" has been saved in the database.");
        } else {
            throw new ResumeExistException(resume.getUuid());
        }
    }
    
    @Override
    public int size() {
        return storage.size();
    }
    
    /**
     * @return array, contains only Resumes in storage (without null)
     */
    @Override
    public Resume[] getAll() {
        return storage.toArray(new Resume[0]);
    }
    
    @Override
    public void clear() {
        storage.clear();
    }
    
    @Override
    protected int getIndex(String uuid) {
        return storage.indexOf(new Resume(uuid));
    }
    
    @Override
    protected Resume getResume(int index) {
        return storage.get(index);
    }
    
    @Override
    protected void updateResume(Resume resume, int index) {
        storage.set(index, resume);
    }
    
    @Override
    protected void deleteResume(int index) {
        storage.remove(index);
    }
}