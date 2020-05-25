package co.movil.computacion.dtos;

import org.joda.time.DateTime;

import java.io.Serializable;

public class PublicacionesDTO implements Serializable {

    public Integer id;
    public Integer idLogin;
    public Integer idEvento;
    public String comentario;
    //public DateTime fechaIngreso;


    public String imagen;
    public int likes;

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFechaIngresoMostrar() {
        return fechaIngresoMostrar;
    }

    public void setFechaIngresoMostrar(String fechaIngresoMostrar) {
        this.fechaIngresoMostrar = fechaIngresoMostrar;
    }

    public Boolean getClicked() {
        return clicked;
    }

    public void setClicked(Boolean clicked) {
        this.clicked = clicked;
    }

    public String avatar;
    public String userName;
    public String nombre;
    public String fechaIngresoMostrar;


    Boolean clicked = false;
    public boolean isClicked()
    {
        return this.clicked;
    }

    public void setClicked( boolean clicked )
    {
        this.clicked = clicked;
    }

    public void addLike()
    {
        this.likes+=1;
    }

    public void removeLike()
    {
        this.likes-=1;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }



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

  /*  public DateTime getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(DateTime fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }
*/
    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

}
