package org.example.view;

import org.example.presenter.CadastroPresenter;
import org.example.utility.Cores;
import org.example.utility.TextoUtil;
import org.example.utility.Util;
import org.example.view.interfaces.IInicialView;

public class InicialView implements IInicialView {

    private static final Util u = new Util();
    private final

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
        System.out.print("➤ ");
        int opcao = u.lInt();

        u.next();

        switch (opcao) {
            case 1 -> cadastroPresenter.realizarCadastro();
        }
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
