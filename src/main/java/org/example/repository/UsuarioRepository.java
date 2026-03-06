package org.example.repository;

import org.example.config.ConnectionFactory;
import org.example.model.Usuario;

import java.sql.*;

public class UsuarioRepository
{
    public void inserirUsuario(Usuario usuario)
    {
        String querySql = "INSERT INTO Usuario (email,senha,nome,status) VALUES (?,?,?,?)";

        try (Connection connection = ConnectionFactory.getConnection(); PreparedStatement stmt = connection.prepareStatement(querySql, Statement.RETURN_GENERATED_KEYS))
        {
            stmt.setString(1, usuario.getEmail());
            stmt.setString(2, usuario.getSenha());
            stmt.setString(3, usuario.getNome());
            stmt.setInt(4, usuario.getStatus().getCodigo());

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

    public void desatitvarUsuario(long idUsuario)
    {
        String querySql = "UPDATE Usuario SET status = ? WHERE id = ?";

        try (Connection connection = ConnectionFactory.getConnection();PreparedStatement stmt = connection.prepareStatement(querySql))
        {
            stmt.setInt(1,4);
            stmt.setLong(2,idUsuario);
            stmt.executeUpdate();
        }

        catch (SQLException e)
        {
            System.err.println("Erro na hora de destativar usuário: " + e);
        }
    }
}
