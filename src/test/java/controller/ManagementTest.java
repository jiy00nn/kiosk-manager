/**
 * @packageName     : controller
 * @filewName       : ManagementTest.java
 * @author          : Jiyoon Bak, Jongho An
 * @date            : 2021.06.05
 * @description     : Strategy Pattern Test
 * =======================================================
 *      DATE         AUTHOR          NOTE
 * -------------------------------------------------------
 * 2021.06.01       JiYoon Bak      최초 생성
 * 2021.06.02       JiYoon Bak      Strategy Pattern Test 추가 
 * 2021.06.02       Jongho An       Strategy Pattern Test 수정 
 */
package controller;

import org.junit.Test;
import controller.Management;
import controller.ManagementUser;
import controller.ManagementBooks;

public class ManagementTest { 
    /**
     * Test of setManagement method, of class Management.
     */
    @Test
    public void testStrategyPattern(){
        System.out.println("[ Strategy Pattern Test ]");
        Management client = new Management();
        
        // Show select menu view
        client.start();
        
        // Show Book Management view
        client.setManagement(new ManagementBooks());
        client.start();
        
        // Show User Managemnet view
        client.setManagement(new ManagementUser());
        client.start();
    }

}
