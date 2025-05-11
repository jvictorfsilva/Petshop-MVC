package com.petshop.view;

import com.petshop.controller.ClientController;
import com.petshop.controller.PetController;
import com.petshop.dao.ClientDAO;
import com.petshop.dao.PetDAO;
import com.petshop.model.Client;
import com.petshop.model.Pet;


import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class PetShopApp extends JFrame {
    private JTextArea displayArea;
    private PetController petController;
    private ClientController clientController;

    public PetShopApp(Connection connection) throws SQLException {
        PetDAO petDAO = new PetDAO(connection);
        petController = new PetController(petDAO);

        ClientDAO clientDAO = new ClientDAO(connection);
        clientController = new ClientController(clientDAO);

        setTitle("Pet Shop");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        displayArea = new JTextArea();
        displayArea.setEditable(false);
        add(new JScrollPane(displayArea), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 3));

        JButton addClientButton = new JButton("Add Client");
        addClientButton.addActionListener(e -> addClient());
        buttonPanel.add(addClientButton);

        JButton addPetButton = new JButton("Add Pet");
        addPetButton.addActionListener(e -> addPet());
        buttonPanel.add(addPetButton);

        JButton listClientsButton = new JButton("List Clients");
        listClientsButton.addActionListener(e -> listClients());
        buttonPanel.add(listClientsButton);

        JButton listPetsButton = new JButton("List Pets");
        listPetsButton.addActionListener(e -> listPets());
        buttonPanel.add(listPetsButton);

        JButton removeClientButton = new JButton("Remove Client");
        removeClientButton.addActionListener(e -> removeClient());
        buttonPanel.add(removeClientButton);

        JButton removePetButton = new JButton("Remove Pet");
        removePetButton.addActionListener(e -> removePet());
        buttonPanel.add(removePetButton);

        JButton searchCpfButton = new JButton("Search by CPF");
        searchCpfButton.addActionListener(e -> searchByCpf());
        buttonPanel.add(searchCpfButton);

        JButton searchNameButton = new JButton("Search by Name");
        searchNameButton.addActionListener(e -> searchByName());
        buttonPanel.add(searchNameButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    private boolean isNameValid(String name) {
        return name != null && name.matches("[a-zA-Z\\s]+");
    }

    private boolean isCpfValid(String cpf) {
        return cpf != null && cpf.matches("\\d{11}");
    }

    private boolean isAgeValid(String ageStr) {
        return ageStr != null && ageStr.matches("\\d+");
    }

    private void addClient() {
        String name = JOptionPane.showInputDialog(this, "Enter client name:");
        if (!isNameValid(name)) {
            displayArea.setText("Error: Invalid name. Use letters only.");
            return;
        }

        String cpf = JOptionPane.showInputDialog(this, "Enter client CPF (numbers only):");
        if (!isCpfValid(cpf)) {
            displayArea.setText("Error: Invalid CPF. Must be 11 digits.");
            return;
        }

        String phone = JOptionPane.showInputDialog(this, "Enter client phone number:");
        try {
            clientController.addClient(name, cpf, phone);
            displayArea.setText("Client added successfully.");
        } catch (SQLException e) {
            displayArea.setText("Error adding client.");
            e.printStackTrace();
        }
    }

    private void addPet() {
        String name = JOptionPane.showInputDialog(this, "Enter pet name:");
        if (!isNameValid(name)) {
            displayArea.setText("Error: Invalid pet name.");
            return;
        }

        String ageStr = JOptionPane.showInputDialog(this, "Enter pet age:");
        if (!isAgeValid(ageStr)) {
            displayArea.setText("Error: Invalid age.");
            return;
        }
        int age = Integer.parseInt(ageStr);

        String type = JOptionPane.showInputDialog(this, "Enter pet type (e.g., Dog):");
        if (!isNameValid(type)) {
            displayArea.setText("Error: Invalid type.");
            return;
        }

        String breed = JOptionPane.showInputDialog(this, "Enter pet breed:");
        if (!isNameValid(breed)) {
            displayArea.setText("Error: Invalid breed.");
            return;
        }

        String clientIdStr = JOptionPane.showInputDialog(this, "Enter owner ID:");
        if (!isAgeValid(clientIdStr)) {
            displayArea.setText("Error: Invalid owner ID.");
            return;
        }
        int clientId = Integer.parseInt(clientIdStr);

        try {
            petController.addPet(name, age, type, breed, clientId);
            displayArea.setText("Pet added successfully.");
        } catch (SQLException e) {
            displayArea.setText("Error adding pet.");
            e.printStackTrace();
        }
    }

    private void listClients() {
        try {
            displayArea.setText("");
            List<Client> clients = clientController.listClients();
            for (Client client : clients) {
                displayArea.append("ID: " + client.getId() + " | Name: " + client.getName() +
                        " | CPF: " + client.getCpf() + " | Phone: " + client.getPhone() + "\n");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error listing clients!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void listPets() {
        try {
            displayArea.setText("");
            List<Pet> pets = petController.listPets();
            for (Pet pet : pets) {
                displayArea.append("ID: " + pet.getId() + " | Name: " + pet.getName() +
                        " | Age: " + pet.getAge() + " | Type: " + pet.getType() +
                        " | Breed: " + pet.getBreed() + "\n");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error listing pets!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void removeClient() {
        String clientIdStr = JOptionPane.showInputDialog(this, "Enter client ID to remove:");
        if (clientIdStr != null && !clientIdStr.trim().isEmpty()) {
            try {
                int clientId = Integer.parseInt(clientIdStr);
                String result = clientController.removeClient(clientId);
                displayArea.setText(result);
            } catch (NumberFormatException e) {
                displayArea.setText("Error: Invalid ID. Must be a number.");
            } catch (SQLException e) {
                displayArea.setText("Error removing client.");
                e.printStackTrace();
            }
        } else {
            displayArea.setText("Client ID not provided.");
        }
    }

    private void removePet() {
        String petIdStr = JOptionPane.showInputDialog(this, "Enter pet ID to remove:");
        if (petIdStr != null && !petIdStr.trim().isEmpty()) {
            try {
                int petId = Integer.parseInt(petIdStr);
                String result = petController.removePet(petId);
                displayArea.setText(result);
            } catch (NumberFormatException e) {
                displayArea.setText("Error: Invalid ID. Must be a number.");
            } catch (SQLException e) {
                displayArea.setText("Error removing pet.");
                e.printStackTrace();
            }
        } else {
            displayArea.setText("Pet ID not provided.");
        }
    }

    private void searchByCpf() {
        try {
            String cpf = JOptionPane.showInputDialog(this, "Enter client CPF:");
            Client client = clientController.searchByCpf(cpf);
            if (client != null) {
                displayArea.append("Client found: ID: " + client.getId() + " | Name: " + client.getName() +
                        " | CPF: " + client.getCpf() + " | Phone: " + client.getPhone() + "\n");
            } else {
                displayArea.append("Client with CPF " + cpf + " not found.\n");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error searching client!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void searchByName() {
        String name = JOptionPane.showInputDialog(this, "Enter client name to search:");
        if (name != null && !name.trim().isEmpty()) {
            try {
                List<Client> clients = clientController.searchByName(name);
                displayArea.setText("");
                if (clients.isEmpty()) {
                    displayArea.append("No clients found with name: " + name);
                } else {
                    for (Client client : clients) {
                        displayArea.append("ID: " + client.getId() + ", Name: " + client.getName() +
                                ", CPF: " + client.getCpf() + ", Phone: " + client.getPhone() + "\n");
                    }
                }
            } catch (SQLException e) {
                displayArea.setText("Error searching clients by name.");
                e.printStackTrace();
            }
        } else {
            displayArea.setText("Name not provided for search.");
        }
    }

    private void clearDisplay() {
        displayArea.setText("");
    }
}