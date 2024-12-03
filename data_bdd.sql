-- Création de la base de données
CREATE DATABASE your_car_your_way;
USE your_car_your_way;

-- Table agency
CREATE TABLE agency (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    country VARCHAR(100) NOT NULL,
    city VARCHAR(100) NOT NULL,
    adress VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    phone INT NOT NULL,
    created_at DATE,
    updated_at DATE,
    deleted BOOLEAN DEFAULT FALSE
);

-- Table car
CREATE TABLE car (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    category VARCHAR(100) NOT NULL,
    color VARCHAR(50) NOT NULL
);

-- Table client
CREATE TABLE client (
    id INT AUTO_INCREMENT PRIMARY KEY,
    firstname VARCHAR(100) NOT NULL,
    lastname VARCHAR(100) NOT NULL,
    city VARCHAR(100) NOT NULL,
    adress VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    phone INT NOT NULL
);

-- Table user
CREATE TABLE user (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_client INT NOT NULL,
    password VARCHAR(255) NOT NULL,
    created_at DATE,
    updated_at DATE,
    deleted BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (id_client) REFERENCES client(id) ON DELETE CASCADE
);

-- Table payement
CREATE TABLE payement (
    id INT AUTO_INCREMENT PRIMARY KEY,
    payement BOOLEAN DEFAULT FALSE,
    payement_method VARCHAR(100) NOT NULL
);

-- Table reservation
CREATE TABLE reservation (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_user INT NOT NULL,
    id_car INT NOT NULL,
    id_payement INT NOT NULL,
    date_of_coverage DATE NOT NULL,
    return_date DATE NOT NULL,
    FOREIGN KEY (id_user) REFERENCES user(id) ON DELETE CASCADE,
    FOREIGN KEY (id_car) REFERENCES car(id) ON DELETE CASCADE,
    FOREIGN KEY (id_payement) REFERENCES payement(id) ON DELETE SET NULL
);

-- Table agent
CREATE TABLE agent (
    id INT AUTO_INCREMENT PRIMARY KEY,
    firstname VARCHAR(100) NOT NULL,
    lastname VARCHAR(100) NOT NULL,
    password VARCHAR(255) NOT NULL,
    created_at DATE,
    updated_at DATE,
    deleted BOOLEAN DEFAULT FALSE
);

-- Table chat_message
CREATE TABLE chat_message (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_agent INT NOT NULL,
    id_user INT NOT NULL,
    date DATE NOT NULL,
    content TEXT NOT NULL,
    closed BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (id_agent) REFERENCES agent(id) ON DELETE CASCADE,
    FOREIGN KEY (id_user) REFERENCES user(id) ON DELETE CASCADE
);

-- Table faq
CREATE TABLE faq (
    id INT AUTO_INCREMENT PRIMARY KEY,
    theme VARCHAR(100) NOT NULL,
    content TEXT NOT NULL,
    created_at DATE,
    updated_at DATE,
    deleted BOOLEAN DEFAULT FALSE
);
