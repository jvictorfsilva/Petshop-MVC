package controller;

import model.Pet;
import dao.PetDAO;

import java.sql.SQLException;
import java.util.List;

public class PetController {
    private PetDAO petDAO;

    public PetController(PetDAO petDAO) {
        this.petDAO = petDAO;
    }

    public List<Pet> listarPets() throws SQLException {
        return petDAO.listarPets();
    }

    public void adicionarPet(String nome, int idade, String tipo, String raca, int idCliente) throws SQLException {
        Pet pet = new Pet(0, nome, idade, tipo, raca, idCliente);
        petDAO.adicionarPet(pet);
    }

    public boolean existePet(int idPet) throws SQLException {
        return petDAO.buscarPetPorId(idPet) != null;
    }

    public String removerPet(int idPet) throws SQLException {
        if (existePet(idPet)) {
            petDAO.removerPet(idPet);
            return "Pet removido com sucesso.";
        } else {
            return "Erro: Pet com ID " + idPet + " não encontrado.";
        }
    }

}
