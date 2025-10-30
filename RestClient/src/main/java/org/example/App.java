package org.example;

import org.example.controllers.Controller;

import javax.swing.*;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        SwingUtilities.invokeLater(Controller::new);
    }
}
