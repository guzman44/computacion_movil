package co.movil.computacion.controller;


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

public class MenuUpFragment extends Fragment {

    ImageView ivMenu;
    private Menu menu;

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.activity_header, container, false);

        ivMenu = (ImageView) view.findViewById(R.id.ivMenu);

        ivMenu.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                
            }
        });
       /* ivProfile = (ImageView) view.findViewById(R.id.ivProfile);
        ivCloseSesion = (ImageView) view.findViewById(R.id.ivCloseSesion);
        ivConfig = (ImageView) view.findViewById(R.id.ivConfig);

        ivProfile.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(v.getContext(), Profile.class );
                //intent.putExtra("event", "Home");
                startActivity(intent);
            }
        });

        ivConfig.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(v.getContext(), Configuration.class );
                startActivity(intent);
            }
        });

        ivCloseSesion.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

                new AlertDialog.Builder(v.getContext())
                        .setTitle("Cerrar Sesion")
                        .setCancelable(false)
                        .setMessage("¿Desea salir del sistema?")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                Intent intent = new Intent(view.getContext(), LoginActivity.class );
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                            }
                        })
                        .show();
            }
        });

        */
        return view;
    }

}
