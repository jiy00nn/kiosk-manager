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
import view.EditUserInfo;
import view.MemeberManagement;

/**
 *
 * @author mkaid
 */
public class ManagementUser extends EditUser implements ControllerInterface {
    private List<UserDto> user_list;
    private MemeberManagement view;
    private EditUserInfo editView;
    private int index;
    
    public ManagementUser() {
        try{
            user_list = userDao.getAll();
        } catch (SQLException e) {
            javax.swing.JOptionPane.showMessageDialog(null, "Cannot load user list.");
        }
        view = new MemeberManagement(this, this.user_list);
    };
    
    @Override
    public void start(){
        view.setVisible(true);
        view.showUserList(user_list);
    }
    
    @Override
    public void back() {
        view.setVisible(false);
        new Management().start();
    }
    
    public List<UserDto> getUserList() {
        try{
            user_list = userDao.getAll();
        } catch (SQLException e) {
            javax.swing.JOptionPane.showMessageDialog(null, "Cannot load user list.");
        }
        return user_list;
    }

    public void showEditUser(UUID user_number) {
        for(int i = 0 ; i < user_list.size() ; i++){
            if(user_number == user_list.get(i).getUserNumber()){
                index = i;
                break;
            }
        }
        
        try {
            this.cbook_list = this.getCheckoutList(user_list.get(index));
        } catch (SQLException e) {
            javax.swing.JOptionPane.showMessageDialog(null, "Cannot load user information.");
        }
        editView = new EditUserInfo(this, user_list.get(index), this.cbook_list);
        editView.setVisible(true);
    }
    
    public List<UserDto> searchUsers(String key, String search_text) {
        List<UserDto> model = new ArrayList();
        search_text = search_text.toLowerCase();
        
        switch (key) {
            case "ID":
                for(int i = 0 ; i < user_list.size() ; i++){
                    if(user_list.get(i).getId().toLowerCase().contains(search_text)){
                        model.add(user_list.get(i));
                    }
                }
                break;
            case "Name":
                for(int i = 0 ; i < user_list.size() ; i++){
                    if(user_list.get(i).getName().toLowerCase().contains(search_text)){
                        model.add(user_list.get(i));
                    }
                }
                break;
        }
        
        
        return model;
    }
}
