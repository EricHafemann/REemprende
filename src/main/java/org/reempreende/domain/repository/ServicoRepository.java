package org.reempreende.domain.repository;

import org.reempreende.domain.entities.Servico;
import java.util.List;
import java.util.Optional;

public interface ServicoRepository {

    Servico insert(Servico servico);

    Optional<Servico> findById(long id);

    List<Servico> findAll();

    boolean update(Servico servico);

    boolean delete(long id);

    List<Servico> findByComercianteId(long comercianteId);

    long count();

    long countByComercianteId(long comercianteId);

    boolean existsById(long id);
}