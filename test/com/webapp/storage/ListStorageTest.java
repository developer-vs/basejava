package com.webapp.storage;

import org.junit.Ignore;
import org.junit.Test;

public class ListStorageTest extends AbstractArrayStorageTest {
    public ListStorageTest() {
        super(new ListStorage());
    }

    @Override
    @Ignore
    @Test
    public void fillStorageThrowExceptionTesting() {
    }
}