/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import db.DBConnection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import dto.BookDto;
import dto.UserDto;
import dto.CheckoutBookDto;
import java.util.Date;
import java.util.UUID;


public class CheckoutBookDao {
    private final DBConnection db = DBConnection.getInstance();
    private UserDto user;
    private BookDto book;
    private CheckoutBookDto check_out;
    
    public CheckoutBookDao() {
        this.user = new UserDto();
        this.book = new BookDto();
        this.check_out = new CheckoutBookDto();
    }
    
    public CheckoutBookDao(UserDto user, BookDto book, CheckoutBookDto check_out) {
        this.user = user;
        this.book = book;
        this.check_out = check_out;
    }
    
    private java.sql.Date getDate(LocalDate date) {
        java.sql.Date sql_date = java.sql.Date.valueOf(date);
        return sql_date;
    }
    
    private java.sql.Date UtilDateToSqlDate (Date data) {
        java.sql.Date sql_date = new java.sql.Date(data.getTime());
        return sql_date;
    }
    
    
    public void add() throws SQLException {
        
        //SQL 문장 생성
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO CHECKOUTBOOK (user_number, book_id, rental_date, return_date) VALUES (?,?,?,?)");
        
        try(Connection conn = db.connectDB()){
            try(PreparedStatement pstm = conn.prepareStatement(sql.toString())){
                LocalDate todaysDate = LocalDate.now();
                pstm.setString(1, user.getUserNumber().toString());
                pstm.setString(2, book.getId().toString());
                pstm.setDate(3, getDate(todaysDate));
                pstm.setDate(4, getDate(todaysDate.plusDays(7)));
                pstm.executeUpdate();
            }
        }
    };
    
    
    public List<CheckoutBookDto> getBookListByUser(UserDto user) throws SQLException {
        
        List<CheckoutBookDto> book_list;
        book_list = new ArrayList<>();
        
        //SQL 문장 생성
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * "
                + "FROM BOOK, CHECKOUTBOOK "
                + "WHERE BOOK.id = CHECKOUTBOOK.book_id AND user_number = '").append(user.getUserNumber().toString()).append("'");
        
        try(Connection conn = db.connectDB()){
            try(Statement stmt = conn.createStatement()){
                try(ResultSet rs = stmt.executeQuery(sql.toString())){
                    while(rs.next()) {
                        CheckoutBookDto data = new CheckoutBookDto();
                        data.setUserId(user.getUserNumber());
                        data.setBookId(UUID.fromString(rs.getString("book_id")));
                        data.setRentalDate(rs.getDate("rental_date"));
                        data.setReturnDate(rs.getDate("return_date"));
                        book_list.add(data);
                    }
                }
            }
        }
        
        return book_list;
    };
    
    
    public CheckoutBookDto getDataByKey(String key, String value) throws SQLException {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM CHECKOUTBOOK WHERE ").append(key).append(" = '").append(value).append("'");
        
        try(Connection conn = db.connectDB()){
            try(Statement stmt = conn.createStatement()){
                try(ResultSet rs = stmt.executeQuery(sql.toString())){
                    while(rs.next()) {
                        check_out.setUserId(user.getUserNumber());
                        check_out.setBookId(UUID.fromString(rs.getString("book_id")));
                        check_out.setRentalDate(rs.getDate("rental_date"));
                        check_out.setReturnDate(rs.getDate("return_date"));
                    }
                }
            }
        }
        return this.check_out;
    }
    
    
    
    public void update(CheckoutBookDto check_out) throws SQLException {
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE CHECKOUTBOOK SET "
                + "return_date = ? "
                + "WHERE user_number = ? AND book_id = ? AND rental_date = ?");
        
        try(Connection conn = db.connectDB()){
            try(PreparedStatement pstm = conn.prepareStatement(sql.toString())){
                pstm.setDate(1, UtilDateToSqlDate(check_out.getReturnDate()));
                pstm.setString(2, check_out.getUserId().toString());
                pstm.setString(3, check_out.getBookId().toString());
                pstm.setDate(4, UtilDateToSqlDate(check_out.getRentalDate()));
                pstm.executeUpdate();
            }
        }
    };
    
    
    public void delete(CheckoutBookDto check_out) throws SQLException {
        
        //SQL 문장 생성
        StringBuilder sql = new StringBuilder();
        sql.append("DELETE FROM CHECKOUTBOOK WHERE user_number = ?");
        
        try(Connection conn = db.connectDB()){
            try(PreparedStatement pstm = conn.prepareStatement(sql.toString())){
                pstm.setString(1, check_out.getUserId().toString());
                pstm.executeUpdate();
            }
        }
    };
    
    
    public void deleteByBook(UUID book_id) throws SQLException {
        
        //SQL 문장 생성
        StringBuilder sql = new StringBuilder();
        sql.append("DELETE FROM CHECKOUTBOOK WHERE book_id = ?");
        
        try(Connection conn = db.connectDB()){
            try(PreparedStatement pstm = conn.prepareStatement(sql.toString())){
                pstm.setString(1, book_id.toString());
                pstm.executeUpdate();
            }
        }
    };
    
    public void deleteByUser(UUID user_number) throws SQLException {
        
        //SQL 문장 생성
        StringBuilder sql = new StringBuilder();
        sql.append("DELETE FROM CHECKOUTBOOK WHERE user_number = ?");
        
        try(Connection conn = db.connectDB()){
            try(PreparedStatement pstm = conn.prepareStatement(sql.toString())){
                pstm.setString(1, user_number.toString());
                pstm.executeUpdate();
            }
        }
    };
}
