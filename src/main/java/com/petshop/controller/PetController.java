package com.petshop.controller;

import com.petshop.model.Pet;
import com.petshop.dao.PetDAO;

import java.sql.SQLException;
import java.util.List;

public class PetController {
    private PetDAO petDAO;

    public PetController(PetDAO petDAO) {
        this.petDAO = petDAO;
    }

    public List<Pet> listPets() throws SQLException {
        return petDAO.listPets();
    }

    public void addPet(String name, int age, String type, String breed, int clientId) throws SQLException {
        Pet pet = new Pet(0, name, age, type, breed, clientId);
        petDAO.addPet(pet);
    }

    public boolean petExists(int petId) throws SQLException {
        return petDAO.findPetById(petId) != null;
    }

    public String removePet(int petId) throws SQLException {
        if (petExists(petId)) {
            petDAO.removePet(petId);
            return "Pet removed successfully.";
        } else {
            return "Error: Pet with ID " + petId + " not found.";
        }
    }
}