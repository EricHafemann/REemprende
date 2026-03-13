package org.reempreende.presentation.view.cliente;

import org.reempreende.infrastructure.utility.TextoUtil;
import org.reempreende.presentation.interfaces.icliente.IClienteViewAgendarDisponivel;

import java.util.OptionalInt;

public class ClienteViewAgendarDisponivel implements IClienteViewAgendarDisponivel {
    @Override
    public void exibirHorariosDisponiveis() {

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
