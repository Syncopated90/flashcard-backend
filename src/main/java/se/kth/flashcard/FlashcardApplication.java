package se.kth.flashcard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class FlashcardApplication {
	public static void main(String[] args) {
		SpringApplication.run(FlashcardApplication.class, args);
	}
  @GetMapping("/hello")
  public String hello(@RequestParam(value = "name", defaultValue = "world") String name){
    System.out.println("hello world called");
    return String.format("hello hello %s!", name);
  }
  
}
