package org.example.repository;

import org.example.config.ConnectionFactory;
import org.example.model.Servico;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServicoRepository {

    public void insertServico(Servico servico) {
        String sql = "INSERT INTO Servicos (avaliacao, descricao, duracaoHoras, idComerciante) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, servico.getAvalicao());
            stmt.setString(2, servico.getDescricao());
            stmt.setDouble(3, servico.getDuracaoHoras());
            stmt.setLong(4, servico.getComerciante().getId());

            stmt.executeUpdate();
            System.out.println("Serviço cadastrado com sucesso!");

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar serviço: " + e.getMessage());
        }
    }

    public List<Servico> findAllServicos() {
        List<Servico> servicos = new ArrayList<>();
        String sql = "SELECT * FROM Servicos";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                Servico s = new Servico(
                        rs.getLong("idServico"),
                        rs.getString("avaliacao"),
                        rs.getString("descricao"),
                        rs.getDouble("duracaoHoras"),
                        null
                );
                servicos.add(s);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar serviços: " + e.getMessage());
        }
        return servicos;
    }

    public Servico findById(long id) {
        String sql = "SELECT * FROM Servicos WHERE idServico = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {

                    return new Servico(
                            rs.getLong("idServico"),
                            rs.getString("avaliacao"),
                            rs.getString("descricao"),
                            rs.getDouble("duracaoHoras"),
                            null
                    );
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar serviço por ID: " + e.getMessage());
        }
        return null;
    }
}