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
            return  sc.nextDouble();
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
            return  sc.nextInt();
        }catch (InputMismatchException e)
        {
            System.err.println("Digite apenas números !");
            next();
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

    public void next() {
        sc.next();
    }

    public Scanner getSc() {
        return sc;
    }

    public void setSc(Scanner sc) {
        this.sc = sc;
    }
}
