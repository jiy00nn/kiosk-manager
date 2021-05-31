/**
 * @packageName     : db
 * @filewName       : DBConnection.java
 * @author          : Jiyoon Bak
 * @date            : 2021.05.03
 * @description     : NCP의 MariaDB와 연결 
 * =======================================================
 *      DATE         AUTHOR          NOTE
 * -------------------------------------------------------
 * 2021.05.01       JiYoon Bak      최초 생성
 * 2021.05.03       JiYoon Bak      DB Connect 기능 추가
 * 2021.05.05       JiYoon Bak      Singleton Pattern 적용
 */
package db;

import java.io.Reader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

import java.util.Properties;

/**
 * MariaDB와 connection을 만든다.
 */
public class DBConnection {
    /**
     * DB Connection을 위한 객체
     * @see #connectDB() 
    **/
    private Connection con;
    
    // 외부에서의 인스턴스화를 막는다.
    private DBConnection(){}
    
    /* LazyHolder를 이용하여 싱글톤 패턴을 구현한다. */
    private static class LazyHolder {
        private static final DBConnection INSTANCE = new DBConnection();
    }
    
    /**
     * DBConnection의 Instance를 반환한다.
     * @return DBConnection의 Instance
    **/
    public static DBConnection getInstance() {
        return LazyHolder.INSTANCE;
    }
    
    /**
     * DB와 Connection을 하기 위한 메소드
     * @return Connection 객체
    **/
    public Connection connectDB() {
        Properties properties = new Properties();
        
        // database.properties 파일 읽기
        try{
            FileReader resource = new FileReader("src/main/java/config/database.properties");
            properties.load(resource);
        } catch (FileNotFoundException e){
            // TODO logging 처리
            System.out.println("Properties file not found");
        } catch (IOException e) { }
  
        // DB 연결을 위한 정보 확인(driver, url, username, password)
        String db_driver_class = properties.getProperty("driver");
        String db_url = properties.getProperty("url");
        String db_username = properties.getProperty("username");
        String db_password = properties.getProperty("password");
        
        // DB 연결 후 Connection 객체 반환
        try {
            Class.forName(db_driver_class);
            con = DriverManager.getConnection(db_url, db_username, db_password);
        }catch (ClassNotFoundException e){
            System.out.println("Fail drive loading");
        }catch (SQLException e){
            System.out.println("File to connect database");
        }catch (Exception e){
            e.printStackTrace();
        }
        
        return con;
    }
}
