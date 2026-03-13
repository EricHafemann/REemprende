package org.reempreende.initializer;

import org.reempreende.application.service.ClienteService;
import org.reempreende.application.service.ComercianteService;
import org.reempreende.application.service.UsuarioService;
import org.reempreende.domain.repository.ClienteRepository;
import org.reempreende.domain.repository.ComercianteRepository;
import org.reempreende.domain.repository.UsuarioRepository;
import org.reempreende.infrastructure.repository.ClienteRepositoryImpl;
import org.reempreende.infrastructure.repository.ComercianteRepositoryImpl;
import org.reempreende.infrastructure.repository.UsuarioRepositoryImpl;
import org.reempreende.presentation.interfaces.inicial.IInicialView;
import org.reempreende.presentation.presenter.InicialPresenter;
import org.reempreende.presentation.router.AppRouter;
import org.reempreende.presentation.view.inicio.InicialView;

public class Main {

    private static final InicialView inicial = new InicialView();

    public static void main(String[] args) {
        UsuarioRepository usuarioRepository = new UsuarioRepositoryImpl();
        UsuarioService usuarioService = new UsuarioService(usuarioRepository);

        ClienteRepository clienteRepository = new ClienteRepositoryImpl();
        ClienteService clienteService = new ClienteService(usuarioRepository, clienteRepository);

        ComercianteRepository comercianteRepository = new ComercianteRepositoryImpl();
        ComercianteService comercianteService = new ComercianteService(usuarioRepository, comercianteRepository);

        AppRouter appRouter = new AppRouter(usuarioService, clienteService, comercianteService);

        appRouter.iniciarSistema();
    }

}