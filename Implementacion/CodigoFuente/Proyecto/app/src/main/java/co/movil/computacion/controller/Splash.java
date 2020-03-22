package co.movil.computacion.controller;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;
import co.movil.computacion.R;
import co.movil.computacion.controller.login.LoginActivity;

public class Splash  extends AppCompatActivity {
    private static final int MULTIPLE_PERMISSION_REQUEST_CODE = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadView();
        loadLoginController();
    }

    //Paso 1. ------------------------------- Cargar vista
    public void loadView(){
        setContentView(R.layout.view_splash);
    }

    //Paso 2. ------------------------------- Iniciar
    public void loadLoginController(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Splash.this.startActivity(
                        new Intent(Splash.this, LoginActivity.class)
                );
                Splash.this.finish();
            }
        },2000);
    }
}