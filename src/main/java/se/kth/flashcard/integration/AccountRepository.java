package se.kth.flashcard.integration;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import se.kth.flashcard.model.Account;

public interface AccountRepository extends CrudRepository<Account, Long>{
  Account findByUsername(String username);
  Account findById(long id);
  Account findByUsernameAndPassword(String username, String password);
  //Account save(Account account);
}
