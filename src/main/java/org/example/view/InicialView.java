package org.example.view;

import org.example.utility.Util;

public class InicialView {
    private final Util util = new Util();
    private final java.util.Scanner scanner = new java.util.Scanner(System.in);

    public void mostraTelaInicial() {
        System.out.println(util.getCIANO() + "==========================================================" + util.getRESET());
        System.out.println(util.getCIANO() + "||                                                      ||" + util.getRESET());
        System.out.println(util.getCIANO() + "|| " + util.getNEGRITO() + util.getAMARELO() + "             Bem-vindo ao REer                 " + util.getRESET() + util.getCIANO() + " ||" + util.getRESET());
        System.out.println(util.getCIANO() + "||                                                      ||" + util.getRESET());
        System.out.println(util.getCIANO() + "==========================================================" + util.getRESET());

        System.out.println("\n" + util.getNEGRITO() + "Digite a opção desejada!" + util.getRESET());
        System.out.println(util.getNEGRITO() + "1 = Cadastro" + util.getRESET());
        System.out.println(util.getNEGRITO() + "2 = Login" + util.getRESET());
        System.out.println(util.getNEGRITO() + "3 = Sair" + util.getRESET());
        System.out.print(util.getNEGRITO() + "Resposta: " + util.getRESET() + "> ");

        String opcao = scanner.nextLine();
    }
}