package org.reempreende.presentation.interfaces.icliente;

import org.reempreende.application.dto.response.AgendamentoResponseDTO;
import org.reempreende.presentation.interfaces.InterfaceView;

public interface IClienteViewHorarios extends InterfaceView {
    void exibirHorarios(AgendamentoResponseDTO agendamento);
}
