package co.movil.computacion.interfaces;

import co.movil.computacion.dtos.Account.AcountDTO;
import co.movil.computacion.dtos.Account.PasswordDTO;
import co.movil.computacion.dtos.Account.PerfilUserDTO;
import co.movil.computacion.dtos.ResponseDTO;
import co.movil.computacion.model.RequestAuthentication;
import co.movil.computacion.model.UserTokenViewModel;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface IAuthentication {

    /**Componente Authentication**/

    @POST("api/Authentication/login")
   Call<UserTokenViewModel> login(
           @Header("Content-Type") String content_type,
           @Body RequestAuthentication body
    );


    @POST("api/Authentication/create/acount")
    Call<ResponseDTO> crarCuenta(
            @Header("Content-Type") String content_type,
            @Header("Authorization") String token,
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


}

