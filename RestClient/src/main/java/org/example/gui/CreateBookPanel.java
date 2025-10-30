package org.example.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class CreateBookPanel extends JPanel {

    private FormListener formListener;

    public CreateBookPanel() {
//        setBackground(Color.red);
        JLabel formLabel = new JLabel("Create Book");
        formLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));

        setLayout(new GridBagLayout());

        GridBagConstraints gc = new GridBagConstraints();

        gc.gridx = 0;
        gc.gridy = 0;

//        gc.weightx = 1;
//        gc.gridwidth = 2;
//        add(formLabel, gc);

//        gc.gridy++;

        JPanel panel = new JPanel();

        JLabel nameLabel = new JLabel("Author:");
        JLabel passLabel = new JLabel("Title:");

        JTextField nameField = new JTextField(15);
        JTextField passField = new JTextField(15);

        JButton saveButton = new JButton("Save");

        saveButton.addActionListener((ActionEvent e) -> {
            String username = nameField.getText();
            String password = passField.getText();
            if (formListener != null) {
                formListener.formSubmitted(username, password);
            }
        });

        panel.setLayout(new GridBagLayout());

        Insets rightPadding = new Insets(0, 0, 0, 10);
        Insets zeroPadding = new Insets(0, 0, 0, 0);

        gc.gridwidth = 1;

//        gc.gridy++;
//        gc.weighty = 0.1;
        gc.gridx = 0;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = rightPadding;
        add(nameLabel, gc);
        gc.insets = zeroPadding;

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        add(nameField, gc);

        gc.gridy++;

        gc.gridx = 0;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = rightPadding;
        add(passLabel, gc);
        gc.insets = zeroPadding;

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        add(passField, gc);

        gc.gridy++;
//        gc.weighty = 10;
        gc.gridx = 1;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(saveButton, gc);
    }

    public void setFormListener(FormListener formListener) {
        this.formListener = formListener;
    }

}
