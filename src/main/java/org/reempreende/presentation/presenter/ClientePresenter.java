package org.reempreende.presentation.presenter;

import org.reempreende.application.dto.request.UsuarioRequestDTO;
import org.reempreende.application.service.ClienteService;
import org.reempreende.presentation.interfaces.icadastro.ICadastroClienteView;
import org.reempreende.presentation.router.AppRouter;

public class ClientePresenter {

    private final ICadastroClienteView cadastroView;
    private final ClienteService clienteService;
    private final AppRouter appRouter;

    public ClientePresenter(AppRouter appRouter, ICadastroClienteView cadastroView,
                            ClienteService clienteService) {
        this.appRouter = appRouter;
        this.cadastroView = cadastroView;
        this.clienteService = clienteService;
    }

    public void registerClient(UsuarioRequestDTO usuarioDTO) {
        String cpf = cadastroView.pedirCPF();

        usuarioDTO.setCpf(cpf);

        clienteService.cadastrar(usuarioDTO);
        cadastroView.exibirErro("Cliente cadastrado com sucesso no sistema!");

        appRouter.iniciarSistema();
    }
}
