CREATE TABLE account(
  id int GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  username varchar(50) NOT NULL,
  password varchar(50) NOT NULL
);

CREATE TABLE flashcard(
  id int GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  first_side varchar(100) NOT NULL,
  second_side varchar(100) NOT NULL
);

CREATE TABLE account_card(
  account_id int NOT NULL,
  flashcard_id int NOT NULL,
  CONSTRAINT account_fk
    FOREIGN KEY(account_id)
      REFERENCES account(id)
        ON DELETE CASCADE,
  CONSTRAINT flashcard_fk
    FOREIGN KEY(flashcard_id)
      REFERENCES flashcard(id)
        ON DELETE CASCADE
);