package co.movil.computacion.controller;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import co.movil.computacion.R;
import co.movil.computacion.assets.utilidades.ViewComponent;

public class MapActivity extends AppCompatActivity {

    ViewComponent vc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        vc = new ViewComponent(this,"MAPA",null);
        vc.setDatosLogin();

    }
}
