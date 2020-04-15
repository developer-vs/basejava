package com.webapp.storage;

import com.webapp.exception.ResumeExistException;
import com.webapp.exception.ResumeNotFoundException;
import com.webapp.exception.StorageException;
import com.webapp.model.Resume;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public abstract class AbstractArrayStorageTest extends AbstractStorageTest{

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";
    private final Resume resume1 = new Resume(UUID_1);
    private final Resume resume2 = new Resume(UUID_2);
    private final Resume resume3 = new Resume(UUID_3);
    private final Resume resume4 = new Resume(UUID_4);
    private final Storage storage;

    public AbstractArrayStorageTest(Storage storage) {
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

    @Override
    @Test
    public void save() { ;
        assertSame(resume1, storage.get(UUID_1));
    }

    @Test(expected = ResumeExistException.class)
    public void saveResumeThrowExceptionTesting() {
        storage.save(resume3);
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
        storage.delete(UUID_3);
        assertSame(resume4, storage.get(UUID_4));
    }

    @Test
    public void clear() {
        storage.clear();
        assertEquals(0, storage.size());
    }

    @Test
    public void getAll() {
        Resume[] expectedStorage = {
                new Resume(UUID_1),
                new Resume(UUID_2),
                new Resume(UUID_3)};
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