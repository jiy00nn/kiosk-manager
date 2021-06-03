/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import db.DBConnection;
import dto.UserDto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author mkaid
 */
public class UserDao {
    public final DBConnection db = DBConnection.getInstance();
    private UserDto user;
    private List<UserDto> user_list;
    
    public UserDao() { 
        super();
        user = new UserDto();
        user_list = new ArrayList<>();
    }
    
    public void add() throws SQLException {
        
        //SQL 문장 생성
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO USER (user_number, id, password, name) VALUES (?,?,?,?)");
        
        try(Connection conn = db.connectDB()){
            try(PreparedStatement pstm = conn.prepareStatement(sql.toString())){
                pstm.setString(1, user.getUserNumber().toString());
                pstm.setString(2, user.getId());
                pstm.setString(3, user.getPassword());
                pstm.setString(4, user.getName());
                
                pstm.executeUpdate();
            }
        }
    };

    
    public List<UserDto> getAll() throws SQLException {
        
        user_list = new ArrayList<>();
        
        //SQL 문장 생성
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM USER");
        
        try(Connection conn = db.connectDB()){
            try(Statement stmt = conn.createStatement()){
                try(ResultSet rs = stmt.executeQuery(sql.toString())){
                    while(rs.next()) {
                        UserDto user = new UserDto();
                        user.setUserNumber(UUID.fromString(rs.getString("user_number")));
                        user.setId(rs.getString("id"));
                        user.setPassword(rs.getString("password"));
                        user.setName(rs.getString("name"));
                        user_list.add(user);
                    }
                }
            }
        }
        
        return this.user_list;
    };
    
    
    public UserDto getDataByKey(String key, String value) throws SQLException {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM USER WHERE ").append(key).append(" = '").append(value).append("'");
        
        try(Connection conn = db.connectDB()){
            try(Statement stmt = conn.createStatement()){
                try(ResultSet rs = stmt.executeQuery(sql.toString())){
                    while(rs.next()) {
                        user.setUserNumber(UUID.fromString(rs.getString("user_number")));
                        user.setId(rs.getString("id"));
                        user.setPassword(rs.getString("password"));
                        user.setName(rs.getString("name"));
                    }
                }
            }
        }
        return this.user;
    }
    
    
    public void update(UserDto user) throws SQLException {
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE USER SET "
                + "id = ?, password = ?, name = ? "
                + "WHERE user_number = ?");
        
        try(Connection conn = db.connectDB()){
            try(PreparedStatement pstm = conn.prepareStatement(sql.toString())){
                pstm.setString(1, user.getId());
                pstm.setString(2, user.getPassword());
                pstm.setString(3, user.getName());
                pstm.setString(4, user.getUserNumber().toString());
                pstm.executeUpdate();
            }
        }
    };
    
    
    public void delete(UserDto user) throws SQLException {
        
        //SQL 문장 생성
        StringBuilder sql = new StringBuilder();
        sql.append("DELETE FROM USER where user_number = ?");
        
        try(Connection conn = db.connectDB()){
            try(PreparedStatement pstm = conn.prepareStatement(sql.toString())){
                pstm.setString(1, user.getUserNumber().toString());
                pstm.executeUpdate();
            }
        }
    }
    
    
    public void deleteByKey(String key, String value) throws SQLException {
        
        //SQL 문장 생성
        StringBuilder sql = new StringBuilder();
        sql.append("DELETE FROM USER where ? = ?");
        
        try(Connection conn = db.connectDB()){
            try(PreparedStatement pstm = conn.prepareStatement(sql.toString())){
                pstm.setString(1, key);
                pstm.setString(1, value);
                pstm.executeUpdate();
            }
        }
    }
    
}
