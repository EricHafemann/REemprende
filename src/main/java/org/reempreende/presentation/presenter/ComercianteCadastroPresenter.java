package org.reempreende.presentation.presenter;

import org.reempreende.application.dto.request.UsuarioRequestDTO;
import org.reempreende.application.service.ComercianteService;
import org.reempreende.presentation.interfaces.icadastro.ICadastroComercianteView;
import org.reempreende.presentation.router.AppRouter;

public class ComercianteCadastroPresenter {
    private final AppRouter appRouter;
    private final ComercianteService comercianteService;
    private final ICadastroComercianteView cadastroComercianteView;

    public ComercianteCadastroPresenter(AppRouter appRouter, ComercianteService comercianteService,
                                        ICadastroComercianteView cadastroComercianteView) {
        this.appRouter = appRouter;
        this.comercianteService = comercianteService;
        this.cadastroComercianteView = cadastroComercianteView;
    }

    public void registerComerciante(UsuarioRequestDTO usuarioDTO) {
        String cnpj = cadastroComercianteView.pedirCNPJ();
        String senhaAcesso = cadastroComercianteView.pedirSenhaAcesso();

        usuarioDTO.setCnpj(cnpj);
        usuarioDTO.setSenhaAcesso(senhaAcesso);

        comercianteService.insert(usuarioDTO);
        cadastroComercianteView.exibirSucesso("Comerciante cadastrado com sucesso!");
    }
}
