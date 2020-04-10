package com.webapp.storage;

import com.webapp.model.Resume;

import java.util.HashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage {
    
    private Map<String, Resume> storage = new HashMap<>();
    
    @Override
    protected int getSearchKey(Object uuid) {
        
        if (!storage.containsKey(uuid)) {
            return -1;
        }
        return storage.get(uuid).hashCode();
    }
    
    protected void saveResume(Resume resume, int index) {
        storage.put(resume.getUuid(), resume);
        System.out.println("\nThe resume with \"" + resume.getUuid() + "\" has been saved in the database.");
    }
    
    @Override
    protected Resume getResume(Object uuid) {
        return storage.get(uuid);
    }
    
    @Override
    protected void updateResume(Resume resume, int index) {
        storage.replace(resume.getUuid(), resume);
    }
    
    @Override
    protected void deleteResume(Object uuid) {
        storage.remove(uuid);
    }
    
    @Override
    public int size() {
        return storage.size();
    }
    
    @Override
    public void clear() {
        storage.clear();
    }
    
    /**
     * @return array, contains only Resumes in storage (without null)
     */
    @Override
    public Resume[] getAll() {
        return storage.values().toArray(new Resume[0]);
    }
}
