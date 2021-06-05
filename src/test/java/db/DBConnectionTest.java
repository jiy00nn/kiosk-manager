/**
 * @packageName     : db
 * @filewName       : DBConnectionTest.java
 * @author          : Jiyoon Bak, Junseok Lee
 * @date            : 2021.05.06
 * @description     : DB 연결 테스트 클래스 
 * =======================================================
 *      DATE         AUTHOR          NOTE
 * -------------------------------------------------------
 * 2021.05.06       JiYoon Bak      최초 생성 및 연결 테스트
 * 2021.06.01       Junseok Lee     testGetInstance 테스트 수정
 */
package db;

import org.junit.Test;
import static org.junit.Assert.*;
import db.DBConnection;

public class DBConnectionTest {

    /**
     * Test of getInstance method, of class DBConnection.
     */
    @Test
    public void testGetInstance() {
        System.out.println("[ getInstance method Test ]");
        
        DBConnection result1 = DBConnection.getInstance();
        System.out.println(result1);
        assertNotNull(result1);
        
        DBConnection result2 = DBConnection.getInstance();
        System.out.println(result2);
        assertNotNull(result1);
    }

    /**
     * Test of ConnectDB method, of class DBConnection.
     */
    @Test
    public void testConnectDB() {
        System.out.println("[ connectDB method Test ]");
        DBConnection connect = DBConnection.getInstance();
        connect.connectDB();
    }
}

