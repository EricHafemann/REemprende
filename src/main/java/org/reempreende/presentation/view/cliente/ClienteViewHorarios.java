package org.reempreende.presentation.view.cliente;

import org.reempreende.infrastructure.utility.TextoUtil;
import org.reempreende.presentation.interfaces.icliente.IClienteViewHorarios;

import java.util.OptionalInt;

public class ClienteViewHorarios implements IClienteViewHorarios {
    @Override
    public void exibirHorarios() {

    }

    @Override
    public OptionalInt mostrarTela() {
        return OptionalInt.empty();
    }

    @Override
    public void exibirErro(String mensagem) {
        System.out.println(TextoUtil.transformar(mensagem));
    }

    @Override
    public void exibirSucesso(String mensagem) {
        System.out.println(TextoUtil.transformar(mensagem));
    }

    @Override
    public void sair(String mensagem) {
        System.out.println(TextoUtil.transformar(mensagem));
    }

    @Override
    public void exibirMensagem(String mensagem) {
        System.out.println(TextoUtil.transformar(mensagem));
    }
}
