package org.reempreende.domain.repository;

import org.reempreende.domain.entities.Agendamento;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface AgendamentoRepository {

    Agendamento insert(Agendamento agendamento);

    Optional<Agendamento> findById(long id);

    List<Agendamento> findAll();

    boolean update(Agendamento agendamento);

    boolean delete(long id);

    List<Agendamento> findByClientId(long clientId);

    List<Agendamento> findByDate(LocalDateTime date);

    boolean isAvailable(LocalDateTime startTime, LocalDateTime endTime);

    long countByClientId(long clientId);

    List<Agendamento> findUpcoming();

    List<Agendamento> findPast();
}