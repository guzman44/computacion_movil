package co.movil.computacion.dtos;

import org.joda.time.DateTime;

import java.io.Serializable;

public class PublicacionesDTO implements Serializable {

    public Integer id;
    public Integer idLogin;
    public Integer idEvento;
    public String comentario;
    public DateTime fechaIngreso;
    public String imagen;

    public PublicacionesDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdLogin() {
        return idLogin;
    }

    public void setIdLogin(Integer idLogin) {
        this.idLogin = idLogin;
    }

    public Integer getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(Integer idEvento) {
        this.idEvento = idEvento;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public DateTime getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(DateTime fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

}
