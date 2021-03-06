package co.movil.computacion.controller;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import co.movil.computacion.R;
import co.movil.computacion.assets.utilidades.ViewComponent;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

//public class Menu extends AppCompatActivity {
public class MenuFragment extends Fragment {

    @Nullable
    ImageView ivHome = null;
    ImageView ivSearch = null;
    ImageView ivAddEvent = null;
    ImageView ivMultimedia = null;
    String someTitle = "";
    ViewComponent vc;

    public void activity(String param) {
        Context c = getActivity().getApplicationContext();
        int id = c.getResources().getIdentifier("drawable/"+ param.toLowerCase() + "_select", null, c.getPackageName());
        switch (param.toLowerCase()){
            case "home":
                ivHome.setImageResource(id);
                break;
            case "search":
                ivSearch.setImageResource(id);
                break;
            case "calendar":
                ivAddEvent.setImageResource(id);
                break;
            case "camera":
                ivMultimedia.setImageResource(id);
                break;

        }

    }

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        vc = new ViewComponent(this.getActivity());
        vc.setDatosLogin();
     //   someTitle = getArguments().getString("someTitle", "");
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_menu, container, false);

        SetControls(view);
        AddEvents();
        return view;
    }

    private void AddEvents(){

        ivHome.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(v.getContext(), Home.class );
                intent.putExtras(vc.getUserBuble());
                intent.putExtra("event", "Home");
                startActivity(intent);

            }
        });

        ivSearch.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(v.getContext(), Search.class);
                intent.putExtras(vc.getUserBuble());
                intent.putExtra("event", "Search");
                startActivity(intent);

            }
        });
        ivAddEvent.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(v.getContext(), Event.class );
                intent.putExtras(vc.getUserBuble());
                intent.putExtra("event", "Calendar");
                startActivity(intent);

            }
        });

        ivMultimedia.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(v.getContext(),Multimedia.class );
                intent.putExtras(vc.getUserBuble());
                intent.putExtra("event", "Camera");
                startActivity(intent);

            }
        });
    }

    private void SetControls(View view){

        ivHome = view.findViewById(R.id.ivHome);
        ivSearch= view.findViewById(R.id.ivSearch);
        ivAddEvent= view.findViewById(R.id.ivAddEvent);
        ivMultimedia= view.findViewById(R.id.ivMultimedia);
    }

}
