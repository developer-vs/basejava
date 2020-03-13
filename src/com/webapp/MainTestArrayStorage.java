package com.webapp;

import com.webapp.model.Resume;
import com.webapp.storage.SortedArrayStorage;
import com.webapp.storage.Storage;

public class MainTestArrayStorage {
    private static final Storage ARRAY_STORAGE = new SortedArrayStorage();

    public static void main(String[] args) {
        final Resume r1 = new Resume();
        r1.setUuid("uuid1");
        final Resume r2 = new Resume();
        r2.setUuid("uuid2");
        final Resume r3 = new Resume();
        r3.setUuid("uuid3");

        System.out.println("Creating three resumes...");
        ARRAY_STORAGE.save(r1);
        ARRAY_STORAGE.save(r2);
        ARRAY_STORAGE.save(r3);

        System.out.println("\nGet resume with \"" + r1.getUuid() + "\": " + ARRAY_STORAGE.get(r1.getUuid()));

        System.out.println("\nSize of storage: " + ARRAY_STORAGE.size());

        System.out.println("Get resume with \"dummy\": " + ARRAY_STORAGE.get("dummy"));
        printAll();

        System.out.println("\nTrying to delete the resume with \"uuid1\"...");
        ARRAY_STORAGE.delete(r1.getUuid());
        printAll();

        System.out.println("\nTrying to update resume with \"uuid2\" to \"uuid22\"...");
        r2.setUuid("uuid22");
        ARRAY_STORAGE.update(r2);
        printAll();

        System.out.println("\nTrying to update resume with \"uuid3\" to \"uuid33\"...");
        r3.setUuid("uuid33");
        ARRAY_STORAGE.update(r3);
        printAll();

        System.out.println("\nTrying to update resume with \"\"uuid1\" to \"uuid7\"...");
        r1.setUuid("uuid7");
        ARRAY_STORAGE.update(r1);
        printAll();

        System.out.print("\nTrying to save resume with \"uuid1\"...");
        final Resume r4 = new Resume();
        r4.setUuid("uuid1");
        ARRAY_STORAGE.save(r4);
        printAll();

        System.out.print("\nTrying to save resume with \"uuid5\"...");
        final Resume r5 = new Resume();
        r5.setUuid("uuid5");
        ARRAY_STORAGE.save(r5);
        printAll();

        System.out.print("\nTrying to save resume with \"uuid4\"...");
        final Resume r6 = new Resume();
        r6.setUuid("uuid4");
        ARRAY_STORAGE.save(r6);
        printAll();

        System.out.println("\nTrying to delete the resume with \"uuid1\"...");
        ARRAY_STORAGE.delete(r4.getUuid());
        printAll();

        System.out.println("\nTrying to deleting the resume with \"uuid1\"...");
        ARRAY_STORAGE.delete(r4.getUuid());
        printAll();

        System.out.println("\nTrying to delete the resume with \"uuid22\"...");
        ARRAY_STORAGE.delete(r2.getUuid());
        printAll();

        System.out.print("\nTrying to clear the database...");
        ARRAY_STORAGE.clear();
        printAll();

        System.out.println("Size: " + ARRAY_STORAGE.size());
    }

    static void printAll() {
        System.out.println("\nGet All");
        for (Resume r : ARRAY_STORAGE.getAll()) {
            System.out.println(r);
        }
    }
}