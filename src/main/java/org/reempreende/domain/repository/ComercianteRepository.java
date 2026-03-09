package org.reempreende.domain.repository;

import org.reempreende.domain.entities.Comerciante;
import java.util.Optional;

public interface ComercianteRepository {

    Comerciante insert(Comerciante comerciante);

    Optional<Comerciante> findById(long id);

    boolean update(Comerciante comerciante);

    boolean delete(long id);

    boolean existsByCnpj(String cnpj);
}