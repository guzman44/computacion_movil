package co.movil.computacion.controller;

import androidx.appcompat.app.AppCompatActivity;
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
      //  SetRecyclerView();

        Bundle extras = getIntent().getExtras();
        Intent instenService = new Intent(this, NotificationService.class);
        instenService.putExtras(vc.getUserBuble());
        instenService.putExtra("token",vc.userToken.getToken());
        instenService.putExtra("username",(String) extras.get("userName"));
        instenService.putExtra("password",(String) extras.get("password"));
        startService(instenService);

    }

/*    public void SetRecyclerView( List<PublicacionesDTO> list){
*//*        ModelFeed modelFeed = new ModelFeed(1,7,3,R.drawable.ic_propic1,R.drawable.img_post1,"Carlos Rodriguez","1 hora","Feria del automovil" );
        feedList.add(modelFeed);
        modelFeed = new ModelFeed(2,5,1,R.drawable.ic_propic2,0,"Carolina Gomez","2 horas","Microsoft Dynamics CRM / Módulo de ventas" );
        feedList.add(modelFeed);
        modelFeed = new ModelFeed(3,1,2,R.drawable.ic_propic3,R.drawable.img_post2,"Alejandro Rodriguez","3 horas","Clásicos" );
        feedList.add(modelFeed);

        modelFeed = new ModelFeed(4,2,0,R.drawable.ic_propic2,0,"marco","2 horas","Infraestructura Azure" );
        feedList.add(modelFeed);
        modelFeed = new ModelFeed(5,3,1,R.drawable.ic_propic3,R.drawable.img_post2,"JJ","3 horas","Clásicos" );
        feedList.add(modelFeed);*//*

        feedList
        adapterFeed.notifyDataSetChanged();
    }*/

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


                    //adapterFeed.notifyDataSetChanged();

                }
            }

            @Override
            public void onFailure(Call<List<PublicacionesDTO>> call, Throwable t) {
int a = 1;
            }
        });



    }

}
