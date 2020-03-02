package com.webapp.storage;

import com.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public interface Storage {

    void save(Resume resume);

    void update(Resume resume);

    Resume get(String uuid);

    void delete(String uuid);

    int size();

    void clear();

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll();
}
