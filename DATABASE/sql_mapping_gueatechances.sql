DROP DATABASE IF EXISTS guateChances_DB;

CREATE DATABASE guateChances_DB;
USE guateChances_DB;

CREATE TABLE categories(
    id_code INT NOT NULL AUTO_INCREMENT,
    cat_name VARCHAR(50) NOT NULL,
    cat_desc VARCHAR (150) NOT NULL,
    isActive INT DEFAULT (1),  /*0 inactiva en la plataforma, 1 activaa*/
    PRIMARY KEY (id_code) 
);

CREATE TABLE registered_cards(
    no_card BIGINT NOT NULL, 
    titular VARCHAR(50) NOT NULL,
    csv INT NOT NULL,
    PRIMARY KEY (no_card)
);

CREATE TABLE users(
    id_code VARCHAR (50) NOT NULL,
    forename VARCHAR (50) NOT NULL,
    direction VARCHAR(50),
    username VARCHAR(50) UNIQUE NOT NULL,
    userpass VARCHAR(64) NOT NULL,
    CUI VARCHAR(25) UNIQUE,
    email VARCHAR(50) NOT NULL,
    birth_date DATE NOT NULL,
    userType INT NOT NULL,
    isActive INT DEFAULT (1), /*0 inactivo en la plataforma, 1 activo, 2 contratado para alguna empreza*/
    PRIMARY KEY (id_code)
);

CREATE TABLE employers(
    id_code VARCHAR (50) NOT NULL,
    no_card BIGINT,
    vision VARCHAR(400) NULL,
    mision VARCHAR(400) NULL,
    paysPlataform DECIMAL(8,2) DEFAULT (0),
    PRIMARY KEY (id_code),
    CONSTRAINT id_employer_fk 
    FOREIGN KEY (id_code)
    REFERENCES users(id_code),
    CONSTRAINT assoc_card_fk
    FOREIGN KEY (no_card)
    REFERENCES registered_cards(no_card)
);

CREATE TABLE jobSeekers(
    id_code VARCHAR (50) NOT NULL,
    curriculumPDF MEDIUMBLOB,
    PRIMARY KEY (id_code),
    CONSTRAINT id_seeker_fk
    FOREIGN KEY (id_code)
    REFERENCES users(id_code)
);

CREATE TABLE user_categories(
    user_code VARCHAR (50) NOT NULL,
    category_id INT NOT NULL,
    CONSTRAINT user_code_fk
    FOREIGN KEY (user_code)
    REFERENCES users(id_code),
    CONSTRAINT cat_code_fk
    FOREIGN KEY (category_id)
    REFERENCES categories(id_code)
);

CREATE TABLE phonebook(
    user_code VARCHAR (50) NOT NULL,
    telephone BIGINT NOT NULL,
    CONSTRAINT user_agend_fk
    FOREIGN KEY (user_code)
    REFERENCES users(id_code)
);

CREATE TABLE offers(
    id_code INT NOT NULL AUTO_INCREMENT,
    offer_name VARCHAR(100) NOT NULL,
    offer_desc VARCHAR(300) NOT NULL,
    employer_id VARCHAR (50) NOT NULL, 
    category_id INT NOT NULL,
    offer_state INT DEFAULT (0),  /*0 activa, 1 expirada/esperando entrevista, 2 FINALIZADA*/
    publication_date DATE NOT NULL,
    expiration_date DATE NOT NULL, 
    payment DECIMAL(8,2) NOT NULL,
    modality INT NOT NULL, /*0 presencial, 1 remoto, 2 Hibrido*/
    direction VARCHAR(60) NOT NULL,
    details VARCHAR(500) NOT NULL,
    user_selected VARCHAR (50) DEFAULT (NULL),
    PRIMARY KEY (id_code),
    CONSTRAINT employer_code_fk
    FOREIGN KEY (employer_id)
    REFERENCES employers(id_code)
    ON UPDATE CASCADE,
    CONSTRAINT offer_category_fk
    FOREIGN KEY (category_id)
    REFERENCES categories(id_code)
);

CREATE TABLE payment_logs(
    id_code INT NOT NULL AUTO_INCREMENT,
    user_code VARCHAR (50) NOT NULL,
    payment_date DATE NOT NULL,
    plataformPayment DECIMAL(8,2),
    PRIMARY KEY (id_code),
    CONSTRAINT user_payment_fk
    FOREIGN KEY (user_code)
    REFERENCES users(id_code)
    ON UPDATE CASCADE
);

