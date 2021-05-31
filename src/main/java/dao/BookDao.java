/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import db.DBConnection;
import dto.BookDto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class BookDao {
    public final DBConnection db = DBConnection.getInstance();
    private BookDto book;
    private List<BookDto> book_list;
    
    public BookDao() { 
        super();
        book = new BookDto();
        book_list = new ArrayList<>();
    }
    
    private java.sql.Date UtilDateToSqlDate (Date data) {
        java.sql.Date sql_date = new java.sql.Date(data.getTime());
        return sql_date;
    }
    
    public void add(BookDto book) throws SQLException {
        
        //SQL 문장 생성
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO BOOK (id, title, genre, author, status, count, publication) VALUES (?,?,?,?,?,?,?)");
        try(Connection conn = db.connectDB()){
            try(PreparedStatement pstm = conn.prepareStatement(sql.toString())){
                pstm.setString(1, book.getId().toString());
                pstm.setString(2, book.getTitle());
                pstm.setString(3, book.getGenre());
                pstm.setString(4, book.getAuthor());
                pstm.setString(5, book.getStatus());
                pstm.setInt(6, book.getCount());
                pstm.setDate(7, UtilDateToSqlDate(book.getDate()));
                pstm.executeUpdate();
            }
        }
    };

    public List<BookDto> getAll() throws SQLException, ParseException {
        
        List<BookDto> book_list;
        book_list = new ArrayList<>();
        
        //SQL 문장 생성
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM BOOK");
        
        try(Connection conn = db.connectDB()){
            try(Statement stmt = conn.createStatement()){
                try(ResultSet rs = stmt.executeQuery(sql.toString())){
                    while(rs.next()) {
                        BookDto book = new BookDto();
                        book.setId(UUID.fromString(rs.getString("id")));
                        book.setTitle(rs.getString("title"));
                        book.setGenre(rs.getString("genre"));
                        book.setAuthor(rs.getString("author"));
                        book.setStatus(rs.getString("status"));
                        book.setCount(rs.getInt("count"));
                        book.setDate(rs.getDate("publication"));
                        book_list.add(book);
                    }
                }
            }
        }
        
        return book_list;
    };
    
    public BookDto getData(String key, String data) throws SQLException, ParseException {
        BookDto result = new BookDto();
        
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM BOOK WHERE ").append(key).append(" = '").append(data).append("'");
        
        try(Connection conn = db.connectDB()){
            try(Statement stmt = conn.createStatement()){
                try(ResultSet rs = stmt.executeQuery(sql.toString())){
                    while(rs.next()) {
                        result.setId(UUID.fromString(rs.getString("id")));
                        result.setTitle(rs.getString("title"));
                        result.setGenre(rs.getString("genre"));
                        result.setAuthor(rs.getString("author"));
                        result.setStatus(rs.getString("status"));
                        result.setCount(rs.getInt("count"));
                        result.setDate(rs.getDate("publication"));
                    }
                }
            }
        }
        
        return result;
    }
    
    public void update(BookDto book) throws SQLException {
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE BOOK SET "
                + "title = ?, genre = ?, author = ?, status = ?, count = ?, publication = ? "
                + "WHERE id = ?");
        
        try(Connection conn = db.connectDB()){
            try(PreparedStatement pstm = conn.prepareStatement(sql.toString())){
                pstm.setString(1, book.getTitle());
                pstm.setString(2, book.getGenre());
                pstm.setString(3, book.getAuthor());
                pstm.setString(4, book.getStatus());
                pstm.setInt(5, book.getCount());
                pstm.setDate(6, UtilDateToSqlDate(book.getDate()));
                pstm.setString(7, book.getId().toString());
                pstm.executeUpdate();
            }
        }
    };
    
    public void delete(BookDto book) throws SQLException {
        
        //SQL 문장 생성
        StringBuilder sql = new StringBuilder();
        sql.append("DELETE FROM BOOK where id = ?");
        
        try(Connection conn = db.connectDB()){
            try(PreparedStatement pstm = conn.prepareStatement(sql.toString())){
                pstm.setString(1, book.getId().toString());
                pstm.executeUpdate();
            }
        }
    }
}
