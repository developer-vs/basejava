package com.webapp.storage;

import com.webapp.exception.StorageException;
import com.webapp.model.Resume;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public abstract class AbstractArrayStorageTest extends AbstractStorageTest {

    public AbstractArrayStorageTest(Storage storage) {
        super(storage);
    }

    @Test
    public void fillStorage() {
        storage.clear();
        for (int i = 0; i < AbstractArrayStorage.STORAGE_LIMIT; i++) {
            storage.save(new Resume());
        }
        assertEquals(AbstractArrayStorage.STORAGE_LIMIT, storage.size());
    }

    @Test(expected = StorageException.class)
    public void fillStorageException() {
        fillStorage();
        storage.save(new Resume());
    }
}