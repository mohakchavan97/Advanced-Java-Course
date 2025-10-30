package org.example.gui;

import org.example.model.Book;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ViewBooksPanel extends JPanel {

    private BookTableModel tableModel;
    private JTable jTable;

    public ViewBooksPanel(List<Book> bookList) {

        tableModel = new BookTableModel(bookList);
        jTable = new JTable();
        jTable.setModel(tableModel);

        setLayout(new BorderLayout());
        JScrollPane scrollPane = new JScrollPane(jTable);
        add(scrollPane, BorderLayout.CENTER);

    }

    public void refresh() {
        tableModel.fireTableDataChanged();
    }
}
