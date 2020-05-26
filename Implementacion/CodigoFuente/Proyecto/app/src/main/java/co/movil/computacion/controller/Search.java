package co.movil.computacion.controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import co.movil.Helper.RetrofitClientInstance;
import co.movil.computacion.R;
import co.movil.computacion.assets.utilidades.ViewComponent;
import co.movil.computacion.dtos.Evento.EventDTO;

import co.movil.computacion.interfaces.IAuthentication;
import co.movil.computacion.model.ModelEvent;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Search extends AppCompatActivity {

    List<ModelEvent> eventList;
    AdapterSearch adapterSearch;
    ImageView ivMap;
    ViewComponent vc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        vc = new ViewComponent(this,"SEARCH",null);
        vc.setDatosLogin();

        Intent intent = getIntent();
        String optionMenu = intent.getStringExtra("event");

        if(optionMenu != null){
            MenuFragment fragmentDemo = (MenuFragment)
                    getSupportFragmentManager().findFragmentById(R.id.menuFromSearch);
            fragmentDemo.activity(optionMenu);
        }
        GetEventList();
       // SetEventGallery();
        SearchView searchView =  (SearchView)findViewById(R.id.svSearch);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapterSearch.getFilter().filter(newText);
                return false;
            }
        });

        ivMap = findViewById(R.id.ivMap);
        ivMap.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(v.getContext(), Map.class );
                intent.putExtra("event", "Map");
                intent.putExtras(vc.getUserBuble());
                startActivity(intent);
            }
        });

    }

    private void SetEventGallery(List<EventDTO> eventList ){
       // eventList = new ArrayList<>();
/*
        eventList.add(new ModelEvent("The Vegitarian","Categorie Book","Description for The Vegitarian",R.drawable.thevigitarian, new GregorianCalendar(2020, 4, 4), new LatLng(4.55, -74.05)));
        eventList.add(new ModelEvent("The Wild Robot","Categorie Book","Description for The Wild Robot ",R.drawable.thewildrobot, new GregorianCalendar(2020, 4 ,10), new LatLng(4.65, -74.15)));
        eventList.add(new ModelEvent("Maria Semples","Categorie Book","Description for Maria Semples",R.drawable.mariasemples, new GregorianCalendar(2020, 4, 25), new LatLng(4.50, -74.05)));
*/
     /*   eventList.add(new ModelEvent("The Martian","Categorie Book","Description book",R.drawable.themartian));
        eventList.add(new ModelEvent("He Died with...","Categorie Book","Description book",R.drawable.hediedwith));
        eventList.add(new ModelEvent("The Vegitarian","Categorie Book","Description book",R.drawable.thevigitarian));
        eventList.add(new ModelEvent("The Wild Robot","Categorie Book","Description book",R.drawable.thewildrobot));
        eventList.add(new ModelEvent("Maria Semples","Categorie Book","Description book",R.drawable.mariasemples));
        eventList.add(new ModelEvent("The Martian","Categorie Book","Description book",R.drawable.themartian));
        eventList.add(new ModelEvent("He Died with...","Categorie Book","Description book",R.drawable.hediedwith));
        eventList.add(new ModelEvent("The Vegitarian","Categorie Book","Description book",R.drawable.thevigitarian));
        eventList.add(new ModelEvent("The Wild Robot","Categorie Book","Description book",R.drawable.thewildrobot));
        eventList.add(new ModelEvent("Maria Semples","Categorie Book","Description book",R.drawable.mariasemples));
        eventList.add(new ModelEvent("The Martian","Categorie Book","Description book",R.drawable.themartian));
        eventList.add(new ModelEvent("He Died with...","Categorie Book","Description book",R.drawable.hediedwith));*/




        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerviewGallery);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        adapterSearch = new AdapterSearch(this,eventList, vc);

        recyclerView.setAdapter(adapterSearch);
        adapterSearch.notifyDataSetChanged();

    }



    private void GetEventList(){
        IAuthentication service = RetrofitClientInstance.getRetrofitInstance().create(IAuthentication.class);

        Call<List<EventDTO>> call = service.listaTodosEventos("application/json","Bearer " + vc.getUserToken().getToken(), vc.getUserToken().getIdLogin());
        call.enqueue(new Callback<List<EventDTO>>() {
            @Override
            public void onResponse(Call<List<EventDTO>> call, Response<List<EventDTO>> response) {
                if(response.body()!= null) {
                    SetEventGallery(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<EventDTO>> call, Throwable t) {

            }
        });



    }


}
