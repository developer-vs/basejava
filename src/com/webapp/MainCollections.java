package com.webapp;

import com.webapp.model.Resume;

import java.util.*;

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
    
        List<Object> list = new ArrayList<>();
        list.add("uuid1");
        list.add(1);
        System.out.println(list);
        
        list.add(0, "uuid0");
        System.out.println(list);
        
        List<String> list2 = Arrays.asList("uuid1", "uuid2", "uuid3");
        // list2.add("four"); // UnsupportedOperationException
        // list2.remove("one"); // UnsupportedOperationException
        
        List<String> uuids = Arrays.asList("uuid3", "uuid7", "uuid10", "uuid2", "uuid4", "uuid9");
        Collections.sort(uuids);
        System.out.println(uuids);
    
        List<Integer> numbers = Arrays.asList(3, 7, 10, 2, 4, 9);
        int index = Collections.binarySearch(numbers, 4); // -2
        System.out.println("index: " + index);
        
        Collections.sort(numbers);
        System.out.println(numbers);
        
        int index2 = Collections.binarySearch(numbers, 10);
        System.out.println("index: " + index2); // 5
    }
}
