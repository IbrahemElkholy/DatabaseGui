/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab3_db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Elkholy
 */
public class DBMangement {

    Connection c = null;
     PreparedStatement stmt;
    
        
    

    public DBMangement() throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        c = DriverManager.getConnection("jdbc:sqlite:USER.db");
        stmt = c.prepareStatement(null);
        System.out.println("Opened database successfully");

    }
     
    public void insertRow(User s) throws SQLException{
        
    String sql ="INSERT INTO company(F_NAME,M_NAME,L_NAME,phone) VALUES(?,?,?,?)";
         stmt.setString(1,s.getfName());
         stmt.setString(1,s.getmName());
         stmt.setString(1,s.getmName());
         stmt.setInt(1,s.getPhone());
         stmt.executeUpdate(sql);
    }
    
    public void updateRow(User s)throws SQLException{
     String sql="UPDATE company "
                + "SET F_NAME = ? "
                + "WHERE id = ?";
    }
    public List <User> getAll(){
    List <User>users=new ArrayList<>();
    return users;
    }
    public void deletRow(User s){
    
    }

}
