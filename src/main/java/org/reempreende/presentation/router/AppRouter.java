package org.reempreende.presentation.router;

import org.reempreende.application.dto.request.UsuarioRequestDTO;
import org.reempreende.application.service.ClienteService;
import org.reempreende.application.service.ComercianteService;
import org.reempreende.application.service.UsuarioService;
import org.reempreende.presentation.interfaces.icadastro.ICadastroClienteView;
import org.reempreende.presentation.interfaces.icadastro.ICadastroComercianteView;
import org.reempreende.presentation.interfaces.icliente.IClienteView;
import org.reempreende.presentation.interfaces.inicial.IInicialView;
import org.reempreende.presentation.presenter.CadastrarUsuarioPresenter;
import org.reempreende.presentation.presenter.ClientePresenter;
import org.reempreende.presentation.presenter.ComerciantePresenter;
import org.reempreende.presentation.presenter.InicialPresenter;
import org.reempreende.presentation.view.cliente.CadastroClienteView;
import org.reempreende.presentation.view.cliente.ClienteView;
import org.reempreende.presentation.view.cliente.ClienteViewHistorico;
import org.reempreende.presentation.view.comerciante.CadastroComercianteView;
import org.reempreende.presentation.view.inicio.InicialView;
import org.reempreende.presentation.view.cadastro.CadastroBaseView;

public class AppRouter {
    private final UsuarioService usuarioService;
    private final ClienteService clienteService;
    private final ComercianteService comercianteService;

    public AppRouter(UsuarioService usuarioService, ClienteService clienteService,
                     ComercianteService comercianteService) {
        this.usuarioService = usuarioService;
        this.clienteService = clienteService;
        this.comercianteService = comercianteService;
    }

    public void iniciarSistema() {
        IInicialView inicialView = new InicialView();
        InicialPresenter inicialPresenter = new InicialPresenter(this, inicialView);

        inicialPresenter.iniciar();
    }

    public void registerUser() {
        IInicialView inicialView = new InicialView();

        CadastroBaseView cadastroBaseView = new CadastroBaseView();

        UsuarioRequestDTO usuarioDTO = new UsuarioRequestDTO();

        CadastrarUsuarioPresenter cadastrarUsuarioPresenter = new CadastrarUsuarioPresenter(this, inicialView, cadastroBaseView,
                usuarioDTO ,usuarioService);

        cadastrarUsuarioPresenter.collectInfo();
    }

    public void registerClient(UsuarioRequestDTO usuarioDTO) {
        ICadastroClienteView cadastroClienteView = new CadastroClienteView();

        ClientePresenter clientePresenter = new ClientePresenter(this, cadastroClienteView, clienteService);

        clientePresenter.registerClient(usuarioDTO);
    }

    public void registerComerciante(UsuarioRequestDTO usuarioDTO) {
        ICadastroComercianteView cadastroComercianteView = new CadastroComercianteView();

        ComerciantePresenter comerciantePresenter = new ComerciantePresenter(this, comercianteService,
                cadastroComercianteView);

        comerciantePresenter.registerComerciante(usuarioDTO);
    }

    public void startClientView() {
        IClienteView clienteView = new ClienteView();

        clienteView.mostrarTela();
    }

}
