package view;

import controller.ClienteController;
import controller.PetController;
import dao.ClienteDAO;
import dao.PetDAO;
import model.Cliente;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class PetShopApp extends JFrame {
    private JTextArea displayArea;
    private PetController petController;
    private ClienteController clienteController;

    public PetShopApp(Connection connection) throws SQLException {
        PetDAO petDAO = new PetDAO(connection);
        petController = new PetController(petDAO);

        ClienteDAO clienteDAO = new ClienteDAO(connection);
        clienteController = new ClienteController(clienteDAO);

        setTitle("Pet Shop");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        displayArea = new JTextArea();
        displayArea.setEditable(false);
        add(new JScrollPane(displayArea), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 4));

        JButton addClienteButton = new JButton("Adicionar Cliente");
        addClienteButton.addActionListener(e -> adicionarCliente());
        buttonPanel.add(addClienteButton);

        JButton addPetButton = new JButton("Adicionar Pet");
        addPetButton.addActionListener(e -> adicionarPet());
        buttonPanel.add(addPetButton);

        JButton listClientesButton = new JButton("Listar Clientes");
        listClientesButton.addActionListener(e -> listarClientes());
        buttonPanel.add(listClientesButton);

        JButton listPetButton = new JButton("Listar Pets");
        listPetButton.addActionListener(e -> listarPets());
        buttonPanel.add(listPetButton);

        JButton removeClienteButton = new JButton("Remover Cliente");
        removeClienteButton.addActionListener(e -> removerCliente());
        buttonPanel.add(removeClienteButton);

        JButton removePetButton = new JButton("Remover Pet");
        removePetButton.addActionListener(e -> removerPet());
        buttonPanel.add(removePetButton);

        JButton searchCpfButton = new JButton("Pesquisar por CPF");
        searchCpfButton.addActionListener(e -> pesquisarPorCpf());
        buttonPanel.add(searchCpfButton);
        
        JButton searchNomeButton = new JButton("Pesquisar por Nome");
        searchNomeButton.addActionListener(e -> pesquisarPorNome());
        buttonPanel.add(searchNomeButton);

        JButton clearButton = new JButton("Limpar Display");
        clearButton.addActionListener(e -> clearDisplay());
        buttonPanel.add(clearButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }
    
    private boolean isNomeValido(String nome) {
        return nome != null && nome.matches("[a-zA-Z\\s]+");
    }

    private boolean isCpfValido(String cpf) {
        return cpf != null && cpf.matches("\\d{11}");
    }

    private boolean isIdadeValida(String idade) {
        return idade != null && idade.matches("\\d+");
    }

    private void adicionarCliente() {
        String nome = JOptionPane.showInputDialog(this, "Digite o nome do cliente:");
        if (!isNomeValido(nome)) {
            displayArea.setText("Erro: Nome inválido ou vazio. Use apenas letras.");
            return;
        }

        String cpf = JOptionPane.showInputDialog(this, "Digite o CPF do cliente (somente números):");
        if (!isCpfValido(cpf)) {
            displayArea.setText("Erro: CPF inválido ou vazio. Deve conter 11 dígitos.");
            return;
        }

        String telefone = JOptionPane.showInputDialog(this, "Digite o telefone do cliente:");
        try {
            clienteController.adicionarCliente(nome, cpf, telefone);
            displayArea.setText("Cliente adicionado com sucesso.");
        } catch (SQLException ex) {
            displayArea.setText("Erro ao adicionar cliente.");
            ex.printStackTrace();
        }
    }

    private void adicionarPet() {
        String nome = JOptionPane.showInputDialog(this, "Digite o nome do pet:");
        if (!isNomeValido(nome)) {
            displayArea.setText("Erro: Nome do pet inválido. Use apenas letras.");
            return;
        }

        String idadeStr = JOptionPane.showInputDialog(this, "Digite a idade do pet:");
        if (!isIdadeValida(idadeStr)) {
            displayArea.setText("Erro: Idade inválida. Deve conter apenas números.");
            return;
        }
        int idade = Integer.parseInt(idadeStr);

        String tipo = JOptionPane.showInputDialog(this, "Digite o tipo do pet (ex: Cachorro):");
        if (!isNomeValido(tipo)) {
            displayArea.setText("Erro: Tipo inválido. Use apenas letras.");
            return;
        }

        String raca = JOptionPane.showInputDialog(this, "Digite a raça do pet:");
        if (!isNomeValido(raca)) {
            displayArea.setText("Erro: Raça inválida. Use apenas letras.");
            return;
        }

        String idClienteStr = JOptionPane.showInputDialog(this, "Digite o ID do dono:");
        if (!isIdadeValida(idClienteStr)) {
            displayArea.setText("Erro: ID do dono inválido. Deve conter apenas números.");
            return;
        }
        int idCliente = Integer.parseInt(idClienteStr);

        try {
            petController.adicionarPet(nome, idade, tipo, raca, idCliente);
            displayArea.setText("Pet adicionado com sucesso.");
        } catch (SQLException ex) {
            displayArea.setText("Erro ao adicionar pet.");
            ex.printStackTrace();
        }
    }

    
    private void listarClientes() {
        try {
            clearDisplay();
            var clientes = clienteController.listarClientes();
            for (var cliente : clientes) {
                displayArea.append("ID: " + cliente.getId() + " | Nome: " + cliente.getNome() + 
                                   " | CPF: " + cliente.getCpf() + " | Telefone: " + cliente.getTelefone() + "\n");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao listar clientes!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void listarPets() {
        try {
            clearDisplay();
            var pets = petController.listarPets();
            for (var pet : pets) {
                displayArea.append("ID: " + pet.getId() + " | Nome: " + pet.getNome() + " | Idade: " + pet.getIdade() +
                                   " | Tipo: " + pet.getTipo() + " | Raça: " + pet.getRaca() + "\n");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao listar pets!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void removerCliente() {
        String idClienteStr = JOptionPane.showInputDialog(this, "Digite o ID do cliente para remover:");
        if (idClienteStr != null && !idClienteStr.trim().isEmpty()) {
            try {
                int idCliente = Integer.parseInt(idClienteStr);
                String resultado = clienteController.removerCliente(idCliente);
                displayArea.setText(resultado);
            } catch (NumberFormatException ex) {
                displayArea.setText("Erro: ID inválido. Deve ser um número.");
            } catch (SQLException ex) {
                displayArea.setText("Erro ao remover cliente.");
                ex.printStackTrace();
            }
        } else {
            displayArea.setText("ID do cliente não fornecido.");
        }
    }


    private void removerPet() {
        String idPetStr = JOptionPane.showInputDialog(this, "Digite o ID do pet para remover:");
        if (idPetStr != null && !idPetStr.trim().isEmpty()) {
            try {
                int idPet = Integer.parseInt(idPetStr);
                String resultado = petController.removerPet(idPet);
                displayArea.setText(resultado);
            } catch (NumberFormatException ex) {
                displayArea.setText("Erro: ID inválido. Deve ser um número.");
            } catch (SQLException ex) {
                displayArea.setText("Erro ao remover pet.");
                ex.printStackTrace();
            }
        } else {
            displayArea.setText("ID do pet não fornecido.");
        }
    }


    private void pesquisarPorCpf() {
        try {
            String cpf = JOptionPane.showInputDialog(this, "Digite o CPF do cliente:");
            var cliente = clienteController.pesquisarPorCpf(cpf);
            if (cliente != null) {
                displayArea.append("Cliente encontrado: ID: " + cliente.getId() + " | Nome: " + cliente.getNome() +
                                   " | CPF: " + cliente.getCpf() + " | Telefone: " + cliente.getTelefone() + "\n");
            } else {
                displayArea.append("Cliente com CPF " + cpf + " não encontrado.\n");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao pesquisar cliente!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void pesquisarPorNome() {
        String nome = JOptionPane.showInputDialog(this, "Digite o nome do cliente:");
        if (nome != null && !nome.trim().isEmpty()) {
            try {
                List<Cliente> clientes = clienteController.pesquisarPorNome(nome);
                displayArea.setText("");
                if (clientes.isEmpty()) {
                    displayArea.append("Nenhum cliente encontrado com o nome: " + nome);
                } else {
                    for (Cliente cliente : clientes) {
                        displayArea.append("ID: " + cliente.getId() + ", Nome: " + cliente.getNome() +
                                           ", CPF: " + cliente.getCpf() + ", Telefone: " + cliente.getTelefone() + "\n");
                    }
                }
            } catch (SQLException ex) {
                displayArea.setText("Erro ao pesquisar clientes por nome.");
                ex.printStackTrace();
            }
        } else {
            displayArea.setText("Nome não fornecido para pesquisa.");
        }
    }


    private void clearDisplay() {
        displayArea.setText("");
    }
}
