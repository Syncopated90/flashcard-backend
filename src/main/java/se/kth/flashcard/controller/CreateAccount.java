package se.kth.flashcard.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import se.kth.flashcard.model.Account;
import se.kth.flashcard.integration.DBCaller;
import se.kth.flashcard.integration.AccountRepository;

@RestController
public class CreateAccount {
  private final String ORIGIN_URL = "http://localhost:5173";
  @Autowired
  private AccountRepository accountRepo;
  private DBCaller dbc;

  @CrossOrigin(origins = ORIGIN_URL)
  @PostMapping(value="/createAccount", produces = "application/json")
  public ResponseEntity<String> createAccount(@RequestBody Account account) {
    this.dbc = new DBCaller();
    //System.out.println("checking if helo exists: " + accountRepo.findByUsername("helo"));
    if(accountRepo.findByUsername(account.username) == null){
      account = accountRepo.save(account);
      return new ResponseEntity<>(
      account.toJson(),
      HttpStatus.OK);
    }
    else{
      return new ResponseEntity<>(HttpStatus.CONFLICT);
    }
  }

  @CrossOrigin(origins = ORIGIN_URL)
  @GetMapping(value="/createAccount", produces = "application/json")
  public ResponseEntity<String> createAccountByGet(@RequestParam("username") String username, 
    @RequestParam("password") String password) {
      this.dbc = new DBCaller();
      Account account = new Account(username, password);
      if(!dbc.authenticateUser(account.username, account.password)){
        account = accountRepo.save(account);
        return new ResponseEntity<>(
        account.toJson(),
        HttpStatus.OK);
    }
    else{
      return new ResponseEntity<>(HttpStatus.CONFLICT);
    }
  }
  
}
