package co.movil.computacion.dtos;

import java.io.Serializable;

public class EventoUsuarioDTO implements Serializable {

    private Integer idEvento;
    private Integer idLogin;

    public EventoUsuarioDTO() {
    }

    public Integer getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(Integer idEvento) {
        this.idEvento = idEvento;
    }

    public Integer getIdLogin() {
        return idLogin;
    }

    public void setIdLogin(Integer idLogin) {
        this.idLogin = idLogin;
    }
}
