package org.example.repository;

import org.example.config.ConnectionFactory;
import org.example.model.Cliente;
import org.example.model.Comerciante;
import org.example.model.Usuario;
import org.example.model.enums.Status;
import org.example.model.enums.TipoUsuario;

import java.sql.*;

public class UsuarioRepository {

    public void inserirUsuario(Usuario usuario) {

        String sql = "INSERT INTO Usuario (email, senha, nome, status, tipoUsuario) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, usuario.getEmail());
            stmt.setString(2, usuario.getSenha());
            stmt.setString(3, usuario.getNome());
            stmt.setInt(4, usuario.getStatus().getCodigo());
            stmt.setInt(5, usuario.getTipoUsuario().getCodigo());

            int linhas = stmt.executeUpdate();

            if (linhas > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        usuario.setId(rs.getLong(1));
                    }
                }
            }

        } catch (SQLException e) {
            System.err.println("Erro ao inserir usuario !");
        }
    }

    public void disableUsuario(long idUsuario) {

        String sql = "UPDATE Usuario SET status = ? WHERE idUsuario = ?";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, Status.INATIVO.getCodigo());
            stmt.setLong(2, idUsuario);

            stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Erro ao desativar usuário !");
        }
    }

    public Usuario getById(long id) {

        Usuario usuario = null;

        String sql =
                "SELECT U.idUsuario, U.email, U.senha, U.nome, U.status, U.tipoUsuario, " +
                        "C.cpf, " +
                        "Co.cnpj, Co.senhaAcesso " +
                        "FROM Usuario U " +
                        "LEFT JOIN Cliente C ON U.idUsuario = C.idUsuario " +
                        "LEFT JOIN Comerciante Co ON U.idUsuario = Co.idUsuario " +
                        "WHERE U.idUsuario = ?";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setLong(1, id);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {

                    long idUsuario = rs.getLong("idUsuario");
                    String email = rs.getString("email");
                    String senha = rs.getString("senha");
                    String nome = rs.getString("nome");

                    Status status = Status.fromCodigo(rs.getInt("status"));
                    TipoUsuario tipo = TipoUsuario.fromCodigo(rs.getInt("tipoUsuario"));

                    switch (tipo) {

                        case CLIENTE -> {
                            String cpf = rs.getString("cpf");

                            usuario = new Cliente(
                                    idUsuario,
                                    email,
                                    senha,
                                    nome,
                                    status,
                                    tipo,
                                    cpf
                            );
                        }

                        case COMERCIANTE -> {

                            String cnpj = rs.getString("cnpj");
                            String senhaAcesso = rs.getString("senhaAcesso");

                            usuario = new Comerciante(
                                    idUsuario,
                                    email,
                                    senha,
                                    nome,
                                    status,
                                    tipo,
                                    cnpj,
                                    senhaAcesso
                            );
                        }
                    }
                }
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar usuario por id !");
        }

        return usuario;
    }
}