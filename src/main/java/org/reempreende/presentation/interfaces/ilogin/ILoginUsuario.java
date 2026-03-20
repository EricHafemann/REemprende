package org.reempreende.presentation.interfaces.ilogin;

import org.reempreende.presentation.interfaces.InterfaceView;

public interface ILoginUsuario extends InterfaceView {
    String askSenha();
    String askEmail();
    String askSenhaAcesso();
}
