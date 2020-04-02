package com.webapp.storage;

import com.webapp.exception.ResumeExistException;
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
    private Resume resume4 = new Resume(UUID_4);

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
        storage.save(resume4);
        assertEquals(4, storage.size());
        assertSame(resume4, storage.get(UUID_4));
    }
    
    @Test(expected = ResumeExistException.class)
    public void saveResumeThrowExceptionTesting() {
        storage.save(resume4);
        storage.save(resume4);
    }

    @Test(expected = StorageException.class)
    public void fillStorageThrowExceptionTesting() {
        storage.clear();
        for (int i = 0; i < AbstractArrayStorage.STORAGE_LIMIT + 1; i++) {
            storage.save(new Resume());
        }
        assertEquals(AbstractArrayStorage.STORAGE_LIMIT, storage.size());
    }

    @Test
    public void fillStorage() {
        storage.clear();
        for (int i = 0; i < AbstractArrayStorage.STORAGE_LIMIT; i++) {
            storage.save(new Resume());
        }
        assertEquals(AbstractArrayStorage.STORAGE_LIMIT, storage.size());
    }

    @Test
    public void update() {
        storage.save(resume4);
        storage.update(resume4);
        assertSame(resume4, storage.get(UUID_4));
    }
    
    @Test(expected = ResumeNotFoundException.class)
    public void updateResumeThrowExceptionTesting() {
        storage.update(resume4);
    }

    @Test(expected = ResumeNotFoundException.class)
    public void delete() {
        storage.save(resume4);
        storage.delete(UUID_4);
        assertSame(resume4, storage.get(UUID_4));
    }

    @Test
    public void clear() {
        storage.clear();
        assertEquals(0, storage.size());
    }

    @Test
    public void getAll() {
        assertEquals(3, storage.size());
        Resume[] expectedStorage = {new Resume(UUID_1), new Resume(UUID_2), new Resume(UUID_3)};
        assertArrayEquals(expectedStorage, storage.getAll());
    }

    @Test
    public void get() {
        storage.save(resume4);
        assertSame(resume4, storage.get(UUID_4));
    }

    @Test(expected = ResumeNotFoundException.class)
    public void getResumeThrowExceptionTesting() {
        storage.get("dummy");
    }
}