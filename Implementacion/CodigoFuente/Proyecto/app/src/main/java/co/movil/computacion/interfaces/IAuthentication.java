package co.movil.computacion.interfaces;

import java.util.List;

import co.movil.computacion.dtos.Account.AcountDTO;
import co.movil.computacion.dtos.Account.PasswordDTO;
import co.movil.computacion.dtos.Account.PerfilUserDTO;
import co.movil.computacion.dtos.Evento.EventDTO;
import co.movil.computacion.dtos.GaleriaDTO;
import co.movil.computacion.dtos.LocalizacionDTO;
import co.movil.computacion.dtos.NotificacionesDTO;
import co.movil.computacion.dtos.PublicacionesDTO;
import co.movil.computacion.dtos.ResponseDTO;
import co.movil.computacion.dtos.SearchEventDTO;
import co.movil.computacion.dtos.SelectDTO;
import co.movil.computacion.dtos.UserDTO;
import co.movil.computacion.model.RequestAuthentication;
import co.movil.computacion.model.UserTokenViewModel;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface IAuthentication {

    /**Componente Authentication**/

    @POST("api/Authentication/login")
   Call<UserTokenViewModel> login(
           @Header("Content-Type") String content_type,
           @Body RequestAuthentication body
    );


    @POST("api/Authentication/create/acount")
    Call<ResponseDTO> crearCuenta(
            @Header("Content-Type") String content_type,
            @Body AcountDTO body
    );


    @POST("api/Authentication/change/password")
    Call<ResponseDTO> cambiarPassword(
            @Header("Content-Type") String content_type,
            @Header("Authorization") String token,
            @Body PasswordDTO body
    );


    @PUT("api/Authentication/update/perfilUser")
    Call<ResponseDTO> actualizarPerfil(
            @Header("Content-Type") String content_type,
            @Header("Authorization") String token,
            @Body PerfilUserDTO body
    );


    /**Componente Event**/

    /**Crea un evento asociado a un IdLogin**/
    @POST("api/Event/create")
    Call<ResponseDTO> crearEvento(
            @Header("Content-Type") String content_type,
            @Header("Authorization") String token,
            @Body EventDTO body
    );


    /**actualiza un evento asociado a un IdLogin**/
    @PUT("api/Event/uptade")
    Call<ResponseDTO> actualizarEvento(
            @Header("Content-Type") String content_type,
            @Header("Authorization") String token,
            @Body EventDTO body
    );


    /**Listado de todos los eventos por idLogin**/
    @GET("api/Event/list/{idLogin}")
    Call<List<EventDTO>> listaTodosEventos(
            @Header("Content-Type") String content_type,
            @Header("Authorization") String token,
            @Path("idLogin") Integer idLogin
    );


    /**Listado de todas los eventos que trae las imagenes de la galeria, publicaciones, localizacion**/
    @GET("api/Event/list/all/{idEvento}")
    Call<EventDTO> listaTodosEventosALL(
            @Header("Content-Type") String content_type,
            @Header("Authorization") String token,
            @Path("idEvento") Integer idEvento
    );

    /**Listado de todas las imagenes de galeria por un evento**/
    @GET("api/Event/gallery/{idEvento}")
    Call<EventDTO> listaTodosEventosGaleria(
            @Header("Content-Type") String content_type,
            @Header("Authorization") String token,
            @Path("idEvento") Integer idEvento
    );


    /**Listado de todas las publicaciones por un evento**/
    @GET("api/Event/publication/{idEvento}")
    Call<EventDTO> listaTodosEventosPublicacion(
            @Header("Content-Type") String content_type,
            @Header("Authorization") String token,
            @Path("idEvento") Integer idEvento
    );


    /**Listado de todas las localizaciones por un evento**/
    @GET("api/Event/location/{idEvento}")
    Call<EventDTO> listaTodosEventosLocalizacion(
            @Header("Content-Type") String content_type,
            @Header("Authorization") String token,
            @Path("idEvento") Integer idEvento
    );


    /**Cargar las imagenes de la galeria x una**/
    @POST("api/Event/gallery/id")
    Call<ResponseDTO> crearGaleria(
            @Header("Content-Type") String content_type,
            @Header("Authorization") String token,
            @Body GaleriaDTO body
    );

    /**Cargar las imagenes de la galeria x varias**/
    @POST("api/Event/gallery/id")
    Call<ResponseDTO> crearGaleriaAll(
            @Header("Content-Type") String content_type,
            @Header("Authorization") String token,
            @Body List<GaleriaDTO> body
    );


    /**Crear una publicacion**/
    @POST("api/Event/publication")
    Call<ResponseDTO> crearPublicacion(
            @Header("Content-Type") String content_type,
            @Header("Authorization") String token,
            @Body PublicacionesDTO body
    );


    /**Creacion de una localizacion x una**/
    @POST("api/Event/location")
    Call<ResponseDTO> crearLocalicacion(
            @Header("Content-Type") String content_type,
            @Header("Authorization") String token,
            @Body LocalizacionDTO body
    );


    /**Creacion de una localizacion x varias**/
    @POST("api/Event/location/all")
    Call<ResponseDTO> crearLocalizacionAll(
            @Header("Content-Type") String content_type,
            @Header("Authorization") String token,
            @Body List<LocalizacionDTO> body
    );


    /**Creacion de un like a un usuario x evento**/
    @POST("api/Event/like/{idEvent}/{idLogin}")
    Call<ResponseDTO> crearLikeUsuarioEvento(
            @Header("Content-Type") String content_type,
            @Header("Authorization") String token,
            @Path("idEvent") Integer idEvent,
            @Path("idLogin") Integer idLogin
    );

    /**Eliminacion de una like de un evento por usuario**/
    @DELETE("api/Event/like/{idEvent}/{idLogin}")
    Call<ResponseDTO> eliminarLike(
            @Header("Content-Type") String content_type,
            @Header("Authorization") String token,
            @Path("idEvent") Integer idEvent,
            @Path("idLogin") Integer idLogin
    );


    /**Eliminacion de una localizcion de un evento por usuario**/
    @DELETE("api/Event/publication/{idEvent}/{idLogin}")
    Call<ResponseDTO> eliminarPublicacion(
            @Header("Content-Type") String content_type,
            @Header("Authorization") String token,
            @Path("idEvent") Integer idEvent,
            @Path("idLogin") Integer idLogin
    );


    /**Eliminacion de una localizacion de un evento por usuario**/
    @DELETE("api/Event/location/{idEvent}/{idLogin}")
    Call<ResponseDTO> eliminarLocaclizacion(
            @Header("Content-Type") String content_type,
            @Header("Authorization") String token,
            @Path("idEvent") Integer idEvent,
            @Path("idLogin") Integer idLogin
    );


    /**Realiza busqueda por el nombre del evento los que tenga asociado el login**/
    @POST("api/Event/search/login")
    Call<List<EventDTO>> busquedaEventoxLogin(
            @Header("Content-Type") String content_type,
            @Header("Authorization") String token,
            @Body SearchEventDTO body
    );


    /**Realiza busqueda por el nombre del evento en toda la tabla de evento no importa el login, este es para la busqueda del panel como facebook**/
    @POST("api/Event/search/all")
    Call<List<EventDTO>> busquedaEventoxLoginAll(
            @Header("Content-Type") String content_type,
            @Header("Authorization") String token,
            @Body SearchEventDTO body
    );



    /**Componente List**/
    /**Obtiene el Select por el tipo de valor  ....CAT_EVENTO  ....CAT_HISTORICO**/
    @GET("api/List/{tipoValor}")
    Call<List<SelectDTO >> selectTipoValor(
            @Header("Content-Type") String content_type,
            @Header("Authorization") String token,
            @Path("tipoValor") String tipoValor
    );



    /**Componente Publication**/
    /**Listado de todos las publicaciones**/
    @GET("api/Publication")
    Call<List<PublicacionesDTO>> listadoTodasPublicaciones(
            @Header("Content-Type") String content_type,
            @Header("Authorization") String token
    );


   /**Listado de todos las notificaciones por usuario**/
   @GET("api/Publication/notificacion/list/{idLogin}")
   Call<List<NotificacionesDTO>> listadoTodasNotificaconesxIdLogin(
           @Header("Content-Type") String content_type,
           @Header("Authorization") String token,
           @Path("idLogin") Integer idLogin
   );


   /**Crea una notificacion asociada a un IdLogin**/
   @POST("api/Publication/notificacion/create")
   Call<ResponseDTO> crearNotificacion(
           @Header("Content-Type") String content_type,
           @Header("Authorization") String token,
           @Body NotificacionesDTO body
   );

   /** **/
   @PUT("api/Publication/notificacion/update")
   Call<ResponseDTO> actualizarNotificacion(
           @Header("Content-Type") String content_type,
           @Header("Authorization") String token,
           @Body NotificacionesDTO body
   );




 /**Componente User**/
    /**Listado de todos los usuarios**/
    @GET("api/User/All")
    Call<List<UserDTO>> listadoTodosUsusarios(
            @Header("Content-Type") String content_type,
            @Header("Authorization") String token
    );


    /**Obtiene el usuario por Id**/
    @GET("api/User/id/{id}")
    Call<UserDTO> obtieneUsuarioxId(
            @Header("Content-Type") String content_type,
            @Header("Authorization") String token,
            @Path("id") Integer id
    );


    /**Obtiene el usuario por usernema**/
    @GET("api/User/username/{username}")
    Call<UserDTO> obtieneUsuarioxUsername(
            @Header("Content-Type") String content_type,
            @Header("Authorization") String token,
            @Path("username") String username
    );

}

