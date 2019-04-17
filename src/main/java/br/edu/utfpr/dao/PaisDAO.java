package br.edu.utfpr.dao;


import java.sql.Connection;
import java.sql.DriverManager;

import br.edu.utfpr.dto.PaisDTO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import lombok.extern.java.Log;

@Log
public class PaisDAO {

    // Responsável por criar a tabela País no banco
    public PaisDAO() {
        try (Connection conn = DriverManager.getConnection("jdbc:derby:memory:database;create=true")) {

            log.info("Criando tabela pais ...");
            conn.createStatement().executeUpdate(
            "CREATE TABLE pais (" +
						"id int NOT NULL GENERATED ALWAYS AS IDENTITY CONSTRAINT id_pais_pk PRIMARY KEY," +
						"nome varchar(255)," +
						"sigla varchar(3)," + 
						"codigoTelefone int)");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
     public String createPais(PaisDTO paisDTO) {

        String paisID = null;
        try (Connection conn = DriverManager.getConnection("jdbc:derby:memory:database;create=true")) {

            PreparedStatement prepStmt = null;
            log.info("Conectando na tabela pais ...");

            String query = "insert into pais (id, nome, sigla, codigoTelefone) values (?, ?, ?, ?)";
            prepStmt = conn.prepareStatement(query);
            int i = 1;
            prepStmt.setInt(i++, paisDTO.getId());
            prepStmt.setString(i++, paisDTO.getNome());
            prepStmt.setString(i++, paisDTO.getSigla());
            prepStmt.setInt(i++, paisDTO.getCodigoTelefone());
            

            int rowsInserted = prepStmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new user was inserted successfully!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return paisID;
    }

    public List<PaisDTO> readPais() {
        
        List<PaisDTO> paisDTO = null;
        PreparedStatement prepStmt = null;
        ResultSet rs = null;

        try (Connection conn = DriverManager.getConnection("jdbc:derby:memory:database;create=true")) {

            log.info("Conectando na tabela Pais...");
            String query = "select * from pais";
            prepStmt = conn.prepareStatement(query);
            
            rs = prepStmt.executeQuery(query);

            int count = 0;

            while (rs.next()) {
                paisDTO.get(count).getPaisData();
                
                paisDTO.get(count).setId(rs.getInt(1));
                paisDTO.get(count).setNome(rs.getString(2));
                paisDTO.get(count).setSigla(rs.getString(3));
                paisDTO.get(count).setCodigoTelefone(rs.getInt(4));
                
                count++;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return paisDTO;
    }

    public void updatePais(PaisDTO pais) {

        PreparedStatement prepStmt = null;
        try (Connection conn = DriverManager.getConnection("jdbc:derby:memory:database;create=true")) {

            log.info("Conectando na tabela pais ...");
            
            String query = "update pais set id=?, name=?, sigla=?, codigoTelefone=? WHERE id=?";
            prepStmt = conn.prepareStatement(query);
            int i = 1;
            prepStmt.setInt(i++, pais.getId());
            prepStmt.setString(i++, pais.getNome());
            prepStmt.setString(i++, pais.getSigla());
            prepStmt.setInt(i++, pais.getCodigoTelefone());
            

            int rowsInserted = prepStmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("An existing user was updated successfully!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteCliente(int clienteID) {
        PreparedStatement prepStmt = null;
        try (Connection conn = DriverManager.getConnection("jdbc:derby:memory:database;create=true")) {

            log.info("Conectando na tabela Pais ...");
            String query = "delete * from pais where id = ?";
            prepStmt = conn.prepareStatement(query);
            prepStmt.setInt(1, clienteID);

            int rowsDeleted= prepStmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("A user was deleted successfully!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}