package org.reempreende.infrastructure.repository;

import org.reempreende.domain.repository.AgendamentoRepository;
import org.reempreende.domain.entities.Agendamento;
import org.reempreende.domain.entities.Cliente;
import org.reempreende.domain.entities.enums.Status;
import org.reempreende.domain.entities.enums.TipoUsuario;
import org.reempreende.infrastructure.config.ConnectionFactory;
import org.reempreende.infrastructure.exception.RepositoryException;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AgendamentoRepositoryImpl implements AgendamentoRepository {

    @Override
    public Agendamento insert(Agendamento agendamento) {
        String sql = "INSERT INTO Agendamentos (dataInicio, dataFim, observacao, idCliente) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = ConnectionFactory.getConnection()
                .prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setTimestamp(1, Timestamp.valueOf(agendamento.getDataInicio()));
            stmt.setTimestamp(2, Timestamp.valueOf(agendamento.getDataFim()));
            stmt.setString(3, agendamento.getObservacao());

            if (agendamento.getCliente() != null) {
                stmt.setLong(4, agendamento.getCliente().getId());
            } else {
                stmt.setNull(4, java.sql.Types.BIGINT);
            }

            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    agendamento.setIdAgendamento(generatedKeys.getLong(1));
                }
            }

            return agendamento;
        } catch (SQLException e) {
            throw new RepositoryException("Erro ao inserir agendamento: " + e.getMessage());
        }
    }

    @Override
    public boolean existsById(Long id) {
        String sql = "SELECT 1 FROM Agendamentos WHERE idAgendamento = ?";

        try (PreparedStatement stmt = ConnectionFactory.getConnection().prepareStatement(sql)) {
            stmt.setLong(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            throw new RepositoryException("Erro ao verificar existência do agendamento: " + e.getMessage());
        }
    }

    @Override
    public Optional<Agendamento> findById(long id) {
        String sql = "SELECT a.idAgendamento, a.dataInicio, a.dataFim, a.observacao, a.idCliente, " +
                "u.id, u.email, u.senha, u.nome, u.status, u.tipoUsuario, " +
                "c.cpf " +
                "FROM Agendamentos a " +
                "LEFT JOIN Clientes c ON a.idCliente = c.idCliente " +
                "LEFT JOIN Usuarios u ON c.idCliente = u.id " +
                "WHERE a.idAgendamento = ?";

        try (PreparedStatement stmt = ConnectionFactory.getConnection().prepareStatement(sql)) {
            stmt.setLong(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapResultSetToAgendamento(rs));
                }
            }
        } catch (SQLException e) {
            throw new RepositoryException("Erro ao buscar agendamento por ID: " + e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public List<Agendamento> findAll() {
        List<Agendamento> agendamentos = new ArrayList<>();
        String sql = "SELECT a.idAgendamento, a.dataInicio, a.dataFim, a.observacao, a.idCliente, " +
                "u.id, u.email, u.senha, u.nome, u.status, u.tipoUsuario, " +
                "c.cpf " +
                "FROM Agendamentos a " +
                "LEFT JOIN Clientes c ON a.idCliente = c.idCliente " +
                "LEFT JOIN Usuarios u ON c.idCliente = u.id " +
                "ORDER BY a.dataInicio DESC";

        try (Statement stmt = ConnectionFactory.getConnection().createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                agendamentos.add(mapResultSetToAgendamento(rs));
            }
        } catch (SQLException e) {
            throw new RepositoryException("Erro ao buscar todos os agendamentos: " + e.getMessage());
        }
        return agendamentos;
    }

    @Override
    public boolean update(Agendamento agendamento) {
        String sql = "UPDATE Agendamentos SET dataInicio = ?, dataFim = ?, observacao = ?, idCliente = ? " +
                "WHERE idAgendamento = ?";

        try (PreparedStatement stmt = ConnectionFactory.getConnection().prepareStatement(sql)) {
            stmt.setTimestamp(1, Timestamp.valueOf(agendamento.getDataInicio()));
            stmt.setTimestamp(2, Timestamp.valueOf(agendamento.getDataFim()));
            stmt.setString(3, agendamento.getObservacao());

            if (agendamento.getCliente() != null) {
                stmt.setLong(4, agendamento.getCliente().getId());
            } else {
                stmt.setNull(4, Types.BIGINT);
            }

            stmt.setLong(5, agendamento.getIdAgendamento());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RepositoryException("Erro ao atualizar agendamento: " + e.getMessage());
        }
    }

    @Override
    public boolean delete(long id) {
        String sql = "DELETE FROM Agendamentos WHERE idAgendamento = ?";

        try (PreparedStatement stmt = ConnectionFactory.getConnection().prepareStatement(sql)) {
            stmt.setLong(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RepositoryException("Erro ao deletar agendamento: " + e.getMessage());
        }
    }

    @Override
    public boolean deleteAgendamentoByClienteId(Long idAgendamento) {
        String sql = "DELETE FROM Agendamentos WHERE idCliente = ?";

        try (PreparedStatement stmt = ConnectionFactory.getConnection().prepareStatement(sql)) {
            stmt.setLong(1, idAgendamento);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RepositoryException("Erro ao deletar agendamento por cliente: " + e.getMessage());
        }
    }

    @Override
    public List<Agendamento> findByClientId(long clientId) {
        List<Agendamento> agendamentos = new ArrayList<>();
        String sql = "SELECT a.idAgendamento, a.dataInicio, a.dataFim, a.observacao, a.idCliente, " +
                "u.id, u.email, u.senha, u.nome, u.status, u.tipoUsuario, " +
                "c.cpf " +
                "FROM Agendamentos a " +
                "LEFT JOIN Clientes c ON a.idCliente = c.idCliente " +
                "LEFT JOIN Usuarios u ON c.idCliente = u.id " +
                "WHERE a.idCliente = ? " +
                "ORDER BY a.dataInicio DESC";

        try (PreparedStatement stmt = ConnectionFactory.getConnection().prepareStatement(sql)) {
            stmt.setLong(1, clientId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    agendamentos.add(mapResultSetToAgendamento(rs));
                }
            }
        } catch (SQLException e) {
            throw new RepositoryException("Erro ao buscar agendamentos do cliente: " + e.getMessage());
        }
        return agendamentos;
    }

    @Override
    public List<Agendamento> findByDate(LocalDateTime date) {
        List<Agendamento> agendamentos = new ArrayList<>();
        String sql = "SELECT a.idAgendamento, a.dataInicio, a.dataFim, a.observacao, a.idCliente, " +
                "u.id, u.email, u.senha, u.nome, u.status, u.tipoUsuario, " +
                "c.cpf " +
                "FROM Agendamentos a " +
                "LEFT JOIN Clientes c ON a.idCliente = c.idCliente " +
                "LEFT JOIN Usuarios u ON c.idCliente = u.id " +
                "WHERE DATE(a.dataInicio) = DATE(?) " +
                "ORDER BY a.dataInicio";

        try (PreparedStatement stmt = ConnectionFactory.getConnection().prepareStatement(sql)) {
            stmt.setTimestamp(1, Timestamp.valueOf(date));

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    agendamentos.add(mapResultSetToAgendamento(rs));
                }
            }
        } catch (SQLException e) {
            throw new RepositoryException("Erro ao buscar agendamentos por data: " + e.getMessage());
        }
        return agendamentos;
    }

    @Override
    public boolean isAvailable(LocalDateTime startTime, LocalDateTime endTime) {
        String sql = "SELECT COUNT(*) FROM Agendamentos WHERE " +
                "NOT (dataFim <= ? OR dataInicio >= ?)";

        try (PreparedStatement stmt = ConnectionFactory.getConnection().prepareStatement(sql)) {
            stmt.setTimestamp(1, Timestamp.valueOf(startTime));
            stmt.setTimestamp(2, Timestamp.valueOf(endTime));

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) == 0;
                }
            }
        } catch (SQLException e) {
            throw new RepositoryException("Erro ao verificar disponibilidade: " + e.getMessage());
        }
        return false;
    }

    @Override
    public long countByClientId(long clientId) {
        String sql = "SELECT COUNT(*) FROM Agendamentos WHERE idCliente = ?";

        try (PreparedStatement stmt = ConnectionFactory.getConnection().prepareStatement(sql)) {
            stmt.setLong(1, clientId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getLong(1);
                }
            }
        } catch (SQLException e) {
            throw new RepositoryException("Erro ao contar agendamentos do cliente: " + e.getMessage());
        }
        return 0;
    }

    @Override
    public List<Agendamento> findUpcoming() {
        List<Agendamento> agendamentos = new ArrayList<>();
        String sql = "SELECT a.idAgendamento, a.dataInicio, a.dataFim, a.observacao, a.idCliente, " +
                "u.id, u.email, u.senha, u.nome, u.status, u.tipoUsuario, " +
                "c.cpf " +
                "FROM Agendamentos a " +
                "LEFT JOIN Clientes c ON a.idCliente = c.idCliente " +
                "LEFT JOIN Usuarios u ON c.idCliente = u.id " +
                "WHERE a.dataInicio > NOW() " +
                "ORDER BY a.dataInicio";

        try (Statement stmt = ConnectionFactory.getConnection().createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                agendamentos.add(mapResultSetToAgendamento(rs));
            }
        } catch (SQLException e) {
            throw new RepositoryException("Erro ao buscar agendamentos futuros: " + e.getMessage());
        }
        return agendamentos;
    }

    @Override
    public List<Agendamento> findPast() {
        List<Agendamento> agendamentos = new ArrayList<>();
        String sql = "SELECT a.idAgendamento, a.dataInicio, a.dataFim, a.observacao, a.idCliente, " +
                "u.id, u.email, u.senha, u.nome, u.status, u.tipoUsuario, " +
                "c.cpf " +
                "FROM Agendamentos a " +
                "LEFT JOIN Clientes c ON a.idCliente = c.idCliente " +
                "LEFT JOIN Usuarios u ON c.idCliente = u.id " +
                "WHERE a.dataFim < NOW() " +
                "ORDER BY a.dataInicio DESC";

        try (Statement stmt = ConnectionFactory.getConnection().createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                agendamentos.add(mapResultSetToAgendamento(rs));
            }
        } catch (SQLException e) {
            throw new RepositoryException("Erro ao buscar agendamentos passados: " + e.getMessage());
        }
        return agendamentos;
    }

    private Agendamento mapResultSetToAgendamento(ResultSet rs) throws SQLException {
        Cliente cliente = null;

        long idCliente = rs.getLong("idCliente");

        if (idCliente > 0) {
            int statusCodigo = rs.getInt("status");
            int tipoUsuarioCodigo = rs.getInt("tipoUsuario");

            cliente = new Cliente(
                    idCliente,
                    rs.getString("email") != null ? rs.getString("email") : "",
                    rs.getString("senha") != null ? rs.getString("senha") : "",
                    rs.getString("nome") != null ? rs.getString("nome") : "",
                    Status.fromCodigo(statusCodigo),
                    TipoUsuario.fromCodigo(tipoUsuarioCodigo),
                    rs.getString("cpf") != null ? rs.getString("cpf") : ""
            );
        }

        return new Agendamento(
                rs.getLong("idAgendamento"),
                rs.getTimestamp("dataInicio").toLocalDateTime(),
                rs.getTimestamp("dataFim").toLocalDateTime(),
                rs.getString("observacao") != null ? rs.getString("observacao") : "",
                cliente
        );
    }
}