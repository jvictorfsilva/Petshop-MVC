package com.petshop.dao;

import com.petshop.model.Client;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientDAO {
    private Connection connection;

    public ClientDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Client> listClients() throws SQLException {
        List<Client> clients = new ArrayList<>();
        String sql = "SELECT * FROM clients";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                Client client = new Client(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("cpf"),
                        resultSet.getString("phone")
                );
                clients.add(client);
            }
        }
        return clients;
    }

    public void addClient(Client client) throws SQLException {
        String sql = "INSERT INTO clients (name, cpf, phone) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, client.getName());
            statement.setString(2, client.getCpf());
            statement.setString(3, client.getPhone());
            statement.executeUpdate();
        }
    }

    public void removeClient(int clientId) throws SQLException {
        String sql = "DELETE FROM clients WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, clientId);
            statement.executeUpdate();
        }
    }

    public Client searchByCpf(String cpf) throws SQLException {
        String sql = "SELECT * FROM clients WHERE cpf = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, cpf);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Client(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getString("cpf"),
                            resultSet.getString("phone")
                    );
                }
            }
        }
        return null;
    }

    public List<Client> searchByName(String name) throws SQLException {
        List<Client> clients = new ArrayList<>();
        String sql = "SELECT * FROM clients WHERE name LIKE ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, "%" + name + "%");
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Client client = new Client(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getString("cpf"),
                            resultSet.getString("phone")
                    );
                    clients.add(client);
                }
            }
        }
        return clients;
    }

    public Client findClientById(int clientId) throws SQLException {
        String sql = "SELECT * FROM clients WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, clientId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Client(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getString("cpf"),
                            resultSet.getString("phone")
                    );
                }
            }
        }
        return null;
    }
}
