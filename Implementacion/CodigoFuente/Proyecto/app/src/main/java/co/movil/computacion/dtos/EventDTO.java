package co.movil.computacion.dtos;

import org.joda.time.DateTime;

import java.io.Serializable;
import java.util.Date;

public class EventDTO  implements Serializable {

    private Integer id;
    private Integer idLogin;
    private String nombre;
    private String descripcion;
    private String imagenMiniatura;
    private String fechaInicioMostrar;
    private String fechaFinMostrar;
    private DateTime fechaInicio;
    private DateTime fechaFin;
    private Integer idTipo;
    private String tipo;
    private String nombreUsuario;
    private Boolean like;
    private Integer likes;
    private String fechaRegistroMostrar;
    private DateTime fechaRegistro;

    public EventDTO() {
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImagenMiniatura() {
        return imagenMiniatura;
    }

    public void setImagenMiniatura(String imagenMiniatura) {
        this.imagenMiniatura = imagenMiniatura;
    }

    public String getFechaInicioMostrar() {
        return fechaInicioMostrar;
    }

    public void setFechaInicioMostrar(String fechaInicioMostrar) {
        this.fechaInicioMostrar = fechaInicioMostrar;
    }

    public String getFechaFinMostrar() {
        return fechaFinMostrar;
    }

    public void setFechaFinMostrar(String fechaFinMostrar) {
        this.fechaFinMostrar = fechaFinMostrar;
    }

    public DateTime getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(DateTime fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public DateTime getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(DateTime fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Integer getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(Integer idTipo) {
        this.idTipo = idTipo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public Boolean getLike() {
        return like;
    }

    public void setLike(Boolean like) {
        this.like = like;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public String getFechaRegistroMostrar() {
        return fechaRegistroMostrar;
    }

    public void setFechaRegistroMostrar(String fechaRegistroMostrar) {
        this.fechaRegistroMostrar = fechaRegistroMostrar;
    }

    public DateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(DateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
}
