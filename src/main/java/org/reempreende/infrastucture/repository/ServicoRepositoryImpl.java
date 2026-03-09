package org.reempreende.infrastucture.repository;

import org.reempreende.domain.repository.ServicoRepository;
import org.reempreende.domain.entities.Servico;
import org.reempreende.domain.entities.Comerciante;
import org.reempreende.domain.entities.enums.Status;
import org.reempreende.domain.entities.enums.TipoUsuario;
import org.reempreende.infrastucture.config.ConnectionFactory;
import org.reempreende.infrastucture.exception.RepositoryException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ServicoRepositoryImpl implements ServicoRepository {

    @Override
    public Servico insert(Servico servico) {
        String sql = "INSERT INTO Servicos (avaliacao, descricao, duracaoHoras, idComerciante) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = ConnectionFactory.getConnection()
                .prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, servico.getAvaliacao());
            stmt.setString(2, servico.getDescricao());
            stmt.setDouble(3, servico.getDuracaoHoras());
            stmt.setLong(4, servico.getComerciante().getId());

            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    servico.setIdServico(generatedKeys.getLong(1));
                }
            }

            return servico;
        } catch (SQLException e) {
            throw new RepositoryException("Erro ao inserir serviço");
        }
    }

    @Override
    public Optional<Servico> findById(long id) {
        String sql = "SELECT s.*, u.*, c.* " +
                "FROM Servicos s " +
                "INNER JOIN Comerciantes c ON s.idComerciante = c.idComerciante " +
                "INNER JOIN Usuarios u ON c.idComerciante = u.id " +
                "WHERE s.idServico = ?";

        try (PreparedStatement stmt = ConnectionFactory.getConnection()
                .prepareStatement(sql)) {
            stmt.setLong(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapResultSetToServico(rs));
                }
            }
        } catch (SQLException e) {
            throw new RepositoryException("Erro ao buscar serviço");
        }
        return Optional.empty();
    }

    @Override
    public List<Servico> findAll() {
        List<Servico> servicos = new ArrayList<>();
        String sql = "SELECT s.*, u.*, c.* " +
                "FROM Servicos s " +
                "INNER JOIN Comerciantes c ON s.idComerciante = c.idComerciante " +
                "INNER JOIN Usuarios u ON c.idComerciante = u.id " +
                "ORDER BY s.idServico";

        try (Statement stmt = ConnectionFactory.getConnection().createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                servicos.add(mapResultSetToServico(rs));
            }
        } catch (SQLException e) {
            throw new RepositoryException("Erro ao buscar serviços");
        }
        return servicos;
    }

    @Override
    public boolean update(Servico servico) {
        String sql = "UPDATE Servicos SET avaliacao = ?, descricao = ?, duracaoHoras = ?, idComerciante = ? " +
                "WHERE idServico = ?";

        try (PreparedStatement stmt = ConnectionFactory.getConnection()
                .prepareStatement(sql)) {
            stmt.setString(1, servico.getAvaliacao());
            stmt.setString(2, servico.getDescricao());
            stmt.setDouble(3, servico.getDuracaoHoras());
            stmt.setLong(4, servico.getComerciante().getId());
            stmt.setLong(5, servico.getIdServico());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RepositoryException("Erro ao atualizar serviço");
        }
    }

    @Override
    public boolean delete(long id) {
        String sql = "DELETE FROM Servicos WHERE idServico = ?";

        try (PreparedStatement stmt = ConnectionFactory.getConnection()
                .prepareStatement(sql)) {
            stmt.setLong(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RepositoryException("Erro ao deletar serviço");
        }
    }

    @Override
    public List<Servico> findByComercianteId(long comercianteId) {
        List<Servico> servicos = new ArrayList<>();
        String sql = "SELECT s.*, u.*, c.* " +
                "FROM Servicos s " +
                "INNER JOIN Comerciantes c ON s.idComerciante = c.idComerciante " +
                "INNER JOIN Usuarios u ON c.idComerciante = u.id " +
                "WHERE s.idComerciante = ? " +
                "ORDER BY s.idServico";

        try (PreparedStatement stmt = ConnectionFactory.getConnection()
                .prepareStatement(sql)) {
            stmt.setLong(1, comercianteId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    servicos.add(mapResultSetToServico(rs));
                }
            }
        } catch (SQLException e) {
            throw new RepositoryException("Erro ao buscar serviços do comerciante");
        }
        return servicos;
    }

    @Override
    public long count() {
        String sql = "SELECT COUNT(*) FROM Servicos";

        try (Statement stmt = ConnectionFactory.getConnection()
                .createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getLong(1);
            }
        } catch (SQLException e) {
            throw new RepositoryException("Erro ao contar serviços");
        }
        return 0;
    }

    @Override
    public long countByComercianteId(long comercianteId) {
        String sql = "SELECT COUNT(*) FROM Servicos WHERE idComerciante = ?";

        try (PreparedStatement stmt = ConnectionFactory.getConnection()
                .prepareStatement(sql)) {
            stmt.setLong(1, comercianteId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getLong(1);
                }
            }
        } catch (SQLException e) {
            throw new RepositoryException("Erro ao contar serviços do comerciante");
        }
        return 0;
    }

    @Override
    public boolean existsById(long id) {
        String sql = "SELECT 1 FROM Servicos WHERE idServico = ?";

        try (PreparedStatement stmt = ConnectionFactory.getConnection()
                .prepareStatement(sql)) {
            stmt.setLong(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            throw new RepositoryException("Erro ao verificar existência do serviço");
        }
    }

    private Servico mapResultSetToServico(ResultSet rs) throws SQLException {
        Comerciante comerciante = new Comerciante(
                rs.getLong("idComerciante"),
                rs.getString("email"),
                rs.getString("senha"),
                rs.getString("nome"),
                Status.fromCodigo(rs.getInt("status")),
                TipoUsuario.fromCodigo(rs.getInt("tipoUsuario")),
                rs.getString("cnpj"),
                rs.getString("senhaAcesso")
        );

        return new Servico(
                rs.getLong("idServico"),
                rs.getString("avaliacao"),
                rs.getString("descricao"),
                rs.getDouble("duracaoHoras"),
                comerciante
        );
    }
}