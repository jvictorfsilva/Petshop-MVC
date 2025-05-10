package dao;

import model.Pet;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PetDAO {
    private Connection connection;

    public PetDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Pet> listarPets() throws SQLException {
        List<Pet> pets = new ArrayList<>();
        String sql = "SELECT * FROM pets";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                Pet pet = new Pet(
                    resultSet.getInt("id"),
                    resultSet.getString("nome"),
                    resultSet.getInt("idade"),
                    resultSet.getString("tipo"),
                    resultSet.getString("raca"),
                    resultSet.getInt("id_cliente")
                );
                pets.add(pet);
            }
        }
        return pets;
    }

    public void adicionarPet(Pet pet) throws SQLException {
        String sql = "INSERT INTO pets (nome, idade, tipo, raca, id_cliente) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, pet.getNome());
            statement.setInt(2, pet.getIdade());
            statement.setString(3, pet.getTipo());
            statement.setString(4, pet.getRaca());
            statement.setInt(5, pet.getIdCliente());
            statement.executeUpdate();
        }
    }

    public void removerPet(int idPet) throws SQLException {
        String sql = "DELETE FROM pets WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idPet);
            statement.executeUpdate();
        }
    }
    public Pet buscarPetPorId(int idPet) throws SQLException {
        String sql = "SELECT * FROM pets WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idPet);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Pet(
                        resultSet.getInt("id"),
                        resultSet.getString("nome"),
                        resultSet.getInt("idade"),
                        resultSet.getString("tipo"),
                        resultSet.getString("raca"),
                        resultSet.getInt("id_cliente")
                    );
                }
            }
        }
        return null;
    }

}
