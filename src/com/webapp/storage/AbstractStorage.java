package com.webapp.storage;

import com.webapp.exception.ResumeExistException;
import com.webapp.exception.ResumeNotFoundException;
import com.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    @Override
    public void save(Resume resume) {
        int index = getIndex(resume.getUuid());

        if (index < 0) {
            saveResume(resume, index);
        } else {
            throw new ResumeExistException(resume.getUuid());
        }
    }

    @Override
    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        throwResumeNotFoundException(resume.getUuid(), index);
        updateResume(resume, index);
        System.out.println("\nThe resume with \"" + resume.getUuid() + "\" has been updated.");
    }

    @Override
    public void delete(String uuid) {
        int index = getIndex(uuid);
        throwResumeNotFoundException(uuid, index);
        deleteResume(index);
        System.out.println("\nThe resume with \"" + uuid + "\" has been removed from the database.");
    }

    @Override
    public Resume get(String uuid) {
        int index = getIndex(uuid);
        throwResumeNotFoundException(uuid, index);
        return getResume(index);
    }

    protected void throwResumeNotFoundException(String uuid, int index) {
        if (index < 0) {
            throw new ResumeNotFoundException(uuid);
        }
    }

    protected abstract int getIndex(String uuid);

    protected abstract void saveResume(Resume resume, int index);

    protected abstract Resume getResume(int index);

    protected abstract void updateResume(Resume resume, int index);

    protected abstract void deleteResume(int index);
}