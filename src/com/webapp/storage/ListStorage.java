package com.webapp.storage;

import com.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {
    
    private List<Resume> storage = new ArrayList<>();
    
    protected void saveResume(Resume resume, int index) {
        storage.add(resume);
        System.out.println("\nThe resume with \"" + resume.getUuid() + "\" has been saved in the database.");
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
    protected int getSearchKey(Object uuid) {
        return storage.indexOf(new Resume((String) uuid));
    }
    
    @Override
    protected Resume getResume(Object index) {
        return storage.get((int) index);
    }
    
    @Override
    protected void updateResume(Resume resume, int index) {
        storage.set(index, resume);
    }
    
    @Override
    protected void deleteResume(Object index) {
        storage.remove((int) index);
    }
}