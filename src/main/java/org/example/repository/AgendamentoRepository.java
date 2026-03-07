package org.example.repository;

import org.example.config.ConnectionFactory;
import org.example.model.Agendamento;
import org.example.model.Cliente;

import java.sql.*;

public class AgendamentoRepository {

    public void insertAgendamento (Agendamento agendamento)
    {
        String querySql = "INSERT INTO Usuario (dataInicio, dataFIm, observacao, idClient) VALUES (?,?,?,?)";

        try (Connection connection = ConnectionFactory.getConnection(); PreparedStatement stmt = connection.prepareStatement(querySql, Statement.RETURN_GENERATED_KEYS))
        {
            stmt.setDate(1, java.sql.Date.valueOf(agendamento.getDataInicio()));
            stmt.setDate(2, java.sql.Date.valueOf(agendamento.getDataFim()));
            stmt.setString(3, agendamento.getObservacao());
            stmt.setLong(4, agendamento.getCliente().getId());

            int numLinhas = stmt.executeUpdate();

            if (numLinhas > 0)
            {
                try (ResultSet resultSet = stmt.getGeneratedKeys())
                {
                    if (resultSet.next())
                    {
                        long idGerado = resultSet.getLong(1);
                        agendamento.setIdAgendamento(idGerado);
                    }
                }
            }
        }

        catch (SQLException e)
        {
            System.err.println("Erro na hora de inserir agendamento ao banco !");
        }
    }

    public void deleteAgendamento(long idAgendamento) {
        String querySql = "DELETE FROM Usuario WHERE idAgendamento = ?";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(querySql)) {

            stmt.setLong(1, idAgendamento);

            stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Erro ao excluir agendamento !");
        }
    }

    public void updateAgendamento(Agendamento agendamento) {

        String querySql = "UPDATE Usuario SET dataInicio = ?, dataFim = ?, observacao = ?, idClient = ? WHERE idAgendamento = ?";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(querySql)) {

            stmt.setDate(1, java.sql.Date.valueOf(agendamento.getDataInicio()));
            stmt.setDate(2, java.sql.Date.valueOf(agendamento.getDataFim()));
            stmt.setString(3, agendamento.getObservacao());
            stmt.setLong(4, agendamento.getCliente().getId());

            stmt.setLong(5, agendamento.getIdAgendamento());

            stmt.executeUpdate();


        } catch (SQLException e) {
            System.err.println("Erro ao atualizar agendamento !");
        }
    }

    public Agendamento findById(long id) {
        String querySql = "SELECT * FROM Usuario WHERE idAgendamento = ?";
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(querySql)) {

            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Cliente cliente = new Cliente();
                    cliente.setId(rs.getLong("idClient"));

                    return new Agendamento(
                            rs.getLong("idAgendamento"),
                            rs.getDate("dataInicio").toLocalDate(),
                            rs.getDate("dataFim").toLocalDate(),
                            rs.getString("observacao"),
                            cliente
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar: " + e);
        }
        return null;
    }



}
