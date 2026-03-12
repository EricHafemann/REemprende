package org.reempreende.presentation.router;

import org.reempreende.application.dto.request.UsuarioDTO;
import org.reempreende.application.service.UsuarioService;
import org.reempreende.domain.repository.UsuarioRepository;
import org.reempreende.infrastructure.repository.UsuarioRepositoryImpl;
import org.reempreende.presentation.interfaces.icadastro.ICadastroClienteView;
import org.reempreende.presentation.interfaces.icadastro.ICadastroComercianteView;
import org.reempreende.presentation.interfaces.icadastro.ICadastroView;
import org.reempreende.presentation.interfaces.inicial.IInicialView;
import org.reempreende.presentation.presenter.CadastrarUsuarioPresenter;
import org.reempreende.presentation.presenter.ClientePresenter;
import org.reempreende.presentation.presenter.ComerciantePresenter;
import org.reempreende.presentation.presenter.InicialPresenter;
import org.reempreende.presentation.view.cliente.CadastroClienteView;
import org.reempreende.presentation.view.inicio.InicialView;
import org.reempreende.presentation.view.usuario.CadastroBaseView;

public class AppRouter {
    public void iniciarSistema() {
        IInicialView inicialView = new InicialView();
        InicialPresenter inicialPresenter = new InicialPresenter(this, inicialView);

        inicialPresenter.iniciar();
    }

    public void registerUser() {
        IInicialView inicialView = new InicialView();

        CadastroBaseView cadastroBaseView = new CadastroBaseView();

        UsuarioDTO usuarioDTO = new UsuarioDTO();

        UsuarioRepository usuarioRepository = new UsuarioRepositoryImpl();
        UsuarioService usuarioService = new UsuarioService(usuarioRepository);

        CadastrarUsuarioPresenter cadastrarUsuarioPresenter = new CadastrarUsuarioPresenter(this, inicialView, cadastroBaseView,
                usuarioDTO ,usuarioService);

        cadastrarUsuarioPresenter.collectInfo();
    }

    public void registerClient(UsuarioDTO usuarioDTO) {
        ICadastroClienteView cadastroClienteView = new CadastroClienteView();

        UsuarioRepository usuarioRepository = new UsuarioRepositoryImpl();
        UsuarioService usuarioService = new UsuarioService(usuarioRepository);

        ClientePresenter clientePresenter = new ClientePresenter(this, cadastroClienteView, usuarioService);

        clientePresenter.iniciarCadastro(usuarioDTO);
    }

}
