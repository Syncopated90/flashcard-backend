package se.kth.flashcard.controller;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import se.kth.flashcard.model.Account;
import se.kth.flashcard.integration.DBCaller;
import se.kth.flashcard.integration.AccountRepository;

@RestController
public class GetAccount {
  private final String ORIGIN_URL = "http://localhost:5173";
  @Autowired
  private AccountRepository accountRepo;

  @GetMapping("/getuser")
  @CrossOrigin(origins = ORIGIN_URL)
  public String getUser(@RequestParam("username") String username){
    return accountRepo.findByUsername(username).toJson();
  }
}
