/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import org.junit.Test;
import org.junit.Assert;
import dto.ManagerDto;
import controller.SignIn;
import java.util.UUID;

public class SignInTest {
    private final ManagerDto manager = new ManagerDto(UUID.randomUUID(), "test_id", "test_password", "test_name");
    private final SignIn signIn = new SignIn();
    
    @Test
    public void singInTest() {
        assert signIn.login(manager);
    }
}
