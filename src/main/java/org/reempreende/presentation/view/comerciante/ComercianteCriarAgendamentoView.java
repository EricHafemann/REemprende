package org.reempreende.presentation.view.comerciante;

import org.reempreende.infrastructure.utility.Util;
import org.reempreende.presentation.interfaces.icomerciante.IComercianteCriarAgendamentoView;

import java.time.LocalTime;
import java.util.OptionalInt;

public class ComercianteCriarAgendamentoView implements IComercianteCriarAgendamentoView {
    private final Util u = new Util();

    @Override
    public OptionalInt mostrarTela() {
        System.out.println("             ██████╗██████╗ ██╗ █████╗ ██████╗       █████╗  ██████╗ ███████╗███╗   ██╗██████╗  █████╗ ███╗   ███╗███████╗███╗   ██╗████████╗ ██████╗ ");
        System.out.println("            ██╔════╝██╔══██╗██║██╔══██╗██╔══██╗     ██╔══██╗██╔════╝ ██╔════╝████╗  ██║██╔══██╗██╔══██╗████╗ ████║██╔════╝████╗  ██║╚══██╔══╝██╔═══██╗");
        System.out.println("            ██║     ██████╔╝██║███████║██████╔╝     ███████║██║  ███╗█████╗  ██╔██╗ ██║██║  ██║███████║██╔████╔██║█████╗  ██╔██╗ ██║   ██║   ██║   ██║");
        System.out.println("            ██║     ██╔══██╗██║██╔══██║██╔══██╗     ██╔══██║██║   ██║██╔══╝  ██║╚██╗██║██║  ██║██╔══██║██║╚██╔╝██║██╔══╝  ██║╚██╗██║   ██║   ██║   ██║");
        System.out.println("            ╚██████╗██║  ██║██║██║  ██║██║  ██║     ██║  ██║╚██████╔╝███████╗██║ ╚████║██████╔╝██║  ██║██║ ╚═╝ ██║███████╗██║ ╚████║   ██║   ╚██████╔╝");
        System.out.println("             ╚═════╝╚═╝  ╚═╝╚═╝╚═╝  ╚═╝╚═╝  ╚═╝     ╚═╝  ╚═╝ ╚═════╝ ╚══════╝╚═╝  ╚═══╝╚═════╝ ╚═╝  ╚═╝╚═╝     ╚═╝╚══════╝╚═╝  ╚═══╝   ╚═╝    ╚═════╝ ");

        return OptionalInt.empty();
    }

    @Override
    public String askObservacao() {
        System.out.println("Digite uma observação para os agendamentos");
        System.out.print("➤ ");

        return u.lString();
    }

    @Override
    public LocalTime askAbreAgendamento() {
        System.out.println("Digite as horas que abre o serviço");
        System.out.print("➤ ");
        int horas = u.lInt();

        System.out.println("Digite os minutos que abre o serviço");
        System.out.print("➤ ");
        int minutos = u.lInt();

        return LocalTime.of(horas, minutos);
    }

    @Override
    public LocalTime askFechaAgendamento() {
        System.out.println("Digite as horas que fecha o serviço");
        System.out.print("➤ ");
        int horas = u.lInt();

        System.out.println("Digite os minutos que fecha o serviço");
        System.out.print("➤ ");
        int minutos = u.lInt();

        return LocalTime.of(horas, minutos);
    }

}
