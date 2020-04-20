package com.webapp.storage;

import com.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {

    private final List<Resume> storage = new ArrayList<>();

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
        return storage.toArray(new Resume[0]);
    }

    @Override
    protected void saveResume(Resume resume) {
        storage.add(resume);
    }

    @Override
    protected int getSearchKey(String uuid) {
        return storage.indexOf(new Resume(uuid));
    }

    @Override
    public Resume getResume(Object index) {
        return storage.get((int) index);
    }

    @Override
    protected void updateResume(Resume resume) {
        storage.set(updateResumeKey, resume);
    }

    @Override
    public void deleteResume(String uuid) {
        storage.remove(checkSearchKey(uuid));
    }
}