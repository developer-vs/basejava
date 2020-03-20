package com.webapp;

import com.webapp.model.Resume;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainReflection {
    
    public static void main(String[] args) throws IllegalAccessException,
        InvocationTargetException, NoSuchMethodException {
        Resume resume = new Resume();
        Field field = resume.getClass().getDeclaredFields()[0];
        
        field.setAccessible(true); // allows us to get access to the private fields
        System.out.println(field.getName()); // uuid
        
        // can not access a member of class Resume with modifiers "private final" without field.setAccessible(true)
        System.out.println(field.get(resume)); // 8c334dff-fd37-4ac8-b1f8-96ab4db96347
        
        field.set(resume, "new_uuid");
        System.out.println(resume); // new_uuid
        
        // Example 1
        Method method = resume.getClass().getDeclaredMethod("toString");
        System.out.println(method.invoke(resume)); // new_uuid

//        Example 2
//        Method[] methods2 = resume.getClass().getDeclaredMethods();
//        System.out.println(methods2[2].getName()); // toString
//        System.out.println(methods2[2].getDefaultValue()); // null
//        System.out.println(methods2[2].getReturnType()); // class java.lang.String
//        System.out.println(methods2[2].invoke(resume)); // new_uuid

//        Example 3
//        Method[] methods3 = Resume.class.getMethods();
//        System.out.println(methods3[2].invoke(resume));  // new_uuid
        
    }
}
