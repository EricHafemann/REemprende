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
            stmt.setLong(4, agendamento.getCliente().getId());

            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    agendamento.setIdAgendamento(generatedKeys.getLong(1));
                }
            }

            return agendamento;
        } catch (SQLException e) {
            throw new RepositoryException("Erro ao inserir agendamento");
        }
    }

    @Override
    public boolean existsById(Long id) {
        String sql = "SELECT 1 FROM Agendamentos WHERE idAgendamento = ?";

        try (PreparedStatement stmt = ConnectionFactory.getConnection()
                .prepareStatement(sql)) {

            stmt.setLong(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            throw new RepositoryException("Erro ao verificar existência do agendamento");
        }
    }

    @Override
    public Optional<Agendamento> findById(long id) {
        String sql = "SELECT a.*, u.*, c.* " +
                "FROM Agendamentos a " +
                "INNER JOIN Clientes c ON a.idCliente = c.idCliente " +
                "INNER JOIN Usuarios u ON c.idCliente = u.id " +
                "WHERE a.idAgendamento = ?";

        try (PreparedStatement stmt = ConnectionFactory.getConnection()
                .prepareStatement(sql)) {
            stmt.setLong(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapResultSetToAgendamento(rs));
                }
            }
        } catch (SQLException e) {
            throw new RepositoryException("Erro ao buscar agendamento");
        }
        return Optional.empty();
    }

    @Override
    public List<Agendamento> findAll() {
        List<Agendamento> agendamentos = new ArrayList<>();
        String sql = "SELECT a.*, u.*, c.* " +
                "FROM Agendamentos a " +
                "INNER JOIN Clientes c ON a.idCliente = c.idCliente " +
                "INNER JOIN Usuarios u ON c.idCliente = u.id " +
                "ORDER BY a.dataInicio DESC";

        try (Statement stmt = ConnectionFactory.getConnection().createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                agendamentos.add(mapResultSetToAgendamento(rs));
            }
        } catch (SQLException e) {
            throw new RepositoryException("Erro ao buscar agendamentos");
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
            stmt.setLong(4, agendamento.getCliente().getId());
            stmt.setLong(5, agendamento.getIdAgendamento());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RepositoryException("Erro ao atualizar agendamento");
        }
    }

    @Override
    public boolean delete(long id) {
        String sql = "DELETE FROM Agendamentos WHERE idAgendamento = ?";

        try (PreparedStatement stmt = ConnectionFactory.getConnection().prepareStatement(sql)) {
            stmt.setLong(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RepositoryException("Erro ao deletar agendamento");
        }
    }

    @Override
    public boolean deleteAgendamentoByClienteId(Long idAgendamento) {
        String sql = "DELETE FROM Agendamentos WHERE idCliente = ?";

        try(PreparedStatement stmt = ConnectionFactory.getConnection().prepareStatement(sql))
        {
            stmt.setLong(1, idAgendamento);
            return stmt.executeUpdate() > 0;
        }catch (SQLException e)
        {
            throw new RepositoryException("Erro ao deletar agendamento");
        }
    }

    @Override
    public List<Agendamento> findByClientId(long clientId) {
        List<Agendamento> agendamentos = new ArrayList<>();
        String sql = "SELECT a.*, u.*, c.* " +
                "FROM Agendamentos a " +
                "INNER JOIN Clientes c ON a.idCliente = c.idCliente " +
                "INNER JOIN Usuarios u ON c.idCliente = u.id " +
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
            throw new RepositoryException("Erro ao buscar agendamentos do cliente");
        }
        return agendamentos;
    }

    @Override
    public List<Agendamento> findByDate(LocalDateTime date) {
        List<Agendamento> agendamentos = new ArrayList<>();
        String sql = "SELECT a.*, u.*, c.* " +
                "FROM Agendamentos a " +
                "INNER JOIN Clientes c ON a.idCliente = c.idCliente " +
                "INNER JOIN Usuarios u ON c.idCliente = u.id " +
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
            throw new RepositoryException("Erro ao buscar agendamentos por data");
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
                    return rs.getInt(1) == 0; // Disponível se não há conflitos
                }
            }
        } catch (SQLException e) {
            throw new RepositoryException("Erro ao verificar disponibilidade.");
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
            throw new RepositoryException("Erro ao contar agendamentos do cliente");
        }
        return 0;
    }

    @Override
    public List<Agendamento> findUpcoming() {
        List<Agendamento> agendamentos = new ArrayList<>();
        String sql = "SELECT a.*, u.*, c.* " +
                "FROM Agendamentos a " +
                "INNER JOIN Clientes c ON a.idCliente = c.idCliente " +
                "INNER JOIN Usuarios u ON c.idCliente = u.id " +
                "WHERE a.dataInicio > NOW() " +
                "ORDER BY a.dataInicio";

        try (Statement stmt = ConnectionFactory.getConnection().createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                agendamentos.add(mapResultSetToAgendamento(rs));
            }
        } catch (SQLException e) {
            throw new RepositoryException("Erro ao buscar agendamentos futuros");
        }
        return agendamentos;
    }

    @Override
    public List<Agendamento> findPast() {
        List<Agendamento> agendamentos = new ArrayList<>();
        String sql = "SELECT a.*, u.*, c.* " +
                "FROM Agendamentos a " +
                "INNER JOIN Clientes c ON a.idCliente = c.idCliente " +
                "INNER JOIN Usuarios u ON c.idCliente = u.id " +
                "WHERE a.dataFim < NOW() " +
                "ORDER BY a.dataInicio DESC";

        try (Statement stmt = ConnectionFactory.getConnection().createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                agendamentos.add(mapResultSetToAgendamento(rs));
            }
        } catch (SQLException e) {
            throw new RepositoryException("Erro ao buscar agendamentos passados");
        }
        return agendamentos;
    }

    private Agendamento mapResultSetToAgendamento(ResultSet rs) throws SQLException {
        Cliente cliente = new Cliente(
                rs.getLong("idCliente"),
                rs.getString("email"),
                rs.getString("senha"),
                rs.getString("nome"),
                Status.fromCodigo(rs.getInt("status")),
                TipoUsuario.fromCodigo(rs.getInt("tipoUsuario")),
                rs.getString("cpf")
        );

        return new Agendamento(
                rs.getLong("idAgendamento"),
                rs.getTimestamp("dataInicio").toLocalDateTime(),
                rs.getTimestamp("dataFim").toLocalDateTime(),
                rs.getString("observacao"),
                cliente
        );
    }
}