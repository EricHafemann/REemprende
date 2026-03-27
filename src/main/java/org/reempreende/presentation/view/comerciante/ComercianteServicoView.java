package org.reempreende.presentation.view.comerciante;

import org.reempreende.infrastructure.utility.Cores;
import org.reempreende.infrastructure.utility.Util;
import org.reempreende.presentation.interfaces.icomerciante.IComercianteServicoView;

import java.util.OptionalInt;

public class ComercianteServicoView implements IComercianteServicoView {
    @Override
    public OptionalInt mostrarTela() {
        System.out.println(Cores.VERDE +"            ███████╗███████╗██████╗ ██╗   ██╗██╗  ██████╗  ██████╗ " + Cores.RESET);
        System.out.println(Cores.VERDE +"            ██╔════╝██╔════╝██╔══██╗██║   ██║██║ ██╔════╝ ██╔═══██╗" + Cores.RESET);
        System.out.println(Cores.VERDE +"            ███████╗█████╗  ██████╔╝██║   ██║██║ ██║      ██║   ██║" + Cores.RESET);
        System.out.println(Cores.VERDE +"            ╚════██║██╔══╝  ██╔══██╗╚██╗ ██╔╝██║ ██║      ██║   ██║" + Cores.RESET);
        System.out.println(Cores.VERDE +"            ███████║███████╗██║  ██║ ╚████╔╝ ██║ ╚██████╗ ╚██████╔╝" + Cores.RESET);
        System.out.println(Cores.VERDE +"            ╚══════╝╚══════╝╚═╝  ╚═╝  ╚═══╝  ╚═╝  ╚═════╝  ╚═════╝ " + Cores.RESET);
        System.out.println(Cores.VERDE +"                                                    ╚═╝            " + Cores.RESET);

        return OptionalInt.empty();
    }


    @Override
    public String askDescricao() {
        System.out.println("Digite uma descrição para o serviço");
        System.out.print("➤ ");

        return Util.lString();
    }

    @Override
    public double askDuracaoDeCadaAgendamento() {
        System.out.println("Digite a duração de cada agendamento (Em horas)");
        System.out.print("➤ ");

        return Util.lDouble();
    }

}
