package se.kth.flashcard.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import se.kth.flashcard.integration.DBCaller;
import se.kth.flashcard.model.Account;

public class Login {
  final String ORIGIN_URL = "http://localhost:5173";
  @CrossOrigin(origins = ORIGIN_URL)
  @PostMapping(value = "/login", produces = "application/json")
  ResponseEntity<String> login(@RequestBody Account userData){
    DBCaller dbc = new DBCaller();
    //dbc.printDatabase();
    System.out.println("received POST login request: " + userData.username);
    String DBResponse = jsonify(userData.username);
    System.out.println("DBResponse: " + DBResponse);
    if(dbc.authenticateUser(userData.username, userData.password)){
      return new ResponseEntity<>(
      DBResponse,
      HttpStatus.OK);
    }
    else{
      return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
  }


  String jsonify(String data){
    return "{\"Username\": \"" + data + "\"}";
  }

  @CrossOrigin(origins = ORIGIN_URL)
  @GetMapping(value="/login", produces = "application/json")
  ResponseEntity<String> login(){
    System.out.println("received get request on /login");
    return new ResponseEntity<>("{\"message\":\"hello user trying to login\"}", HttpStatus.OK);
  }
}
