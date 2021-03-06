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
import android.widget.Toast;

import java.io.FileNotFoundException;

import androidx.appcompat.app.AppCompatActivity;
import co.movil.Helper.RetrofitClientInstance;
import co.movil.computacion.R;
import co.movil.computacion.assets.utilidades.ViewComponent;
import co.movil.computacion.controller.login.LoginActivity;
import co.movil.computacion.dtos.Account.PasswordDTO;
import co.movil.computacion.dtos.Account.PerfilUserDTO;
import co.movil.computacion.dtos.ResponseDTO;
import co.movil.computacion.interfaces.IAuthentication;
import co.movil.computacion.model.User;
import co.movil.computacion.model.UserTokenViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Configuration extends AppCompatActivity {

    private Button btnSave;
    View view ;
    ViewComponent vc;

    private EditText etUser;
    private EditText etPassword;
    private EditText etPasswordNew;
    private EditText etPasswordRepeat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuration);
        vc = new ViewComponent(this,"CONFIGURATION",null);
        vc.setDatosLogin();

        etUser = (EditText)findViewById(R.id.etUser);
        etPassword = (EditText)findViewById(R.id.etPassword);
        etPasswordNew = (EditText)findViewById(R.id.etPasswordNew);
        etPasswordRepeat = (EditText)findViewById(R.id.etPasswordRepeat);
        etUser.setText(vc.getUserToken().getUsername());
        etUser.setEnabled(false);
        btnSave = findViewById(R.id.btnSaveConfig);

        btnSave.setOnClickListener( new Button.OnClickListener(){

            @Override
            public void onClick(View v) {
                view = v;
                vc.progressBarProcess(R.id.loading,true);

                new AlertDialog.Builder(v.getContext())
                        .setTitle("Confirmar Cambio Clave")
                        .setCancelable(false)
                        .setMessage("¿Desea cambiar la clave actual, lo cual el sistema debe loguearse nuevamente?")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                PasswordDTO objeto = new PasswordDTO();
                                objeto.setUsername(etUser.getText().toString());
                                objeto.setNewPassword(etPasswordNew.getText().toString());
                                objeto.setOldPassword(etPassword.getText().toString());
                                objeto.setRepeatNewPassword(etPasswordRepeat.getText().toString());

                                IAuthentication service = RetrofitClientInstance.getRetrofitInstance().create(IAuthentication.class);
                                Call<ResponseDTO> call = service.cambiarPassword("application/json","Bearer " + vc.getUserToken().getToken(),objeto);

                                call.enqueue(new Callback<ResponseDTO>() {
                                    @Override
                                    public void onResponse(Call<ResponseDTO> call, Response<ResponseDTO> response) {
                                        if(response.body()!= null && response.body().getResponse()!= null && response.body().getType().equals("error")){
                                            Toast.makeText(getApplicationContext(), response.body().getResponse().toString(), Toast.LENGTH_LONG).show();
                                        }else{
                                            Toast.makeText(getApplicationContext(), "SUCCESS --->"+response.body().getResponse().toString(), Toast.LENGTH_LONG).show();
                                            Intent intent = new Intent(view.getContext(), LoginActivity.class );
                                            //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                            startActivity(intent);
                                            finish();
                                        }
                                        vc.progressBarProcess(R.id.loading,false);
                                    }
                                    @Override
                                    public void onFailure(Call<ResponseDTO> call, Throwable t) {
                                        Toast.makeText(getApplicationContext(), "Error en el servicio", Toast.LENGTH_LONG).show();
                                        vc.progressBarProcess(R.id.loading,false);
                                    }
                                });
                            }
                        })
                        .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent( view.getContext(), Home.class );
                                intent.putExtras(vc.getUserBuble());
                                startActivity( intent );
                                vc.progressBarProcess(R.id.loading,false);
                            }
                        })
                        .show();
            }
        });
    }
}