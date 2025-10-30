/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mohakchavan.mavenproject1;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Date;
import java.util.function.Predicate;

/**
 *
 * @author Mohak Chavan
 */
public class Mavenproject1 {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException, SecurityException, NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        System.out.println("=================\nHello World!\n");
        
        /*
//        var class1 = Employee.class;
        Class<Employee> class1 = Employee.class;
        System.out.println(class1);
        
//        var class2 = Class.forName("com.mohakchavan.mavenproject1.Employee");
        Class<?> class2 = Class.forName("com.mohakchavan.mavenproject1.Employee");
        System.out.println(class2);
        
        User u = new Employee();
//        var class3 = u.getClass();
        Class<? extends User> class3 = u.getClass();
        System.out.println(class3);
        */
        
        /*
        Class<Employee> clazz = Employee.class;
        System.out.println(Arrays.asList(clazz.getFields()));
        System.out.println(Arrays.asList(clazz.getDeclaredFields()));
        
        System.out.println();
        
        Arrays.asList(clazz.getMethods()).forEach(System.out::println);
        System.out.println();
        Arrays.asList(clazz.getDeclaredMethods()).forEach(System.out::println);
        */
        
        /*
        Class<Employee> clazz = Employee.class;
        
        Field name = clazz.getField("name");
        System.out.println(name);
        
        var setUpdatedMethod = clazz.getDeclaredMethod("setUpdated", Date.class);
        System.out.println(setUpdatedMethod);
        
        var setUpdatedMethod2 = clazz.getDeclaredMethod("setUpdated", int.class);
        System.out.println(setUpdatedMethod2);
        
        var doesMethodExist = Arrays.stream(clazz.getDeclaredMethods()).anyMatch(t -> t.getName().equals("setUpdated"));
        System.out.println("doesMethodExist -> "+doesMethodExist);
        
        setUpdatedMethod2.setAccessible(true);
        setUpdatedMethod2.invoke(new Employee(), 7);
        */
        
        Class<Employee> clazz = Employee.class;
        
        Field nameField = clazz.getDeclaredField("name");
        
        User u = new Employee();
        nameField.setAccessible(true);
        nameField.set(u, "Jupiter");
        System.out.println(u);
        
        System.out.println("\n================");
    }
}

class User {
    public int id;
    private String password2;
    
    public void userMethod() {}

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", password2=" + password2 + '}';
    }
}

class Employee extends User {
    private String password;
    private String name;
    
    private boolean setUpdated(Date updatedDate) {
        return true;
    }
    
    private boolean setUpdated(int sequence) {
        System.out.println("setUpdated :: sequence -> "+sequence);
        return true;
    }

    @Override
    public String toString() {
        return "Employee{" + "password=" + password + ", name=" + name + '}';
    }
}
