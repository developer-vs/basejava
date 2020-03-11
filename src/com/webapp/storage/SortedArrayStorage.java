package com.webapp.storage;

import com.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {
    
    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
    
    @Override
    protected boolean isResumeExist(Resume resume) {
        return getIndex(resume.getUuid()) <= -1;
    }
    
    @Override
    protected void sortResume(Resume resume) {
        int index = Arrays.binarySearch(storage, 0, size, resume);
        
        if (index == -1) {
            System.arraycopy(storage, 0, storage, 1, size);
            storage[0] = resume;
        } else if (index < -1) {
            System.arraycopy(storage, -index - 1, storage, -index, size);
            storage[-index - 1] = resume;
        }
    }
}
