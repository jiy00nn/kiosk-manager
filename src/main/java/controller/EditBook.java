/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.BookDao;
import dto.BookDto;
import java.sql.SQLException;

public class EditBook extends CommonFeatures {
    protected BookDao dao;
    
    public EditBook() {
        dao = new BookDao();
    }
    
    protected BookDto updateData(BookDto book) throws SQLException {
        if(book.getCount() > 0) {
            book.setStatus("True");
        } else {
            book.setStatus("False");
        }
        dao.update(book);
        return book;
    }
    
    protected void deleteData(BookDto book) throws SQLException {
        dao.delete(book);
    }
}
