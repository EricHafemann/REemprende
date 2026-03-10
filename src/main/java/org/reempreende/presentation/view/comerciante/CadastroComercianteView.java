package org.reempreende.presentation.view.comerciante;

import org.reempreende.infrastucture.utility.Util;
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
    public int pedirTipoUsuario() {
        return 0;
    }

    @Override
    public int pedirStatus() {
        return 0;
    }

    @Override
    public String pedirCNPJ() {
        System.out.println("Digite o seu CNPJ");
        System.out.print("➤ ");
        return u.lString();
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
