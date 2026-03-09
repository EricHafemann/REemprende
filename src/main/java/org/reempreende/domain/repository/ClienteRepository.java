package org.reempreende.domain.repository;

import org.reempreende.domain.entities.Cliente;
import java.util.Optional;

public interface ClienteRepository {

    Cliente insert(Cliente cliente);

    Optional<Cliente> findById(long id);

    boolean update(Cliente cliente);

    boolean delete(long id);

    boolean existsByCpf(String cpf);
}