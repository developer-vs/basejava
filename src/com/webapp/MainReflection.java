package com.webapp;

import com.webapp.model.Resume;
import com.webapp.storage.ReflectionChecker;

import java.lang.reflect.InvocationTargetException;

public class MainReflection {
    
    public static void main(String[] args) throws IllegalAccessException,
        InvocationTargetException, NoSuchMethodException, NoSuchFieldException {
        Resume resume = new Resume();
        
        ReflectionChecker reflectionChecker = new ReflectionChecker();
        reflectionChecker.showClassName(resume);
        reflectionChecker.showFields(resume);
        reflectionChecker.showMethods(resume);
        reflectionChecker.showMethodsAnnotations(resume);
        reflectionChecker.setPrivateField(resume, "new_uuid");
        reflectionChecker.invokeMethod(resume, "toString");
        
    }
}
