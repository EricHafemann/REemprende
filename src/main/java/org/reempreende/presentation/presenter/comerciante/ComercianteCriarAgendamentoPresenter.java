package org.reempreende.presentation.presenter.comerciante;

import org.reempreende.application.dto.request.AgendamentoRequestDTO;
import org.reempreende.application.dto.response.ServicoResponseDTO;
import org.reempreende.application.service.AgendamentoService;
import org.reempreende.infrastructure.sessao.Sessao;
import org.reempreende.presentation.interfaces.icomerciante.IComercianteCriarAgendamentoView;
import org.reempreende.presentation.router.AppRouter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class ComercianteCriarAgendamentoPresenter {
    private final AppRouter appRouter;
    private final Sessao sessao;
    private final AgendamentoService agendamentoService;
    private final IComercianteCriarAgendamentoView view;

    public ComercianteCriarAgendamentoPresenter(AppRouter appRouter,
                                                Sessao sessao, AgendamentoService agendamentoService,
                                                IComercianteCriarAgendamentoView view) {
        this.appRouter = appRouter;
        this.sessao = sessao;
        this.agendamentoService = agendamentoService;
        this.view = view;
    }

    public void createAgendamentoViaServico(ServicoResponseDTO servicoResponseDTO) {
        LocalTime abre = view.askAbreAgendamento();
        LocalTime fecha = view.askFechaAgendamento();

        String observacao = view.askObservacao();

        while (abre.isBefore(fecha)) {
            AgendamentoRequestDTO agendamentoRequestDTO = new AgendamentoRequestDTO();

            LocalTime fechaNovo = abre.plusMinutes((long) (servicoResponseDTO.getDuracaoHoras() * 60));

            agendamentoRequestDTO.setDataInicio(abre.atDate(LocalDate.now()));
            agendamentoRequestDTO.setDataFim(fechaNovo.atDate(LocalDate.now()));
            agendamentoRequestDTO.setObservacao(observacao);
            agendamentoRequestDTO.setIdCliente(null);

            createAgendamento(agendamentoRequestDTO, servicoResponseDTO.getIdServico());
            abre = abre.plusMinutes((long) (servicoResponseDTO.getDuracaoHoras() * 60));
        }

    }

    public void createAgendamento(AgendamentoRequestDTO agendamentoRequestDTO, long idServico) {
        try {
            agendamentoService.insertAgendamento(agendamentoRequestDTO, idServico);
        } catch (Exception e) {
            view.exibirErro(e.getMessage());
            return;
        }

        view.exibirSucesso("Agendamento criado com sucesso!");
    }
}
