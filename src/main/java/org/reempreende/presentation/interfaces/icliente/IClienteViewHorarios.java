package org.reempreende.presentation.interfaces.icliente;

import org.reempreende.presentation.interfaces.interfaceBase.InterfaceView;

public interface IClienteViewHorarios extends InterfaceView {
    void exibirHorarios(String mensagem);
    long askIdServico();
}
