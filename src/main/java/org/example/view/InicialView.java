package org.example.view;

public class InicialView {
    public static void mostraTelaInicial()
    {
        String RESET = "\u001B[0m";
        String CIANO = "\u001B[36m";
        String VERDE = "\u001B[32m";
        String AMARELO = "\u001B[33m";
        String NEGRITO = "\u001B[1m";

        System.out.println(CIANO + "===============================================" + RESET);
        System.out.println(CIANO + "||                                           ||" + RESET);
        System.out.println(CIANO + "||   " + NEGRITO + AMARELO + "      Bem-vindo ao REemprende  " + RESET + CIANO + "       ||" + RESET);
        System.out.println(CIANO + "||                                           ||" + RESET);
        System.out.println(CIANO + "===============================================" + RESET);

        System.out.println("\n" + VERDE + "  [✓] Conexão estabelecida com sucesso." + RESET);

        System.out.println("\n" + NEGRITO + "Digite a opção desejada!" + RESET);
        System.out.println("\n" + NEGRITO + "1 = Cadastro:" + RESET);
        System.out.println("\n" + NEGRITO + "2 = Login" + RESET);
        System.out.println("\n" + NEGRITO + "3 = Sair" + RESET);
        System.out.println("\n" + NEGRITO + "Resposta: " + RESET);
        System.out.print("> ");
    }
}
