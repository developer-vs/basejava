package com.webapp.storage;

import com.webapp.model.Resume;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class MapStorage extends AbstractStorage {

    private Map<String, Resume> storage = new HashMap<>();

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    public void clear() {
        storage.clear();
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    @Override
    public Resume[] getAll() {
        storage = new TreeMap<>(storage);
        return storage.values().toArray(new Resume[0]);
    }

    @Override
    public Resume get(String uuid) {
        checkSearchKey(uuid);
        return getResume(uuid);
    }

    @Override
    protected int getSearchKey(String uuid) {
        if (!storage.containsKey(uuid)) {
            return -1;
        }
        return 1;
    }

    @Override
    public Resume getResume(Object searchKey) {
        return storage.get((String) searchKey);
    }

    @Override
    protected void saveResume(Resume resume) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    protected void updateResume(Resume resume) {
        storage.replace(resume.getUuid(), resume);
    }

    @Override
    public void deleteResume(String uuid) {
        checkSearchKey(uuid);
        storage.remove(uuid);
    }
}