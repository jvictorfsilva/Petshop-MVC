package com.petshop.controller;

import com.petshop.model.Client;
import com.petshop.dao.ClientDAO;

import java.sql.SQLException;
import java.util.List;

public class ClientController {
    private ClientDAO clientDAO;

    public ClientController(ClientDAO clientDAO) {
        this.clientDAO = clientDAO;
    }

    public List<Client> listClients() throws SQLException {
        return clientDAO.listClients();
    }

    public void addClient(String name, String cpf, String phone) throws SQLException {
        Client client = new Client(0, name, cpf, phone);
        clientDAO.addClient(client);
    }

    public boolean clientExists(int clientId) throws SQLException {
        return clientDAO.findClientById(clientId) != null;
    }

    public String removeClient(int clientId) throws SQLException {
        if (clientExists(clientId)) {
            clientDAO.removeClient(clientId);
            return "Client removed successfully.";
        } else {
            return "Error: Client with ID " + clientId + " not found.";
        }
    }

    public Client searchByCpf(String cpf) throws SQLException {
        return clientDAO.searchByCpf(cpf);
    }

    public List<Client> searchByName(String name) throws SQLException {
        return clientDAO.searchByName(name);
    }
}
