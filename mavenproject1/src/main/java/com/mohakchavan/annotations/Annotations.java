/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mohakchavan.annotations;

/**
 *
 * @author Mohak Chavan
 */
public class Annotations {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        User user = new User(0L, "Thor");

        Repository<User> r = new Repository();
        r.save(user);
    }

}
