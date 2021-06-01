/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dto.BookDto;
import dto.UserDto;
import dao.BookDao;
import dao.UserDao;
import dto.CheckoutBookDto;
import dao.CheckoutBookDao;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import view.MemeberManagement;

/**
 *
 * @author mkaid
 */
public class ManagementUser extends CommonFeatures implements ControllerInterface {
    private UserDao userDao;
    private BookDao bookDao;
    private CheckoutBookDao cDao;
    private List<CheckoutBookDto> cbook;
    private List<BookDto> book ;
    private List<UserDto> user_list;
    private MemeberManagement view;
    
    public ManagementUser() {
        userDao = new UserDao();
        user_list = new ArrayList();
        bookDao = new BookDao();
        cDao = new CheckoutBookDao();
        book = new ArrayList();
        cbook = new ArrayList();
        view = new MemeberManagement(this);
    };
    
    @Override
    public void start(){
        view.setVisible(true);
        try {
            user_list = userDao.getAll();
            view.showUserList(user_list);
        } catch (SQLException e) {
            javax.swing.JOptionPane.showMessageDialog(null, "Cannot load user list.");
        }
    }
    
    @Override
    public void back() {
        view.setVisible(false);
        new Management().start();
    }

    public void editUser() {
        
    }
    
    public void getUserInfo(UserDto user) throws SQLException, ParseException {
        cbook = cDao.getBookListByUser(user);
        for (int i = 0 ; i < cbook.size() ; i++){
            book.add(bookDao.getData("id", cbook.get(i).getBookId().toString()));
        }
    }
    
    public List<CheckoutBookDto> getCheckOutBookList() {
        return this.cbook;
    }
    
    public List<BookDto> getBookList() {
        return this.book;
    }
    
    public void updateUserInfo(UserDto user, List<CheckoutBookDto> book_list) throws SQLException {
        userDao.update(user);
        for(int i = 0 ; i < book_list.size() ; i++) {
            cDao.update(book_list.get(i));
        }
    }
    
    public void deleteCheckOutBook(UUID book_id) throws SQLException {
        cDao.deleteByBook(book_id);
    }
    
    public void deleteUser(UserDto user) throws SQLException {
        cDao.deleteByUser(user.getUserNumber());
        userDao.delete(user);
    }

}
