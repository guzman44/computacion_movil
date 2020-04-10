package co.movil.computacion;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import co.movil.Helper.RetrofitClientInstance;
import co.movil.computacion.interfaces.IAuthentication;
import co.movil.computacion.model.UserTokenViewModel;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Testing extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testing);

        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("UserName", "mguzman")
                .addFormDataPart("Password", "123")
                .build();

        IAuthentication service = RetrofitClientInstance.getRetrofitInstance().create(IAuthentication.class);
        Call<UserTokenViewModel> call = service.getUserProfile(requestBody);

        call.enqueue(new Callback<UserTokenViewModel>() {

            @Override
            public void onResponse(Call<UserTokenViewModel> call, Response<UserTokenViewModel> response) {
                response.body();
            }

            @Override
            public void onFailure(Call<UserTokenViewModel> call, Throwable t) {
                Log.w("error proyecto AAAAAAA:" , t.getMessage().toString());
            }
        });

    }
}
