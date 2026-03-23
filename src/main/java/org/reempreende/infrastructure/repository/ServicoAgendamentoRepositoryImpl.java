package org.reempreende.infrastructure.repository;

import org.reempreende.domain.repository.ServicoAgendamentoRepository;
import org.reempreende.infrastructure.config.ConnectionFactory;
import org.reempreende.infrastructure.exception.RepositoryException;
import org.reempreende.domain.entities.Agendamento;
import org.reempreende.domain.entities.Servico;
import org.reempreende.domain.entities.ServicoAgendamento;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServicoAgendamentoRepositoryImpl implements ServicoAgendamentoRepository {

    @Override
    public ServicoAgendamento insert(Long idAgendamento, Long idServico) {
        String sql = "INSERT INTO Servicos_Agendamentos (idServico, idAgendamento) VALUES (?, ?)";

        ServicoAgendamento servicoAgendamento = new ServicoAgendamento();

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setLong(1, idServico);
            stmt.setLong(2, idAgendamento);

            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    servicoAgendamento.setIdServicosAgendamentos(generatedKeys.getLong(1));
                }
            }


            return servicoAgendamento;

        } catch (SQLException e) {

            throw new RepositoryException("Erro ao associar serviço ao agendamento");
        }
    }
    @Override
    public ServicoAgendamento insert(ServicoAgendamento servicoAgendamento) {
        String sql = "INSERT INTO Servicos_Agendamentos (idServico, idAgendamento) VALUES (?, ?)";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setLong(1, servicoAgendamento.getServico().getIdServico());
            stmt.setLong(2, servicoAgendamento.getAgendamento().getIdAgendamento());

            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    servicoAgendamento.setIdServicosAgendamentos(generatedKeys.getLong(1));
                }
            }

            return servicoAgendamento;

        } catch (SQLException e) {
            throw new RepositoryException("Erro ao associar serviço ao agendamento");
        }
    }

    @Override
    public void insertAll(long idAgendamento, List<Servico> servicos) {
        String sql = "INSERT INTO Servicos_Agendamentos (idServico, idAgendamento) VALUES (?, ?)";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            for (Servico servico : servicos) {
                stmt.setLong(1, servico.getIdServico());
                stmt.setLong(2, idAgendamento);
                stmt.addBatch();
            }

            stmt.executeBatch();

        } catch (SQLException e) {
            throw new RepositoryException("Erro ao associar serviços ao agendamento");
        }
    }

    @Override
    public List<Servico> findServicosByAgendamentoId(long idAgendamento) {
        List<Servico> servicos = new ArrayList<>();
        String sql = "SELECT s.* FROM Servicos_Agendamentos sa " +
                "INNER JOIN Servicos s ON sa.idServico = s.idServico " +
                "WHERE sa.idAgendamento = ?";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setLong(1, idAgendamento);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Servico servico = new Servico(
                            rs.getLong("idServico"),
                            rs.getString("avaliacao"),
                            rs.getString("descricao"),
                            rs.getDouble("duracaoHoras"),
                            null // Comerciante será preenchido depois se necessário
                    );
                    servicos.add(servico);
                }
            }

        } catch (SQLException e) {
            throw new RepositoryException("Erro ao buscar serviços do agendamento");
        }

        return servicos;
    }

    @Override
    public List<Agendamento> findAgendamentosByServicoId(long idServico) {
        List<Agendamento> agendamentos = new ArrayList<>();
        String sql = "SELECT a.* FROM Servicos_Agendamentos sa " +
                "INNER JOIN Agendamentos a ON sa.idAgendamento = a.idAgendamento " +
                "WHERE sa.idServico = ?";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setLong(1, idServico);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Agendamento agendamento = new Agendamento(
                            rs.getLong("idAgendamento"),
                            rs.getTimestamp("dataInicio").toLocalDateTime(),
                            rs.getTimestamp("dataFim").toLocalDateTime(),
                            rs.getString("observacao"),
                            null // Cliente será preenchido depois se necessário
                    );
                    agendamentos.add(agendamento);
                }
            }

        } catch (SQLException e) {
            throw new RepositoryException("Erro ao buscar agendamentos do serviço");
        }

        return agendamentos;
    }

    @Override
    public boolean deleteByAgendamentoId(long idAgendamento) {
        String sql = "DELETE FROM Servicos_Agendamentos WHERE idAgendamento = ?";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setLong(1, idAgendamento);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RepositoryException("Erro ao deletar associações do agendamento");
        }
    }

    @Override
    public boolean deleteByServicoId(long idServico) {
        String sql = "DELETE FROM Servicos_Agendamentos WHERE idServico = ?";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setLong(1, idServico);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RepositoryException("Erro ao deletar associações do serviço");
        }
    }

    @Override
    public boolean delete(long idServico, long idAgendamento) {
        String sql = "DELETE FROM Servicos_Agendamentos WHERE idServico = ? AND idAgendamento = ?";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setLong(1, idServico);
            stmt.setLong(2, idAgendamento);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RepositoryException("Erro ao deletar associação específica");
        }
    }

    @Override
    public long countByAgendamentoId(long idAgendamento) {
        String sql = "SELECT COUNT(*) FROM Servicos_Agendamentos WHERE idAgendamento = ?";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setLong(1, idAgendamento);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getLong(1);
                }
            }

        } catch (SQLException e) {
            throw new RepositoryException("Erro ao contar serviços do agendamento");
        }

        return 0;
    }

    @Override
    public long countByServicoId(long idServico) {
        String sql = "SELECT COUNT(*) FROM Servicos_Agendamentos WHERE idServico = ?";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setLong(1, idServico);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getLong(1);
                }
            }

        } catch (SQLException e) {
            throw new RepositoryException("Erro ao contar agendamentos do serviço");
        }

        return 0;
    }

    @Override
    public boolean exists(long idServico, long idAgendamento) {
        String sql = "SELECT 1 FROM Servicos_Agendamentos WHERE idServico = ? AND idAgendamento = ?";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setLong(1, idServico);
            stmt.setLong(2, idAgendamento);

            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }

        } catch (SQLException e) {
            throw new RepositoryException("Erro ao verificar existência da associação");
        }
    }
}