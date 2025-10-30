/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mohakchavan.mavensql;

import java.util.Properties;

/**
 *
 * @author Mohak Chavan
 */
public class Profile {
    
    public static Properties getProperties(String propName) {
        Properties props = new Properties();
        
//        String env = System.getProperty("env", "dev");
        String env = System.getProperty("env", "test");
        String propFile = String.format("/config/%s.%s.properties", propName, env);
        System.out.println(propFile);
        try {
            props.load(App.class.getResourceAsStream(propFile));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException("Cannot load properties file: " + propFile);
        }
        return props;
    }
    
}
