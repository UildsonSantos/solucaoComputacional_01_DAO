package br.edu.utfpr.dto;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class PaisDTO {
    private int id;
    private String nome;
    private String sigla;
    private int codigoTelefone;
    
    
    public PaisDTO getPaisData(){
        PaisDTO paisDTO = new PaisDTO(id,nome,sigla, codigoTelefone);
        paisDTO.id = id;
        paisDTO.nome = nome;
        paisDTO.sigla = sigla;
        paisDTO.codigoTelefone = codigoTelefone;
        
        return paisDTO;
    }
    
     public void setPaisData(PaisDTO updatePaisDTO) {
        mergePaisData(updatePaisDTO);
    }

    private void mergePaisData(PaisDTO updatePaisDTO) {
        id = updatePaisDTO.id;
        nome = updatePaisDTO.nome;
        sigla = updatePaisDTO.sigla;
        codigoTelefone = updatePaisDTO.codigoTelefone;
    }
}