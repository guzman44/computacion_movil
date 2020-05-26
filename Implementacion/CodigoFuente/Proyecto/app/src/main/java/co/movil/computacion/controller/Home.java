package co.movil.computacion.controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import co.movil.Helper.RetrofitClientInstance;
import co.movil.computacion.R;
import co.movil.computacion.assets.utilidades.ViewComponent;
import co.movil.computacion.dtos.Evento.EventDTO;
import co.movil.computacion.dtos.PublicacionesDTO;
import co.movil.computacion.interfaces.IAuthentication;
import co.movil.computacion.model.ModelFeed;
import co.movil.computacion.service.NotificationService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity {

    ViewComponent vc;
    RecyclerView recyclerView;
    List<PublicacionesDTO> feedList = new ArrayList<>();
    AdapterFeed adapterFeed;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        vc = new ViewComponent(this,"HOME",null);
        vc.setDatosLogin();
        setContentView(R.layout.activity_home);

        Intent intent = getIntent();
        String optionMenu = intent.getStringExtra("event");

        if(optionMenu != null){
            MenuFragment fragmentDemo = (MenuFragment)getSupportFragmentManager().findFragmentById(R.id.menuFromHome);
            fragmentDemo.activity(optionMenu);
        }

        GetPublicationList();

        Bundle extras = getIntent().getExtras();
        Intent instenService = new Intent(this, NotificationService.class);
        instenService.putExtras(vc.getUserBuble());
        instenService.putExtra("userToken",vc.userToken);
        instenService.putExtra("token",vc.userToken.getToken());
        instenService.putExtra("idLogin",vc.userToken.getIdLogin());
        instenService.putExtra("username",(String) extras.get("userName"));
        instenService.putExtra("password",(String) extras.get("password"));
        startService(instenService);


    }


    private void GetPublicationList(){
        IAuthentication service = RetrofitClientInstance.getRetrofitInstance().create(IAuthentication.class);

        Call<List<PublicacionesDTO>> call = service.listadoTodasPublicaciones("application/json","Bearer " + vc.getUserToken().getToken());
        call.enqueue(new Callback<List<PublicacionesDTO>>() {
            @Override
            public void onResponse(Call<List<PublicacionesDTO>> call, Response<List<PublicacionesDTO>> response) {
                if(response.body()!= null) {

                    Context ctx =getApplicationContext();
                    recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ctx);
                    recyclerView.setLayoutManager(layoutManager);

                    feedList = (List<PublicacionesDTO>)response.body();
                    adapterFeed = new AdapterFeed(ctx, feedList);
                    recyclerView.setAdapter(adapterFeed);
                }
            }

            @Override
            public void onFailure(Call<List<PublicacionesDTO>> call, Throwable t) {
            }
        });



    }

}
