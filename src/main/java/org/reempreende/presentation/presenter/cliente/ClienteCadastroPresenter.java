package org.reempreende.presentation.presenter.cliente;

import org.reempreende.application.dto.request.UsuarioRequestDTO;
import org.reempreende.application.service.ClienteService;
import org.reempreende.infrastructure.utility.Util;
import org.reempreende.presentation.interfaces.icadastro.ICadastroClienteView;
import org.reempreende.presentation.router.AppRouter;

public class ClienteCadastroPresenter {
    private final ICadastroClienteView cadastroView;
    private final ClienteService clienteService;
    private final AppRouter appRouter;

    public ClienteCadastroPresenter(AppRouter appRouter, ICadastroClienteView cadastroView,
                                    ClienteService clienteService) {
        this.appRouter = appRouter;
        this.cadastroView = cadastroView;
        this.clienteService = clienteService;
    }

    public void registerClient(UsuarioRequestDTO usuarioDTO) {
        try {
            String cpf = cadastroView.pedirCPF();

            usuarioDTO.setCpf(cpf);

            clienteService.insert(usuarioDTO);

            cadastroView.exibirSucesso("Cliente cadastrado com sucesso no sistema!");

            appRouter.startSystem();
        } catch (IllegalArgumentException e) {
            cadastroView.exibirErro(e.getMessage());
            Util.next();
            Util.digiteEnterParaContinuar();
        }
    }

}
