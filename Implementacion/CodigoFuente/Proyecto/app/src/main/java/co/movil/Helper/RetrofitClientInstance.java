package co.movil.Helper;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientInstance {

    private static Retrofit retrofit;
    //private static final String BASE_URL = "http://10.0.2.2:8084/";
    private static final String BASE_URL = "https://eventplusapi.azurewebsites.net/";
    public static String HostApi = "https://eventplusapi.azurewebsites.net/";

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}