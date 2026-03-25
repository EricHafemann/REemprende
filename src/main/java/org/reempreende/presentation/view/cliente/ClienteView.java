package org.reempreende.presentation.view.cliente;

import org.reempreende.infrastructure.utility.Cores;
import org.reempreende.infrastructure.utility.TextoUtil;
import org.reempreende.infrastructure.utility.Util;
import org.reempreende.presentation.interfaces.icliente.IClienteView;

import java.util.OptionalInt;

public class ClienteView implements IClienteView {
    @Override
    public OptionalInt mostrarTela() {
        Util.cls(15);

        System.out.println("            ███████╗ ██╗     ██╗███████╗███╗   ██ ████████╗███████╗");
        System.out.println("            ██╔════╝ ██║     ██║██╔════╝████╗  ██║╚══██╔══╝██╔════╝");
        System.out.println("            ██║      ██║     ██║█████╗  ██╔██╗ ██║   ██║   █████╗ ");
        System.out.println("            ██║      ██║     ██║██╔══╝  ██║╚██╗██║   ██║   ██╔══╝  ");
        System.out.println("            ███████╗ ███████╗██║███████╗██║ ╚████║   ██║   ███████╗");
        System.out.println("            ╚══════╝ ╚══════╝╚═╝╚══════╝╚═╝  ╚═══╝   ╚═╝   ╚══════╝");

        System.out.println("\n" + TextoUtil.transformar("Digite a opção desejada!"));
        System.out.println("\n" + Cores.NEGRITO + TextoUtil.transformar("1 ➤ Visualizar Horários") + Cores.RESET);
        System.out.println("\n" + Cores.NEGRITO + TextoUtil.transformar("2 ➤ Agendar Horário Disponível") + Cores.RESET);
        System.out.println("\n" + Cores.NEGRITO + TextoUtil.transformar("3 ➤ Ver Histórico de Agendamentos") + Cores.RESET);
        System.out.println("\n" + Cores.NEGRITO + TextoUtil.transformar("4 ➤ Atualizar informações") + Cores.RESET);
        System.out.println("\n" + Cores.NEGRITO + TextoUtil.transformar("0 ➤ Sair") + Cores.RESET);
        System.out.println("\n" + Cores.NEGRITO + TextoUtil.transformar("SELECIONE UMA DAS OPÇÕES ACIMA ^ ") + Cores.RESET);
        System.out.print("➤ ");

        return OptionalInt.of(Util.lInt());
    }
}
