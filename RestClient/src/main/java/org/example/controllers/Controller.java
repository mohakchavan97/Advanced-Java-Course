package org.example.controllers;

import org.example.gui.CreateBookPanel;
import org.example.gui.MainFrame;
import org.example.gui.ViewBooksPanel;
import org.example.model.Book;
import org.example.services.BookService;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Controller {

    private MainFrame mainFrame;
    private CreateBookPanel createBookPanel;
    private ViewBooksPanel viewBooksPanel;
    private BookService bookService;

    private final List<Book> bookList = new ArrayList<>();

    public Controller() {

        bookService = new BookService();
        createBookPanel = new CreateBookPanel();
        viewBooksPanel = new ViewBooksPanel(bookList);

        createBookPanel.setFormListener(((author, title) -> {
            System.out.println("author = " + author + " :: title = " + title);
            try {
                bookService.save(new Book(title, author));
                refresh();
            } catch (IOException e) {
                e.printStackTrace(System.err);
                JOptionPane.showMessageDialog(mainFrame,
                        "Error while saving the Book.",
                        "Service Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }));

        mainFrame = new MainFrame(createBookPanel, viewBooksPanel);
        mainFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                refresh();
            }
        });
    }

    protected void refresh() {
        bookList.clear();
        try {
            bookList.addAll(bookService.getAll());
            viewBooksPanel.refresh();
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(mainFrame,
                    "Error while refreshing Books.",
                    "Service Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
