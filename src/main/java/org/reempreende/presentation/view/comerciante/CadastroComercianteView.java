package org.reempreende.presentation.view.comerciante;

import org.reempreende.infrastructure.utility.Util;
import org.reempreende.presentation.interfaces.icadastro.ICadastroView;

public class CadastroComercianteView implements ICadastroView {
    private final Util u = new Util();

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
    public int pedirStatus() {
        return 0;
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
