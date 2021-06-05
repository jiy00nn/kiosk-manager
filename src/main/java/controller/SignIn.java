/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dto.ManagerDto;
import dao.ManagerDao;
import java.sql.SQLException;

public class SignIn {
    private ManagerDto data;
    private final ManagerDao dao;
    private final view.SignIn view;
    
    private SignIn() {
        data = new ManagerDto();
        dao = new ManagerDao();
        view = new view.SignIn(this);
    };
    
    private static class LazyHolder {
        private static final SignIn INSTANCE = new SignIn();
    }
    
    public static SignIn getInstance() {
        return LazyHolder.INSTANCE;
    }
    
    public void start() {
        view.setVisible(true);
    }
    
    public ManagerDto getData() {
        return this.data;
    }
    
    public void setData(ManagerDto data) {
        this.data = data;
    }
    
    public boolean login() {
        try {
            data = dao.getDataByKey("id", data.getId());
            view.setVisible(false);
            return data.getId().equals(data.getId()) && data.getPassword().equals(data.getPassword());
        } catch (SQLException e) {
            return false;
        }
    }
    
    public void connect() {
        new Management().start();
    }
}
