package org.reempreende.presentation.view.comerciante;

import org.reempreende.infrastructure.utility.Cores;
import org.reempreende.infrastructure.utility.Util;
import org.reempreende.presentation.interfaces.icomerciante.IComercianteCriarAgendamentoView;

import java.time.LocalTime;
import java.util.OptionalInt;

public class ComercianteCriarAgendamentoView implements IComercianteCriarAgendamentoView {
    @Override
    public OptionalInt mostrarTela() {
        Util.cls(15);
        System.out.println(Cores.VERDE + "             ██████╗██████╗ ██╗ █████╗ ██████╗       █████╗  ██████╗ ███████╗███╗   ██╗██████╗  █████╗ ███╗   ███╗███████╗███╗   ██╗████████╗ ██████╗ " + Cores.RESET);
        System.out.println(Cores.VERDE + "            ██╔════╝██╔══██╗██║██╔══██╗██╔══██╗     ██╔══██╗██╔════╝ ██╔════╝████╗  ██║██╔══██╗██╔══██╗████╗ ████║██╔════╝████╗  ██║╚══██╔══╝██╔═══██╗" + Cores.RESET);
        System.out.println(Cores.VERDE + "            ██║     ██████╔╝██║███████║██████╔╝     ███████║██║  ███╗█████╗  ██╔██╗ ██║██║  ██║███████║██╔████╔██║█████╗  ██╔██╗ ██║   ██║   ██║   ██║" + Cores.RESET);
        System.out.println(Cores.VERDE + "            ██║     ██╔══██╗██║██╔══██║██╔══██╗     ██╔══██║██║   ██║██╔══╝  ██║╚██╗██║██║  ██║██╔══██║██║╚██╔╝██║██╔══╝  ██║╚██╗██║   ██║   ██║   ██║" + Cores.RESET);
        System.out.println(Cores.VERDE + "            ╚██████╗██║  ██║██║██║  ██║██║  ██║     ██║  ██║╚██████╔╝███████╗██║ ╚████║██████╔╝██║  ██║██║ ╚═╝ ██║███████╗██║ ╚████║   ██║   ╚██████╔╝" + Cores.RESET);
        System.out.println(Cores.VERDE + "             ╚═════╝╚═╝  ╚═╝╚═╝╚═╝  ╚═╝╚═╝  ╚═╝     ╚═╝  ╚═╝ ╚═════╝ ╚══════╝╚═╝  ╚═══╝╚═════╝ ╚═╝  ╚═╝╚═╝     ╚═╝╚══════╝╚═╝  ╚═══╝   ╚═╝    ╚═════╝ " + Cores.RESET);

        return OptionalInt.empty();
    }

    @Override
    public String askObservacao() {
        System.out.println("Digite uma observação para os agendamentos");
        System.out.print("➤ ");

        return Util.lString();
    }

    @Override
    public LocalTime askAbreAgendamento() {
        System.out.println("Digite as horas que abre o serviço");
        System.out.print("➤ ");
        int horas = Util.lInt();

        System.out.println("Digite os minutos que abre o serviço");
        System.out.print("➤ ");
        int minutos = Util.lInt();

        return LocalTime.of(horas, minutos);
    }

    @Override
    public LocalTime askFechaAgendamento() {
        System.out.println("Digite as horas que fecha o serviço");
        System.out.print("➤ ");
        int horas = Util.lInt();

        System.out.println("Digite os minutos que fecha o serviço");
        System.out.print("➤ ");
        int minutos = Util.lInt();

        return LocalTime.of(horas, minutos);
    }

}
