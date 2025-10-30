/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SwingProject.controller;

import SwingProject.Database;
import SwingProject.Profile;
import SwingProject.dao.UserDao;
import SwingProject.impl.UserDaoImpl;
import SwingProject.model.User;
import SwingProject.view.MainFrame;
import SwingProject.view.MainPanel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Properties;

/**
 *
 * @author Mohak Chavan
 */
public class Controller {

    private MainFrame mainFrame;
    private MainPanel mainPanel;

    public Controller() {

        Properties props = Profile.getProperties("db");
        Database db = Database.getInstance();

        try {
            db.connect(props);
        } catch (Exception ex) {
            System.out.println("Cannot Connect to database.");
            ex.printStackTrace();
            return;
        }

        System.out.println("Connected to Database.");
        UserDao userDao = new UserDaoImpl();

        mainPanel = new MainPanel();

        mainPanel.setFormListener((username, password) -> {
            System.out.println(username + "::" + password);
            userDao.save(new User(username, password));
        });

        mainFrame = new MainFrame();

        mainFrame.setContentPane(mainPanel);
        mainFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("WIndow Closing.");
                try {
                    db.close();
                } catch (Exception ex) {
                    System.out.println("Cannot close the connection to database.");
                    ex.printStackTrace();
                }
            }
        });
    }

}
