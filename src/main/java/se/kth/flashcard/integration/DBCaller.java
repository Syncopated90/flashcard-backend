package se.kth.flashcard.integration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.ResultSet;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

public class DBCaller {
  private Connection conn;
  public String info;

  public DBCaller(){
    this.info = "user info";
    this.conn = accessDB();
  }
  
  public boolean authenticateUser(String username, String password){
    try{
      PreparedStatement getUser = conn.prepareStatement("SELECT * FROM account WHERE USERNAME = ? AND PASSWORD = ?;");
      getUser.setString(1, username);
      getUser.setString(2, password);
      ResultSet user = getUser.executeQuery();
      if(user.isBeforeFirst()){
        System.out.println("user " + username + " was authenticated successfully");
        return true;
      }
    }catch(SQLException e){
      e.printStackTrace();
    } 
    System.out.println("unsuccesful user authentication");
    return false;
  }

  public void printDatabase(){
    try{
      Statement stmt = this.conn.createStatement();
      ResultSet users = stmt.executeQuery("SELECT * FROM account");
      while(users.next()){
        System.out.print("A row: ");
        for(int i = 1; i < users.getMetaData().getColumnCount() + 1; i++){
          System.out.print(users.getMetaData().getColumnName(i) + ": " + users.getString(i) + " ");
        }
        System.out.println("");
      }
    }catch(Exception err){
      err.printStackTrace();
    }
  }

   private Connection accessDB(){
        try{
            this.conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/flashcard",
                                                     "postgres", "postgres");
            this.conn.setAutoCommit(false);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return conn;
    }
}
