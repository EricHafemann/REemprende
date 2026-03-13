package org.reempreende.presentation.view.cliente;

import org.reempreende.infrastructure.utility.Util;
import org.reempreende.presentation.interfaces.icadastro.ICadastroClienteView;
import org.reempreende.presentation.view.cadastro.CadastroBaseView;

import java.util.OptionalInt;

public class CadastroClienteView extends CadastroBaseView implements ICadastroClienteView {
    private final Util u = new Util();

    @Override
    public String pedirCPF() {
        System.out.println("Digite o seu CPF:");
        System.out.print("➤ ");
        return u.lString();
    }

    @Override
    public OptionalInt mostrarTela() {
        return OptionalInt.empty();
    }
}
