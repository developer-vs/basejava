package com.webapp.storage;

import com.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {
    
    protected int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
    
    protected boolean isResumeExist(Resume resume) {
        return getIndex(resume.getUuid()) == -1;
    }
}