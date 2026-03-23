package org.reempreende.presentation.router;

import org.reempreende.application.dto.request.UsuarioRequestDTO;
import org.reempreende.application.service.*;
import org.reempreende.infrastructure.sessao.Sessao;
import org.reempreende.infrastructure.utility.Util;
import org.reempreende.presentation.interfaces.icadastro.ICadastroClienteView;
import org.reempreende.presentation.interfaces.icadastro.ICadastroComercianteView;
import org.reempreende.presentation.interfaces.icliente.*;
import org.reempreende.presentation.interfaces.icomerciante.IComercianteServicoView;
import org.reempreende.presentation.interfaces.icomerciante.IComercianteView;
import org.reempreende.presentation.interfaces.ilogin.ILoginUsuario;
import org.reempreende.presentation.interfaces.inicial.IInicialView;
import org.reempreende.presentation.presenter.*;
import org.reempreende.presentation.presenter.cliente.*;
import org.reempreende.presentation.presenter.comerciante.ComercianteCadastroPresenter;
import org.reempreende.presentation.presenter.comerciante.ComerciantePresenter;
import org.reempreende.presentation.presenter.comerciante.ComercianteServicoPresenter;
import org.reempreende.presentation.view.cliente.*;
import org.reempreende.presentation.view.comerciante.CadastroComercianteView;
import org.reempreende.presentation.view.comerciante.ComercianteServicoView;
import org.reempreende.presentation.view.comerciante.ComercianteView;
import org.reempreende.presentation.view.inicio.InicialView;
import org.reempreende.presentation.view.cadastro.CadastroBaseView;
import org.reempreende.presentation.view.login.LoginView;

public class AppRouter {
    private final Util u = new Util();

    private final UsuarioService usuarioService;
    private final ClienteService clienteService;
    private final ComercianteService comercianteService;
    private final AgendamentoService agendamentoService;
    private final Sessao sessao;
    private final ServicoService servicoService;

    public AppRouter(UsuarioService usuarioService, ClienteService clienteService,
                     ComercianteService comercianteService, AgendamentoService agendamentoService,
                     ServicoService service, Sessao sessao) {
        this.usuarioService = usuarioService;
        this.clienteService = clienteService;
        this.comercianteService = comercianteService;
        this.agendamentoService = agendamentoService;
        this.servicoService = service;
        this.sessao = sessao;
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
        IClienteViewHorarios viewHorarios = new ClienteViewHorarios();

        ClienteHorariosPresenter clienteHorariosPresenter = new ClienteHorariosPresenter(this, viewHorarios, agendamentoService);

        clienteHorariosPresenter.showHorarios();
    }

    public void clientAgendarHorarioDisponivel() {
        IClienteViewHorarios clienteViewHorarios = new ClienteViewHorarios();
        IClienteViewAgendarDisponivel agendarDisponivelView = new ClienteViewAgendarDisponivel();

        ClienteHorariosPresenter clienteHorariosPresenter = new ClienteHorariosPresenter(this, clienteViewHorarios, agendamentoService);

        ClienteAgendarPresenter clienteAgendarPresenter = new ClienteAgendarPresenter(this, agendarDisponivelView, agendamentoService,
                this.sessao, clienteHorariosPresenter);

        clienteAgendarPresenter.schedule();
    }

    public void clientViewHistory() {
        IClienteViewHistorico viewHistorico = new ClienteViewHistorico();

        ClienteHistoricoPresenter clienteHistoricoPresenter = new ClienteHistoricoPresenter(this, viewHistorico, agendamentoService, this.sessao);

        clienteHistoricoPresenter.showHistory();
    }

    public void login() {
        ILoginUsuario loginUsuario = new LoginView();
        IClienteView clienteView = new ClienteView();

        LoginPresenter loginPresenter = new LoginPresenter(this, loginUsuario, usuarioService, comercianteService, this.sessao);

        loginPresenter.login();
    }

    public void startComercianteView() {
        IComercianteView view = new ComercianteView();

        ComerciantePresenter comerciantePresenter =
                new ComerciantePresenter(this, view);

        comerciantePresenter.selectOptions();
    }

    public void updateCliente() {
        IClienteAtualizarView view = new ClienteAtualizarView();

        ClienteAtualizarPresenter clienteAtualizarPresenter =
                new ClienteAtualizarPresenter(this, this.sessao, view, clienteService);

        clienteAtualizarPresenter.update();
    }

    public void servicoComerciante() {
        IComercianteServicoView view = new ComercianteServicoView();

        ComercianteServicoPresenter comercianteServicoPresenter =
                new ComercianteServicoPresenter(this, view, servicoService,
                        this.comercianteService, this.sessao);

        comercianteServicoPresenter.createServico();
    }

    public void logout() {
        this.sessao.logout();
    }

}
