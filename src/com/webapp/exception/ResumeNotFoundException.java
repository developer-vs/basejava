package com.webapp.exception;

public class ResumeNotFoundException extends StorageException  {
    public ResumeNotFoundException(Object uuid) {
        super("The resume with \"" + uuid + "\" does not exist in the database.", (String) uuid);
    }
}
