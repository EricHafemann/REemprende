package org.reempreende.presentation.view.usuario;

import org.reempreende.presentation.interfaces.icadastro.ICadastroView;

public abstract class CadastroBaseView implements ICadastroView {
    @Override
    public String pedirNome() {
        return "";
    }

    @Override
    public String pedirEmail() {
        return "";
    }

    @Override
    public String pedirSenha() {
        return "";
    }

    @Override
    public int pedirTipoUsuario() {
        return 0;
    }

    @Override
    public int pedirStatus() {
        return 0;
    }

    @Override
    public String pedirCNPJ() {
        return "";
    }

    @Override
    public String pedirSenhaAcesso() {
        return "";
    }

    @Override
    public void exibirErro(String mensagem) {

    }

    @Override
    public void exibirSucesso(String mensagem) {

    }

    @Override
    public void sair(String mensagem) {

    }
}
