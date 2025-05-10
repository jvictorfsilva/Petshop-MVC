CREATE DATABASE IF NOT EXISTS petshop_db;
USE petshop_db;

CREATE TABLE IF NOT EXISTS clientes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    telefone VARCHAR(20) NOT NULL,
    cpf VARCHAR(11) UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS pets (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    idade INT NOT NULL,
    tipo VARCHAR(50) NOT NULL,
    raca VARCHAR(50) NOT NULL,
    id_cliente INT,
    FOREIGN KEY (id_cliente) REFERENCES clientes(id) ON DELETE CASCADE
);

INSERT INTO clientes (nome, telefone, cpf) VALUES 
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

INSERT INTO pets (nome, idade, tipo, raca, id_cliente) VALUES 
('Rex', 3, 'Cachorro', 'Labrador', 1),
('Miau', 2, 'Gato', 'Siamês', 1),
('Fido', 4, 'Cachorro', 'Bulldog', 2),
('Luna', 1, 'Gato', 'Persa', 3),
('Bob', 5, 'Cachorro', 'Poodle', 4),
('Kitty', 2, 'Gato', 'Sphynx', 5),
('Thor', 6, 'Cachorro', 'Golden Retriever', 6),
('Nina', 4, 'Gato', 'Maine Coon', 7),
('Max', 1, 'Cachorro', 'Beagle', 8),
('Bella', 3, 'Gato', 'Ragdoll', 9);
