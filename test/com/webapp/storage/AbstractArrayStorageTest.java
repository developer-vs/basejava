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
        Resume resume = new Resume(UUID_4);
        storage.save(resume);
        assertEquals(4, storage.size());
        assertSame(resume, storage.get(UUID_4));
    }
    
    @Test(expected = StorageException.class)
    public void fillStorageThrowExceptionTesting() {
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
        Resume resume50 = new Resume("uuid50");
        storage.save(resume50);
        storage.update(resume50);
        assertEquals("uuid50", storage.get("uuid50").getUuid());
    }
    
    @Test
    public void delete() {
        int size = storage.size() - 1;
        storage.delete(UUID_1);
        assertEquals(size, storage.size());
    }
    
    @Test(expected = ResumeNotFoundException.class)
    public void deleteResumeThatNotExist() {
        storage.delete("uuid200");
    }
    
    @Test
    public void clear() {
        storage.clear();
        assertEquals(0, storage.size());
    }
    
    @Test
    public void getAll() {
        assertEquals(3, storage.size());
        Resume[] resumeStorage = storage.getAll();
        assertEquals(resumeStorage[0], storage.get(UUID_1));
        assertEquals(resumeStorage[1], storage.get(UUID_2));
        assertEquals(resumeStorage[2], storage.get(UUID_3));
    }
    
    @Test
    public void get() {
        Resume resume100 = new Resume("uuid100");
        storage.save(resume100);
        assertEquals(resume100, storage.get("uuid100"));
    }

    @Test(expected = ResumeNotFoundException.class)
    public void getResumeThrowExceptionTesting() {
        storage.get("dummy");
    }
}