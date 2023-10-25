DROP DATABASE IF EXISTS guateChances_DB;

CREATE DATABASE guateChances_DB;
USE guateChances_DB;

CREATE TABLE categories(
    id_code INT NOT NULL,
    cat_name VARCHAR(50) NOT NULL,
    cat_desc VARCHAR (150) NOT NULL,
    PRIMARY KEY (id_code) 
);

CREATE TABLE registered_cards(
    no_card INT NOT NULL, 
    titular VARCHAR(50) NOT NULL,
    csv INT NOT NULL,
    PRIMARY KEY (no_card)
);

CREATE TABLE users(
    id_code INT NOT NULL,
    forename VARCHAR (50) NOT NULL,
    direction VARCHAR(50),
    username VARCHAR(50) NOT NULL,
    userpass VARCHAR(64) NOT NULL,
    CUI VARCHAR(25),
    email VARCHAR(50) NOT NULL,
    birth_date DATETIME NOT NULL,
    PRIMARY KEY (id_code)
);

CREATE TABLE employers(
    id_code INT NOT NULL,
    no_card INT NOT NULL,
    vision VARCHAR(400) NOT NULL,
    mision VARCHAR(400) NOT NULL,
    PRIMARY KEY (id_code),
    CONSTRAINT id_employer_fk 
    FOREIGN KEY (id_code)
    REFERENCES users(id_code),
    CONSTRAINT assoc_card_fk
    FOREIGN KEY (no_card)
    REFERENCES registered_cards(no_card)
);

CREATE TABLE jobSeekers(
    id_code INT NOT NULL,
    curriculumPDF MEDIUMBLOB,
    PRIMARY KEY (id_code),
    CONSTRAINT id_seeker_fk
    FOREIGN KEY (id_code)
    REFERENCES users(id_code)
);

CREATE TABLE user_categories(
    user_code INT NOT NULL,
    category_id INT NOT NULL,
    CONSTRAINT user_code_fk
    FOREIGN KEY (user_code)
    REFERENCES users(id_code),
    CONSTRAINT cat_code_fk
    FOREIGN KEY (category_id)
    REFERENCES categories(id_code)
);

CREATE TABLE phonebook(
    user_code INT NOT NULL,
    telephone INT NOT NULL,
    CONSTRAINT user_agend_fk
    FOREIGN KEY (user_code)
    REFERENCES users(id_code)
);

CREATE TABLE offers(
    id_code INT NOT NULL,
    offer_name VARCHAR(50) NOT NULL,
    offer_desc VARCHAR(300) NOT NULL,
    employer_id INT NOT NULL, 
    category_id INT NOT NULL,
    offer_state INT DEFAULT (0),
    publication_date DATE NOT NULL,
    expiration_date DATE NOT NULL, 
    payment DECIMAL(8,2) NOT NULL,
    modality INT NOT NULL,
    direction VARCHAR(60) NOT NULL,
    details VARCHAR(150) NOT NULL,
    PRIMARY KEY (id_code),
    CONSTRAINT employer_code_fk
    FOREIGN KEY (employer_id)
    REFERENCES employers(id_code),
    CONSTRAINT offer_category_fk
    FOREIGN KEY (category_id)
    REFERENCES categories(id_code)
);

CREATE TABLE applications(
    id_code INT NOT NULL,
    seeker_code INT NOT NULL,
    offer_code INT NOT NULL,
    seeker_message VARCHAR(500),
    PRIMARY KEY (id_code),
    CONSTRAINT seeker_code_fk
    FOREIGN KEY (seeker_code)
    REFERENCES jobSeekers(id_code), 
    CONSTRAINT offer_code_fk
    FOREIGN KEY (offer_code)
    REFERENCES offers(id_code)
);

CREATE TABLE selected_users(
    user_code INT NOT NULL,
    offer_code INT NOT NULL,
    CONSTRAINT user_selected_fk
    FOREIGN KEY (user_code)
    REFERENCES jobSeekers(id_code),
    CONSTRAINT offer_selection_fk
    FOREIGN KEY (offer_code)
    REFERENCES offers(id_code)
);

CREATE TABLE interviews(
    id_code INT NOT NULL, 
    seeker_code INT NOT NULL,
    offer_code INT NOT NULL,
    interview_date DATE NOT NULL,
    inteview_time TIME NOT NULL, 
    interview_state INT DEFAULT(0),
    direction VARCHAR(60) NOT NULL,
    notes VARCHAR(300) NOT NULL,
    PRIMARY KEY (id_code),
    CONSTRAINT seeker_guest_fk
    FOREIGN KEY (seeker_code)
    REFERENCES selected_users(user_code),
    CONSTRAINT offer_fk
    FOREIGN KEY (offer_code)
    REFERENCES selected_users(offer_code)
);


INSERT INTO users(id_code, forename, username, userpass, email, birth_date) VALUES(1, 'admin', 'admin', 'admin', 'admin@email.com', '2000-06-06');