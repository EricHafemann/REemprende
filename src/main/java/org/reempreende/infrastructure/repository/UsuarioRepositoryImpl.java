package org.reempreende.infrastructure.repository;

import org.reempreende.domain.repository.UsuarioRepository;
import org.reempreende.infrastructure.config.ConnectionFactory;
import org.reempreende.infrastructure.exception.RepositoryException;
import org.reempreende.domain.entities.Usuario;
import org.reempreende.domain.entities.enums.Status;
import org.reempreende.domain.entities.enums.TipoUsuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsuarioRepositoryImpl implements UsuarioRepository {

    @Override
    public Usuario insert(Usuario usuario) {
        String sql = "INSERT INTO Usuarios (email, senha, nome, status, tipoUsuario) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, usuario.getEmail());
            stmt.setString(2, usuario.getSenha());
            stmt.setString(3, usuario.getNome());
            stmt.setInt(4, usuario.getStatus().getCodigo());
            stmt.setInt(5, usuario.getTipoUsuario().getCodigo());

            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    usuario.setId(generatedKeys.getLong(1));
                }
            }

            return usuario;

        } catch (SQLException e) {
            throw new RepositoryException("Erro ao inserir usuário");
        }
    }

    @Override
    public Optional<Usuario> findById(long id) {
        String sql = "SELECT * FROM Usuarios WHERE id = ?";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setLong(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapResultSetToUsuario(rs));
                }
            }

        } catch (SQLException e) {
            throw new RepositoryException("Erro ao buscar usuário por ID");
        }

        return Optional.empty();
    }

    @Override
    public List<Usuario> findAll() {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM Usuarios ORDER BY nome";

        try (Connection connection = ConnectionFactory.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                usuarios.add(mapResultSetToUsuario(rs));
            }

        } catch (SQLException e) {
            throw new RepositoryException("Erro ao buscar todos os usuários");
        }

        return usuarios;
    }


    @Override
    public Optional<Usuario> findByEmail(String email) {
        String sql = "SELECT * FROM Usuarios WHERE email = ?";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, email);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapResultSetToUsuario(rs));
                }
            }

        } catch (SQLException e) {
            throw new RepositoryException("Erro ao buscar usuário por email");
        }

        return Optional.empty();
    }

    @Override
    public boolean update(Usuario usuario) {
        String sql = "UPDATE Usuarios SET email = ?, senha = ?, nome = ?, status = ?, tipoUsuario = ? WHERE id = ?";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, usuario.getEmail());
            stmt.setString(2, usuario.getSenha());
            stmt.setString(3, usuario.getNome());
            stmt.setInt(4, usuario.getStatus().getCodigo());
            stmt.setInt(5, usuario.getTipoUsuario().getCodigo());
            stmt.setLong(6, usuario.getId());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RepositoryException("Erro ao atualizar usuário");
        }
    }

    @Override
    public boolean disable(long id) {
        String sql = "UPDATE Usuarios SET status = ? WHERE id = ?";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, Status.INATIVO.getCodigo());
            stmt.setLong(2, id);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RepositoryException("Erro ao desativar usuário");
        }
    }

    @Override
    public boolean enable(long id) {
        String sql = "UPDATE Usuarios SET status = ? WHERE id = ?";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, Status.ATIVO.getCodigo());
            stmt.setLong(2, id);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RepositoryException("Erro ao ativar usuário");
        }
    }

    @Override
    public boolean delete(long id) {
        String sql = "DELETE FROM Usuarios WHERE id = ?";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setLong(1, id);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RepositoryException("Erro ao deletar usuário");
        }
    }

    @Override
    public boolean existsByEmail(String email) {
        String sql = "SELECT 1 FROM Usuarios WHERE email = ?";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, email);

            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }

        } catch (SQLException e) {
            throw new RepositoryException("Erro ao verificar existência de email");
        }
    }

    @Override
    public boolean existsById(long id) {
        String sql = "SELECT 1 FROM Usuarios WHERE id = ?";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setLong(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }

        } catch (SQLException e) {
            throw new RepositoryException("Erro ao verificar existência de usuário");
        }
    }

    @Override
    public Optional<Usuario> login(String email, String senha) {
        String sql = "SELECT * FROM Usuarios WHERE email = ? AND senha = ? AND status = ?";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, email);
            stmt.setString(2, senha);
            stmt.setInt(3, Status.ATIVO.getCodigo());

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapResultSetToUsuario(rs));
                }
            }

        } catch (SQLException e) {
            throw new RepositoryException("Erro ao fazer login");
        }

        return Optional.empty();
    }

    private Usuario mapResultSetToUsuario(ResultSet rs) throws SQLException {
        long id = rs.getLong("id");
        String email = rs.getString("email");
        String senha = rs.getString("senha");
        String nome = rs.getString("nome");
        Status status = Status.fromCodigo(rs.getInt("status"));
        TipoUsuario tipo = TipoUsuario.fromCodigo(rs.getInt("tipoUsuario"));

        return new Usuario(id, email, senha, nome, status, tipo) {
            // Classe anônima só para instanciar o abstrato
        };
    }
}