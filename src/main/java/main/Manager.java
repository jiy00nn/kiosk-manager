/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import controller.SignIn;

public class Manager {

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                SignIn signIn = SignIn.getInstance();
                signIn.start();
            }
        });
    }
    
}
