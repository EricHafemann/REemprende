package org.reempreende.presentation.view.login;

import org.reempreende.infrastructure.utility.TextoUtil;
import org.reempreende.infrastructure.utility.Util;
import org.reempreende.presentation.interfaces.ilogin.ILoginUsuario;

import java.util.OptionalInt;

public class LoginView implements ILoginUsuario {
    private Util u = new Util();

    @Override
    public String askSenha() {
        System.out.println(TextoUtil.transformar("Digite a sua senha:"));
        System.out.print("➤ ");

        return u.lString();
    }

    @Override
    public String askEmail() {
        System.out.println(TextoUtil.transformar("Digite o seu email:"));
        System.out.print("➤ ");

        return u.lString();
    }

    @Override
    public String askSenhaAcesso() {
        System.out.println(TextoUtil.transformar("Digite a sua senha de Acesso:"));
        System.out.print("➤ ");

        return u.lString();
    }

    @Override
    public OptionalInt mostrarTela() {
        return OptionalInt.empty();
    }

}
