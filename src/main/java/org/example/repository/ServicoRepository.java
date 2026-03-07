package org.example.repository;

import org.example.config.ConnectionFactory;
import org.example.model.Servico;
import org.example.model.Comerciante;
import org.example.model.enums.Status;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServicoRepository {


    public void insert(Servico servico) {
        String sql = "INSERT INTO Servicos (avaliacao, descricao, duracaoHoras, idComerciante) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, servico.getAvalicao());
            stmt.setString(2, servico.getDescricao());
            stmt.setDouble(3, servico.getDuracaoHoras());
            stmt.setLong(4, servico.getComerciante().getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir serviço: " + e.getMessage());
        }
    }


    public Servico findById(long id) {
        String sql = "SELECT s.*, c.email, c.senha, c.nome, c.status, c.cnpj, c.senhaAcesso " +
                "FROM Servicos s " +
                "JOIN Comerciantes c ON s.idComerciante = c.id " +
                "WHERE s.idServico = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapearServico(rs);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar serviço por ID: " + e.getMessage());
        }
        return null;
    }

    public List<Servico> findAll() {
        List<Servico> servicos = new ArrayList<>();
        String sql = "SELECT s.*, c.email, c.senha, c.nome, c.status, c.cnpj, c.senhaAcesso " +
                "FROM Servicos s " +
                "JOIN Comerciantes c ON s.idComerciante = c.id";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                servicos.add(mapearServico(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar serviços: " + e.getMessage());
        }
        return servicos;
    }


    private Servico mapearServico(ResultSet rs) throws SQLException {
        Comerciante comerciante = new Comerciante(
                rs.getLong("idComerciante"),
                rs.getString("email"),
                rs.getString("senha"),
                rs.getString("nome"),
                Status.valueOf(rs.getString("status")),
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