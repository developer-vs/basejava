package com.webapp.storage;

import com.webapp.exception.ResumeExistException;
import com.webapp.exception.ResumeNotFoundException;
import com.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    protected int saveResumeKey;
    protected int updateResumeKey;

    @Override
    public void save(Resume resume) {
        saveResumeKey = getSearchKey(resume.getUuid());

        if (saveResumeKey < 0) {
            saveResume(resume);
            System.out.println(showMessage(resume.getUuid(), "saved"));
        } else {
            throw new ResumeExistException(resume.getUuid());
        }
    }

    @Override
    public Resume get(String uuid) {
        int searchKey = checkSearchKey(uuid);
        return getResume(searchKey);
    }

    @Override
    public void update(Resume resume) {
        updateResumeKey = checkSearchKey(resume.getUuid());
        updateResume(resume);
        System.out.println(showMessage(resume.getUuid(), "updated"));
    }

    @Override
    public void delete(String uuid) {
        deleteResume(uuid);
        System.out.println(showMessage(uuid , "removed"));
    }

    protected int checkSearchKey(String uuid) {
        int searchKey = getSearchKey(uuid);

        if (searchKey < 0) {
            throw new ResumeNotFoundException(uuid);
        }
        return searchKey;
    }

    protected String showMessage(String uuid, String msg) {
        return "\nThe resume with \"" + uuid + "\" has been " + msg + ".";
    }

    protected String showMessageStorageFull(Resume resume) {
        return "The storage is FULL, the resume with \"" + resume.getUuid() +
                "\" cannot be saved in the database.";
    }

    protected abstract void saveResume(Resume resume);

    protected abstract int getSearchKey(String uuid);

    protected abstract Resume getResume(Object searchKey);

    protected abstract void updateResume(Resume resume);

    protected abstract void deleteResume(String uuid);
}