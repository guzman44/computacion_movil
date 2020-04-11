package co.movil.computacion.controller;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import co.movil.computacion.R;
import co.movil.computacion.controller.login.LoginActivity;

public class LateralMenu extends AppCompatActivity {

    LinearLayout menuProfile;
    LinearLayout menuConfig;
    LinearLayout menuExit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upper_menu);

        Intent intent = getIntent();
        String optionMenu = intent.getStringExtra("event");

        if(optionMenu != null){
            MenuFragment fragmentDemo = (MenuFragment)getSupportFragmentManager().findFragmentById(R.id.menuFromHome);
            fragmentDemo.activity(optionMenu);
        }

        menuProfile = (LinearLayout) findViewById(R.id.menu_profile);
        menuConfig = (LinearLayout) findViewById(R.id.menu_config);
        menuExit = (LinearLayout) findViewById(R.id.menu_exit);

        menuProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( v.getContext(), Profile.class );
                intent.putExtra("event", "profile");
                startActivity(intent);
            }
        });

        menuConfig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( v.getContext(), Configuration.class );
                intent.putExtra("event", "config");
                startActivity(intent);
            }
        });

        menuExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(LateralMenu.this)
                        .setTitle("Cerrar Sesion")
                        .setCancelable(false)
                        .setMessage("¿Desea salir del sistema?")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                Intent intent = new Intent( LateralMenu.this, LoginActivity.class );
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
    }
}
