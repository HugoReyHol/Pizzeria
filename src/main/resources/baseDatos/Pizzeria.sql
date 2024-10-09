DROP DATABASE IF EXISTS Pizzeria;
CREATE DATABASE Pizzeria;
USE Pizzeria;


CREATE TABLE Clientes(
	IdCliente INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
	Nombre VARCHAR(15),
    Contrasena Varchar(64)

);


CREATE TABLE Pedidos(
	IdPedido INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    Cliente INT UNSIGNED,
    Pizza enum('Barbacoa', '4 Quesos', 'Margarita'),
    Cantidad INT,
    Bebida enum('Agua', 'CocaCola', 'Te'),
    FOREIGN KEY (Cliente) REFERENCES Clientes(IdCliente)

);