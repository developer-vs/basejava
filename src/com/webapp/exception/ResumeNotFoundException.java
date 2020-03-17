package com.webapp.exception;

public class ResumeNotFoundException extends StorageException  {
    public ResumeNotFoundException(String uuid) {
        super("The resume with \"" + uuid + "\" does not exist in the database.", uuid);
    }
}
