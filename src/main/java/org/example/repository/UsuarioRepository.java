package org.example.repository;

import org.example.config.ConnectionFactory;
import org.example.model.Usuario;

import java.sql.*;

public class UsuarioRepository
{
    public void inserirUsuario(Usuario usuario)
    {
        String querySql = "INSERT INTO Usuario (email,senha,nome) VALUES (?,?,?)";

        try (Connection connection = ConnectionFactory.getConnection(); PreparedStatement stmt = connection.prepareStatement(querySql, Statement.RETURN_GENERATED_KEYS))
        {
            stmt.setString(1, "email");
            stmt.setString(2, "senha");
            stmt.setString(3, "nome");


            int numLinhas = stmt.executeUpdate();

            if (numLinhas > 0)
            {
                try (ResultSet resultSet = stmt.getGeneratedKeys())
                {
                    if (resultSet.next())
                    {
                        long idGerado = resultSet.getLong(1);
                        usuario.setId(idGerado);
                    }
                }
            }
        }

        catch (SQLException e)
        {
            System.err.println("Erro na hora de inserir usuario ao banco: " + e);
        }
    }

}
