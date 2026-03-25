package org.reempreende.presentation.presenter.servico;

import org.reempreende.application.dto.mapper.UsuarioMapper;
import org.reempreende.application.dto.request.ServicoRequestDTO;
import org.reempreende.application.dto.request.UsuarioRequestDTO;
import org.reempreende.application.exception.BusinessException;
import org.reempreende.application.service.ServicoService;
import org.reempreende.domain.entities.Comerciante;
import org.reempreende.presentation.interfaces.iservico.IServicoView;
import org.reempreende.presentation.router.AppRouter;

public class ServicoPresenter
{
    private final AppRouter appRouter;
    private final ServicoService servicoService;
    private final IServicoView servicoView;

    public ServicoPresenter(AppRouter appRouter, ServicoService servicoService, IServicoView servicoView)
    {
        this.appRouter = appRouter;
        this.servicoService = servicoService;
        this.servicoView = servicoView;
    }


    public void registerServico(UsuarioRequestDTO usuarioRequestDTO, long idComerciante)
    {
        try {
            String descricao = servicoView.pedirDescricao();
            double duracaoHoras = servicoView.pedirDuracaoHoras();

            Comerciante comerciante = (Comerciante) UsuarioMapper.toEntity(usuarioRequestDTO,idComerciante);

            ServicoRequestDTO servicoRequestDTOs = new ServicoRequestDTO(null, descricao, duracaoHoras, comerciante.getId());

            servicoService.insertServico(servicoRequestDTOs);
            servicoView.exibirSucesso("Serviço cadastrado com sucesso!!!");
        }

        catch (BusinessException e)
        {
            servicoView.exibirErro("Erro ao registrar servico: " + e);
        }
    }
}
