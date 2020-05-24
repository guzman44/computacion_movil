package co.movil.computacion.dtos;

import java.io.Serializable;

public class SearchEventDTO implements Serializable {

    public Integer idLogin;
    public String text;

    public SearchEventDTO() {
    }

    public Integer getIdLogin() {
        return idLogin;
    }

    public void setIdLogin(Integer idLogin) {
        this.idLogin = idLogin;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
