/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SwingProject.view;

import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 *
 * @author Mohak Chavan
 */
public class MainFrame extends JFrame {

    public MainFrame() {
        super("Swing Demo");

        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
//        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 500);
//        setBounds(200, 200, 1000, 800);

        setJMenuBar(createMenu());
    }

    private JMenuBar createMenu() {
        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        JMenuItem exitItem = new JMenuItem("Exit");

        exitItem.addActionListener((ActionEvent e) -> {
            System.exit(0);
        });

        fileMenu.add(exitItem);
        menuBar.add(fileMenu);

        return menuBar;
    }

}
