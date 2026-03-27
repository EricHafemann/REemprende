package org.reempreende.presentation.presenter.cliente;

import org.reempreende.application.dto.request.AgendamentoRequestDTO;
import org.reempreende.application.dto.response.AgendamentoResponseDTO;
import org.reempreende.application.service.AgendamentoService;
import org.reempreende.infrastructure.repository.AgendamentoRepositoryImpl;
import org.reempreende.infrastructure.repository.ServicoAgendamentoRepositoryImpl;
import org.reempreende.infrastructure.sessao.Sessao;
import org.reempreende.infrastructure.utility.Util;
import org.reempreende.presentation.interfaces.icliente.IClienteCancelarAgendamentoView;
import org.reempreende.presentation.router.AppRouter;
import org.reempreende.presentation.view.cliente.ClienteCancelarAgendamentoView;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

public class ClienteCancelarAgendamentoPresenter {
    private AppRouter appRouter;
    private Sessao sessao;
    private AgendamentoService agendamentoService;
    private IClienteCancelarAgendamentoView view;

    public ClienteCancelarAgendamentoPresenter(AppRouter appRouter, Sessao sessao, AgendamentoService agendamentoService,
                                               IClienteCancelarAgendamentoView view) {
        this.appRouter = appRouter;
        this.sessao = sessao;
        this.agendamentoService = agendamentoService;
        this.view = view;
    }

    public void cancelarAgendamento() {
        view.mostrarTela();

        try {
            List<AgendamentoResponseDTO> agendamentos = agendamentoService.findByClientId(sessao.getUsuarioLogado().getId());

            for (AgendamentoResponseDTO agendamentoResponseDTO : agendamentos) {
              view.exibirHorarioCliente(agendamentoResponseDTO.exibirInfo());
            }

            long idAgendamento = view.askIdAgendamento();

            AgendamentoResponseDTO agendamentoResponseDTO = agendamentoService.findById(idAgendamento);

            if (!Objects.equals(agendamentoResponseDTO.getIdCliente(), sessao.getUsuarioLogado().getId()) ||
                    agendamentoResponseDTO.getIdCliente() == null) {
                    view.exibirErro("Agendamento não é do Cliente!");
                    return;
            }

            LocalDateTime agora = LocalDateTime.now();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            LocalDateTime dataInicioAgendamento = LocalDateTime.parse(agendamentoResponseDTO.getDataInicio(), formatter);
            dataInicioAgendamento.minusHours(1);

            if (dataInicioAgendamento.isBefore(agora)) {
                dataInicioAgendamento.plusHours(1);

                AgendamentoRequestDTO agendamentoRequestDTO = new AgendamentoRequestDTO();
                agendamentoRequestDTO.setDataInicio(dataInicioAgendamento);
                agendamentoRequestDTO.setDataFim(LocalDateTime.parse(agendamentoResponseDTO.getDataFim(), formatter));
                agendamentoRequestDTO.setObservacao(agendamentoResponseDTO.getObservacao());
                agendamentoRequestDTO.setIdCliente(null);

                boolean deuCerto = agendamentoService.cancelAgendamento(idAgendamento, agendamentoRequestDTO);

                if (deuCerto) {
                    view.exibirSucesso("Agendamento cancelado com sucesso!");
                } else {
                    view.exibirErro("Agendamento não foi cancelado!");
                }

                Util.digiteEnterParaContinuar();
            }
        } catch (Exception e) {
            view.exibirErro(e.getMessage());
            Util.digiteEnterParaContinuar();
        }
    }

}
