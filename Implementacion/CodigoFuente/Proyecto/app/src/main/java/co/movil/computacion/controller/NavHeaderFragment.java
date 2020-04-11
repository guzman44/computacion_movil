package co.movil.computacion.controller;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import co.movil.computacion.R;

public class NavHeaderFragment extends AppCompatActivity {

    private final String SHARED_PREFS = "sharedPreferences";

    ImageView ivProfileMenu;
    TextView tvUsernameMenu;

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ivProfileMenu = (ImageView) findViewById(R.id.ivProfileMenu);
        tvUsernameMenu = (TextView) findViewById(R.id.tvUsernameMenu);

        //TODO: Cargar nombre de usuario e imagen desde shared preferences o sesion var.

        SharedPreferences sharedPreferences = getSharedPreferences( SHARED_PREFS, Context.MODE_PRIVATE );
        tvUsernameMenu.setText( sharedPreferences.getString( "username", "undefined" ) );
        Log.i("username: ", tvUsernameMenu.getText().toString());
    }


}
