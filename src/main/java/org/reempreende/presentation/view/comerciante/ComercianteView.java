package org.reempreende.presentation.view.comerciante;

import org.reempreende.domain.entities.enums.TipoUsuario;
import org.reempreende.infrastructure.utility.Cores;
import org.reempreende.infrastructure.utility.TextoUtil;
import org.reempreende.infrastructure.utility.Util;
import org.reempreende.presentation.interfaces.icomerciante.IComercianteView;

import java.util.OptionalInt;


public class ComercianteView implements IComercianteView
{
    private static final Util u = new Util();

    public int mostraMenuComerciante()
    {
        u.cls(15);
        System.out.println(Cores.VERDE + "             ██████╗  ██████╗ ███╗   ███╗███████╗██████╗  ██████╗██╗ █████╗ ███╗   ██╗███████╗███████╗" + Cores.RESET);
        System.out.println(Cores.VERDE + "            ██╔════╝ ██╔═══██╗████╗ ████║██╔════╝██╔══██╗██╔════╝██║██╔══██╗████╗  ██║╚══██╔══╝██╔════╝" + Cores.RESET);
        System.out.println(Cores.VERDE + "            ██║      ██║   ██║██╔████╔██║█████╗  ██████╔╝██║     ██║███████║██╔██╗ ██║   ██║   █████╗ " + Cores.RESET);
        System.out.println(Cores.VERDE + "            ██║      ██║   ██║██║╚██╔╝██║██╔══╝  ██╔══██╗██║     ██║██╔══██║██║╚██╗██║   ██║   ██╔══╝  " + Cores.RESET);
        System.out.println(Cores.VERDE + "            ╚██████╗ ╚██████╔╝██║ ╚═╝ ██║███████╗██║  ██║╚██████╗██║██║  ██║██║ ╚████║   ██║   ███████╗" + Cores.RESET);
        System.out.println(Cores.VERDE + "             ╚═════╝  ╚═════╝ ╚═╝     ╚═╝╚══════╝╚═╝  ╚═╝ ╚═════╝╚═╝╚═╝  ╚═╝╚═╝  ╚═══╝   ╚═╝   ╚══════╝" + Cores.RESET);
        System.out.println("\n" + TextoUtil.transformar("Digite a opção desejada!"));
        System.out.println("\n" + Cores.NEGRITO + TextoUtil.transformar("1 ➤ Cadastrar horário de trabalho") + Cores.RESET);
        System.out.println("\n" + Cores.NEGRITO + TextoUtil.transformar("2 ➤ Cadastrar serviço") + Cores.RESET);
        System.out.println("\n" + Cores.NEGRITO + TextoUtil.transformar("0 ➤ Sair") + Cores.RESET);
        System.out.println("\n" + Cores.NEGRITO + TextoUtil.transformar("SELECIONE UMA DAS OPÇÕES ACIMA ^ ") + Cores.RESET);
        System.out.print("➤ ");
        return u.lInt();
    }


    @Override
    public OptionalInt mostrarTela() {
        return OptionalInt.empty();
    }

    @Override
    public void exibirErro(String mensagem) {
        IComercianteView.super.exibirErro(mensagem);
    }

    @Override
    public void exibirSucesso(String mensagem) {
        IComercianteView.super.exibirSucesso(mensagem);
    }

    @Override
    public void sair(String mensagem) {
        IComercianteView.super.sair(mensagem);
    }

    @Override
    public void exibirMensagem(String mensagem) {
        IComercianteView.super.exibirMensagem(mensagem);
    }
}
