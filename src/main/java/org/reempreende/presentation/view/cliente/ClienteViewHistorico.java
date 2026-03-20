package org.reempreende.presentation.view.cliente;

import org.reempreende.infrastructure.utility.TextoUtil;
import org.reempreende.presentation.interfaces.icliente.IClienteViewHistorico;

import java.util.OptionalInt;

public class ClienteViewHistorico implements IClienteViewHistorico {
    @Override
    public void verHistoricoAgendamentos(String message) {
        System.out.println("\n" + message);
    }

    @Override
    public OptionalInt mostrarTela() {
        System.out.println("            ██╗  ██╗██╗███████╗ ███████╗ ██████╗ ██████╗ ██╗ ██████╗  ██████╗ ");
        System.out.println("            ██║  ██║██║██╔════╝╚══██╔══╝██╔═══██╗██╔══██╗██║██╔════╝ ██╔═══██╗");
        System.out.println("            ███████║██║███████╗   ██║   ██║   ██║██████╔╝██║██║      ██║   ██║");
        System.out.println("            ██╔══██║██║╚════██║   ██║   ██║   ██║██╔══██╗██║██║      ██║   ██║");
        System.out.println("            ██║  ██║██║███████║   ██║   ╚██████╔╝██║  ██║██║╚██████╗ ╚██████╔╝");
        System.out.println("            ╚═╝  ╚═╝╚═╝╚══════╝   ╚═╝    ╚═════╝ ╚═╝  ╚═╝╚═╝ ╚═════╝  ╚═════╝ ");

        return OptionalInt.empty();
    }

    @Override
    public void exibirErro(String mensagem) {
        System.out.println(TextoUtil.transformar(mensagem));
    }

    @Override
    public void exibirSucesso(String mensagem) {
        System.out.println(TextoUtil.transformar(mensagem));
    }

    @Override
    public void sair(String mensagem) {
        System.out.println(TextoUtil.transformar(mensagem));
    }

    @Override
    public void exibirMensagem(String mensagem) {
        System.out.println(TextoUtil.transformar(mensagem));
    }
}
