package org.reempreende.initializer;

import org.reempreende.presentation.interfaces.inicial.IInicialView;
import org.reempreende.presentation.presenter.InicialPresenter;
import org.reempreende.presentation.view.InicialView;

public class Main {

    private static final InicialView inicial = new InicialView();

    public static void main(String[] args) {
        IInicialView inicialView = new InicialView();
        InicialPresenter inicialPresenter = new InicialPresenter(inicialView);

        inicialPresenter.iniciar();
    }

}