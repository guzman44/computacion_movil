package co.movil.computacion.controller;

import androidx.appcompat.app.AppCompatActivity;
import co.movil.computacion.R;

import android.content.Intent;
import android.os.Bundle;

public class Event extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        Intent intent = getIntent();
        String optionMenu = intent.getStringExtra("event");

        if(optionMenu != null){
            MenuFragment fragmentDemo = (MenuFragment)
                    getSupportFragmentManager().findFragmentById(R.id.menuFromEvent);
            fragmentDemo.activity(optionMenu);
        }

    }
}
