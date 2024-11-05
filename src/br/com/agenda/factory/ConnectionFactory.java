package br.com.agenda.factory;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {

    private static final String USERNAME = "root";
    private static final String PASSWORD = "CRUD123@";
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/agenda";

    public static Connection createConnectionToMySQL() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver"); 
        Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
        return connection;
    }

    public static void main(String[] args) {
        try (Connection con = createConnectionToMySQL()) {
            if (con != null) {
                System.out.println("Conexão obtida com sucesso!");
            } else {
                System.out.println("Conexão falhou!");
            }
        } catch (Exception e) {
            System.out.println("Erro ao conectar com o banco de dados:");
            e.printStackTrace();
        }
    }
}
