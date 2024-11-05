package br.com.agenda.aplicacao;

import java.sql.Date;

import br.com.agenda.dao.ContatoDAO;
import br.com.agenda.model.Contato;

public class Main {

    public static void main(String[] args) throws Exception {
        ContatoDAO contatoDAO = new ContatoDAO();

       //Para salvar o contato
        Contato contato = new Contato();
        contato.setNome("Gustavo Nunes");
        contato.setIdade(22);
        contato.setDatacadastro(new Date(System.currentTimeMillis()));
        
        contatoDAO.save(contato); 
        System.out.println("Contato salvo: " + contato.getNome());

        //Para atualizar o contato (verificar o ID no MySQL)
        contato.setId(1); 
        contato.setNome("Gustavo Nunes"); 
        contato.setIdade(22); 
        
        contatoDAO.update(contato);
        
        //Deletando pelo ID
        contatoDAO.deleteByID(0);

       
        System.out.println("Lista de contatos:");
        for (Contato c : contatoDAO.getContatos()) {
            System.out.println("Contato: " + c.getNome());
        }
    }
}
