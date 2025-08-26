package haras.negocio;

import haras.dados.RepositorioCliente;
import java.io.IOException;

import haras.basicas.Cliente;
import haras.exception.HarasException;
import java.util.ArrayList;
import java.util.List;

public class GerenciadorCliente {
    private RepositorioCliente repositorio = new RepositorioCliente();
    private List<Cliente> clientes;

    public GerenciadorCliente() {
        try {
            clientes = repositorio.carregarClientes();
        } catch (Exception e) {
            clientes = new ArrayList<>();
        }
    }

    private void salvar() {
        try {
            repositorio.salvarClientes(clientes);
        } catch (IOException e) {
            System.err.println("Erro ao salvar clientes: " + e.getMessage());
        }
    }

    public void cadastrarCliente(Cliente cliente) throws HarasException {
        if (cliente == null) {
            throw new HarasException("Cliente inválido.");
        }
        clientes.add(cliente);
        salvar();
    }

    public void editarCliente(Cliente cliente) throws HarasException {
        if (cliente == null) {
            throw new HarasException("Cliente inválido.");
        }
        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).getId() == cliente.getId()) {
                clientes.set(i, cliente);
                salvar();
                return;
            }
        }
        throw new HarasException("Cliente não encontrado.");
    }

    public boolean excluirCliente(int id) {
        boolean removido = clientes.removeIf(c -> c.getId() == id);
        if (removido)
            salvar();
        return removido;
    }

    public List<Cliente> listarClientes() {
        return clientes;
    }
}
