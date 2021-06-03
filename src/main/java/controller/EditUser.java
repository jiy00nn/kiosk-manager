/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.BookDao;
import dao.CheckoutBookDao;
import dao.UserDao;
import dto.UserDto;

import java.sql.SQLException;

public class EditUser extends CommonFeatures {
    protected UserDao userDao;
    protected CheckoutBookDao cDao;
    protected BookDao bookDao;
    
    public EditUser() {
        userDao = new UserDao();
        cDao = new CheckoutBookDao();
        bookDao = new BookDao();
    }
    
    
    public void UdateData(UserDto user) throws SQLException {
        userDao.update(user);
    }
}
