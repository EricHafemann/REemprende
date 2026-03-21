package org.reempreende.presentation.view.servico;

import org.reempreende.infrastructure.utility.Util;
import org.reempreende.presentation.interfaces.iservico.IServicoView;
import org.reempreende.presentation.view.cadastro.CadastroBaseView;

public class ServicoView extends CadastroBaseView implements IServicoView
{
    private final Util u = new Util();

    @Override
    public String pedirDescricao()
    {
        System.out.println("Digite a descrição do serviço:");
        System.out.print("➤ ");

        return u.lString();
    }

    @Override
    public Double pedirDuracaoHoras()
    {
        System.out.println("Dgite a duração em horas do serviço:");
        System.out.print("➤ ");

        return u.lDouble();
    }
}
