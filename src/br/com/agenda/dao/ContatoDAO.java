package br.com.agenda.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.agenda.factory.ConnectionFactory;
import br.com.agenda.model.Contato;

public class ContatoDAO {

    
    public void save(Contato contato) throws Exception {
        if (contatoExists(contato.getNome())) {
            System.out.println("Contato já existe!");
            return;
        }

        String sql = "INSERT INTO contatos(nome, idade, datacadastro) VALUES (?, ?, ?)";
        
        try (Connection conn = ConnectionFactory.createConnectionToMySQL();
             PreparedStatement pstm = conn.prepareStatement(sql)) {
            
            pstm.setString(1, contato.getNome());
            pstm.setInt(2, contato.getIdade());
            pstm.setDate(3, new Date(contato.getDatacadastro().getTime()));
            
            pstm.execute();
            System.out.println("Contato salvo com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
    public boolean contatoExists(String nome) throws Exception {
        String sql = "SELECT COUNT(*) FROM contatos WHERE nome = ?";
        try (Connection conn = ConnectionFactory.createConnectionToMySQL();
             PreparedStatement pstm = conn.prepareStatement(sql)) {
            
            pstm.setString(1, nome);
            ResultSet rset = pstm.executeQuery();
            rset.next();
            return rset.getInt(1) > 0; 
        }
    }

    
    public void update(Contato contato) throws Exception {
        String sql = "UPDATE contatos SET nome = ?, idade = ?, datacadastro = ? WHERE id = ?";
        
        try (Connection conn = ConnectionFactory.createConnectionToMySQL();
             PreparedStatement pstm = conn.prepareStatement(sql)) {
            
            pstm.setString(1, contato.getNome());
            pstm.setInt(2, contato.getIdade());
            pstm.setDate(3, new Date(contato.getDatacadastro().getTime()));
            pstm.setInt(4, contato.getId()); 
            
            int rowsAffected = pstm.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Contato atualizado com sucesso!");
            } else {
                System.out.println("Nenhum contato encontrado para atualizar.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void deleteByID(int id) {
    	
    	String sql = "DELETE FROM contatos WHERE id = ?";
    	
    	Connection conn = null;
    	
    	PreparedStatement pstm = null;
    	
    	try {
    		conn = ConnectionFactory.createConnectionToMySQL();
    		
    		pstm = conn.prepareStatement(sql);
    		
    		pstm.setInt(1, id);
    		
    		pstm.execute();
    		
    	}catch (Exception e) {
    		e.printStackTrace();
    	}finally {
    		try {
    			if(pstm != null) {
    				pstm.close();
    			}
    			
    			if(conn != null) {
    				conn.close();
    			}
    		}catch (Exception e) {
    			e.printStackTrace();
    		}
    	}
    	
    }
    
    
    public List<Contato> getContatos() throws Exception {
        String sql = "SELECT * FROM contatos";
        List<Contato> contatos = new ArrayList<>();
        
        try (Connection conn = ConnectionFactory.createConnectionToMySQL();
             PreparedStatement pstm = conn.prepareStatement(sql);
             ResultSet rset = pstm.executeQuery()) {
            
            while (rset.next()) {
                Contato contato = new Contato();
                contato.setId(rset.getInt("id"));
                contato.setNome(rset.getString("nome")); 
                contato.setIdade(rset.getInt("idade"));
                contato.setDatacadastro(rset.getDate("datacadastro"));
                contatos.add(contato);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return contatos;
    }
}
