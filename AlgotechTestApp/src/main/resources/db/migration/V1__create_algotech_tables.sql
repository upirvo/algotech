CREATE TABLE user
(
  id INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
  name VARCHAR(255) NOT NULL,
  pesel BIGINT NOT NULL,
  surname VARCHAR(255) NOT NULL
);
CREATE UNIQUE INDEX user_id_uindex ON user (id);
CREATE UNIQUE INDEX user_pesel_uindex ON user (pesel);