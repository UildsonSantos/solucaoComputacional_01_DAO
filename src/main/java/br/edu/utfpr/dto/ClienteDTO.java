package br.edu.utfpr.dto;

import br.edu.utfpr.dto.PaisDTO;
import lombok.Builder;
import lombok.Data;
import br.edu.utfpr.excecao.NomeClienteMenor5CaracteresException;

@Data
@Builder
public class ClienteDTO {

    private int id;
    private String nome;
    private int idade;
    private String telefone;
    private double limiteCredito;
    private PaisDTO pais;

    public void setNome(String nome) throws NomeClienteMenor5CaracteresException {
        if (nome.length() < 5) {
            throw new NomeClienteMenor5CaracteresException(nome);
        }

        this.nome = nome;
    }

    public ClienteDTO getClienteData() {
        ClienteDTO clienteDTO = new ClienteDTO(id, nome, idade, telefone, limiteCredito, pais);
        clienteDTO.id = id;
        clienteDTO.nome = nome;
        clienteDTO.idade = idade;
        clienteDTO.telefone = telefone;
        clienteDTO.limiteCredito = limiteCredito;
        return clienteDTO;
    }

    public void setClienteData(ClienteDTO updateClienteDTO) {
        mergeClienteData(updateClienteDTO);
    }

    private void mergeClienteData(ClienteDTO updateClienteDTO) {
        id = updateClienteDTO.id;
        nome = updateClienteDTO.nome;
        idade = updateClienteDTO.idade;
        telefone = updateClienteDTO.telefone;
        limiteCredito = updateClienteDTO.limiteCredito;
    }
}
