package org.example.utility;

import java.util.InputMismatchException;
import java.util.Scanner;

public class util {

    private final String RESET = "\u001B[0m";
    private final String NEGRITO = "\u001B[1m";
    private final String SUBLINHADO = "\u001B[4m";

    private final String PRETO = "\u001B[30m";
    private final String VERMELHO = "\u001B[31m";
    private final String VERDE = "\u001B[32m";
    private final String AMARELO = "\u001B[33m";
    private final String AZUL = "\u001B[34m";
    private final String ROXO = "\u001B[35m";
    private final String CIANO = "\u001B[36m";
    private final String BRANCO = "\u001B[37m";

    private final String PRETO_BRILHANTE = "\u001B[90m";
    private final String VERMELHO_BRILHANTE = "\u001B[91m";
    private final String VERDE_BRILHANTE = "\u001B[92m";
    private final String AMARELO_BRILHANTE = "\u001B[93m";
    private final String AZUL_BRILHANTE = "\u001B[94m";
    private final String ROXO_BRILHANTE = "\u001B[95m";
    private final String CIANO_BRILHANTE = "\u001B[96m";
    private final String BRANCO_BRILHANTE = "\u001B[97m";

    private final String FUNDO_PRETO = "\u001B[40m";
    private final String FUNDO_VERMELHO = "\u001B[41m";
    private final String FUNDO_VERDE = "\u001B[42m";
    private final String FUNDO_AMARELO = "\u001B[43m";
    private final String FUNDO_AZUL = "\u001B[44m";
    private final String FUNDO_ROXO = "\u001B[45m";
    private final String FUNDO_CIANO = "\u001B[46m";
    private final String FUNDO_BRANCO = "\u001B[47m";

    private Scanner sc = new Scanner(System.in);

    public String lString ()
    {
        return  sc.nextLine();
    }

    public double lDouble ()
    {
        try
        {
            return  sc.nextDouble();
        }catch (InputMismatchException e)
        {
            System.err.println("Digite apenas números !");
        }
        return 0;
    }

    public double lInt ()
    {
        try
        {
            return  sc.nextInt();
        }catch (InputMismatchException e)
        {
            System.err.println("Digite apenas números !");
        }
        return 0;
    }

    public void delay (int time)
    {
        try
        {
            Thread.sleep(time);
        }catch (InterruptedException e)
        {
            System.err.println("Erro no Delay !");
        }
    }

    public void cls (int quantityLines)
    {
        for (int i = 0; i < quantityLines; i++) {
            System.out.println();
        }
    }

    public void cls ()
    {
        for (int i = 0; i < 30; i++) {
            System.out.println();
        }
    }
}
