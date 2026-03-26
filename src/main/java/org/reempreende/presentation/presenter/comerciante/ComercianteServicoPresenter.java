package org.reempreende.presentation.presenter.comerciante;

import org.reempreende.application.dto.request.ServicoRequestDTO;
import org.reempreende.application.dto.response.ServicoResponseDTO;
import org.reempreende.application.service.*;
import org.reempreende.infrastructure.sessao.Sessao;
import org.reempreende.presentation.interfaces.icomerciante.IComercianteServicoView;
import org.reempreende.presentation.router.AppRouter;

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
        String descricao = view.askDescricao();
        double duracao = view.askDuracaoDeCadaAgendamento();

        ServicoRequestDTO servicoRequestDTO = new ServicoRequestDTO();
        servicoRequestDTO.setAvaliacao("Não possui avaliações ainda");
        servicoRequestDTO.setDescricao(descricao);
        servicoRequestDTO.setDuracaoHoras(duracao);
        servicoRequestDTO.setIdComerciante(this.sessao.getUsuarioLogado().getId());

        ServicoResponseDTO servicoResponseDTO = null;

        try {
            servicoResponseDTO = service.insertServico(servicoRequestDTO);
        } catch (Exception e) {
            view.exibirErro(e.getMessage());
            return;
        }


        appRouter.createAgendamentoComerciante(servicoResponseDTO);
    }

}
