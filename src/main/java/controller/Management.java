/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

/**
 *
 * @author mkaid
 */
public class Management {
    private ControllerInterface management;
    private view.Management view;
    
    public Management() {
        view = new view.Management(this);
    }
    
    public void setManagement(ControllerInterface management) {
        this.management = management;
    }
    
    public void start() {
        view.setVisible(true);
    };
    
    public void menu() {
        view.setVisible(false);
        management.start();
    }
    
}
