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
    protected int getSearchKey(Object uuid) {
        if (!storage.containsKey(uuid)) {
            return -1;
        }
        return 1;
    }

    @Override
    protected Resume getResume(Object searchKey, Object uuid) {
        return storage.get(uuid);
    }

    protected void saveResume(Resume resume, int searchKey) {
        storage.put(resume.getUuid(), resume);
        System.out.println("\nThe resume with \"" + resume.getUuid() + "\" has been saved in the database.");
    }

    @Override
    protected void updateResume(Resume resume, int searchKey) {
        storage.replace(resume.getUuid(), resume);
    }

    @Override
    protected void deleteResume(Object searchKey, Object uuid) {
        storage.remove(uuid);
    }
}