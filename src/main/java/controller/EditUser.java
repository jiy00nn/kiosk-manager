/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.CheckoutBookDao;
import dao.UserDao;
import dto.CheckoutBookDto;
import dto.UserDto;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class EditUser extends CommonFeatures {
    protected UserDao userDao;
    protected CheckoutBookDao cDao;
    
    public UserDto user;
    public List<CheckoutBookDto> cbook_list;
    
    public EditUser() {
        userDao = new UserDao();
        cDao = new CheckoutBookDao();
    }
    
    public List<CheckoutBookDto> getCheckoutList(UserDto user) throws SQLException {
        return cDao.getBookListByUser(user);
    }
    
    public void updateCheckoutList(UserDto user, List<CheckoutBookDto> cbook_list) throws SQLException {
        userDao.update(user);
        for(int i = 0 ; i < cbook_list.size() ; i++){
            cDao.update(cbook_list.get(i));
        }
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
