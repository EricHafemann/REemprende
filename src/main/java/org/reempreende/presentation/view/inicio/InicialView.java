package org.reempreende.presentation.view.inicio;

import org.reempreende.domain.entities.enums.TipoUsuario;
import org.reempreende.infrastructure.utility.Cores;
import org.reempreende.infrastructure.utility.TextoUtil;
import org.reempreende.infrastructure.utility.Util;
import org.reempreende.presentation.interfaces.inicial.IInicialView;

public class InicialView implements IInicialView {

    private static final Util u = new Util();

    @Override
    public int mostrarTelaInicial()
    {
        System.out.println(Cores.VERDE + "              ██████╗ ███████╗███╗   ███╗      ██╗   ██╗██╗███╗   ██╗██████╗  ██████╗ " + Cores.RESET);
        System.out.println(Cores.VERDE + "              ██╔══██╗██╔════╝████╗ ████║      ██║   ██║██║████╗  ██║██╔══██╗██╔═══██╗" + Cores.RESET);
        System.out.println(Cores.VERDE + "              ██████╔╝█████╗  ██╔████╔██║ ━━━━ ██║   ██║██║██╔██╗ ██║██║  ██║██║   ██║" + Cores.RESET);
        System.out.println(Cores.VERDE + "              ██╔══██╗██╔══╝  ██║╚██╔╝██║      ╚██╗ ██╔╝██║██║╚██╗██║██║  ██║██║   ██║" + Cores.RESET);
        System.out.println(Cores.VERDE + "              ██████╔╝███████╗██║ ╚═╝ ██║       ╚████╔╝ ██║██║ ╚████║██████╔╝╚██████╔╝" + Cores.RESET);
        System.out.println(Cores.VERDE + "              ╚═════╝ ╚══════╝╚═╝     ╚═╝        ╚═══╝  ╚═╝╚═╝  ╚═══╝╚═════╝  ╚═════╝ " + Cores.RESET);
        System.out.println("\n" + Cores.NEGRITO + TextoUtil.transformar("Digite a opção desejada!") + Cores.RESET);
        System.out.println("\n" + Cores.NEGRITO + "1 ➤ Cadastro:" + Cores.RESET);
        System.out.println("\n" + Cores.NEGRITO + "2 ➤ Login" + Cores.RESET);
        System.out.println("\n" + Cores.NEGRITO + "0 ➤ Sair" + Cores.RESET);
        System.out.println("\n" + Cores.NEGRITO + "Resposta: " + Cores.RESET);
        System.out.println("\n" + Cores.NEGRITO + "SELECIONE UMA DAS OPÇÕES ACIMA ^ " + Cores.RESET);
        System.out.print("➤ ");
        return u.lInt();

    }

    @Override
    public int selecionarTipoUsuario() {
        TipoUsuario[] tipoUsuario = TipoUsuario.values();

        System.out.println("Selecione um dos tipos de Usuários:");

        for (TipoUsuario t : tipoUsuario) {
            System.out.printf("%n%s : %d", t.name(), t.ordinal());
        }
        System.out.print("➤ ");

        return u.lInt();
    }

}
