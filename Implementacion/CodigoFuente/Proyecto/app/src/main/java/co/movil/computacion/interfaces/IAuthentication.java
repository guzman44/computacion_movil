package co.movil.computacion.interfaces;

import co.movil.computacion.model.RequestAuthentication;
import co.movil.computacion.model.UserTokenViewModel;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface IAuthentication {

    /**Componente Authentication**/

    @POST("api/Authentication/login")
   Call<UserTokenViewModel> login(
           @Header("Content-Type") String content_type,
           @Body RequestAuthentication body
    );


}

