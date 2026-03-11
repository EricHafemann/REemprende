package org.reempreende.presentation.interfaces.icadastro;

import org.reempreende.domain.entities.enums.TipoUsuario;
import org.reempreende.presentation.interfaces.InterfaceView;

public interface ICadastroView extends InterfaceView {
    String pedirNome();
    String pedirEmail();
    String pedirSenha();
    int pedirStatus();

}
