package org.reempreende.presentation.view.cadastro;

import org.reempreende.domain.entities.enums.Status;
import org.reempreende.infrastructure.utility.TextoUtil;
import org.reempreende.infrastructure.utility.Util;
import org.reempreende.presentation.interfaces.icadastro.ICadastroView;

import java.util.OptionalInt;

public class CadastroBaseView implements ICadastroView {
    @Override
    public String pedirNome() {
        System.out.println("Digite o seu nome:");
        System.out.print("➤ ");
        return Util.lString();
    }

    @Override
    public String pedirEmail() {
        System.out.println("Digite o seu e-mail:");
        System.out.print("➤ ");
        return Util.lString();
    }

    @Override
    public String pedirSenha() {
        System.out.println("(8 dígitos, com no mínimo uma letra maiúscula, minúscula e carácter especial)");
        System.out.println("Crie uma senha:");
        System.out.print("➤ ");
        return Util.lString();
    }


    @Override
    public int pedirStatus() {
        Status[] status = Status.values();

        System.out.println("Selecione um dos estados:");

        for (Status s : status) {
            System.out.printf("%n%s : %d", s.name(), s.getCodigo());
        }
        System.out.print("➤ ");

        return Util.lInt();
    }

    @Override
    public OptionalInt mostrarTela() {
        return OptionalInt.empty();
    }

    @Override
    public void exibirErro(String mensagem) {
        throw new RuntimeException(mensagem);
    }

    @Override
    public void exibirSucesso(String mensagem) {
        System.out.println(mensagem);
    }

    @Override
    public void sair(String mensagem) {
        System.out.println("Saindo...");
        Util.delay(2000);
    }

    @Override
    public void exibirMensagem(String mensagem) {
        System.out.println(TextoUtil.transformar(mensagem));
    }

}
