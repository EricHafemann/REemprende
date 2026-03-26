package org.reempreende.presentation.view.comerciante;

import org.reempreende.domain.entities.enums.TipoUsuario;
import org.reempreende.infrastructure.utility.Cores;
import org.reempreende.infrastructure.utility.TextoUtil;
import org.reempreende.infrastructure.utility.Util;
import org.reempreende.presentation.interfaces.icomerciante.IComercianteView;

import java.util.OptionalInt;


public class ComercianteView implements IComercianteView
{
    @Override
    public OptionalInt mostrarTela() {
        Util.cls(15);

        System.out.println(Cores.VERDE + "             ██████╗  ██████╗ ███╗   ███╗███████╗██████╗  ██████╗██╗ █████╗ ███╗   ██╗███████╗███████╗" + Cores.RESET);
        System.out.println(Cores.VERDE + "            ██╔════╝ ██╔═══██╗████╗ ████║██╔════╝██╔══██╗██╔════╝██║██╔══██╗████╗  ██║╚══██╔══╝██╔════╝" + Cores.RESET);
        System.out.println(Cores.VERDE + "            ██║      ██║   ██║██╔████╔██║█████╗  ██████╔╝██║     ██║███████║██╔██╗ ██║   ██║   █████╗ " + Cores.RESET);
        System.out.println(Cores.VERDE + "            ██║      ██║   ██║██║╚██╔╝██║██╔══╝  ██╔══██╗██║     ██║██╔══██║██║╚██╗██║   ██║   ██╔══╝  " + Cores.RESET);
        System.out.println(Cores.VERDE + "            ╚██████╗ ╚██████╔╝██║ ╚═╝ ██║███████╗██║  ██║╚██████╗██║██║  ██║██║ ╚████║   ██║   ███████╗" + Cores.RESET);
        System.out.println(Cores.VERDE + "             ╚═════╝  ╚═════╝ ╚═╝     ╚═╝╚══════╝╚═╝  ╚═╝ ╚═════╝╚═╝╚═╝  ╚═╝╚═╝  ╚═══╝   ╚═╝   ╚══════╝" + Cores.RESET);
        System.out.println("\n" + TextoUtil.transformar("Digite a opção desejada!"));
        System.out.println("\n" + Cores.NEGRITO + TextoUtil.transformar("1 ➤ Cadastrar serviço") + Cores.RESET);
        System.out.println("\n" + Cores.NEGRITO + TextoUtil.transformar("2 ➤ Atualizar seus Dados") + Cores.RESET);
        System.out.println("\n" + Cores.NEGRITO + TextoUtil.transformar("0 ➤ Sair") + Cores.RESET);
        System.out.println("\n" + Cores.NEGRITO + TextoUtil.transformar("SELECIONE UMA DAS OPÇÕES ACIMA ^ ") + Cores.RESET);
        System.out.print("➤ ");

        return OptionalInt.of(Util.lInt());
    }

}
