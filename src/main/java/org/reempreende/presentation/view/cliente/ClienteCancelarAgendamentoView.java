package org.reempreende.presentation.view.cliente;

import org.reempreende.infrastructure.utility.Cores;
import org.reempreende.infrastructure.utility.Util;
import org.reempreende.presentation.interfaces.icliente.IClienteCancelarAgendamentoView;

import java.util.OptionalInt;

public class ClienteCancelarAgendamentoView implements IClienteCancelarAgendamentoView {
    @Override
    public OptionalInt mostrarTela() {
        Util.cls(15);
        System.out.println(Cores.VERDE + "            ██████╗  █████╗  ███╗   ██╗  ██████╗ ███████╗ ██╗      █████╗  ██████╗ \n" +
                "           ██╔════╝ ██╔══██╗ ████╗  ██║ ██╔════╝ ██╔════╝ ██║     ██╔══██╗ ██╔══██╗\n" +
                "           ██║      ███████║ ██╔██╗ ██║ ██║      █████╗   ██║     ███████║ ██████╔╝\n" +
                "           ██║      ██╔══██║ ██║╚██╗██║ ██║      ██╔══╝   ██║     ██╔══██║ ██╔══██╗\n" +
                "           ╚██████╗ ██║  ██║ ██║ ╚████║ ╚██████╗ ███████╗ ███████╗██║  ██║ ██║  ██║\n" +
                "            ╚═════╝ ╚═╝  ╚═╝ ╚═╝  ╚═══╝  ╚═════╝ ╚══════╝ ╚══════╝╚═╝  ╚═╝ ╚═╝  ╚═╝" + Cores.RESET);

        return OptionalInt.empty();
    }

    @Override
    public void exibirHorarioCliente(String mensagem) {
        System.out.println(mensagem);
    }

    @Override
    public long askIdAgendamento() {
        System.out.println("Digite um dos IDs dos agendamentos acima para cancelar");
        System.out.print("➤ ");

        return Util.lLong();
    }
}
