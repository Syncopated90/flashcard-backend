package se.kth.flashcard.integration;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import se.kth.flashcard.model.Flashcard;


public interface FlashcardRepository extends CrudRepository<Flashcard, Long> {
  Flashcard findById(long id);
  List<Flashcard> findByAccountKey(long accountKey);
}
