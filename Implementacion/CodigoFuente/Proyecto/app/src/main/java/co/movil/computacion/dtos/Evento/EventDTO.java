package co.movil.computacion.dtos.Evento;

import org.joda.time.DateTime;

import java.io.Serializable;
import java.util.List;

import co.movil.computacion.dtos.CategoriaDTO;
import co.movil.computacion.dtos.EventoUsuarioDTO;
import co.movil.computacion.dtos.GaleriaDTO;
import co.movil.computacion.dtos.LocalizacionDTO;
import co.movil.computacion.dtos.PublicacionesDTO;

public class EventDTO  implements Serializable {

    private Integer id;
    private Integer idLogin;
    private String nombre;
    private String descripcion;
    private String imagenMiniatura;
    private String fechaInicioMostrar;
    private String fechaFinMostrar;
    private String fechaInicio;
    private String fechaFin;
    private Integer idTipo;
    private String tipo;
    private String nombreUsuario;
    private Boolean like;
    private Integer likes;
    private String fechaRegistroMostrar;
    private String fechaRegistro;

    private List<CategoriaDTO> categoria;
    private List<EventoUsuarioDTO> eventoUsuario;
    private List<GaleriaDTO> galeria;
    private List<LocalizacionDTO> localizacion;
    private List<PublicacionesDTO> publicaciones;

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

    public List<CategoriaDTO> getCategoria() {
        return categoria;
    }

    public void setCategoria(List<CategoriaDTO> categoria) {
        this.categoria = categoria;
    }

    public List<EventoUsuarioDTO> getEventoUsuario() {
        return eventoUsuario;
    }

    public void setEventoUsuario(List<EventoUsuarioDTO> eventoUsuario) {
        this.eventoUsuario = eventoUsuario;
    }

    public List<GaleriaDTO> getGaleria() {
        return galeria;
    }

    public void setGaleria(List<GaleriaDTO> galeria) {
        this.galeria = galeria;
    }

    public List<LocalizacionDTO> getLocalizacion() {
        return localizacion;
    }

    public void setLocalizacion(List<LocalizacionDTO> localizacion) {
        this.localizacion = localizacion;
    }

    public List<PublicacionesDTO> getPublicaciones() {
        return publicaciones;
    }

    public void setPublicaciones(List<PublicacionesDTO> publicaciones) {
        this.publicaciones = publicaciones;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
}
