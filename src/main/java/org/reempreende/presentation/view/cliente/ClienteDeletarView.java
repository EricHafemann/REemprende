package org.reempreende.presentation.view.cliente;

import org.reempreende.infrastructure.utility.Cores;
import org.reempreende.infrastructure.utility.Util;
import org.reempreende.presentation.interfaces.icliente.IClienteDeletarView;

import java.util.OptionalInt;

public class ClienteDeletarView implements IClienteDeletarView {
    @Override
    public OptionalInt mostrarTela() {
        System.out.println(Cores.VERDE + "            ██████╗ ███████╗██╗     ███████╗ ███████╗ █████╗ ██████╗ " + Cores.RESET);
        System.out.println(Cores.VERDE + "            ██╔══██╗██╔════╝██║     ██╔════╝╚══██╔══╝██╔══██╗██╔══██╗" + Cores.RESET);
        System.out.println(Cores.VERDE + "            ██║  ██║█████╗  ██║     █████╗     ██║   ███████║██████╔╝" + Cores.RESET);
        System.out.println(Cores.VERDE + "            ██║  ██║██╔══╝  ██║     ██╔══╝     ██║   ██╔══██║██╔══██╗" + Cores.RESET);
        System.out.println(Cores.VERDE + "            ██████╔╝███████╗███████╗███████╗   ██║   ██║  ██║██║  ██║" + Cores.RESET);
        System.out.println(Cores.VERDE + "            ╚═════╝ ╚══════╝╚══════╝╚══════╝   ╚═╝   ╚═╝  ╚═╝╚═╝  ╚═╝" + Cores.RESET);

        return OptionalInt.empty();
    }

    @Override
    public String confirmarDeletar() {
        System.out.println("Digite DELETAR para confirmar a sua ação");
        System.out.print("➤ ");

        return Util.lString();
    }
}
