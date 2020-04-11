package co.movil.computacion.controller;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.AlertDialog;
import android.content.DialogInterface;

import com.google.android.material.navigation.NavigationView;

import co.movil.computacion.R;
import co.movil.computacion.controller.login.LoginActivity;

public class Header extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Menu menu;
    private Context context;
    DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.i("Header", "Im here!");

        context = this;
        setContentView(R.layout.header_with_navbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        if (navigationView != null) {
            navigationView.setNavigationItemSelectedListener(this);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.nav_menu, menu);
        this.menu = menu;
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();
        Intent intent;
        switch (id)
        {
            case R.id.itmProfile:
                intent = new Intent( context, Profile.class );
                startActivity(intent);
                break;

            case R.id.itmConfig:
                intent = new Intent( context, Configuration.class );
                startActivity(intent);
                break;
            case R.id.itmExit:
                new AlertDialog.Builder(context)
                        .setTitle("Cerrar Sesion")
                        .setCancelable(false)
                        .setMessage("¿Desea salir del sistema?")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                Intent intent = new Intent( context, LoginActivity.class );
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                            }
                        })
                        .show();
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
