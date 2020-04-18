package com.webapp.storage;

import com.webapp.exception.ResumeExistException;
import com.webapp.exception.ResumeNotFoundException;
import com.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    @Override
    public void save(Resume resume) {
        int searchKey = getSearchKey(resume.getUuid());

        if (searchKey < 0) {
            saveResume(resume, searchKey);
        } else {
            throw new ResumeExistException(resume.getUuid());
        }
    }

    @Override
    public void update(Resume resume) {
        int searchKey = checkSearchKey(resume.getUuid());
        updateResume(resume, searchKey);
        System.out.println("\nThe resume with \"" + resume.getUuid() + "\" has been updated.");
    }

    @Override
    public void delete(String uuid) {
        int searchKey = checkSearchKey(uuid);
        deleteResume(searchKey, uuid);
        System.out.println("\nThe resume with \"" + uuid + "\" has been removed from the database.");
    }

    @Override
    public Resume get(String uuid) {
        int searchKey = checkSearchKey(uuid);
        return getResume(searchKey, uuid);
    }

    protected int checkSearchKey(String uuid) {
        int searchKey = getSearchKey(uuid);

        if (searchKey < 0) {
            throw new ResumeNotFoundException(uuid);
        }
        return searchKey;
    }

    protected abstract int getSearchKey(Object uuid);

    protected abstract Resume getResume(Object searchKey, Object uuid);

    protected abstract void deleteResume(Object index, Object uuid);

    protected abstract void saveResume(Resume resume, int searchKey);

    protected abstract void updateResume(Resume resume, int searchKey);
}