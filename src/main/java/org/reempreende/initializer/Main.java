package org.reempreende.initializer;

import org.reempreende.application.service.*;
import org.reempreende.domain.repository.*;
import org.reempreende.infrastructure.repository.*;
import org.reempreende.infrastructure.sessao.Sessao;
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

        AgendamentoRepository agendamentoRepository = new AgendamentoRepositoryImpl();
        AgendamentoService agendamentoService = new AgendamentoService(agendamentoRepository);

        Sessao sessao = new Sessao();

        ServicoRepository servicoRepository = new ServicoRepositoryImpl();
        ServicoAgendamentoRepository servicoAgendamentoRepository = new ServicoAgendamentoRepositoryImpl();
        ServicoService service = new ServicoService(servicoRepository, servicoAgendamentoRepository,
                agendamentoService, comercianteRepository);

        AppRouter appRouter = new AppRouter(usuarioService, clienteService, comercianteService,
                agendamentoService, service, sessao);

        appRouter.startSystem();
    }

}