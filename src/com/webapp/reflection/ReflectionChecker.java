package com.webapp.reflection;

import com.webapp.annotation.ResumeAnnotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectionChecker {
    
    public void showClassName(Object object) {
        System.out.println("class name: " + object.getClass().getName());
    }
    
    public void showFields(Object object) {
        // getDeclaredFields - get access to the fields declared in the class
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
            System.out.println("field name: " + field.getName());
        }
    }
    
    public void showMethods(Object object) {
        // getDeclaredMethods - get access to the methods declared in the class
        Method[] methods = object.getClass().getDeclaredMethods();
        for (Method method : methods) {
            System.out.println("method name: " + method.getName());
        }
    }
    
    public void invokeMethod(Object object, String methodName) throws
        NoSuchMethodException, InvocationTargetException,
        IllegalAccessException {
        Method method = object.getClass().getDeclaredMethod(methodName);
        System.out.println(method.invoke(object));
    }
    
    public void showFieldAnnotation(Object object) {
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
            Annotation[] annotations = field.getAnnotations();
            for (Annotation annotation : annotations) {
                System.out.println("field name: " + field.getName() + ", annotation name: " + annotation.toString());
            }
        }
    }
    
    public void setPrivateFieldByAnnotation(Object object, String newUuid) throws IllegalAccessException {
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
            Annotation annotation = field.getAnnotation(ResumeAnnotation.class);
            if (annotation == null) {
                continue;
            }
            // it allows to get access to the private field
            field.setAccessible(true);
            field.set(object, newUuid);
            field.setAccessible(true);
        }
    }
    
    public void setPrivateFieldByName(Object object, String fieldName, String newUuid) throws
        IllegalAccessException, NoSuchFieldException {
        Field field = object.getClass().getDeclaredField(fieldName);
        // it allows to get access to the private field
        field.setAccessible(true);
        field.set(object, newUuid);
        field.setAccessible(false);
    }
}
