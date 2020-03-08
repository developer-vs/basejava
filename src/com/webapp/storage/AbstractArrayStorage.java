package com.webapp.storage;

import com.webapp.model.Resume;

public abstract class AbstractArrayStorage implements Storage{

    protected static final int STORAGE_LIMIT = 10_000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size;

    public int size() {
        return size;
    }
    
    public Resume get(String uuid) {
        int index = getIndex(uuid);
        
        if (index != -1) {
            System.out.println("\nThis is the resume with \"" + uuid + "\".");
            return storage[index];
        }
        System.out.println("\nThe resume with \"" + uuid + "\" does not exist in the database.");
        return null;
    }
    
    protected abstract int getIndex(String uuid);
}
