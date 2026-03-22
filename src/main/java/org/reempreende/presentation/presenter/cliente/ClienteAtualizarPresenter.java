package org.reempreende.presentation.presenter.cliente;

import org.reempreende.application.dto.request.UsuarioUpdateDTO;
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

                    if (nome == null || nome.length() < 2 || nome.length() > 100) {
                        view.exibirErro("Nome inválido! Deve ter entre 2 e 100 caracteres.");
                        update();
                        return;
                    }

                    UsuarioUpdateDTO dto = new UsuarioUpdateDTO();
                    dto.setNome(nome);

                    clienteService.update(sessao.getUsuarioLogado().getId(), dto);
                    view.exibirSucesso("Nome atualizado com sucesso!");
                }
                case 2 -> {
                    String email = view.newEmail();

                    UsuarioUpdateDTO dto = new UsuarioUpdateDTO();
                    dto.setEmail(email);

                    clienteService.update(sessao.getUsuarioLogado().getId(), dto);
                    view.exibirSucesso("E-mail atualizado com sucesso!");
                }
                case 3 -> {
                    String senha = view.newPassword();

                    UsuarioUpdateDTO dto = new UsuarioUpdateDTO();
                    dto.setSenha(senha);

                    clienteService.update(sessao.getUsuarioLogado().getId(), dto);
                    view.exibirSucesso("Senha atualizada com sucesso!");
                }
                case 0 -> {
                    view.exibirMensagem("Voltando...");
                    u.cls();
                    u.delay(1500);
                }
                default -> {
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