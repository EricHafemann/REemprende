package org.reempreende.presentation.view.cliente;

import org.reempreende.infrastructure.utility.Cores;
import org.reempreende.infrastructure.utility.TextoUtil;
import org.reempreende.infrastructure.utility.Util;
import org.reempreende.presentation.interfaces.icliente.IClienteViewHorarios;

import java.util.OptionalInt;

public class ClienteViewHorarios implements IClienteViewHorarios {
    @Override
    public void exibirHorarios(String mensagem) {
        System.out.println(mensagem);
    }

    @Override
    public long askIdServico() {
        System.out.println("Digite o ID de serviço");
        System.out.print("➤ ");

        return Util.lLong();
    }

    @Override
    public OptionalInt mostrarTela() {
        Util.cls(15);
        System.out.println(Cores.VERDE + "            ██╗  ██╗ ██████╗ ██████╗  █████╗ ██████╗ ██╗ ██████╗ ███████╗" + Cores.RESET);
        System.out.println(Cores.VERDE + "            ██║  ██║██╔═══██╗██╔══██╗██╔══██╗██╔══██╗██║██╔═══██╗██╔════╝" + Cores.RESET);
        System.out.println(Cores.VERDE + "            ███████║██║   ██║██████╔╝███████║██████╔╝██║██║   ██║███████╗" + Cores.RESET);
        System.out.println(Cores.VERDE + "            ██╔══██║██║   ██║██╔══██╗██╔══██║██╔══██╗██║██║   ██║╚════██║" + Cores.RESET);
        System.out.println(Cores.VERDE + "            ██║  ██║╚██████╔╝██║  ██║██║  ██║██║  ██║██║╚██████╔╝███████║" + Cores.RESET);
        System.out.println(Cores.VERDE + "            ╚═╝  ╚═╝ ╚═════╝ ╚═╝  ╚═╝╚═╝  ╚═╝╚═╝  ╚═╝╚═╝ ╚═════╝ ╚══════╝" + Cores.RESET);

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
