package co.movil.computacion.controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import co.movil.computacion.R;
import co.movil.computacion.assets.utilidades.ViewComponent;
import co.movil.computacion.model.ModelFeed;

import android.os.Bundle;

import java.util.ArrayList;

public class Feed extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<ModelFeed> feedList = new ArrayList<>();
    AdapterFeed adapterFeed;
    ViewComponent vc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);
        vc = new ViewComponent(this,"FEED",null);
        vc.setDatosLogin();

      /*  recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapterFeed = new AdapterFeed(this, feedList);
        recyclerView.setAdapter(adapterFeed);
        SetRecyclerView();*/
    }

    public void SetRecyclerView(){
        ModelFeed modelFeed = new ModelFeed(1,7,3,R.drawable.ic_propic1,R.drawable.img_post1,"Carlos Rodriguez","1 hora","Feria del automovil" );
        feedList.add(modelFeed);
        modelFeed = new ModelFeed(2,5,1,R.drawable.ic_propic2,0,"Carolina Gomez","2 horas","Microsoft Dynamics CRM / Módulo de ventas" );
        feedList.add(modelFeed);
        modelFeed = new ModelFeed(3,1,2,R.drawable.ic_propic3,R.drawable.img_post2,"Alejandro Rodriguez","3 horas","Clásicos" );
        feedList.add(modelFeed);
        adapterFeed.notifyDataSetChanged();
    }

}
