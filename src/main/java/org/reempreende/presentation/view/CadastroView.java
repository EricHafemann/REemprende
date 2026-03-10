package org.reempreende.presentation.view;

import org.reempreende.domain.entities.enums.TipoUsuario;
import org.reempreende.infrastucture.utility.Util;
import org.reempreende.presentation.interfaces.icadastro.ICadastroView;

public class CadastroView implements ICadastroView {
    private final Util u = new Util();

    @Override
    public void exibirErro(String mensagem) {
        System.out.println(mensagem);
    }

    @Override
    public void exibirSucesso(String mensagem) {
        System.out.println(mensagem);
    }

    @Override
    public void sair(String mensagem) {
        System.out.println(mensagem);
    }


    @Override
    public String pedirNome() {
        System.out.println("Digite o seu nome: ");
        System.out.println("➤ ");
        return u.lString();
    }

    @Override
    public String pedirEmail() {
        System.out.println("Digite o seu e-mail: ");
        System.out.println("➤ ");
        return u.lString();
    }

    @Override
    public String pedirSenha() {
        System.out.println("Crie uma senha: ");
        System.out.println("➤ ");
        return u.lString();
    }

    @Override
    public int pedirTipoUsuario() {
        System.out.println("Selecione uma das opções abaixo:");

        System.out.println("1 - CLIENTE");
        System.out.println("2 - COMERCIANTE");
        System.out.println("➤ ");
        return u.lInt() - 1;
    }

}
