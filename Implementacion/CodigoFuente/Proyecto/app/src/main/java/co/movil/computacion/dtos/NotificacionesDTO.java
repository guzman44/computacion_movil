package co.movil.computacion.dtos;

import org.joda.time.DateTime;

import java.io.Serializable;

public class NotificacionesDTO implements Serializable {

    private Integer id;
    private Integer idLogin;
    private String userName;
    private String nombreUsuario;
    private String titulo;
    private String mensaje;
    private String entregado;
    private String activo;
    private String fechaRegistroMostrar;

    public NotificacionesDTO() {
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getEntregado() {
        return entregado;
    }

    public void setEntregado(String entregado) {
        this.entregado = entregado;
    }

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }

    public String getFechaRegistroMostrar() {
        return fechaRegistroMostrar;
    }

    public void setFechaRegistroMostrar(String fechaRegistroMostrar) {
        this.fechaRegistroMostrar = fechaRegistroMostrar;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}

