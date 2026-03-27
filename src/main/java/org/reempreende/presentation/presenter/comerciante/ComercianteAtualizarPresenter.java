package org.reempreende.presentation.presenter.comerciante;

import org.reempreende.application.dto.request.UsuarioRequestDTO;
import org.reempreende.application.dto.request.UsuarioUpdateDTO;
import org.reempreende.application.service.ComercianteService;
import org.reempreende.infrastructure.sessao.Sessao;
import org.reempreende.infrastructure.utility.Util;
import org.reempreende.presentation.interfaces.icomerciante.IComercianteAtualizarView;

import java.util.OptionalInt;

public class ComercianteAtualizarPresenter {
    private IComercianteAtualizarView view;
    private ComercianteService comercianteService;
    private Sessao sessao;

    public ComercianteAtualizarPresenter(IComercianteAtualizarView view, ComercianteService comercianteService, Sessao sessao) {
        this.view = view;
        this.comercianteService = comercianteService;
        this.sessao = sessao;
    }

    public void atualizarComerciante() {
        boolean continuar = true;

        while (continuar) {
            try {
                OptionalInt opcaoCaixa = view.mostrarTela();

                int opcao = opcaoCaixa.orElse(-1);

                switch (opcao) {
                    case 1 -> {
                        String nome = view.newNome();

                        if (nome == null || nome.length() < 2 || nome.length() > 100) {
                            view.exibirErro("Nome inválido! Deve ter entre 2 e 100 caracteres.");
                            Util.digiteEnterParaContinuar();
                            return;
                        }

                        UsuarioUpdateDTO dto = new UsuarioUpdateDTO();
                        dto.setNome(nome);

                        comercianteService.update(sessao.getUsuarioLogado().getId(), dto);
                        view.exibirSucesso("Nome atualizado com sucesso!");
                    }
                    case 2 -> {
                        String email = view.newEmail();

                        UsuarioUpdateDTO dto = new UsuarioUpdateDTO();
                        dto.setEmail(email);

                        comercianteService.update(sessao.getUsuarioLogado().getId(), dto);
                        view.exibirSucesso("E-mail atualizado com sucesso!");
                        Util.digiteEnterParaContinuar();
                    }
                    case 3 -> {
                        String senha = view.newPassword();

                        UsuarioUpdateDTO dto = new UsuarioUpdateDTO();
                        dto.setSenha(senha);

                        comercianteService.update(sessao.getUsuarioLogado().getId(), dto);
                        view.exibirSucesso("Senha atualizada com sucesso!");
                        Util.digiteEnterParaContinuar();
                    }
                    case 0 -> {
                        view.exibirMensagem("Voltando...");
                        Util.cls();
                        Util.delay(1500);
                        continuar = false;
                    }
                    default -> {
                        view.exibirErro("Opção inválida! Tente novamente:");
                        Util.digiteEnterParaContinuar();
                    }
                }
            } catch (Exception e) {
                view.exibirErro(e.getMessage());
                Util.next();
                Util.digiteEnterParaContinuar();
            }
        }
    }

    public void createUsuarioDto(String nome, String email, String senha, String senhaAcesso) {
        UsuarioRequestDTO usuarioRequestDTO = new UsuarioRequestDTO();
        usuarioRequestDTO.setNome(nome);
        usuarioRequestDTO.setEmail(email);
        usuarioRequestDTO.setSenha(senha);
        usuarioRequestDTO.setSenhaAcesso(senhaAcesso);
    }

}
