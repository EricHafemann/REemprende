package org.reempreende.presentation.interfaces.icliente;

import org.reempreende.presentation.interfaces.interfaceBase.InterfaceView;

public interface IClienteCancelarAgendamentoView extends InterfaceView {
    void exibirHorarioCliente(String mensagem);
    long askHorarioId();
}
