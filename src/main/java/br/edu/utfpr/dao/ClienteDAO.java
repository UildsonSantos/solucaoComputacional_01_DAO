package br.edu.utfpr.dao;

import java.sql.Connection;
import java.sql.DriverManager;

import br.edu.utfpr.dto.ClienteDTO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import lombok.extern.java.Log;

@Log
public class ClienteDAO {

    // Responsável por criar a tabela Cliente no banco.
    public ClienteDAO() {

        try (Connection conn = DriverManager.getConnection("jdbc:derby:memory:database;create=true")) {

            log.info("Criando tabela cliente ...");
            conn.createStatement().executeUpdate(
                    "CREATE TABLE cliente ("
                    + "id int NOT NULL GENERATED ALWAYS AS IDENTITY CONSTRAINT id_cliente_pk PRIMARY KEY,"
                    + "nome varchar(255),"
                    + "telefone varchar(30),"
                    + "idade int,"
                    + "limiteCredito double,"
                    + "id_pais int)");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String createCliente(ClienteDTO clienteDTO) {

        String clienteID = null;
        try (Connection conn = DriverManager.getConnection("jdbc:derby:memory:database;create=true")) {

            PreparedStatement prepStmt = null;
            log.info("Conectando na tabela cliente ...");

            String query = "insert into cliente (id, nome, telefone, idade, limiteCredito, id_pais) values (?, ?, ?, ?, ?, ?)";
            prepStmt = conn.prepareStatement(query);
            int i = 1;
            prepStmt.setInt(i++, clienteDTO.getId());
            prepStmt.setString(i++, clienteDTO.getNome());
            prepStmt.setString(i++, clienteDTO.getTelefone());
            prepStmt.setInt(i++, clienteDTO.getIdade());
            prepStmt.setDouble(i++, clienteDTO.getLimiteCredito());
            prepStmt.setString(i++, clienteDTO.getPais().getSigla());

            int rowsInserted = prepStmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new user was inserted successfully!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return clienteID;
    }

    public List<ClienteDTO> readCliente() {
        
        List<ClienteDTO> clienteDTO = null;
        PreparedStatement prepStmt = null;
        ResultSet rs = null;

        try (Connection conn = DriverManager.getConnection("jdbc:derby:memory:database;create=true")) {

            log.info("Conectando na tabela cliente ...");
            String query = "select * from cliente";
            prepStmt = conn.prepareStatement(query);
            
            rs = prepStmt.executeQuery(query);

            int count = 0;

            while (rs.next()) {
                clienteDTO.get(count).getClienteData();
                
                clienteDTO.get(count).setId(rs.getInt(1));
                clienteDTO.get(count).setNome(rs.getString(2));
                clienteDTO.get(count).setTelefone(rs.getString(3));
                clienteDTO.get(count).setIdade(rs.getInt(4));
                clienteDTO.get(count).setLimiteCredito(rs.getDouble(5));
                clienteDTO.get(count).getPais().setSigla(rs.getString(6));
                count++;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return clienteDTO;
    }

    public void updateCliente(ClienteDTO cliente) {

        PreparedStatement prepStmt = null;
        try (Connection conn = DriverManager.getConnection("jdbc:derby:memory:database;create=true")) {

            log.info("Conectando na tabela cliente ...");
            
            String query = "update cliente set id=?, name=?, telefone=?, idade=?, limiteCredito=?, id_pais=? WHERE id=?";
            prepStmt = conn.prepareStatement(query);
            int i = 1;
            prepStmt.setInt(i++, cliente.getId());
            prepStmt.setString(i++, cliente.getNome());
            prepStmt.setString(i++, cliente.getTelefone());
            prepStmt.setInt(i++, cliente.getIdade());
            prepStmt.setDouble(i++, cliente.getLimiteCredito());
            prepStmt.setString(i++, cliente.getPais().getSigla());

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

            log.info("Conectando na tabela cliente ...");
            String query = "delete * from cliente where id = ?";
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
