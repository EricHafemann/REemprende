package org.reempreende.presentation.presenter.cliente;

import org.reempreende.application.dto.mapper.AgendamentoMapper;
import org.reempreende.application.dto.response.AgendamentoResponseDTO;
import org.reempreende.application.service.AgendamentoService;
import org.reempreende.application.service.ClienteService;
import org.reempreende.domain.entities.Agendamento;
import org.reempreende.infrastructure.repository.ClienteRepositoryImpl;
import org.reempreende.infrastructure.repository.UsuarioRepositoryImpl;
import org.reempreende.infrastructure.sessao.Sessao;
import org.reempreende.infrastructure.utility.Cores;
import org.reempreende.presentation.exception.InvalidFieldException;
import org.reempreende.presentation.interfaces.icliente.IClienteViewAgendarDisponivel;
import org.reempreende.presentation.interfaces.icliente.IClienteViewHorarios;
import org.reempreende.presentation.router.AppRouter;

import java.util.OptionalInt;

public class ClienteAgendarPresenter {
    private AppRouter appRouter;
    private IClienteViewAgendarDisponivel view;
    private AgendamentoService agendamentoService;
    private ClienteHorariosPresenter clienteHorariosPresenter;
    private Sessao sessao;
    private ClienteService clienteService = new ClienteService(new UsuarioRepositoryImpl(), new ClienteRepositoryImpl());

    public ClienteAgendarPresenter(AppRouter appRouter, IClienteViewAgendarDisponivel view, AgendamentoService agendamentoService,
                                   Sessao sessao, ClienteHorariosPresenter clienteHorariosPresenter) {
        this.appRouter = appRouter;
        this.view = view;
        this.agendamentoService = agendamentoService;
        this.sessao = sessao;
        this.clienteHorariosPresenter = clienteHorariosPresenter;
    }

    public void schedule() {
        view.mostrarTela();

        clienteHorariosPresenter.showHorarios();

        OptionalInt idCaixa = view.mostrarTela();

        long id = idCaixa.orElse(-1);

        try {
           AgendamentoResponseDTO agendamentoResponseDTO = agendamentoService.findById(id);

           if (agendamentoResponseDTO.getIdCliente() != null) {

           }
        } catch (InvalidFieldException e) {
            System.out.println(Cores.VERMELHO + e.getMessage() + Cores.RESET);
        }



    }

}
