package org.reempreende.infrastucture.repository;

import org.reempreende.domain.repository.ComercianteRepository;
import org.reempreende.infrastucture.config.ConnectionFactory;
import org.reempreende.infrastucture.exception.RepositoryException;
import org.reempreende.domain.entities.Comerciante;
import org.reempreende.domain.entities.enums.Status;
import org.reempreende.domain.entities.enums.TipoUsuario;

import java.sql.*;
import java.util.Optional;

public class ComercianteRepositoryImpl implements ComercianteRepository {

    @Override
    public Comerciante insert(Comerciante comerciante) {
        String sql = "INSERT INTO Comerciantes (idComerciante, cnpj, senhaAcesso) VALUES (?, ?, ?)";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setLong(1, comerciante.getId());
            stmt.setString(2, comerciante.getCnpj());
            stmt.setString(3, comerciante.getSenhaAcesso());

            stmt.executeUpdate();
            return comerciante;

        } catch (SQLException e) {
            throw new RepositoryException("Erro ao inserir comerciante");
        }
    }

    @Override
    public Optional<Comerciante> findById(long id) {
        String sql = "SELECT co.*, u.* FROM Comerciantes co " +
                "INNER JOIN Usuarios u ON co.idComerciante = u.id " +
                "WHERE co.idComerciante = ?";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setLong(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapResultSetToComerciante(rs));
                }
            }

        } catch (SQLException e) {
            throw new RepositoryException("Erro ao buscar comerciante");
        }

        return Optional.empty();
    }

    @Override
    public boolean update(Comerciante comerciante) {
        String sql = "UPDATE Comerciantes SET cnpj = ?, senhaAcesso = ? WHERE idComerciante = ?";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, comerciante.getCnpj());
            stmt.setString(2, comerciante.getSenhaAcesso());
            stmt.setLong(3, comerciante.getId());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RepositoryException("Erro ao atualizar comerciante");
        }
    }

    @Override
    public boolean delete(long id) {
        String sql = "DELETE FROM Comerciantes WHERE idComerciante = ?";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setLong(1, id);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RepositoryException("Erro ao deletar comerciante");
        }
    }

    @Override
    public boolean existsByCnpj(String cnpj) {
        String sql = "SELECT 1 FROM Comerciantes WHERE cnpj = ?";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, cnpj);

            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }

        } catch (SQLException e) {
            throw new RepositoryException("Erro ao verificar existência de CNPJ");
        }
    }

    private Comerciante mapResultSetToComerciante(ResultSet rs) throws SQLException {
        return new Comerciante(
                rs.getLong("idComerciante"),
                rs.getString("email"),
                rs.getString("senha"),
                rs.getString("nome"),
                Status.fromCodigo(rs.getInt("status")),
                TipoUsuario.fromCodigo(rs.getInt("tipoUsuario")),
                rs.getString("cnpj"),
                rs.getString("senhaAcesso")
        );
    }
}