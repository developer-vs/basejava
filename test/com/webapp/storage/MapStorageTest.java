package com.webapp.storage;

import com.webapp.exception.ResumeNotFoundException;
import com.webapp.model.Resume;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class MapStorageTest extends AbstractArrayStorageTest {
    public MapStorageTest() {
        super(new MapStorage());
    }

    @Override
    @Ignore
    @Test
    public void fillStorageThrowExceptionTesting() {
    }

//    @Override
//    @Ignore
//    @Test
//    public void save() {
//    }

    @Test
    public void getSearchKey() {
//        Storage storage = getStorage();
//        assertEquals(UUID_1.hashCode(), storage.get(UUID_1).toString().hashCode());
    }

//    @Test(expected = ResumeNotFoundException.class)
//    public void getSearchKeyThrowExceptionTesting() {
//        assertEquals(UUID_1.hashCode(), storage.get(UUID_4).toString().hashCode());
//    }

//    @Test
//    public void saveResume() {
//        storage.save(resume4);
//        assertEquals(4, storage.size());
//        assertSame(resume4, storage.get(UUID_4));
//    }

//    @Test
//    public void getResume() {
//        assertSame(resume1, storage.get(UUID_1));
//    }

//    @Test(expected = ResumeNotFoundException.class)
//    public void getResumeThrowExceptionTesting() {
//        assertSame(resume1, storage.get(UUID_4));
//    }

//    @Test
//    public void updateResume() {
//        storage.update(resume1);
//    }

//    @Test(expected = ResumeNotFoundException.class)
//    public void updateResumeThrowExceptionTesting() {
//        storage.update(resume4);
//    }

//    @Test
//    public void deleteResume() {
//        storage.save(resume4);
//        storage.delete(UUID_4);
//        assertEquals(3, storage.size());
//    }

//    @Test(expected = ResumeNotFoundException.class)
//    public void deleteResumeThrowExceptionTesting() {
//        storage.save(resume4);
//        storage.delete(UUID_4);
//        assertSame(resume4, storage.get(UUID_4));
//    }

//    @Test
//    public void size() {
//        assertEquals(3, storage.size());
//    }

//    @Test
//    public void clear() {
//        storage.clear();
//        assertEquals(0, storage.size());
//    }

//    @Override
//    @Test
//    public void getAll() {
//
//        Storage storage = new MapStorage();
//        System.out.println(storage.get(UUID_1));
//        Resume[] expectedStorage = storage.getAll();
//        System.out.println(expectedStorage[0].getUuid());
//
//        assertEquals(resume1.getUuid(), storage.get(resume1.getUuid()).toString());
//    }
}