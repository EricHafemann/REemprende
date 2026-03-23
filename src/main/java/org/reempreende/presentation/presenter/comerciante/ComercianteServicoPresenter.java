package org.reempreende.presentation.presenter.comerciante;

import org.reempreende.application.dto.mapper.UsuarioMapper;
import org.reempreende.application.dto.request.AgendamentoRequestDTO;
import org.reempreende.application.dto.request.ServicoRequestDTO;
import org.reempreende.application.dto.request.UsuarioRequestDTO;
import org.reempreende.application.dto.response.UsuarioResponseDTO;
import org.reempreende.application.service.*;
import org.reempreende.domain.entities.Comerciante;
import org.reempreende.domain.entities.Servico;
import org.reempreende.domain.repository.*;
import org.reempreende.infrastructure.repository.*;
import org.reempreende.infrastructure.sessao.Sessao;
import org.reempreende.presentation.interfaces.icomerciante.IComercianteServicoView;
import org.reempreende.presentation.router.AppRouter;

import java.time.LocalDate;
import java.time.LocalTime;

public class ComercianteServicoPresenter {
    private AppRouter appRouter;
    private IComercianteServicoView view;
    private ServicoService service;
    private Sessao sessao;
    private ComercianteService comercianteService;

    public ComercianteServicoPresenter(AppRouter appRouter, IComercianteServicoView view,
                                       ServicoService service, ComercianteService comercianteService,
                                       Sessao sessao) {
        this.appRouter = appRouter;
        this.view = view;
        this.service = service;
        this.comercianteService = comercianteService;
        this.sessao = sessao;
    }

    public void createServico() {
        LocalTime abre = view.askHorarioAbertura();
        LocalTime fecha = view.askHorarioFechar();

        double duracao = view.askDuracaoDeCadaAgendamento();

        String descricao = view.askDescricao();

        ServicoRequestDTO servicoRequestDTO = new ServicoRequestDTO();
        servicoRequestDTO.setAvaliacao("Não possui avaliações ainda");
        servicoRequestDTO.setDescricao(descricao);
        servicoRequestDTO.setDuracaoHoras(duracao);
        servicoRequestDTO.setIdComerciante(this.sessao.getUsuarioLogado().getId());

        try {
            service.insertServico(servicoRequestDTO);
        } catch (Exception e) {
            view.exibirErro(e.getMessage());
            return;
        }

        AgendamentoRequestDTO agendamentoRequestDTO = new AgendamentoRequestDTO();

        agendamentoRequestDTO.setDataInicio(abre.atDate(LocalDate.now()));
        agendamentoRequestDTO.setDataFim(fecha.atDate(LocalDate.now()));
        agendamentoRequestDTO.setObservacao("Teste");
        agendamentoRequestDTO.setIdCliente(null);

        appRouter.createAgendamentoComerciante(false, agendamentoRequestDTO);
    }

}
