CREATE DATABASE IF NOT EXISTS petshop_db;
USE petshop_db;

CREATE TABLE IF NOT EXISTS clients (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    cpf CHAR(11) NOT NULL UNIQUE,
    phone VARCHAR(20)
);

CREATE TABLE IF NOT EXISTS pets (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    age INT NOT NULL,
    type VARCHAR(50) NOT NULL,
    breed VARCHAR(50) NOT NULL,
    client_id INT NOT NULL,
    FOREIGN KEY (client_id) REFERENCES clients(id) ON DELETE CASCADE
);

INSERT INTO clients (name, phone, cpf) VALUES
('Alice Silva', '11987654321', '12345678901'),
('Bruno Costa', '11976543210', '12345678902'),
('Carla Mendes', '11987654322', '12345678903'),
('Daniela Rocha', '11976543211', '12345678904'),
('Eduardo Lima', '11987654323', '12345678905'),
('Fernanda Almeida', '11976543212', '12345678906'),
('Gustavo Pereira', '11987654324', '12345678907'),
('Heloísa Santos', '11976543213', '12345678908'),
('Igor Martins', '11987654325', '12345678909'),
('Juliana Ferreira', '11976543214', '12345678910');

INSERT INTO pets (name, age, type, breed, client_id) VALUES 
('Rex', 3, 'Dog', 'Labrador', 1),
('Miau', 2, 'Cat', 'Siamês', 1),
('Fido', 4, 'Dog', 'Bulldog', 2),
('Luna', 1, 'Cat', 'Persa', 3),
('Bob', 5, 'Dog', 'Poodle', 4),
('Kitty', 2, 'Cat', 'Sphynx', 5),
('Thor', 6, 'Dog', 'Golden Retriever', 6),
('Nina', 4, 'Cat', 'Maine Coon', 7),
('Max', 1, 'Dog', 'Beagle', 8),
('Bella', 3, 'Cat', 'Ragdoll', 9);
