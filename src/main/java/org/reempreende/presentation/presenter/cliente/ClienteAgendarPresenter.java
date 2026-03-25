package org.reempreende.presentation.presenter.cliente;

import org.reempreende.application.dto.mapper.AgendamentoMapper;
import org.reempreende.application.dto.request.AgendamentoRequestDTO;
import org.reempreende.application.dto.response.AgendamentoResponseDTO;
import org.reempreende.application.dto.response.ServicoResponseDTO;
import org.reempreende.application.dto.response.UsuarioResponseDTO;
import org.reempreende.application.exception.BusinessException;
import org.reempreende.application.service.AgendamentoService;
import org.reempreende.application.service.ClienteService;
import org.reempreende.application.service.ServicoService;
import org.reempreende.domain.entities.Agendamento;
import org.reempreende.domain.entities.Cliente;
import org.reempreende.domain.entities.Servico;
import org.reempreende.domain.repository.AgendamentoRepository;
import org.reempreende.domain.repository.ServicoAgendamentoRepository;
import org.reempreende.domain.repository.ServicoRepository;
import org.reempreende.infrastructure.repository.*;
import org.reempreende.infrastructure.sessao.Sessao;
import org.reempreende.infrastructure.utility.Cores;
import org.reempreende.presentation.exception.InvalidFieldException;
import org.reempreende.presentation.interfaces.icliente.IClienteViewAgendarDisponivel;
import org.reempreende.presentation.interfaces.icliente.IClienteViewHorarios;
import org.reempreende.presentation.router.AppRouter;
import org.reempreende.presentation.view.cliente.ClienteViewAgendarDisponivel;
import org.reempreende.presentation.view.cliente.ClienteViewHorarios;

import java.util.List;
import java.util.OptionalInt;
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

            List<AgendamentoResponseDTO> agendamentos =  agendamentoService.findAgendamentosByServicoId(idServico);

            for (AgendamentoResponseDTO agendamento : agendamentos) {
                view.exibirMensagem(agendamento.exibirInfo());
            }

            OptionalLong agendamentoCaixa = view.exibirMensagemTela();
            Long idAgendamento = agendamentoCaixa.orElse(-1);

            AgendamentoResponseDTO agendamentoResponseDTO = agendamentoService.findById(idAgendamento);

            if (agendamentoResponseDTO.getIdCliente() == null || idAgendamento != -1L) {
                agendamentoResponseDTO.setIdCliente(this.sessao.getUsuarioLogado().getId());

                agendamentoService.update(idAgendamento,
                        AgendamentoMapper.toRequestDTO(agendamentoResponseDTO));
            } else {
                view.exibirErro("Agendamento já possui Cliente!");
            }
        } catch (Exception e) {
            view.exibirErro(e.getMessage());
        }
    }

}