CREATE TABLE offers_payments(
    id_code INT NOT NULL AUTO_INCREMENT,
    offer_code INT NOT NULL,
    payment_date DATE NOT NULL,
    plataformPayment DECIMAL(8,2) DEFAULT (150),
    PRIMARY KEY (id_code),
    CONSTRAINT offer_payed_fk
    FOREIGN KEY (offer_code)
    REFERENCES offers(id_code)
    ON UPDATE CASCADE
);

CREATE TABLE applications(
    id_code INT NOT NULL AUTO_INCREMENT,
    seeker_code VARCHAR (50) NOT NULL,
    offer_code INT NOT NULL,
    application_state INT DEFAULT (0),  /*0 pendiente, 1 seleccionado, 2 rechazado*/
    seeker_message VARCHAR(500),
    PRIMARY KEY (id_code),
    CONSTRAINT seeker_code_fk
    FOREIGN KEY (seeker_code)
    REFERENCES jobSeekers(id_code)
    ON UPDATE CASCADE, 
    CONSTRAINT offer_code_fk
    FOREIGN KEY (offer_code)
    REFERENCES offers(id_code)
    ON UPDATE CASCADE
);

CREATE TABLE selected_users(
    user_code VARCHAR (50) NOT NULL,
    offer_code INT NOT NULL,
    CONSTRAINT user_selected_fk
    FOREIGN KEY (user_code)
    REFERENCES jobSeekers(id_code)
    ON UPDATE CASCADE,
    CONSTRAINT offer_selection_fk
    FOREIGN KEY (offer_code)
    REFERENCES offers(id_code)
    ON UPDATE CASCADE
);

CREATE TABLE interviews(
    id_code INT NOT NULL AUTO_INCREMENT, 
    application_code INT NOT NULL,
    interview_date DATE NOT NULL,
    inteview_time TIME NOT NULL, 
    interview_state INT DEFAULT(0), /*0 pendiente, 1 realizada, 2 rechazado*/
    direction VARCHAR(60) NOT NULL,
    notes VARCHAR(300) NOT NULL,
    PRIMARY KEY (id_code),
    CONSTRAINT application_guest_fk
    FOREIGN KEY (application_code)
    REFERENCES applications(id_code)
);


INSERT INTO users(id_code, forename, username, userpass, email, birth_date, userType) VALUES
('adminAssing', 'admin1', 'admin1', 'b761bec41deab1e8e6a3b9245cf4ace25ef1e262c44bb45ad8c67809c0d6baec', 'admin@email.com', '2000-06-06', 0);

INSERT INTO payment_logs(user_code, payment_date, plataformPayment) VALUES
('adminAssing', '2021-06-06', 150);

-- INSERT INTO offers_payments(offer_code, payment_date, plataformPayment) VALUES
-- (1, '2021-06-06', 150);

-- SELECT e.id_code AS employer_id, e.forename AS employer_name, SUM(op.plataformPayment) AS total_cost
-- FROM users e JOIN offers o ON e.id_code = o.employer_id JOIN offers_payments op ON o.id_code = op.offer_code
-- WHERE o.offer_state = 2  -- Ofertas finalizadas (cambiar el estado según tu definición)
-- GROUP BY e.id_code, e.forename ORDER BY total_cost DESC;

-- SELECT i.id_code, i.interview_date, i.inteview_time, i.interview_state, i.direction, i.notes
-- FROM interviews i
-- JOIN applications a ON i.application_code = a.id_code
-- WHERE i.interview_date = ?  -- Coloca la fecha específica deseada
-- ORDER BY i.interview_date, i.inteview_time;

-- SELECT u.id_code AS employer_id, u.forename AS employer_name, SUM(op.plataformPayment) AS total_income FROM users u JOIN offers o ON u.id_code = o.employer_id JOIN offers_payments op ON o.id_code = op.offer_code WHERE op.payment_date BETWEEN '2021-05-05' AND '2021-07-07' GROUP BY u.id_code, u.forename ORDER BY total_income DESC LIMIT 5; 
-- -- Insertar datos en la tabla "categories"
-- INSERT INTO categories (id_code, cat_name, cat_desc) VALUES
-- (1, 'Tecnologia', 'Categoria relacionada con empleos en tecnologia'),
-- (2, 'Ventas', 'Categoria relacionada con empleos en ventas'),
-- (3, 'Salud', 'Categoria relacionada con empleos en el campo de la salud');

-- -- Insertar datos en la tabla "registered_cards"
-- INSERT INTO registered_cards (no_card, titular, csv) VALUES
-- (1234567890, 'Juan Perez', 123),
-- (9876543210, 'Maria Gonzalez', 456);

