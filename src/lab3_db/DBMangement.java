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

    private ResultSet users;
    private Connection c = null;
    private PreparedStatement stmt;
    private boolean lastFlag=false,firstFlag=false;

    public DBMangement() throws SQLException, ClassNotFoundException {
        try {
            //Class.forName("org.sqlite.JDBC");
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            //c = DriverManager.getConnection("jdbc:sqlite:USER1");
            c= DriverManager.getConnection("jdbc:mysql://localhost:3307/mysql","root","" );
            users = getAll();
            System.out.println("Opened database successfully");
        } catch (InstantiationException ex) {
            Logger.getLogger(DBMangement.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(DBMangement.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void insertRow(User s) throws SQLException {

        String sql = "INSERT INTO users(F_NAME,M_NAME,L_NAME,EMAIL,phone) VALUES(?,?,?,?,?)";
        stmt.setString(1, s.getfName());
        stmt.setString(2, s.getmName());
        stmt.setString(3, s.getlName());
        stmt.setString(4, s.getEmail());
        stmt.setInt(5, s.getPhone());
        stmt = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

        stmt.executeUpdate();
        c.commit();
        stmt.close();
        c.close();
    }

    public void updateRow(User s) throws SQLException {

        String sql = "UPDATE users SET F_NAME = ? , "
                + "M_NAME = ? ,"
                + "L_NAME = ? ,"
                + "EMAIL = ? ,"
                + "Phone = ? "
                + "WHERE ID = ?";

        stmt = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        stmt.setString(1, s.getfName());
        stmt.setString(2, s.getmName());
        stmt.setString(3, s.getlName());
        stmt.setString(4, s.getEmail());
        stmt.setInt(5, s.getPhone());
        stmt.setInt(6, s.getId());

        stmt.executeUpdate();
        
        setCurrentLocation(s.getId());
    }
    
    private void setCurrentLocation(int userID){
        try {
            users = getAll();
            do {
                users.next();
                System.out.println(users.toString());  
            } while (users.getInt("ID") < userID);
        } catch (SQLException ex) {
            Logger.getLogger(DBMangement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ResultSet getAll() throws SQLException {
        stmt = c.prepareStatement("SELECT * FROM users;", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

        ResultSet rs = stmt.executeQuery();

        return rs;
    }

    public void deletRow(int userID) throws SQLException {
        stmt = c.prepareStatement("DELETE from users where ID = ?;", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

        stmt.setInt(1, userID);
        stmt.executeUpdate();

        setCurrentLocation(userID);
    }

    public User getUser() throws SQLException {

        if (users.next()&&!lastFlag) {
              if(users==null){
                lastFlag=true;
            }else{
            User user = new User();
            user.setId(users.getInt("ID"));
            user.setfName(users.getString("F_NAME"));
            user.setmName(users.getString("M_NAME"));
            user.setlName(users.getString("L_NAME"));
            user.setEmail(users.getString("EMAIL"));
            user.setPhone(users.getInt("Phone"));

            return user;
              }
        }

        return null;
    }

    public User getPrUser() throws SQLException {
        if (users.previous() && !firstFlag) {
            if(users==null){
                firstFlag=true;
            }else{
            User user = new User();
            user.setId(users.getInt("ID"));
            user.setfName(users.getString("F_NAME"));
            user.setmName(users.getString("M_NAME"));
            user.setlName(users.getString("L_NAME"));
            user.setEmail(users.getString("EMAIL"));
            user.setPhone(users.getInt("Phone"));

            return user;
            }
        }

        return null;
    }

    public User getFirst() throws SQLException {

        if (users.first()) {
            User user = new User();
            user.setId(users.getInt("ID"));
            user.setfName(users.getString("F_NAME"));
            user.setmName(users.getString("M_NAME"));
            user.setlName(users.getString("L_NAME"));
            user.setEmail(users.getString("EMAIL"));
            user.setPhone(users.getInt("Phone"));

            return user;
        }
        return null;
    }

    public User getLast() throws SQLException {
        if (users.last()) {
            User user = new User();
            user.setId(users.getInt("ID"));
            user.setfName(users.getString("F_NAME"));
            user.setmName(users.getString("M_NAME"));
            user.setlName(users.getString("L_NAME"));
            user.setEmail(users.getString("EMAIL"));
            user.setPhone(users.getInt("Phone"));

            return user;
        }
        return null;
    }

}
