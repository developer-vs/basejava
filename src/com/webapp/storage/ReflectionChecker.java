package com.webapp.storage;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectionChecker {
    
    public void showClassName(Object object) {
        System.out.println("class name: " + object.getClass().getName());
    }
    
    public void showFields(Object object) {
        // getDeclaredFields - get access to the private fields
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
            System.out.println("field name: " + field.getName());
        }
    }
    
    public void showMethods(Object object) {
        // Get access to the methods that declared in the class
        Method[] methods = object.getClass().getDeclaredMethods();
        for (Method method : methods) {
            System.out.println("method name: " + method.getName());
        }
    }
    
    public void setPrivateField(Object object, String newUuid) throws IllegalAccessException, NoSuchFieldException {
        Field field = object.getClass().getDeclaredField("uuid");
        field.setAccessible(true); // allows us to get access to the private fields
        field.set(object, newUuid);
    }
    
    public void invokeMethod(Object object, String methodName) throws NoSuchMethodException, InvocationTargetException,
        IllegalAccessException {
        Method method = object.getClass().getDeclaredMethod(methodName);
        System.out.println(method.invoke(object));
    }
    
    public void showMethodsAnnotations(Object object) {
        Method[] methods = object.getClass().getDeclaredMethods();
        for (Method method : methods) {
            Annotation[] annotations = method.getAnnotations();
            for (Annotation annotation : annotations) {
                System.out.println("method name: " + method.getName() + ", annotation name: " + annotation.toString());
            }
        }
    }
}
