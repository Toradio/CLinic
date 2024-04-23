/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author w10
 */
public class dbConnector {
    private Connection connect;
    
     public dbConnector(){
            try{
                connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/clinicdb", "root", "");
            }catch(SQLException ex){
                    System.out.println("Can't connect to database: "+ex.getMessage());
            }
        }
     
     //Function to retrieve data
        public ResultSet getData(String sql) throws SQLException{
            Statement stmt = connect.createStatement();
            ResultSet rst = stmt.executeQuery(sql);
            return rst;
        }
        
        public int insertData(String sql) throws SQLException {
        Statement stmt = connect.createStatement();
        int rowsAffected = stmt.executeUpdate(sql);
        return rowsAffected;
    }
        
        public void updateData(String sql){
            int result;
            try{
                PreparedStatement pst = connect.prepareStatement(sql);
                int rowsUpdated = pst.executeUpdate();
                if(!(rowsUpdated>0)){
                    System.out.println("Data Update Failed!");
                }
            }catch(SQLException ex){
                System.out.println("Connection Error: "+ex);
            }
        }
}
