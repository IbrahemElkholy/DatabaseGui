/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lab3_db;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Elkholy
 */
public class DBMangement {
      Connection c = null;
       
      { 
       new  DBMangement();
      }
      public DBMangement() throws SQLException, ClassNotFoundException{
          Class.forName("org.sqlite.JDBC");
       c = DriverManager.getConnection("jdbc:sqlite:USER.db");
        System.out.println("Opened database successfully");
        
        Statement stm=(Statement) c.createStatement();
        String querystring="CREATE TABLE company " +
                        "(ID INT PRIMARY KEY     NOT NULL," +
                        " F_NAME           TEXT    NOT NULL, " + 
                        " M_NAMe           TEXT    NOT NULL, " + 
                         " L_NAME           TEXT    NOT NULL, " + 
                         " EMAIL         TEXT    NOT NULL, " + 
                        "  PHONE           INT     NOT NULL, " ;
                     
      }
   
}
