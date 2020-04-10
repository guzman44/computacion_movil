package co.movil.computacion.interface;

import java.util.List;

import co.movil.computacion.model.UserTokenViewModel;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface IAuthentication {
    @GET("users/{user}/repos")
    Call<List<UserTokenViewModel>> listRepos(@Path("user") String user);
}


/*

    @POST("api/v1/Autenticacion/Login")
    Call<UserToken> getToken(
            @Body RequestBody body
    );*/
