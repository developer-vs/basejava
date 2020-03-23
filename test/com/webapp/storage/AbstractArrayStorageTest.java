package com.webapp.storage;

import com.webapp.exception.ResumeNotFoundException;
import com.webapp.exception.StorageException;
import com.webapp.model.Resume;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public abstract class AbstractArrayStorageTest {
    
    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";
    private Storage storage;
    
    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }
    
    @Before
    public void setUp() {
        storage.clear();
        storage.save(new Resume(UUID_1));
        storage.save(new Resume(UUID_2));
        storage.save(new Resume(UUID_3));
    }
    
    @Test
    public void size() {
        assertEquals(3, storage.size());
    }
    
    @Test
    public void save() {
        Resume r = new Resume(UUID_4);
        storage.save(r);
        assertEquals(4, storage.size());
        assertSame(r, storage.get(UUID_4));
    }
    
    @Test(expected = StorageException.class)
    public void fillStorageWithException() {
        storage.clear();
        for (int i = 0; i < 10_001; i++) {
            storage.save(new Resume());
        }
        assertEquals(10_000, storage.size());
    }
    
    @Test
    public void fillStorage() {
        storage.clear();
        for (int i = 0; i < 10_000; i++) {
            storage.save(new Resume());
        }
        assertEquals(10_000, storage.size());
    }
    
    @Test
    public void update() {
        Resume resume = new Resume("uuid50");
        storage.save(resume);
        storage.update(resume);
    }
    
    @Test
    public void delete() {
        int size = storage.size() - 1;
        storage.delete(UUID_1);
        assertEquals(size, storage.size());
    }
    
    @Test(expected = ResumeNotFoundException.class)
    public void deleteResumeThatNotExist() {
        storage.delete("UUID_5");
    }
    
    @Test
    public void clear() {
        storage.clear();
        assertEquals(0, storage.size());
    }
    
    @Test
    public void getAll() {
        assertEquals(3, storage.size());
    }
    
    @Test
    public void get() {
        assertEquals(r, storage.get(UUID_1));
    }
    
    @Test
    public void getResumeThatNotExist() {
        assertNotEquals("UUID_5", storage.get(UUID_1));
    }
    
    @Test(expected = ResumeNotFoundException.class)
    public void getResumeThrowExceptionTesting() {
        storage.get("dummy");
    }
}