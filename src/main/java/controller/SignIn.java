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
    
    public SignIn() {
        data = new ManagerDto();
        dao = new ManagerDao();
        view = new view.SignIn(this);
    };
    
    public void start() {
        view.setVisible(true);
    }
    
    public void setData(ManagerDto data) {
        this.data = data;
    }
    
    public boolean login() {
        try {
            data = dao.getDataByKey("id", data.getId());
            view.setVisible(false);
            new Management().start();
            return data.getId().equals(data.getId()) && data.getPassword().equals(data.getPassword());
        } catch (SQLException e) {
            return false;
        }
    }
}
