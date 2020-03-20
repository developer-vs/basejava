package com.webapp.storage;

import com.webapp.exception.ResumeNotFoundException;
import com.webapp.model.Resume;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public abstract class AbstractArrayStorageTest {
    
    private Storage storage;
    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";
    
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
    public void save() throws NoSuchFieldException {
        storage.save(new Resume(UUID_4));
        assertEquals(4, storage.size());
    }
    
    @Test
    public void update() {

    }
    
    @Test
    public void delete() {
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
        assertEquals(UUID_1, storage.get(UUID_1).getUuid());
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