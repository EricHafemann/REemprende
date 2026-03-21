package org.reempreende.presentation.view.cliente;

import org.reempreende.infrastructure.utility.TextoUtil;
import org.reempreende.infrastructure.utility.Util;
import org.reempreende.presentation.interfaces.icliente.IClienteViewAgendarDisponivel;

import java.util.OptionalInt;

public class ClienteViewAgendarDisponivel implements IClienteViewAgendarDisponivel {
    private Util u = new Util();

    @Override
    public void exibirHorariosDisponiveis(String mensagem) {
        System.out.println(mensagem);
    }

    @Override
    public void exibirMensagemTela() {

        u.cls(15);
        System.out.println("             █████╗  ██████╗ ███████╗███╗   ██╗██████╗  █████╗ ██████╗ ");
        System.out.println("            ██╔══██╗██╔════╝ ██╔════╝████╗  ██║██╔══██╗██╔══██╗██╔══██╗");
        System.out.println("            ███████║██║  ███╗█████╗  ██╔██╗ ██║██║  ██║███████║██████╔╝");
        System.out.println("            ██╔══██║██║   ██║██╔══╝  ██║╚██╗██║██║  ██║██╔══██║██╔══██╗");
        System.out.println("            ██║  ██║╚██████╔╝███████╗██║ ╚████║██████╔╝██║  ██║██║  ██║");
        System.out.println("            ╚═╝  ╚═╝ ╚═════╝ ╚══════╝╚═╝  ╚═══╝╚═════╝ ╚═╝  ╚═╝╚═╝  ╚═╝");
    }

    @Override
    public OptionalInt mostrarTela() {
        System.out.println("Selecione uma das IDs dos agendamentos acima para agendar: ");
        System.out.print("➤ ");

        return OptionalInt.of(u.lInt());
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
