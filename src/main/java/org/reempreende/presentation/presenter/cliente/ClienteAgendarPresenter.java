package org.reempreende.presentation.presenter.cliente;

import org.reempreende.application.dto.mapper.AgendamentoMapper;
import org.reempreende.application.dto.response.AgendamentoResponseDTO;
import org.reempreende.application.dto.response.ServicoResponseDTO;
import org.reempreende.application.service.AgendamentoService;
import org.reempreende.application.service.ClienteService;
import org.reempreende.application.service.ServicoService;
import org.reempreende.infrastructure.repository.*;
import org.reempreende.infrastructure.sessao.Sessao;
import org.reempreende.infrastructure.utility.Util;
import org.reempreende.presentation.interfaces.icliente.IClienteViewAgendarDisponivel;

import java.util.List;
import java.util.OptionalLong;

public class ClienteAgendarPresenter {
    private IClienteViewAgendarDisponivel view;
    private ServicoService servicoService;
    private AgendamentoService agendamentoService;
    private Sessao sessao;
    private ClienteService clienteService;

    public ClienteAgendarPresenter(IClienteViewAgendarDisponivel view,
                                   ServicoService servicoService, AgendamentoService agendamentoService,
                                   Sessao sessao) {
        this.view = view;
        this.servicoService = servicoService;
        this.agendamentoService = agendamentoService;
        this.sessao = sessao;
        this.clienteService = clienteService;
    }

    public void schedule() {
        view.mostrarTela();

        List<ServicoResponseDTO> servicos = servicoService.findAll();

        for (ServicoResponseDTO servico : servicos) {
            view.exibirMensagem(servico.exibirInfo());
        }

        OptionalLong opcaoCaixa = view.askServico();
        Long idServico = opcaoCaixa.orElse(-1L);

        try {
            servicoService.findById(idServico);

            List<AgendamentoResponseDTO> agendamentos = agendamentoService.findAgendamentosByServicoId(idServico);

            for (AgendamentoResponseDTO agendamento : agendamentos) {
                view.exibirMensagem(agendamento.exibirInfo());
            }

            OptionalLong agendamentoCaixa = view.exibirMensagemTela();
            Long idAgendamento = agendamentoCaixa.orElse(-1L);

            if (idAgendamento == -1L) {
                view.exibirErro("ID inválido!");
                Util.digiteEnterParaContinuar();
                return;
            }

            AgendamentoResponseDTO agendamentoResponseDTO = agendamentoService.findById(idAgendamento);

            if (agendamentoResponseDTO.getIdCliente() != null && agendamentoResponseDTO.getIdCliente() > 0) {
                view.exibirErro("Este horário já está agendado!");
                Util.digiteEnterParaContinuar();
                return;
            }

            agendamentoResponseDTO.setIdCliente(this.sessao.getUsuarioLogado().getId());
            agendamentoService.update(idAgendamento, AgendamentoMapper.toRequestDTO(agendamentoResponseDTO));
            view.exibirSucesso("Agendamento realizado com sucesso!");
            Util.digiteEnterParaContinuar();

        } catch (Exception e) {
            view.exibirErro(e.getMessage());
            Util.next();
            Util.digiteEnterParaContinuar();
        }
    }
}