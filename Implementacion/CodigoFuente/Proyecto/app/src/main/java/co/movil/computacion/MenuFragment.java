package co.movil.computacion;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

//public class Menu extends AppCompatActivity {
public class MenuFragment extends Fragment {
    @Nullable
    ImageView ivHome = null;
    ImageView ivSearch = null;
    ImageView ivAddEvent = null;
    ImageView ivProfile = null;
    String someTitle = "";

    public void doSomething(String param) {
        // do something in fragment
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
            case "user":
                ivProfile.setImageResource(id);
                break;

        }

    }

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     //   someTitle = getArguments().getString("someTitle", "");
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
      //  return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.activity_menu, container, false);
        SetControls(view);



        AddEvents();
        return view;
    }

    private void AddEvents(){

        ivHome.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(v.getContext(), Home.class );
                intent.putExtra("event", "Home");
                startActivity(intent);

            }
        });

        ivSearch.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(v.getContext(), Search.class);
                intent.putExtra("event", "Search");
                startActivity(intent);

            }
        });
        ivAddEvent.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(v.getContext(), Event.class );
                intent.putExtra("event", "Calendar");
                startActivity(intent);

            }
        });

        ivProfile.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(v.getContext(), Profile.class );
                intent.putExtra("event", "User");
                startActivity(intent);

            }
        });
    }

    private void SetControls(View view){

        ivHome = view.findViewById(R.id.ivHome);
        ivSearch= view.findViewById(R.id.ivSearch);
        ivAddEvent= view.findViewById(R.id.ivAddEvent);
        ivProfile= view.findViewById(R.id.ivProfile);
    }

/*
   public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView tvTitle = findViewById(R.id.textView2);

        tvTitle.setText("hhhhh");
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }



    private String optionMenu = "Home";
    //ImageView ivHome = null;
    TextView tvTitle = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        tvTitle = findViewById(R.id.textView2);
       // ivHome = findViewById(R.id.ivHome);
        tvTitle.setText("hhhhh");


        Context c = getApplicationContext();
        int id = c.getResources().getIdentifier("drawable/"+ optionMenu.toLowerCase() + "_select", null, c.getPackageName());
        ivHome.setImageResource(id);


    }
    */
}
