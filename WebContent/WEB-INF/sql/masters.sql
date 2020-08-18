CREATE TABLE IF NOT EXISTS user (
	id INT UNSIGNED NOT NULL AUTO_INCREMENT,
	name VARCHAR(80) NOT NULL,
	profileType VARCHAR(15) NOT NULL,
	email VARCHAR(80) NOT NULL,
	secret_code CHAR(64) NOT NULL,
	PRIMARY KEY (id)
)
ENGINE=INNODB;


INSERT INTO user VALUES("","Dominic Toretto", "Buyer","dom_t@gmail.com", SHA2("dom01", 256));
INSERT INTO user VALUES("","Brian O'Conner", "Buyer","oconner@outlook.com", SHA2("ocb02", 256));