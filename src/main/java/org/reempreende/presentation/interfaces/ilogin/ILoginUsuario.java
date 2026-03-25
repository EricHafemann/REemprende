package org.reempreende.presentation.interfaces.ilogin;

import org.reempreende.presentation.interfaces.interfaceBase.InterfaceView;

public interface ILoginUsuario extends InterfaceView {
    String askSenha();
    String askEmail();
    String askSenhaAcesso();
}
