/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.SQLException;
import java.util.UUID;
import java.util.List;
import dao.BookDao;
import dto.BookDto;
import java.text.ParseException;
import java.util.ArrayList;
import view.*;

public class ManagementBooks extends EditBook implements ControllerInterface {
    private List<BookDto> book_list;  
    private final BookManagement mainView;
    private final AddBook addView;
    private final view.EditBook editView;
    private int index;

    public ManagementBooks() {
        super();
        dao = new BookDao();
        book_list = this.getBookList();
        mainView = new BookManagement(this, book_list);
        addView = new AddBook(this);
        editView = new view.EditBook(this, book_list.get(0));
        this.setLogger(ManagementBooks.class.getName());
    }
    
    @Override
    public void start() {
        this.makeInfoLog("Access Management Books.");
        mainView.setVisible(true);
    }
    
    @Override
    public void back() {
        mainView.setVisible(false);
        new Management().start();
    }
    
    public void viewAddBook() {
        addView.setVisible(true);
    }
    
    public void addBook() throws SQLException, ParseException {
        BookDto book = new BookDto();
        
        book.setId(UUID.randomUUID());
        book.setTitle(addView.getTitle());
        book.setAuthor(addView.getAuthor());
        book.setGenre(addView.getGenre());
        book.setDate(this.handleDate(addView.getDate()));
        book.setStatus("True");
        book.setCount(1);
        
        dao.add(book);
    }
    
    public void editBook(int index) {
        this.index = index;
        editView.setBookInfo(book_list.get(index));
        editView.setVisible(true);
    }
    
    public void updateBook() throws ParseException {
        BookDto book = book_list.get(index);
        
        book.setTitle(editView.getTitle());
        book.setAuthor(editView.getAuthor());
        book.setGenre(editView.getGenre());
        book.setDate(this.handleDate(editView.getDate()));
        book.setCount(editView.getCount());
        
        try {
            this.updateData(book);
            book_list.set(index, book);
            javax.swing.JOptionPane.showMessageDialog(null, "Update sucess");
        } catch (SQLException e){
            javax.swing.JOptionPane.showMessageDialog(null, "Cannot update book info");
        }
    }
    
    public List<BookDto> searchBooks(String key, String search_text) throws SQLException {
        List<BookDto> model = new ArrayList();
        search_text = search_text.toLowerCase();
        
        switch (key) {
            case "title":
                for(int i = 0 ; i < book_list.size() ; i++) {
                     BookDto data = book_list.get(i);
                     if (data.getTitle().toLowerCase().contains(search_text)){
                        model.add(book_list.get(i));
                     }
                 }
                break;
            case "genre":
                for(int i = 0 ; i < book_list.size() ; i++) {
                     BookDto data = book_list.get(i);
                     if (data.getGenre().toLowerCase().contains(search_text)){
                        model.add(book_list.get(i));
                     }
                 }
                break;
            case "author":
                for(int i = 0 ; i < book_list.size() ; i++) {
                     BookDto data = book_list.get(i);
                     if (data.getAuthor().toLowerCase().contains(search_text)){
                        model.add(book_list.get(i));
                     }
                 }
                break;
            case "status":
                for(int i = 0 ; i < book_list.size() ; i++) {
                     BookDto data = book_list.get(i);
                     if (data.getStatus().toLowerCase().contains(search_text)){
                         model.add(book_list.get(i));
                     }
                 }
                break;
            case "count":
                for(int i = 0 ; i < book_list.size() ; i++) {
                     BookDto data = book_list.get(i);
                     if ((data.getCount() >= Integer.parseInt(search_text))){
                        model.add(book_list.get(i));
                     }
                 }
                break;
        }
        return model;
    }

    public void deleteBook() {
        try {
            this.deleteData(book_list.get(index));
            book_list.remove(index);
            javax.swing.JOptionPane.showMessageDialog(null, "Delete Success");
            editView.setVisible(false);
        } catch (SQLException e) {
            javax.swing.JOptionPane.showMessageDialog(null, "Cannot delete book info");
        }
    }
    
    public List<BookDto> getBookList() {
        try {
            book_list = dao.getAll();
        } catch (SQLException e) {
            javax.swing.JOptionPane.showMessageDialog(null, "Cannot load book list.");
        } catch (ParseException e) {
            BookDto book = new BookDto();
            book_list.add(book);
        }
        
        return book_list;
    }

}
