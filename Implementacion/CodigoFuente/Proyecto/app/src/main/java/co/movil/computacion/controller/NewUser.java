package co.movil.computacion.controller;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import androidx.appcompat.app.AppCompatActivity;

import co.movil.Helper.RetrofitClientInstance;
import co.movil.computacion.R;
import co.movil.computacion.assets.utilidades.ViewComponent;
import co.movil.computacion.controller.login.LoginActivity;
import co.movil.computacion.dtos.Account.AcountDTO;
import co.movil.computacion.dtos.ResponseDTO;
import co.movil.computacion.interfaces.IAuthentication;
import co.movil.computacion.model.UserTokenViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewUser extends AppCompatActivity {

    ViewComponent vc;

    private EditText email;
    private EditText user;
    private EditText password;
    private EditText repeatPassword;
    private Button buttonCrearCuenta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);
        vc = new ViewComponent(this,"NEW_USER",null);
        vc.setDatosLogin();

        email = (EditText)findViewById(R.id.email);
        user = (EditText)findViewById(R.id.user);
        password = (EditText)findViewById(R.id.password);
        repeatPassword = (EditText)findViewById(R.id.repeatPassword);
        buttonCrearCuenta = (Button)findViewById(R.id.buttonCrearCuenta);

        buttonCrearCuenta.setOnClickListener( new Button.OnClickListener(){

            @Override
            public void onClick(View v) {
                vc.progressBarProcess(R.id.loading,true);
                AcountDTO objeto = new AcountDTO();
                objeto.setEmail(email.getText().toString());
                objeto.setPassword(password.getText().toString());
                objeto.setRepeatPassword(repeatPassword.getText().toString());
                objeto.setUsername(user.getText().toString());

                if(!email.getText().toString().equals("") && !user.getText().toString().equals("") && !password.getText().toString().equals("") && !repeatPassword.getText().toString().equals("")){

                    Gson gson = new Gson();
                    String json = gson.toJson(objeto);

                    IAuthentication service = RetrofitClientInstance.getRetrofitInstance().create(IAuthentication.class);
                    Call<ResponseDTO> call = service.crearCuenta("application/json",objeto);

                    call.enqueue(new Callback<ResponseDTO>() {

                        @Override
                        public void onResponse(Call<ResponseDTO> call, Response<ResponseDTO> response) {
                            if(response.body()!= null && response.body().getResponse()!= null && response.body().getType().equals("error")){
                                Toast.makeText(getApplicationContext(), response.body().getResponse().toString(), Toast.LENGTH_LONG).show();
                            }else{
                                Toast.makeText(getApplicationContext(), "SUCCESS --->"+response.body().getResponse().toString(), Toast.LENGTH_LONG).show();
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        Intent intent = new Intent( NewUser.this, LoginActivity.class );
                                        startActivity( intent );
                                    }
                                },2000);
                            }
                            vc.progressBarProcess(R.id.loading,false);
                        }
                        @Override
                        public void onFailure(Call<ResponseDTO> call, Throwable t) {
                            Toast.makeText(getApplicationContext(), "Error en el servicio", Toast.LENGTH_LONG).show();
                            vc.progressBarProcess(R.id.loading,false);
                        }
                    });
                }else{
                    Toast.makeText(getApplicationContext(), "Debe llenar todos los campos del formulario", Toast.LENGTH_LONG).show();
                    vc.progressBarProcess(R.id.loading,false);
                }
            }
        });
    }
}
