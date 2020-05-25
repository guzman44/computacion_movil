package co.movil.computacion.dtos;

import org.joda.time.DateTime;

import java.io.Serializable;

public class NotificacionesDTO implements Serializable {

    private Integer Id;
    private Integer IdLogin;
    private String UserName;
    private String NombreUsuario;
    private String Mensaje;
    private String Entregado;
    private String Activo;
    private DateTime FechaRegistro;
    private String FechaRegistroMostrar;

    public NotificacionesDTO() {
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public Integer getIdLogin() {
        return IdLogin;
    }

    public void setIdLogin(Integer idLogin) {
        IdLogin = idLogin;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getNombreUsuario() {
        return NombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        NombreUsuario = nombreUsuario;
    }

    public String getMensaje() {
        return Mensaje;
    }

    public void setMensaje(String mensaje) {
        Mensaje = mensaje;
    }

    public String getEntregado() {
        return Entregado;
    }

    public void setEntregado(String entregado) {
        Entregado = entregado;
    }

    public String getActivo() {
        return Activo;
    }

    public void setActivo(String activo) {
        Activo = activo;
    }

    public DateTime getFechaRegistro() {
        return FechaRegistro;
    }

    public void setFechaRegistro(DateTime fechaRegistro) {
        FechaRegistro = fechaRegistro;
    }

    public String getFechaRegistroMostrar() {
        return FechaRegistroMostrar;
    }

    public void setFechaRegistroMostrar(String fechaRegistroMostrar) {
        FechaRegistroMostrar = fechaRegistroMostrar;
    }
}

