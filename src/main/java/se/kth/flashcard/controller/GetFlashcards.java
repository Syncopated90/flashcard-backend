package se.kth.flashcard.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import se.kth.flashcard.integration.FlashcardRepository;
import se.kth.flashcard.integration.DBCaller;
import se.kth.flashcard.model.Flashcard;
import tools.jackson.databind.ObjectMapper;

import java.util.List;

@RestController
public class GetFlashcards {
  private final String ORIGIN_URL = "http://localhost:5173";
  @Autowired
  private FlashcardRepository flashcardRepo;

  @CrossOrigin(origins = ORIGIN_URL)
  @PostMapping(value = "/getFlashcards", produces = "application/json")
  public ResponseEntity<String> getFlashcards(@RequestBody long id){
    System.out.println("getting flashcards for " + id);
    List<Flashcard> cards = flashcardRepo.findByAccountKey(id);
    //System.out.println(cards);
    //System.out.println(cards.size());
    System.out.println(Flashcard.flashcardListToJson(cards));

    ObjectMapper mapper = new ObjectMapper();
    String json = mapper.writeValueAsString(cards);
    System.out.println(json);

    //Flashcard.flashcardListToJson(cards),
    return new ResponseEntity<>(
    json,
    HttpStatus.OK
  );
  }
}
