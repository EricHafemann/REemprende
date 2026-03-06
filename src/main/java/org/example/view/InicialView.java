package org.example.view;

import org.example.utility.Cores;
import org.example.utility.TextoUtil;
import org.example.view.interfaces.IInicialView;

public class InicialView implements IInicialView {

    @Override
    public void mostrarTelaInicial()
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

    }
}
