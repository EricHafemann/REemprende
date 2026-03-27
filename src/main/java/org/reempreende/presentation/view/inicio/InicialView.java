package org.reempreende.presentation.view.inicio;

import org.reempreende.domain.entities.enums.TipoUsuario;
import org.reempreende.infrastructure.utility.Cores;
import org.reempreende.infrastructure.utility.TextoUtil;
import org.reempreende.infrastructure.utility.Util;
import org.reempreende.presentation.interfaces.inicial.IInicialView;

import java.util.InputMismatchException;
import java.util.OptionalInt;

public class InicialView implements IInicialView {
    @Override
    public OptionalInt mostrarTela()
    {
        Util.cls(20);
        System.out.println(Cores.VERDE + "              ██████╗ ███████╗███╗   ███╗      ██╗   ██╗██╗███╗   ██╗██████╗  ██████╗ " + Cores.RESET);
        System.out.println(Cores.VERDE + "              ██╔══██╗██╔════╝████╗ ████║      ██║   ██║██║████╗  ██║██╔══██╗██╔═══██╗" + Cores.RESET);
        System.out.println(Cores.VERDE + "              ██████╔╝█████╗  ██╔████╔██║ ━━━━ ██║   ██║██║██╔██╗ ██║██║  ██║██║   ██║" + Cores.RESET);
        System.out.println(Cores.VERDE + "              ██╔══██╗██╔══╝  ██║╚██╔╝██║      ╚██╗ ██╔╝██║██║╚██╗██║██║  ██║██║   ██║" + Cores.RESET);
        System.out.println(Cores.VERDE + "              ██████╔╝███████╗██║ ╚═╝ ██║       ╚████╔╝ ██║██║ ╚████║██████╔╝╚██████╔╝" + Cores.RESET);
        System.out.println(Cores.VERDE + "              ╚═════╝ ╚══════╝╚═╝     ╚═╝        ╚═══╝  ╚═╝╚═╝  ╚═══╝╚═════╝  ╚═════╝ " + Cores.RESET);
        System.out.println("\n" + TextoUtil.transformar("Digite a opção desejada!"));
        System.out.println("\n" + Cores.NEGRITO + TextoUtil.transformar("1 ➤ Cadastro") + Cores.RESET);
        System.out.println("\n" + Cores.NEGRITO + TextoUtil.transformar("2 ➤ Login") + Cores.RESET);
        System.out.println("\n" + Cores.NEGRITO + TextoUtil.transformar("0 ➤ Sair") + Cores.RESET);
        System.out.println("\n" + Cores.NEGRITO + TextoUtil.transformar("SELECIONE UMA DAS OPÇÕES ACIMA : ") + Cores.RESET);
        System.out.print("➤ ");

        return OptionalInt.of(Util.lInt());
    }

    @Override
    public int selecionarTipoUsuario() {
        TipoUsuario[] tipoUsuario = TipoUsuario.values();

        System.out.println("Selecione um dos tipos de Usuários:");

        for (TipoUsuario t : tipoUsuario) {
            System.out.printf("%n%s : %d", t.name(), t.ordinal());
        }
        System.out.print("➤ ");

        return Util.lInt();
    }
}