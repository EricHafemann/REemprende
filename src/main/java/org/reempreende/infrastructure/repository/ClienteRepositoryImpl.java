package org.reempreende.infrastructure.repository;

import org.reempreende.domain.repository.ClienteRepository;
import org.reempreende.infrastructure.config.ConnectionFactory;
import org.reempreende.infrastructure.exception.RepositoryException;
import org.reempreende.domain.entities.Cliente;
import org.reempreende.domain.entities.enums.Status;
import org.reempreende.domain.entities.enums.TipoUsuario;

import java.sql.*;
import java.util.Optional;

public class ClienteRepositoryImpl implements ClienteRepository {

    @Override
    public Cliente insert(Cliente cliente) {
        String sql = "INSERT INTO Clientes (idCliente, cpf) VALUES (?, ?)";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setLong(1, cliente.getId());
            stmt.setString(2, cliente.getCpf());

            stmt.executeUpdate();
            return cliente;

        } catch (SQLException e) {
            throw new RepositoryException("Erro ao inserir cliente");
        }
    }

    @Override
    public Optional<Cliente> findById(long id) {
        String sql = "SELECT c.*, u.* FROM Clientes c " +
                "INNER JOIN Usuarios u ON c.idCliente = u.id " +
                "WHERE c.idCliente = ?";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setLong(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapResultSetToCliente(rs));
                }
            }

        } catch (SQLException e) {
            throw new RepositoryException("Erro ao buscar cliente");
        }

        return Optional.empty();
    }

    @Override
    public boolean update(Cliente cliente) {
        String sql = "UPDATE Clientes SET cpf = ? WHERE idCliente = ?";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, cliente.getCpf());
            stmt.setLong(2, cliente.getId());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RepositoryException("Erro ao atualizar cliente");
        }
    }

    @Override
    public boolean delete(long id) {
        String sql = "DELETE FROM Clientes WHERE idCliente = ?";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setLong(1, id);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RepositoryException("Erro ao deletar cliente");
        }
    }

    @Override
    public boolean existsByCpf(String cpf) {
        String sql = "SELECT 1 FROM Clientes WHERE cpf = ?";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, cpf);

            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }

        } catch (SQLException e) {
            throw new RepositoryException("Erro ao verificar existência de CPF");
        }
    }

    private Cliente mapResultSetToCliente(ResultSet rs) throws SQLException {
        return new Cliente(
                rs.getLong("idCliente"),
                rs.getString("email"),
                rs.getString("senha"),
                rs.getString("nome"),
                Status.fromCodigo(rs.getInt("status")),
                TipoUsuario.fromCodigo(rs.getInt("tipoUsuario")),
                rs.getString("cpf")
        );
    }
}