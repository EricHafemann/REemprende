package org.reempreende.presentation.view.comerciante;

import org.reempreende.infrastructure.utility.Util;
import org.reempreende.presentation.interfaces.icomerciante.IComercianteAtualizarView;

import java.util.OptionalInt;

public class ComercianteAtualizarView implements IComercianteAtualizarView {
    @Override
    public OptionalInt mostrarTela() {
        System.out.println("             █████╗ ████████╗██╗   ██╗ █████╗ ██╗     ██╗███████╗ █████╗ ██████╗ ");
        System.out.println("            ██╔══██╗╚══██╔══╝██║   ██║██╔══██╗██║     ██║╚══███╔╝██╔══██╗██╔══██╗");
        System.out.println("            ███████║   ██║   ██║   ██║███████║██║     ██║  ███╔╝ ███████║██████╔╝");
        System.out.println("            ██╔══██║   ██║   ██║   ██║██╔══██║██║     ██║ ███╔╝  ██╔══██║██╔══██╗");
        System.out.println("            ██║  ██║   ██║   ╚██████╔╝██║  ██║███████╗██║███████╗██║  ██║██║  ██║");
        System.out.println("            ╚═╝  ╚═╝   ╚═╝    ╚═════╝ ╚═╝  ╚═╝╚══════╝╚═╝╚══════╝╚═╝  ╚═╝╚═╝  ╚═╝");

        System.out.println("1 - Nome");
        System.out.println("2 - E-mail");
        System.out.println("3 - Senha");
        System.out.println("0 - Voltar");
        System.out.println("SELECIONE UMA DAS OPÇÕES ACIMA:");
        System.out.print("➤ ");

        return OptionalInt.of(Util.lInt());
    }

    @Override
    public String newNome() {
        System.out.println("Digite o seu novo nome");
        System.out.print("➤ ");

        return Util.lString();
    }

    @Override
    public String newEmail() {
        System.out.println("Digite o seu novo E-mail");
        System.out.print("➤ ");

        return Util.lString();
    }

    @Override
    public String newPassword() {
        System.out.println("Digite a sua nova senha");
        System.out.print("➤ ");

        return Util.lString();
    }

}
