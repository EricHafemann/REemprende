package org.reempreende.infrastructure.config;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

    public static Connection getConnection()  {
        Properties props = new Properties();
        try (InputStream input = ConnectionFactory.class.getClassLoader().getResourceAsStream("application.properties")) {
            props.load(input);
        } catch (IOException e) {
            System.out.println("Erro ao ler os arquivos do banco.");
        }

        String Url = props.getProperty("db.url");
        String User = props.getProperty("db.user");
        String Senha = props.getProperty("db.senha");
        try
        {
            return DriverManager.getConnection(Url, User, Senha);
        }
        catch (SQLException e)
        {
            System.err.println("Erro ao Conectar ao Banco de Dados. ");
            return null;
        }
    }
}