package org.reempreende.domain.repository;

import org.reempreende.domain.entities.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository {

    Usuario insert(Usuario usuario);

    Optional<Usuario> findById(long id);

    List<Usuario> findAll();

    Optional<Usuario> findByEmail(String email);

    boolean update(Usuario usuario);

    boolean disable(long id);

    boolean enable(long id);

    boolean delete(long id);

    boolean existsByEmail(String email);

    boolean existsById(long id);

    Optional<Usuario> login(String email, String senha);
}