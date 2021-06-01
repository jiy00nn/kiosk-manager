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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import view.BookManagement;

public class ManagementBook implements ControllerInterface {
    private BookDao dao;
    private BookManagement view;

    public ManagementBook() {
        dao = new BookDao();
        view = new BookManagement(this);
    }
    
    @Override
    public void start() {
        view.setVisible(true);
    }
    
    public void addBook(BookDto book) throws SQLException {
        book.setId(UUID.randomUUID());
        book.setStatus("True");
        book.setCount(1);
        dao.add(book);
    }
    
    public void updateBook(BookDto book) throws SQLException {
        if(book.getCount() > 0) {
            book.setStatus("True");
        } else {
            book.setStatus("False");
        }
        dao.update(book);
    }
    
    public List<BookDto> searchBook(String key, String search_text) throws SQLException{
        List<BookDto> book_list = new ArrayList();
        search_text = search_text.toLowerCase();
        try {
            book_list = dao.getAll();
            switch (key) {
                case "title":
                    for(int i = 0 ; i < book_list.size() ; i++) {
                         BookDto data = book_list.get(i);
                         if (!data.getTitle().toLowerCase().contains(search_text)){
                             book_list.remove(i);
                         }
                     }
                    break;
                case "genre":
                    for(int i = 0 ; i < book_list.size() ; i++) {
                         BookDto data = book_list.get(i);
                         if (!data.getGenre().toLowerCase().contains(search_text)){
                             book_list.remove(i);
                         }
                     }
                    break;
                case "author":
                    for(int i = 0 ; i < book_list.size() ; i++) {
                         BookDto data = book_list.get(i);
                         if (!data.getAuthor().toLowerCase().contains(search_text)){
                             book_list.remove(i);
                         }
                     }
                    break;
                case "status":
                    for(int i = 0 ; i < book_list.size() ; i++) {
                         BookDto data = book_list.get(i);
                         if (!data.getStatus().toLowerCase().contains(search_text)){
                             book_list.remove(i);
                         }
                     }
                    break;
                case "count":
                    for(int i = 0 ; i < book_list.size() ; i++) {
                         BookDto data = book_list.get(i);
                         if (!(data.getCount() >= Integer.parseInt(search_text))){
                             book_list.remove(i);
                         }
                     }
                    break;
            }
            return book_list;
        } catch (ParseException e) {
            BookDto book = new BookDto();
            book_list.add(book);
            return book_list;
        }
    }

    public void deleteBook(BookDto book) throws SQLException {
        dao.delete(book);
    }
    
    public List<BookDto> getAllBookList() throws SQLException {
        List<BookDto> book_list = new ArrayList();
        
        try {
            book_list = dao.getAll();
            return book_list;
        } catch (SQLException e) {
            throw new SQLException();
        } catch (ParseException e) {
            BookDto book = new BookDto();
            book_list.add(book);
            return book_list;
        }
    }


    
}
