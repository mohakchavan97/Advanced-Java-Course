package org.example.gui;

import org.example.model.Book;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class BookTableModel extends AbstractTableModel {

    private List<Book> bookList;

    public BookTableModel(List<Book> bookList) {
        this.bookList = bookList;
    }

    @Override
    public int getRowCount() {
        return bookList.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        String value = "";
        Book book = bookList.get(rowIndex);
        value = String.valueOf(switch (columnIndex) {
            case 0 -> book.getId();
            case 1 -> book.getAuthor();
            case 2 -> book.getTitle();
            default -> "";
        });
        return value;
    }

    @Override
    public String getColumnName(int column) {
        return switch (column) {
            case 0 -> "ID";
            case 1 -> "Author";
            case 2 -> "Title";
            default -> "";
        };
    }
}
