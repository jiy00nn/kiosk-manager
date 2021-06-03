/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javax.swing.table.DefaultTableModel;

public abstract class TemplateMethod extends javax.swing.JFrame {
    
    public DefaultTableModel model;
    
    final void showList() {
        model.setNumRows(0);
        makeModel();
        setTableModel();
    }
    
    abstract void makeModel();
    abstract void setTableModel();
}
