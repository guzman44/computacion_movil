package co.movil.computacion.controller;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import co.movil.computacion.R;
import co.movil.computacion.assets.utilidades.ViewComponent;

public class MenuUpFragment extends Fragment {

    ImageView ivMenu;
    private Menu menu;
    ViewComponent vc;

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.activity_header, container, false);

        ivMenu = (ImageView) view.findViewById(R.id.ivMenu);

        ivMenu.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent( v.getContext(), LateralMenu.class);
                intent.putExtra("event", "menu");
                startActivity(intent);
            }
        });
        return view;
    }

}
