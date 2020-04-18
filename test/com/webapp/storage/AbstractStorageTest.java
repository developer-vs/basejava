package com.webapp.storage;

import com.webapp.exception.ResumeExistException;
import com.webapp.exception.ResumeNotFoundException;
import com.webapp.model.Resume;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public abstract class AbstractStorageTest {
    private final String UUID_1 = "uuid1";
    private final String UUID_2 = "uuid2";
    private final String UUID_3 = "uuid3";
    private final String UUID_4 = "uuid4";
    private final Resume resume1 = new Resume(UUID_1);
    private final Resume resume2 = new Resume(UUID_2);
    private final Resume resume3 = new Resume(UUID_3);
    private final Resume resume4 = new Resume(UUID_4);
    protected final Storage storage;

    public AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        storage.clear();
        storage.save(resume1);
        storage.save(resume2);
        storage.save(resume3);
    }

    @Test
    public void size() {
        assertEquals(3, storage.size());
    }

    @Test
    public void clear() {
        storage.clear();
        assertEquals(0, storage.size());
    }

    @Test
    public void save() {
        storage.save(resume4);
        assertEquals(4, storage.size());
        assertSame(resume4, storage.get(UUID_4));
    }

    @Test(expected = ResumeExistException.class)
    public void saveException() {
        storage.save(resume3);
    }

    @Test
    public void update() {
        storage.update(resume3);
        assertSame(resume3, storage.get(UUID_3));
    }

    @Test(expected = ResumeNotFoundException.class)
    public void updateException() {
        storage.update(resume4);
    }

    @Test(expected = ResumeNotFoundException.class)
    public void delete() {
        storage.delete(UUID_3);
        assertEquals(2, storage.size());
        storage.get(UUID_3);
    }

    @Test
    public void getAll() {
        Resume[] expectedStorage = {resume1, resume2, resume3};
        assertArrayEquals(expectedStorage, storage.getAll());
        assertEquals(expectedStorage.length, storage.size());
    }

    @Test
    public void get() {
        assertSame(resume3, storage.get(UUID_3));
    }

    @Test(expected = ResumeNotFoundException.class)
    public void getException() {
        storage.get(UUID_4);
    }
}