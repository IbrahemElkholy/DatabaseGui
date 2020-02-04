/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab3_db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Elkholy
 */
public class DBMangement {

    ResultSet users;
    Connection c = null;
    PreparedStatement stmt;

    public DBMangement() throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        c = DriverManager.getConnection("jdbc:sqlite:USER1");
        System.out.println("Opened database successfully");

    }

    public void insertRow(User s) throws SQLException {

        String sql = "INSERT INTO company(F_NAME,M_NAME,L_NAME,EMAIL,phone) VALUES(?,?,?,?)";
        stmt.setString(2, s.getfName());
        stmt.setString(3, s.getmName());
        stmt.setString(4, s.getlName());
        stmt.setString(5, s.getEmail());
        stmt.setInt(6, s.getPhone());

        stmt.executeUpdate(sql);
        c.commit();
    }

    public void updateRow(User s) throws SQLException {

        String sql = "UPDATE company SET F_NAME = ? , "
                + "M_NAME = ? ,"
                + "L_NAME = ? ,"
                + "M_NAME = ? ,"
                + "EMAIL = ? ,"
                + "phone = ? "
                + "WHERE ID = ?";
        
                        stmt = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
              stmt.setString(1,s.getfName());
                            stmt.setString(2,s.getmName());
              stmt.setString(3,s.getlName());
              stmt.setString(4,s.getEmail());
              stmt.setInt(5,s.getPhone());
              stmt.setInt(6,s.getId());

        stmt.executeUpdate();
        c.commit();
    }

    public ResultSet getAll() throws SQLException {
        stmt = c.prepareStatement("SELECT * FROM company;", ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);

        ResultSet rs = stmt.executeQuery();

        return rs;
    }

    public void deletRow(User s) throws SQLException {
        stmt = c.prepareStatement("DELETE from COMPANY where ID = ?;", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

         
        stmt.executeUpdate();
        c.commit();
    }

    
     public User getUser() throws SQLException {
         if(users==null){
             users=getAll();
         }
        try {
            if (users.next()) {
                User user = new User();
                user.setId(users.getInt("ID"));
                user.setfName(users.getString("F_NAME"));
                user.setmName(users.getString("M_NAME"));
                user.setlName(users.getString("L_NAME"));
                user.setEmail(users.getString("EMAIL"));
                user.setPhone(users.getInt("Phone"));

                return user;

            }
        } catch (SQLException ex) {
            Logger.getLogger(Lab3_DB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
