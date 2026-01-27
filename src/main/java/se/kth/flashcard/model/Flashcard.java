package se.kth.flashcard.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.List;

@Entity
public class Flashcard {
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private long id;
  public String flashcard1;
  public String flashcard2;
  public long accountKey;

  protected Flashcard(){};

  public Flashcard(String flashcard1, String flashcard2, long accountKey){
    this.flashcard1 = flashcard1;
    this.flashcard2 = flashcard2;
    this.accountKey = accountKey;
  }

  public static String flashcardListToJson(List<Flashcard> cards){
    String jsonList = "{";
    for(int i = 0; i < cards.size(); i++){
      /*String flashcardJson = "\"id\":" + flashcard.getId() 
      + ",\"accountKey\": " + flashcard.accountKey 
      + ",\"flashcard1\": \"" + flashcard.flashcard1 
      + "\",\"flashcard2\": \"" + flashcard.flashcard2 
      + "\"";*/
      jsonList += "\"" + i + "\": \"";
      jsonList += cards.get(i).toJson();
      jsonList += "\", ";
    }
    jsonList += "}";
    return jsonList;
  }

public void flipCard(){
  String temp = this.flashcard1;
  this.flashcard1 = this.flashcard2;
  this.flashcard2 = temp;
}

  public String toString(){
    return "flashcardid: " + this.id + ", accountKey: " + this.accountKey + ", flashcard1: " + this.flashcard1 + ", flashcard2: " + this.flashcard2;
  }

  public String toJson(){
    return "{\"id\":" + this.id 
      + ",\"accountKey\": " + this.accountKey 
      + ",\"flashcard1\": \"" + this.flashcard1 
      + "\",\"flashcard2\": \"" + this.flashcard2 
      + "\"}";
  }

  public long getId(){return this.id;}
}
