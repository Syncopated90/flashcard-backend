package se.kth.flashcard.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import se.kth.flashcard.integration.AccountRepository;
import se.kth.flashcard.integration.DBCaller;
import se.kth.flashcard.model.Account;

@RestController
public class Login {
  @Autowired
  private AccountRepository accountRepo;
  private final String ORIGIN_URL = "http://localhost:5173";
  @CrossOrigin(origins = ORIGIN_URL)
  @PostMapping(value = "/login", produces = "application/json")
  ResponseEntity<String> login(@RequestBody Account userData){
    DBCaller dbc = new DBCaller();
    //dbc.printDatabase();
    System.out.println("received POST login request: " + userData.username);
    //System.out.println("DBResponse: " + DBResponse);
    //boolean userFound = dbc.authenticateUser(userData.username, userData.password);
    Account user = accountRepo.findByUsernameAndPassword(userData.username, userData.password);
    if(user != null){
      return new ResponseEntity<>(
      user.toJson(),
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
