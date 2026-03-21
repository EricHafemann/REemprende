package org.reempreende.presentation.presenter.cliente;

import org.reempreende.application.dto.mapper.UsuarioMapper;
import org.reempreende.application.dto.request.UsuarioRequestDTO;
import org.reempreende.application.dto.response.UsuarioResponseDTO;
import org.reempreende.application.service.ClienteService;
import org.reempreende.infrastructure.sessao.Sessao;
import org.reempreende.infrastructure.utility.Util;
import org.reempreende.presentation.interfaces.icliente.IClienteAtualizarView;
import org.reempreende.presentation.router.AppRouter;

import java.util.OptionalInt;

public class ClienteAtualizarPresenter {
    private final Util u = new Util();

    private final AppRouter appRouter;
    private final Sessao sessao;
    private final IClienteAtualizarView view;
    private final ClienteService clienteService;

    public ClienteAtualizarPresenter(AppRouter appRouter, Sessao sessao, IClienteAtualizarView view, ClienteService clienteService) {
        this.appRouter = appRouter;
        this.sessao = sessao;
        this.view = view;
        this.clienteService = clienteService;
    }

    public void update() {
        try {
            OptionalInt opcaoCaixa = view.mostrarTela();

            int opcao = opcaoCaixa.orElse(-1);

            switch (opcao) {
                case 1 -> {
                    String nome = view.newNome();

                    if (nome == null || nome.length() < 2 || nome.length() > 1000) {
                        view.exibirErro("Nome inválido!");
                        update();
                    }

                    UsuarioRequestDTO usuarioRequestDTO = new UsuarioRequestDTO();
                    usuarioRequestDTO.setSenha(nome);

                    clienteService.update(sessao.getUsuarioLogado().getId(), usuarioRequestDTO);
                } case 2 -> {
                    String email = view.newEmail();

                    if (email == null || email.isBlank() || !email.contains("@") || email.length() > 254) {
                        view.exibirErro("E-mail inválido!");
                        update();
                    }

                    UsuarioRequestDTO usuarioRequestDTO = new UsuarioRequestDTO();
                    usuarioRequestDTO.setSenha(email);

                    clienteService.update(sessao.getUsuarioLogado().getId(), usuarioRequestDTO);
                } case 3 -> {
                    String senha = view.newPassword();

                    if (senha == null || senha.length() < 8 || senha.isBlank()) {
                        view.exibirErro("Erro! Senha menor do que 8 dígitos!");
                        update();
                    }

                    String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[^A-Za-z\\d]).+$";

                    if (!senha.matches(regex)) {
                        view.exibirErro("Senha inválida! Não possui letra maíscula ou minúscula, ou números e carácteres especiais");
                        update();
                    }

                    UsuarioRequestDTO usuarioRequestDTO = new UsuarioRequestDTO();
                    usuarioRequestDTO.setSenha(senha);

                    clienteService.update(sessao.getUsuarioLogado().getId(), usuarioRequestDTO);
                } case 0 -> {
                    view.exibirMensagem("Voltando...");
                    u.cls();
                    u.delay(1500);
                } default -> {
                    view.exibirErro("Opção inválida! Tente novamente:");
                    update();
                }
            }
        } catch (Exception e) {
            view.exibirErro(e.getMessage());
            update();
        }
    }

}
