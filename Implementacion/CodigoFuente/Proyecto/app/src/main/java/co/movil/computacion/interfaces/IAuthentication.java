package co.movil.computacion.interfaces;

import java.util.List;

import co.movil.computacion.model.RequestAuthentication;
import co.movil.computacion.model.UserTokenViewModel;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface IAuthentication {


   // @Headers("Content-Type: application/json")
    @POST("api/Authentication/login")
    //Call<UserTokenViewModel> getUserProfile(@Body RequestAuthentication body);
   Call<UserTokenViewModel> getUserProfile(@Header("Content-Type") String content_type, @Body RequestAuthentication body);


}

