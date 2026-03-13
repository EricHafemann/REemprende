package org.reempreende.presentation.view.comerciante;

import org.reempreende.infrastructure.utility.TextoUtil;
import org.reempreende.infrastructure.utility.Util;
import org.reempreende.presentation.interfaces.icadastro.ICadastroComercianteView;
import org.reempreende.presentation.interfaces.icadastro.ICadastroView;
import org.reempreende.presentation.view.usuario.CadastroBaseView;

public class CadastroComercianteView extends CadastroBaseView implements ICadastroComercianteView {
    private final Util u = new Util();


    @Override
    public String pedirCNPJ() {
        System.out.println("Digite o seu CNPJ:");
        System.out.print("➤ ");

        return u.lString();
    }

    @Override
    public String pedirSenhaAcesso() {
        System.out.println("Crie uma senha de Acesso:");
        System.out.print("➤ ");

        return u.lString();
    }

    @Override
    public void exibirMensagem(String mensagem) {
        System.out.println(TextoUtil.transformar(mensagem));
    }
}
