package org.reempreende.presentation.router;

import org.reempreende.application.dto.request.UsuarioRequestDTO;
import org.reempreende.application.service.ClienteService;
import org.reempreende.application.service.ComercianteService;
import org.reempreende.application.service.UsuarioService;
import org.reempreende.presentation.interfaces.icadastro.ICadastroClienteView;
import org.reempreende.presentation.interfaces.icadastro.ICadastroComercianteView;
import org.reempreende.presentation.interfaces.icliente.IClienteView;
import org.reempreende.presentation.interfaces.icliente.IClienteViewAgendarDisponivel;
import org.reempreende.presentation.interfaces.icliente.IClienteViewHistorico;
import org.reempreende.presentation.interfaces.icliente.IClienteViewHorarios;
import org.reempreende.presentation.interfaces.icomerciante.IComercianteView;
import org.reempreende.presentation.interfaces.ilogin.ILoginUsuario;
import org.reempreende.presentation.interfaces.inicial.IInicialView;
import org.reempreende.presentation.presenter.*;
import org.reempreende.presentation.view.cliente.*;
import org.reempreende.presentation.view.comerciante.CadastroComercianteView;
import org.reempreende.presentation.view.comerciante.ComercianteView;
import org.reempreende.presentation.view.inicio.InicialView;
import org.reempreende.presentation.view.cadastro.CadastroBaseView;
import org.reempreende.presentation.view.login.LoginView;

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

    public void startSystem() {
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

        ClienteCadastroPresenter clientePresenter = new ClienteCadastroPresenter(this, cadastroClienteView, clienteService);

        clientePresenter.registerClient(usuarioDTO);
    }

    public void registerComerciante(UsuarioRequestDTO usuarioDTO) {
        ICadastroComercianteView cadastroComercianteView = new CadastroComercianteView();

        ComercianteCadastroPresenter comerciantePresenter = new ComercianteCadastroPresenter(this, comercianteService,
                cadastroComercianteView);

        comerciantePresenter.registerComerciante(usuarioDTO);
    }

    public void startClientView() {
        IClienteView clienteView = new ClienteView();

        ClientePresenter clientePresenter = new ClientePresenter(this, clienteView);

        clientePresenter.selecionarOpcoes();
    }

    public void clientViewHorarios() {
        //todo
        IClienteViewHorarios viewHorarios = new ClienteViewHorarios();

        viewHorarios.mostrarTela();
    }

    public void clientAgendarHorarioDisponivel() {
        //todo
        IClienteViewAgendarDisponivel agendarDisponivelView = new ClienteViewAgendarDisponivel();

        agendarDisponivelView.exibirHorariosDisponiveis();
        agendarDisponivelView.mostrarTela();
    }

    public void clientViewHistory() {
        //todo
        IClienteViewHistorico clienteViewHistorico = new ClienteViewHistorico();

        clienteViewHistorico.verHistoricoAgendamentos();
    }

    public void login() {
        ILoginUsuario loginUsuario = new LoginView();
        IClienteView clienteView = new ClienteView();

        LoginPresenter loginPresenter = new LoginPresenter(this, loginUsuario, usuarioService, comercianteService, clienteView);

        loginPresenter.login();
    }

    public void startComercianteView() {
        IComercianteView comercianteView = new ComercianteView();

        comercianteView.exibirSucesso("Comerciante View");
    }

}
