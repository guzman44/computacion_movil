package co.movil.computacion.controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import co.movil.computacion.R;
import co.movil.computacion.assets.utilidades.ViewComponent;
import co.movil.computacion.model.ModelFeed;
import co.movil.computacion.service.NotificationService;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;

public class Home extends AppCompatActivity {

    ViewComponent vc;
    RecyclerView recyclerView;
    ArrayList<ModelFeed> feedList = new ArrayList<>();
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

        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapterFeed = new AdapterFeed(this, feedList);
        recyclerView.setAdapter(adapterFeed);
        SetRecyclerView();

        Bundle extras = getIntent().getExtras();
        Intent instenService = new Intent(this, NotificationService.class);
        instenService.putExtras(vc.getUserBuble());
        instenService.putExtra("token",vc.userToken.getToken());
        instenService.putExtra("username",(String) extras.get("userName"));
        instenService.putExtra("password",(String) extras.get("password"));
        startService(instenService);

    }

    public void SetRecyclerView(){
        ModelFeed modelFeed = new ModelFeed(1,7,3,R.drawable.ic_propic1,R.drawable.img_post1,"Carlos Rodriguez","1 hora","Feria del automovil" );
        feedList.add(modelFeed);
        modelFeed = new ModelFeed(2,5,1,R.drawable.ic_propic2,0,"Carolina Gomez","2 horas","Microsoft Dynamics CRM / Módulo de ventas" );
        feedList.add(modelFeed);
        modelFeed = new ModelFeed(3,1,2,R.drawable.ic_propic3,R.drawable.img_post2,"Alejandro Rodriguez","3 horas","Clásicos" );
        feedList.add(modelFeed);

        modelFeed = new ModelFeed(4,2,0,R.drawable.ic_propic2,0,"marco","2 horas","Infraestructura Azure" );
        feedList.add(modelFeed);
        modelFeed = new ModelFeed(5,3,1,R.drawable.ic_propic3,R.drawable.img_post2,"JJ","3 horas","Clásicos" );
        feedList.add(modelFeed);
        adapterFeed.notifyDataSetChanged();
    }

}
