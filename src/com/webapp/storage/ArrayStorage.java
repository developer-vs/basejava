package com.webapp.storage;

import com.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    public void save(Resume resume) {
        if (size < STORAGE_LIMIT) {
            if (getIndex(resume.getUuid()) == -1) {
                storage[size] = resume;
                size++;
                System.out.println("\nThe resume with \"" + resume.getUuid() + "\" has been saved in the database.");
            } else {
                System.out.println("\nThe resume with \"" + resume.getUuid() + "\" already exists in the database.");
            }
        } else {
            System.out.println("\nThe storage is FULL, the resume with \"" + resume.getUuid()
                    + "\" cannot be saved in the database.");
        }
    }

    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());

        if (index != -1) {
            storage[index] = resume;
            System.out.println("\nThe resume with \"" + resume.getUuid() + "\" has been updated.");
        } else {
            System.out.println("\nThe resume with \"" + resume.getUuid() + "\" does not exist in the database.");
        }
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);

        if (index != -1) {
            System.arraycopy(storage, index + 1, storage, index, size - 1 - index);
            storage[size - 1] = null;
            size--;
            System.out.println("\nThe resume with \"" + uuid + "\" has been removed from the database.");
        } else {
            System.out.println("\nThe resume with \"" + uuid + "\" does not exist in the database.");
        }
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    protected int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}