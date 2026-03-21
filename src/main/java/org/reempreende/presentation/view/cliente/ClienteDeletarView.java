package org.reempreende.presentation.view.cliente;

import org.reempreende.infrastructure.utility.Util;
import org.reempreende.presentation.interfaces.icliente.IClienteDeletarView;

import java.util.OptionalInt;

public class ClienteDeletarView implements IClienteDeletarView {
    private final Util u = new Util();

    @Override
    public OptionalInt mostrarTela() {
        System.out.println("            ██████╗ ███████╗██╗     ███████╗ ███████╗ █████╗ ██████╗ ");
        System.out.println("            ██╔══██╗██╔════╝██║     ██╔════╝╚══██╔══╝██╔══██╗██╔══██╗");
        System.out.println("            ██║  ██║█████╗  ██║     █████╗     ██║   ███████║██████╔╝");
        System.out.println("            ██║  ██║██╔══╝  ██║     ██╔══╝     ██║   ██╔══██║██╔══██╗");
        System.out.println("            ██████╔╝███████╗███████╗███████╗   ██║   ██║  ██║██║  ██║");
        System.out.println("            ╚═════╝ ╚══════╝╚══════╝╚══════╝   ╚═╝   ╚═╝  ╚═╝╚═╝  ╚═╝");

        return OptionalInt.empty();
    }

    @Override
    public String confirmarDeletar() {
        System.out.println("Digite DELETAR para confirmar a sua ação");
        System.out.print("➤ ");

        return u.lString();
    }
}
