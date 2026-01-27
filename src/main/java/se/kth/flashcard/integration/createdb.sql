CREATE TABLE account(
  id int GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  username varchar(50) NOT NULL,
  password varchar(50) NOT NULL
);

CREATE TABLE flashcard(
  id int GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  account_key int NOT NULL,
  flashcard1 varchar(100) NOT NULL,
  flashcard2 varchar(100) NOT NULL,
  CONSTRAINT account_fk
    FOREIGN KEY(account_key)
      REFERENCES account(id)
        ON DELETE CASCADE
);


Not used:
CREATE TABLE account_card(
  accountId int NOT NULL,
  flashcard_id int NOT NULL,
  CONSTRAINT account_fk
    FOREIGN KEY(accountId)
      REFERENCES account(id)
        ON DELETE CASCADE,
  CONSTRAINT flashcard_fk
    FOREIGN KEY(flashcard_id)
      REFERENCES flashcard(id)
        ON DELETE CASCADE
);