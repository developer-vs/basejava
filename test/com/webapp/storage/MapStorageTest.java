package com.webapp.storage;

import com.webapp.exception.ResumeNotFoundException;
import com.webapp.model.Resume;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class MapStorageTest {

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";
    private Resume resume1 = new Resume(UUID_1);
    private Resume resume2 = new Resume(UUID_2);
    private Resume resume3 = new Resume(UUID_3);
    private Resume resume4 = new Resume(UUID_4);
    private Storage storage = new MapStorage();

    @Before
    public void setUp() {
        storage.clear();
        storage.save(resume1);
        storage.save(resume2);
        storage.save(resume3);
    }

    @Test
    public void getSearchKey() {
        clear();
        storage.save(resume1);
        Map<String, Resume> hashMap = new HashMap<>();
        hashMap.put(UUID_1, resume1);

        Resume[] r = storage.getAll();
//        storage.get(UUID_1);
        System.out.println(Arrays.toString(storage.getAll()));

        assertSame(UUID_1.hashCode(), r[0].hashCode());
//        System.out.println(storage.get(UUID_4));
//        assertEquals(UUID_2.hashCode(), storage);
    }

    @Test
    public void saveResume() {
        storage.save(resume4);
        assertEquals(4, storage.size());
        assertSame(resume4, storage.get(UUID_4));
    }

    @Test
    public void getResume() {
    }

    @Test
    public void updateResume() {
    }

    @Test
    public void deleteResume() {
        storage.save(resume4);
        storage.delete(UUID_4);
        assertEquals(3, storage.size());
    }

    @Test(expected = ResumeNotFoundException.class)
    public void deleteResumeThrowExceptionTesting() {
        storage.save(resume4);
        storage.delete(UUID_4);
        assertSame(resume4, storage.get(UUID_4));
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
    public void getAll() {
        Resume[] expectedStorage = {resume1, resume2, resume3};
        assertArrayEquals(expectedStorage, storage.getAll());
    }
}