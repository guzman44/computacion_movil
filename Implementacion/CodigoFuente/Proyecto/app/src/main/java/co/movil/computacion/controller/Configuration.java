package co.movil.computacion.controller;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.FileNotFoundException;

import androidx.appcompat.app.AppCompatActivity;
import co.movil.computacion.R;
import co.movil.computacion.controller.login.LoginActivity;
import co.movil.computacion.model.User;

public class Configuration extends AppCompatActivity {

    private Button btnSave, btnCancel;
    View view ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuration);
        btnSave = findViewById(R.id.btnSaveConfig);
        btnCancel = findViewById(R.id.btnCancelConfig);

        btnCancel.setOnClickListener( new Button.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent( v.getContext(), Home.class );
                startActivity( intent );
            }
        });

        btnSave.setOnClickListener( new Button.OnClickListener(){

            @Override
            public void onClick(View v) {
                view = v;

                new AlertDialog.Builder(v.getContext())
                        .setTitle("Confirmar Cambio Clave")
                        .setCancelable(false)
                        .setMessage("¿Desea cambiar la clave actual, lo cual el sistema debe loguearse nuevamente?")
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
                                Intent intent = new Intent( view.getContext(), Home.class );
                                startActivity( intent );
                            }
                        })
                        .show();
            }
        });
    }
}