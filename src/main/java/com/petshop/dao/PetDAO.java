package com.petshop.dao;

import com.petshop.model.Pet;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PetDAO {
    private Connection connection;

    public PetDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Pet> listPets() throws SQLException {
        List<Pet> pets = new ArrayList<>();
        String sql = "SELECT * FROM pets";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                Pet pet = new Pet(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("age"),
                        resultSet.getString("type"),
                        resultSet.getString("breed"),
                        resultSet.getInt("client_id")
                );
                pets.add(pet);
            }
        }
        return pets;
    }

    public void addPet(Pet pet) throws SQLException {
        String sql = "INSERT INTO pets (name, age, type, breed, client_id) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, pet.getName());
            statement.setInt(2, pet.getAge());
            statement.setString(3, pet.getType());
            statement.setString(4, pet.getBreed());
            statement.setInt(5, pet.getClientId());
            statement.executeUpdate();
        }
    }

    public void removePet(int petId) throws SQLException {
        String sql = "DELETE FROM pets WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, petId);
            statement.executeUpdate();
        }
    }

    public Pet findPetById(int petId) throws SQLException {
        String sql = "SELECT * FROM pets WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, petId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Pet(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getInt("age"),
                            resultSet.getString("type"),
                            resultSet.getString("breed"),
                            resultSet.getInt("client_id")
                    );
                }
            }
        }
        return null;
    }
}