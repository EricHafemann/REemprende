package org.reempreende.presentation.interfaces.icomerciante;

import org.reempreende.presentation.interfaces.InterfaceView;

import java.time.LocalTime;

public interface IComercianteCriarAgendamentoView extends InterfaceView {
    String askObservacao();
    LocalTime askAbreAgendamento();
    LocalTime askFechaAgendamento();
}
