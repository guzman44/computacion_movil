package co.movil.computacion.interfaces;

import java.util.List;

import co.movil.computacion.model.RequestAuthentication;
import co.movil.computacion.model.UserTokenViewModel;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface IAuthentication {



    @POST("api/Autenticacion/login")
    Call<UserTokenViewModel> getUserProfile(@Body RequestBody body);
    //Call<List<UserTokenViewModel>> getUserProfile(@Path("Username") String user,);

    @POST("api/v1/Autenticacion/Login")
    Call<UserTokenViewModel> getToken(
            @Body RequestAuthentication body
    );

}


/*

*/
