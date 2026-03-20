package org.reempreende.presentation.interfaces;

import org.reempreende.infrastructure.utility.Cores;
import org.reempreende.infrastructure.utility.TextoUtil;

import java.util.OptionalInt;

public interface InterfaceView {
    OptionalInt mostrarTela();

    default void exibirErro(String mensagem) {
        System.out.println(Cores.VERMELHO_BRILHANTE + TextoUtil.transformar(mensagem) + Cores.RESET);
    }

    default void exibirSucesso(String mensagem) {
        System.out.println(Cores.VERDE_BRILHANTE + TextoUtil.transformar(mensagem) + Cores.RESET);
    }

    default void sair(String mensagem) {
        System.out.println(Cores.CIANO_BRILHANTE + TextoUtil.transformar(mensagem) + Cores.RESET);
    }

    default void exibirMensagem(String mensagem) {
        System.out.println(Cores.AZUL_BRILHANTE + TextoUtil.transformar(mensagem) + Cores.RESET);
    }

}
