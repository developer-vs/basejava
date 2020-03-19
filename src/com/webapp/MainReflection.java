package com.webapp;

import com.webapp.model.Resume;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainReflection {
    
    public static void main(String[] args) throws IllegalAccessException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException {
        Resume resume = new Resume();
        Field field = resume.getClass().getDeclaredFields()[0];
        
        field.setAccessible(true); // allow us to get access to the private fields
        System.out.println(field.getName()); // uuid
    
        // can not access a member of class Resume with modifiers "private final" without field.setAccessible(true)
        System.out.println(field.get(resume)); // 8c334dff-fd37-4ac8-b1f8-96ab4db96347
        
        field.set(resume, "new_uuid");
        System.out.println(resume); // new_uuid
    
        
        Method[] methods1 = resume.getClass().getDeclaredMethods();
        System.out.println(methods1[2].getName()); // toString
        System.out.println(methods1[2].getDefaultValue()); // null
        System.out.println(methods1[2].getReturnType()); // class java.lang.String
        System.out.println(methods1[2].invoke(resume)); // new_uuid
        
        Method[] methods2 = Resume.class.getMethods();
        System.out.println(methods2[2].invoke(resume));  // new_uuid

    }
}
