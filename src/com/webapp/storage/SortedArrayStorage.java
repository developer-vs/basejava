package com.webapp.storage;

import com.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {
    
    public void save(Resume resume) {
        if (size < STORAGE_LIMIT) {
            if (isResumeExist(resume)) {
                storage[size] = resume;
                size++;
                System.out.println("\nThe resume with \"" + resume.getUuid() + "\" has been saved in the database.");
                sortResume(resume);
            } else {
                System.out.println("\nThe resume with \"" + resume.getUuid() + "\" already exists in the database.");
            }
        } else {
            System.out.println("\nThe storage is FULL, the resume with \"" + resume.getUuid()
                + "\" cannot be saved in the database.");
        }
    }
    
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
    
    protected boolean isResumeExist(Resume resume) {
        return getIndex(resume.getUuid()) <= -1;
    }
    
    private void sortResume(Resume resume) {
        System.out.println(Arrays.binarySearch(storage, 0, size, resume));
        System.out.println(Arrays.toString(storage));
    }
}
