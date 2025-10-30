/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mohakchavan.annotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 *
 * @author Mohak Chavan
 */
public class Repository<T> {

    public void save(T t) {

        Class<T> clazz = (Class<T>) t.getClass();
        
        Entity[] entities = clazz.getAnnotationsByType(Entity.class);
        
        String tableName = clazz.getSimpleName().toLowerCase();
        String entityAttribute;
        if (entities.length > 0 && !(entityAttribute = entities[0].value()).isEmpty()) {
            tableName = entityAttribute;
        }

        java.lang.reflect.Field[] fields = clazz.getDeclaredFields();
        ArrayList<String> fieldList = new ArrayList<>();
        
        for (java.lang.reflect.Field field : fields) {
            System.out.println("=================");
            System.out.println(field.getName());
            Field[] annotations = field.getAnnotationsByType(Field.class);

            System.out.println("annotations.length:" + annotations.length);
            for (Field annotation : annotations) {
                String value = annotation.coloumnName();
                if (value.isEmpty()) {
                    value = field.getName();
                }
                System.out.println("value:" + value + "\nisKey:" + annotation.isKey());
                if (!annotation.isKey() && !fieldList.contains(value)) {
                    fieldList.add(value);
                }
            }
            System.out.println("=================\n");
        }
        
        String sqlFields = fieldList.stream().collect(Collectors.joining(", "));
        String sqlPlaceholders = fieldList.stream().map(t1 -> "?").collect(Collectors.joining(", "));
        
        System.out.println(sqlFields);
        System.out.println(sqlPlaceholders);

        String sql = String.format("INSERT INTO %s (%s) VALUES (%s)", tableName, sqlFields, sqlPlaceholders);
        System.out.println("sql:"+sql);
    }

}
