/**
 * @packageName     : dao
 * @filewName       : ManagerDao.java
 * @author          : Jiyoon Bak
 * @date            : 2021.05.10
 * @description     : DB를 사용해 관리자 데이터를 조회하거나 조작한다.
 * =======================================================
 *      DATE         AUTHOR          NOTE
 * -------------------------------------------------------
 * 2021.05.10       JiYoon Bak      최초 생성
 * 2021.05.13       JiYoon Bak      기능 추가...
 */
package dao;

import dto.ManagerDto;
import db.DBConnection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.UUID;
import java.util.ArrayList;

/**
 * DB를 사용해 Manager Table의 데이터를 조회하거나 조작하는 기능을 구현한 클래스
 */
public class ManagerDao {
    public final DBConnection db = DBConnection.getInstance();
    private ManagerDto manager;
    private List<ManagerDto> manager_list;
    
    public ManagerDao() { 
        super();
        manager = new ManagerDto();
        manager_list = new ArrayList<>();
    }
    
    public ManagerDao(ManagerDto manager) { 
        super();
        manager = this.manager;
        manager_list = new ArrayList<>();
    }
    
    public void add() throws SQLException {
        
        //SQL 문장 생성
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO MANAGER (manager_number, id, password, name) VALUES (?,?,?,?)");
        
        try(Connection conn = db.connectDB()){
            try(PreparedStatement pstm = conn.prepareStatement(sql.toString())){
                pstm.setString(1, manager.getManagerNumber().toString());
                pstm.setString(2, manager.getId());
                pstm.setString(3, manager.getPassword());
                pstm.setString(4, manager.getName());

                pstm.executeUpdate();
            }
        }
    };

    
    public List<ManagerDto> getAll() throws SQLException {
        //SQL 문장 생성
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM MANAGER");
        
        try(Connection conn = db.connectDB()){
            try(Statement stmt = conn.createStatement()){
                try(ResultSet rs = stmt.executeQuery(sql.toString())){
                    while(rs.next()) {
                        ManagerDto manager = new ManagerDto();
                        manager.setManagerNumber(UUID.fromString(rs.getString("manager_number")));
                        manager.setId(rs.getString("id"));
                        manager.setPassword(rs.getString("password"));
                        manager.setName(rs.getString("name"));
                        manager_list.add(manager);
                    }
                }
            }
        }
        
        return manager_list;
    };
    
    
    public ManagerDto getDataByKey(String key, String value) throws SQLException {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM MANAGER WHERE ").append(key).append(" = '").append(value).append("'");
        
        try(Connection conn = db.connectDB()){
            try(Statement stmt = conn.createStatement()){
                try(ResultSet rs = stmt.executeQuery(sql.toString())){
                    while(rs.next()) {
                        manager.setManagerNumber(UUID.fromString(rs.getString("manager_number")));
                        manager.setId(rs.getString("id"));
                        manager.setPassword(rs.getString("password"));
                        manager.setName(rs.getString("name"));
                    }
                }
            }
        }
        
        return manager;
    }
    
    
    public void update() throws SQLException {
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE MANAGER SET "
                + "id = ?, password = ?, name = ? "
                + "WHERE manager_number = ?");
        
        try(Connection conn = db.connectDB()){
            try(PreparedStatement pstm = conn.prepareStatement(sql.toString())){
                pstm.setString(1, manager.getId());
                pstm.setString(2, manager.getPassword());
                pstm.setString(3, manager.getName());
                pstm.executeUpdate();
            }
        }
    };
    
    
    public void delete() throws SQLException {
        
        //SQL 문장 생성
        StringBuilder sql = new StringBuilder();
        sql.append("DELETE FROM MANAGER where manager_number = ?");
        
        try(Connection conn = db.connectDB()){
            try(PreparedStatement pstm = conn.prepareStatement(sql.toString())){
                pstm.setString(1, manager.getManagerNumber().toString());
                pstm.executeUpdate();
            }
        }
    };
    
    
    public void deleteByKey(String key, String value) throws SQLException {
        
        //SQL 문장 생성
        StringBuilder sql = new StringBuilder();
        sql.append("DELETE FROM MANAGER where ? = ?");
        
        try(Connection conn = db.connectDB()){
            try(PreparedStatement pstm = conn.prepareStatement(sql.toString())){
                pstm.setString(1, key);
                pstm.setString(1, value);
                pstm.executeUpdate();
            }
        }
    };
   
}