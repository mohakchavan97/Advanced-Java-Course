package org.example.gui;

import javax.swing.*;
import java.awt.*;
import java.io.Serial;

public class MainFrame extends JFrame {

    @Serial
    private static final long serialVersionUID = 1L;

    private JSplitPane splitPane;

    public MainFrame(JPanel leftPanel, JPanel rightPanel) throws HeadlessException {

        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, rightPanel);
        splitPane.setResizeWeight(0.5);
        setLayout(new BorderLayout());
        add(splitPane, BorderLayout.CENTER);

        setSize(1000, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
}
