package org.reempreende.presentation.view.comerciante;

import org.reempreende.infrastructure.utility.Util;
import org.reempreende.presentation.interfaces.icomerciante.IComercianteServicoView;

import java.time.LocalTime;
import java.util.OptionalInt;

public class ComercianteServicoView implements IComercianteServicoView {
    private static final Util u = new Util();

    @Override
    public OptionalInt mostrarTela() {
        System.out.println("            ███████╗███████╗██████╗ ██╗   ██╗██╗  ██████╗  ██████╗ ");
        System.out.println("            ██╔════╝██╔════╝██╔══██╗██║   ██║██║ ██╔════╝ ██╔═══██╗");
        System.out.println("            ███████╗█████╗  ██████╔╝██║   ██║██║ ██║      ██║   ██║");
        System.out.println("            ╚════██║██╔══╝  ██╔══██╗╚██╗ ██╔╝██║ ██║      ██║   ██║");
        System.out.println("            ███████║███████╗██║  ██║ ╚████╔╝ ██║ ╚██████╗ ╚██████╔╝");
        System.out.println("            ╚══════╝╚══════╝╚═╝  ╚═╝  ╚═══╝  ╚═╝  ╚═════╝  ╚═════╝ ");
        System.out.println("                                                    ╚═╝            ");

        return OptionalInt.empty();
    }


    @Override
    public String askDescricao() {
        System.out.println("Digite uma descrição para o serviço");
        System.out.print("➤ ");

        return u.lString();
    }

    @Override
    public double askDuracaoDeCadaAgendamento() {
        System.out.println("Digite a duração de cada agendamento");
        System.out.print("➤ ");

        return u.lDouble();
    }

    @Override
    public LocalTime askHorarioAbertura() {
        System.out.println("Digite as horas que abre o serviço");
        System.out.print("➤ ");
        int horas = u.lInt();

        System.out.println("Digite os minutos que abre o serviço");
        System.out.print("➤ ");
        int minutos = u.lInt();

        return LocalTime.of(horas, minutos);
    }

    @Override
    public LocalTime askHorarioFechar() {
        System.out.println("Digite as horas que fecha o serviço");
        System.out.print("➤ ");
        int horas = u.lInt();

        System.out.println("Digite os minutos que fecha o serviço");
        System.out.print("➤ ");
        int minutos = u.lInt();

        return LocalTime.of(horas, minutos);
    }
}