-- -- Insertar datos en la tabla "users"
-- INSERT INTO users (id_code, forename, direction, username, userpass, CUI, email, birth_date, userType) VALUES
-- ('logol0sfyjbwk','sirbocho', '123-12312', 'sirbocho', 'b761bec41deab1e8e6a3b9245cf4ace25ef1e262c44bb45ad8c67809c0d6baec', '1231231231232', 'byronvasquez21@gmail.com','2000-10-16', 2),
-- ('seeker1', 'Juan', '123 Main St', 'juancito', 'b761bec41deab1e8e6a3b9245cf4ace25ef1e262c44bb45ad8c67809c0d6baec', '123456789012345', 'juan@example.com', '1990-05-15', 2),
-- ('seeker2', 'Maria', '456 Elm St', 'maria123', 'b761bec41deab1e8e6a3b9245cf4ace25ef1e262c44bb45ad8c67809c0d6baec', '234567890123456', 'maria@example.com', '1988-09-20', 2),
-- ('employer1', 'Empresa XYZ', '789 Oak St', 'empresa_xyz', 'b761bec41deab1e8e6a3b9245cf4ace25ef1e262c44bb45ad8c67809c0d6baec', '345678901234567', 'empresa@example.com', '2000-01-10', 1);

-- -- Insertar datos en la tabla "jobSeekers"
-- INSERT INTO jobSeekers (id_code) VALUES
-- ('seeker1'),
-- ('seeker2'),
-- ('logol0sfyjbwk');

-- -- Insertar datos en la tabla "employers"
-- INSERT INTO employers (id_code, no_card, vision, mision) VALUES
-- ('employer1', 1234567890, 'Vision de la empresa', 'Mision de la empresa');

-- -- Insertar datos en la tabla "user_categories"
-- INSERT INTO user_categories (user_code, category_id) VALUES
-- ('seeker1', 1),
-- ('seeker1', 2),
-- ('seeker2', 2),
-- ('employer1', 3),
-- ('logol0sfyjbwk', 2),
-- ('logol0sfyjbwk', 3);

-- -- Insertar datos en la tabla "phonebook"
-- INSERT INTO phonebook (user_code, telephone) VALUES
-- ('seeker1', 1234567890),
-- ('seeker1', 9876543210),
-- ('seeker2', 3232323232),
-- ('seeker2', 5555555555),
-- ('employer1', 8888888888),
-- ('employer1', 7777777777),
-- ('logol0sfyjbwk', 9876543210),
-- ('logol0sfyjbwk', 5555555555);

-- -- Insertar datos en la tabla "offers"
-- INSERT INTO offers (offer_name, offer_desc, employer_id, category_id, offer_state, publication_date, expiration_date, payment, modality, direction, details) VALUES
-- ( 'Desarrollador Web', 'Descripcion de la oferta de desarrollo web', 'employer1', 1, 0, '2023-11-01', '2023-12-01', 5000.00, 1, 'Direccion de la oferta', 'Detalles de la oferta'),
-- ( 'Representante de Ventas', 'Descripcion de la oferta de ventas', 'employer1', 2, 0, '2023-11-02', '2023-12-02', 3000.00, 2, 'Otra direccion de la oferta', 'Detalles de la oferta');

-- INSERT INTO selected_users (user_code, offer_code) VALUES
-- ('seeker1', 1),
-- ('seeker2', 1),
-- ('logol0sfyjbwk', 1),
-- ('logol0sfyjbwk', 2);


-- -- Insertar datos en la tabla "applications"
-- INSERT INTO applications ( seeker_code, offer_code, seeker_message) VALUES
-- ( 'seeker1', 1, 'Mensaje de solicitud de empleo para la oferta 1'),
-- ( 'seeker2', 1, 'Mensaje de solicitud de empleo para la oferta 1'),
-- ( 'seeker1', 2, 'Mensaje de solicitud de empleo para la oferta 2'),
-- ( 'logol0sfyjbwk', 1, 'Mensaje de solicitud de empleo para la oferta 1'),
-- ( 'logol0sfyjbwk', 2, 'Mensaje de solicitud de empleo para la oferta 2');

-- -- Insertar datos en la tabla "selected_users"

-- -- Insertar datos en la tabla "interviews"
-- INSERT INTO interviews (application_code,  interview_date, inteview_time, interview_state, direction, notes) VALUES
-- (1, '2023-11-10', '09:00:00', 0, 'Direccion de la entrevista 1', 'Notas de la entrevista 1'),
-- (2, '2023-11-11', '10:00:00', 0, 'Direccion de la entrevista 2', 'Notas de la entrevista 2'),
-- (4, '2023-11-10', '09:00:00', 0, 'Direccion de la entrevista 1', 'Notas de la entrevista 1');
    