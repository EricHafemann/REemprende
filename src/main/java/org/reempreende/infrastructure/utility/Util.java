package org.reempreende.infrastructure.utility;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Util {

    private Scanner sc = new Scanner(System.in);

    public String lString ()
    {
        return  sc.nextLine();
    }

    public double lDouble ()
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

    public int lInt ()
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

    public long lLong() {
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

    public void next() {
        sc.nextLine();
    }

    public Scanner getSc() {
        return sc;
    }

    public void setSc(Scanner sc) {
        this.sc = sc;
    }
}
