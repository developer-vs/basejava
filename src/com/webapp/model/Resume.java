package com.webapp.model;

import java.util.Objects;

public class Resume implements Comparable<Resume> {

    // Unique identifier
    private String uuid;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @Override
    public String toString() {
        return uuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Resume)) return false;
        Resume resume = (Resume) o;
        return getUuid().equals(resume.getUuid());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUuid());
    }
    
    @Override
    public int compareTo(Resume r) {
        return uuid.compareTo(r.uuid);
    }
}
