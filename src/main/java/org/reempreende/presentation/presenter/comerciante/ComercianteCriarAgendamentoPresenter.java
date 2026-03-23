package org.reempreende.presentation.presenter.comerciante;

import org.reempreende.application.dto.request.AgendamentoRequestDTO;
import org.reempreende.application.service.AgendamentoService;
import org.reempreende.application.service.ServicoService;
import org.reempreende.domain.entities.Agendamento;
import org.reempreende.domain.repository.AgendamentoRepository;
import org.reempreende.infrastructure.repository.AgendamentoRepositoryImpl;
import org.reempreende.infrastructure.repository.ServicoAgendamentoRepositoryImpl;
import org.reempreende.infrastructure.sessao.Sessao;
import org.reempreende.presentation.interfaces.icomerciante.IComercianteCriarAgendamentoView;
import org.reempreende.presentation.interfaces.icomerciante.IComercianteServicoView;
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

    public void createAgendamentoViaServico(long duracao) {
        LocalTime abre = view.askAbreAgendamento();
        LocalTime fecha = view.askFechaAgendamento();

        String observacao = view.askObservacao();

        AgendamentoRequestDTO agendamentoRequestDTO = new AgendamentoRequestDTO();

        while (abre.isBefore(fecha)) {
            agendamentoRequestDTO.setDataInicio(abre.atDate(LocalDate.now()));
            agendamentoRequestDTO.setDataFim(abre.plusMinutes(duracao).atDate(LocalDate.now()));
            agendamentoRequestDTO.setObservacao(observacao);
            agendamentoRequestDTO.setIdCliente(null);

            createAgendamento(agendamentoRequestDTO);
            abre = abre.plusMinutes(duracao * 60);
        }

    }

    public void createAgendamento(AgendamentoRequestDTO agendamentoRequestDTO) {
        try {
            agendamentoService.insertAgendamento(agendamentoRequestDTO);
        } catch (Exception e) {
            view.exibirErro(e.getMessage());
            return;
        }

        view.exibirSucesso("Agendamentos criados com sucesso!");
    }
}
