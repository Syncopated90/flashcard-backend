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

import se.kth.flashcard.model.Flashcard;
import se.kth.flashcard.integration.DBCaller;
import se.kth.flashcard.integration.FlashcardRepository;


@RestController
public class AddFlashcard {
  private final String ORIGIN_URL = "http://localhost:5173";
  @Autowired
  private FlashcardRepository flashcardRepo;

  @CrossOrigin(origins = ORIGIN_URL)
  @PostMapping(value="/addFlashcard", produces = "application/json")
  public ResponseEntity<String> addFlashcard(@RequestBody Flashcard flashcard){
    System.out.println(flashcard.toString());
    System.out.println(flashcard.toJson());

    return new ResponseEntity<>(
      flashcardRepo.save(flashcard).toJson(),
      HttpStatus.OK
    );
  }
}
