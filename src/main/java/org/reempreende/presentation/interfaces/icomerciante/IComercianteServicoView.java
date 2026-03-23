package org.reempreende.presentation.interfaces.icomerciante;

import org.reempreende.presentation.interfaces.InterfaceView;

import java.time.LocalTime;

public interface IComercianteServicoView extends InterfaceView  {
    String askDescricao();
    double askDuracaoDeCadaAgendamento();
    LocalTime askHorarioAbertura();
    LocalTime askHorarioFechar();
}
