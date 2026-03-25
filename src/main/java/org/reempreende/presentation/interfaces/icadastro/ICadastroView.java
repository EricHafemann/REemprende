package org.reempreende.presentation.interfaces.icadastro;

import org.reempreende.presentation.interfaces.interfaceBase.InterfaceView;

public interface ICadastroView extends InterfaceView {
    String pedirNome();
    String pedirEmail();
    String pedirSenha();
    int pedirStatus();

}
