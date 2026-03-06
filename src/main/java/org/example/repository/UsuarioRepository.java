package org.example.repository;

import org.example.config.ConnectionFactory;
import org.example.model.Usuario;
import org.example.model.enums.Status;

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

    public void disableUsuario(long idUsuario)
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
            System.err.println("Erro na hora de desativar usuário: " + e);
        }
    }

    public Usuario findById(long id)
    {
        Usuario usuario = null;

        String querySql = "SELECT * FROM Usuarios WHERE id = ?";

        try (Connection connection = ConnectionFactory.getConnection(); PreparedStatement stmt = connection.prepareStatement(querySql))
        {
            stmt.setLong(1,id);

            try (ResultSet resultSet = stmt.executeQuery())
            {
                if (resultSet.next())
                {
                    long idUsuario = resultSet.getLong("id");
                    String senha = resultSet.getString("senha");
                    String email = resultSet.getString("email");
                    String nome = resultSet.getString("nome");
                    int status = resultSet.getInt("status");

                    usuario = new Usuario( id,  email,senha,  nome,  Status.fromCodigo(status));
                }
            }
        }

        catch (SQLException e)
        {
            System.err.println("Erro na hora de buscar usuario por id: " + e);
        }

        return usuario;
    }
}
