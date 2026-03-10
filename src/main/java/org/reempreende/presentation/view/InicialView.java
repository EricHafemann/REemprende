package org.reempreende.presentation.view;

import org.reempreende.infrastucture.utility.Cores;
import org.reempreende.infrastucture.utility.TextoUtil;
import org.reempreende.infrastucture.utility.Util;
import org.reempreende.presentation.interfaces.inicial.IInicialView;
import org.reempreende.presentation.presenter.InicialPresenter;

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
    public void exibirErro(String mensagem) {
        System.out.println(Cores.VERDE_BRILHANTE + mensagem + Cores.RESET);
    }

    @Override
    public void exibirSucesso(String mensagem) {
        System.out.println(Cores.VERMELHO + mensagem + Cores.RESET);
    }

    @Override
    public void sair(String mensagem) {
        System.out.println(Cores.AMARELO + mensagem + Cores.RESET);
        u.delay(1000);
        System.exit(0);
    }
}
