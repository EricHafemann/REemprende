package org.reempreende.domain.repository;

import org.reempreende.domain.entities.Agendamento;
import org.reempreende.domain.entities.Servico;
import org.reempreende.domain.entities.ServicoAgendamento;
import java.util.List;

public interface ServicoAgendamentoRepository {

    ServicoAgendamento insert(ServicoAgendamento servicoAgendamento);

    void insertAll(long idAgendamento, List<Servico> servicos);

    List<Servico> findServicosByAgendamentoId(long idAgendamento);

    List<Agendamento> findAgendamentosByServicoId(long idServico);

    boolean deleteByAgendamentoId(long idAgendamento);

    boolean deleteByServicoId(long idServico);

    boolean delete(long idServico, long idAgendamento);

    long countByAgendamentoId(long idAgendamento);

    long countByServicoId(long idServico);

    boolean exists(long idServico, long idAgendamento);
}