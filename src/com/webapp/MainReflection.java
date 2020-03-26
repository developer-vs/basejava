package com.webapp;

import com.webapp.model.Resume;
import com.webapp.reflection.ReflectionChecker;

import java.lang.reflect.InvocationTargetException;

public class MainReflection {
    
    public static void main(String[] args) throws IllegalAccessException,
        InvocationTargetException, NoSuchMethodException, NoSuchFieldException {
        Resume resume = new Resume();
        ReflectionChecker reflectionChecker = new ReflectionChecker();
        
        reflectionChecker.showClassName(resume);
        reflectionChecker.showMethods(resume);
        reflectionChecker.showFields(resume);
        
        // show unique identifier created by constructor
        reflectionChecker.invokeMethod(resume, "toString");
        
        // show unique identifier created by constructor
        System.out.println(resume.getUuid());
        
        reflectionChecker.showFieldAnnotation(resume);
        
        reflectionChecker.setPrivateFieldByName(resume, "uuid", "uuid1");
        System.out.println(resume.getUuid());
        
        reflectionChecker.setPrivateFieldByAnnotation(resume, "uuid4");
        System.out.println(resume.getUuid());
    }
}
