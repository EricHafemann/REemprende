package org.reempreende.presentation.view.cliente;

import org.reempreende.infrastructure.utility.TextoUtil;
import org.reempreende.presentation.interfaces.icliente.IClienteViewHistorico;

public class ClienteViewHistorico implements IClienteViewHistorico {
    @Override
    public void verHistoricoAgendamentos() {

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
