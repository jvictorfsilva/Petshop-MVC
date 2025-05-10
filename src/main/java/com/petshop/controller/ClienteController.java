package controller;

import model.Cliente;
import dao.ClienteDAO;

import java.sql.SQLException;
import java.util.List;

public class ClienteController {
    private ClienteDAO clienteDAO;

    public ClienteController(ClienteDAO clienteDAO) {
        this.clienteDAO = clienteDAO;
    }

    public List<Cliente> listarClientes() throws SQLException {
        return clienteDAO.listarClientes();
    }

    public void adicionarCliente(String nome, String cpf, String telefone) throws SQLException {
        Cliente cliente = new Cliente(0, nome, cpf, telefone); // ID será gerado automaticamente pelo banco
        clienteDAO.adicionarCliente(cliente);
    }

    public boolean existeCliente(int idCliente) throws SQLException {
        return clienteDAO.buscarClientePorId(idCliente) != null;
    }

    public String removerCliente(int idCliente) throws SQLException {
        if (existeCliente(idCliente)) {
            clienteDAO.removerCliente(idCliente);
            return "Cliente removido com sucesso.";
        } else {
            return "Erro: Cliente com ID " + idCliente + " não encontrado.";
        }
    }


    public Cliente pesquisarPorCpf(String cpf) throws SQLException {
        return clienteDAO.pesquisarPorCpf(cpf);
    }
    public List<Cliente> pesquisarPorNome(String nome) throws SQLException {
        return clienteDAO.pesquisarPorNome(nome);
    }

}
