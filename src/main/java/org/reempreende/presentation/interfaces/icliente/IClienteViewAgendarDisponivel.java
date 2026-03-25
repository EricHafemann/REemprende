package org.reempreende.presentation.interfaces.icliente;

import org.reempreende.presentation.interfaces.interfaceBase.InterfaceView;

import java.util.OptionalLong;

public interface IClienteViewAgendarDisponivel extends InterfaceView {
    void exibirHorariosDisponiveis(String mensagem);
    OptionalLong exibirMensagemTela();
    OptionalLong askServico();
}
