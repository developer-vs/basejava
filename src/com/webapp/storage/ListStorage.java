package com.webapp.storage;

import com.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {
    
    protected List<Resume> storage = new ArrayList<>();
    
    @Override
    public void save(Resume resume) {
        storage.add(resume);
    }
    
    @Override
    public int size() {
        return storage.size();
    }
    
    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        Resume[] resume = new Resume[storage.size()];
        int counter = 0;
        for(Resume r : storage) {
            resume[counter] = r;
            counter++;
        }
        return resume;
    }
}
