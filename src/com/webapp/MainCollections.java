package com.webapp;

import com.webapp.model.Resume;

import java.util.ArrayList;
import java.util.Collection;

public class MainCollections {
    
    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final Resume resume1 = new Resume(UUID_1);
    private static final Resume resume2 = new Resume(UUID_2);
    private static final Resume resume3 = new Resume(UUID_3);
    
    public static void main(String[] args) {
        Collection<Resume> collection = new ArrayList<>();
        collection.add(resume1);
        collection.add(resume2);
        collection.add(resume3);

//        for (Resume r : collection) {
//            if (r.getUuid().equals(UUID_1)) {
//                collection.remove(r);
//            }
//        }
        
        collection.removeIf(r -> r.getUuid().equals(UUID_1));
        System.out.println(collection.toString());
    }
}
