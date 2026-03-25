package org.reempreende.infrastructure.utility;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Util {

    private static Scanner sc = new Scanner(System.in);

    public static String lString ()
    {
        return  sc.nextLine();
    }

    public static double lDouble ()
    {
        try
        {
            double num = sc.nextDouble();

            next();

            return num;
        }catch (InputMismatchException e)
        {
            System.err.println("Digite apenas números !");
            next();
        }
        return 0;
    }

    public static int lInt ()
    {
        try
        {
            int num = sc.nextInt();

            next();

            return num;
        }catch (InputMismatchException e)
        {
            System.err.println("Digite apenas números !");
            next();
        }
        return 0;
    }

    public static long lLong() {
        try
        {
            long num = sc.nextLong();

            next();

            return num;
        }catch (InputMismatchException e)
        {
            System.err.println("Digite apenas números!");
            next();
        }
        return 0L;
    }

    public static void delay (int time)
    {
        try
        {
            Thread.sleep(time);
        }catch (InterruptedException e)
        {
            System.err.println("Erro no Delay !");
        }
    }

    public static void cls (int quantityLines)
    {
        for (int i = 0; i < quantityLines; i++) {
            System.out.println();
        }
    }

    public static void cls ()
    {
        for (int i = 0; i < 30; i++) {
            System.out.println();
        }
    }

    public static void next() {
        sc.nextLine();
    }

    public static void digiteEnterParaContinuar() {
        System.out.println("Digite enter para continuar: ");
        System.out.print("➤ ");

        Scanner sc = new Scanner(System.in);
        sc.nextLine();
    }

    public Scanner getSc() {
        return sc;
    }

    public void setSc(Scanner sc) {
        this.sc = sc;
    }
}
