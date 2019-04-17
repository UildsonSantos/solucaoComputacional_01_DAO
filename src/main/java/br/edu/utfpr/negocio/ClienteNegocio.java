package br.edu.utfpr.negocio;

import br.edu.utfpr.dao.ClienteDAO;
import java.util.List;

import br.edu.utfpr.dto.ClienteDTO;
import br.edu.utfpr.excecao.NomeClienteJaExisteException;
import java.sql.PreparedStatement;

public class ClienteNegocio {

    public void incluir(ClienteDTO cliente) throws NomeClienteJaExisteException {

        if (this.listar().stream().anyMatch(c -> c.getNome().equalsIgnoreCase(cliente.getNome()))) {
            throw new NomeClienteJaExisteException(cliente.getNome());
        }
              

        // Chamar ClienteDAO para realizar persistência
        ClienteDAO clientedao = new ClienteDAO();
        clientedao.createCliente(cliente);
        
    }

    public List<ClienteDTO> listar() {
        
        // Usar ClienteDAO para retornar valores no banco
        ClienteDAO clientedao = new ClienteDAO();
        
        return clientedao.readCliente();
    }
}
