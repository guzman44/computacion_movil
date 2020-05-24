package co.movil.computacion.controller;

import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import co.movil.computacion.R;
import co.movil.computacion.assets.utilidades.ViewComponent;

public class NewUser extends AppCompatActivity {

    ViewComponent vc;

    private EditText etUser;
    private EditText etPassword;
    private EditText etPasswordNew;
    private EditText etPasswordRepeat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);
        vc = new ViewComponent(this,"NEW_USER",null);
        vc.setDatosLogin();
    }
}
