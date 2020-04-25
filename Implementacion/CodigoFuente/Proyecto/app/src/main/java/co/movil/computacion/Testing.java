package co.movil.computacion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.LocaleList;
import android.util.Log;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import co.movil.Helper.RetrofitClientInstance;
import co.movil.computacion.interfaces.IAuthentication;
import co.movil.computacion.model.RequestAuthentication;
import co.movil.computacion.model.UserTokenViewModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Testing extends AppCompatActivity {

    private FusedLocationProviderClient fuseLocationProviderClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testing);




        RequestAuthentication authentication = new RequestAuthentication();
        authentication.setUsername("mguzman");
        authentication.setPassword("123");


        IAuthentication service = RetrofitClientInstance.getRetrofitInstance().create(IAuthentication.class);
        Call<UserTokenViewModel> call = service.getUserProfile("application/json",authentication);

        call.enqueue(new Callback<UserTokenViewModel>() {

            @Override
            public void onResponse(Call<UserTokenViewModel> call, Response<UserTokenViewModel> response) {
                UserTokenViewModel result =  response.body();
                int llego = 1;
            }

            @Override
            public void onFailure(Call<UserTokenViewModel> call, Throwable t) {
                Log.w("error proyecto AAAAAAA:" , t.getMessage().toString());
            }
        });

    }
}
