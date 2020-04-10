package com.webapp.storage;

import com.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public interface Storage {

    void save(Resume resume);

    void update(Resume resume);

    void delete(String uuid);

    int size();

    void clear();
    
    Resume get(String uuid);

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll();
}
