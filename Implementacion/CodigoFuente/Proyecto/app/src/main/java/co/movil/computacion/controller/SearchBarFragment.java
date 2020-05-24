package co.movil.computacion.controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import co.movil.computacion.R;
import co.movil.computacion.assets.utilidades.ViewComponent;


public class SearchBarFragment extends Fragment {

    SearchView svSearch;
    ImageView ivMap;
    ViewComponent vc;


    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        vc = new ViewComponent();
        vc.setDatosLogin();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.search_bar, container, false);
        SetControls(view);
        AddEvents();
        return view;
    }

    private void SetControls(View view){

        svSearch = view.findViewById(R.id.svSearch);
        ivMap = view.findViewById(R.id.ivMap);
    }

    private void AddEvents()
    {

        ivMap.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(v.getContext(), Map.class );
                intent.putExtra("event", "Map");
                intent.putExtras(vc.getUserBuble());
                startActivity(intent);
            }
        });
    }
}
