package haras.negocio;

import haras.basicas.Cliente;
import haras.exception.HarasException;
import java.util.ArrayList;
import java.util.List;

public class GerenciadorCliente {
    private List<Cliente> clientes = new ArrayList<>();

    public void cadastrarCliente(Cliente cliente) throws HarasException {
        if (cliente == null) {
            throw new HarasException("Cliente inválido.");
        }
        clientes.add(cliente);
    }

    public void editarCliente(Cliente cliente) throws HarasException {
        if (cliente == null) {
            throw new HarasException("Cliente inválido.");
        }
        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).getId() == cliente.getId()) {
                clientes.set(i, cliente);
                return;
            }
        }
        throw new HarasException("Cliente não encontrado.");
    }

    public boolean excluirCliente(int id) {
        return clientes.removeIf(c -> c.getId() == id);
    }

    public List<Cliente> listarClientes() {
        return clientes;
    }
}
