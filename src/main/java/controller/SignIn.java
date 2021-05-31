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
    private ManagerDao dao;
    private ManagerDto data;
    
    public SignIn() {
        data = new ManagerDto();
        dao = new ManagerDao();
    };
    
    
    public boolean login(ManagerDto manager) {
        try {
            data = dao.getDataByKey("id", manager.getId());
            return manager.getId().equals(data.getId()) && manager.getPassword().equals(data.getPassword());
        } catch (SQLException e) {
            return false;
        }
    }
}
