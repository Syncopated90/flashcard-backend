package se.kth.flashcard.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Account {
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private long id;
  public String username;
  public String password;
  
  protected Account(){}

  public Account(String username, String password){
    this.username = username;
    this.password = password;
  }

  public String toString(){
    return new String(this.id + ": user:" + this.username + ", password: " + this.password);
  }

  public String toJson(){
    return "{\"id\":" + this.id + ",\"Username\": \"" + this.username + "\"}";
  }
  String jsonify(String data){
    return "{\"Username\": \"" + data + "\"}";
  }
  public long getId(){return this.id;}
  public String getUsername(){return this.username;}
  public String getPAssword(){return this.password;}
}
