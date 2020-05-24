package co.movil.computacion.dtos;

import org.joda.time.DateTime;

import java.io.Serializable;

public class GaleriaDTO implements Serializable {

    public Integer id;
    public String image;
    public Integer idLogin;
    public Integer idEvento;
    public DateTime fechaRegistro;

    public GaleriaDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(Integer idEvento) {
        this.idEvento = idEvento;
    }

    public DateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(DateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Integer getIdLogin() {
        return idLogin;
    }

    public void setIdLogin(Integer idLogin) {
        this.idLogin = idLogin;
    }
}
