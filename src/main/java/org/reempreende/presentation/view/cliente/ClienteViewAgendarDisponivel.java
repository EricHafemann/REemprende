package org.reempreende.presentation.view.cliente;

import org.reempreende.infrastructure.utility.TextoUtil;
import org.reempreende.infrastructure.utility.Util;
import org.reempreende.presentation.interfaces.icliente.IClienteViewAgendarDisponivel;

import java.util.OptionalInt;
import java.util.OptionalLong;

public class ClienteViewAgendarDisponivel implements IClienteViewAgendarDisponivel {
    private Util u = new Util();

    @Override
    public void exibirHorariosDisponiveis(String mensagem) {
        System.out.println(mensagem);
    }

    @Override
    public OptionalLong exibirMensagemTela() {
        System.out.println("Selecione uma das IDs dos agendamentos acima para agendar: ");
        System.out.print("➤ ");

        return OptionalLong.of(u.lLong());

    }

    @Override
    public OptionalLong askServico() {
        System.out.println("Digite o ID do servico: ");
        System.out.print("➤ ");

        return OptionalLong.of(u.lLong());
    }

    @Override
    public OptionalInt mostrarTela() {
        u.cls(15);
        System.out.println("             █████╗  ██████╗ ███████╗███╗   ██╗██████╗  █████╗ ██████╗ ");
        System.out.println("            ██╔══██╗██╔════╝ ██╔════╝████╗  ██║██╔══██╗██╔══██╗██╔══██╗");
        System.out.println("            ███████║██║  ███╗█████╗  ██╔██╗ ██║██║  ██║███████║██████╔╝");
        System.out.println("            ██╔══██║██║   ██║██╔══╝  ██║╚██╗██║██║  ██║██╔══██║██╔══██╗");
        System.out.println("            ██║  ██║╚██████╔╝███████╗██║ ╚████║██████╔╝██║  ██║██║  ██║");
        System.out.println("            ╚═╝  ╚═╝ ╚═════╝ ╚══════╝╚═╝  ╚═══╝╚═════╝ ╚═╝  ╚═╝╚═╝  ╚═╝");

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
