package com.webapp.exception;

public class ResumeExistException extends StorageException {
    public ResumeExistException(String uuid) {
        super("The resume with \"" + uuid + "\" already exists in the database.", uuid);
    }
}
