package co.movil.computacion.controller;

import androidx.appcompat.app.AppCompatActivity;
import co.movil.Helper.RetrofitClientInstance;
import co.movil.computacion.R;
import co.movil.computacion.assets.utilidades.ViewComponent;
import co.movil.computacion.controller.login.LoginActivity;
import co.movil.computacion.dtos.Account.PerfilUserDTO;
import co.movil.computacion.dtos.ResponseDTO;
import co.movil.computacion.interfaces.IAuthentication;
import co.movil.computacion.model.RequestAuthentication;
import co.movil.computacion.model.User;
import co.movil.computacion.model.UserTokenViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;

public class Profile extends AppCompatActivity {

    private final String SHARED_PREFS = "sharedPreferences";

    private User user = new User();
    private ImageView ivProfilePic;
    private EditText etName;
    private EditText etLastName;
    private EditText etUsername;
    private EditText etEmail;
    private TextView btnLoadImage;
    private Button btnSave;
    ViewComponent vc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        vc = new ViewComponent(this,"PROFILE",null);
        vc.setDatosLogin();

        etName = (EditText)findViewById(R.id.etName);
        etLastName = (EditText)findViewById(R.id.etLastname);
        etUsername = (EditText)findViewById(R.id.etUsername);
        etEmail = (EditText)findViewById(R.id.etEmail);
        ivProfilePic = (ImageView)findViewById(R.id.ivuserprofile);
        btnLoadImage = (TextView) findViewById(R.id.btnLoadPicture);
        btnSave = (Button)findViewById(R.id.btnSaveProfile);

        //load user data
        loadData();

        btnLoadImage.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 0);
            }});

        btnSave.setOnClickListener( new Button.OnClickListener(){

            @Override
            public void onClick(View v) {
                updateData();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK){
            Uri targetUri = data.getData();
            Bitmap bitmap;
            try {
                bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(targetUri));
                ivProfilePic.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private void loadData()
    {
        SharedPreferences sharedPreferences = getSharedPreferences( SHARED_PREFS, MODE_PRIVATE );
        etName.setText( vc.getUserToken().getFirstName() );
        etLastName.setText( vc.getUserToken().getLastName() );
        etUsername.setText( vc.getUserToken().getUsername() );
        etEmail.setText( vc.getUserToken().getEmail() );
        Log.i("username: ", etName.getText().toString());
        //TODO: Load from DB
    }

    private void updateData()
    {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = sharedPreferences.edit();

        PerfilUserDTO perfil = new PerfilUserDTO();
        perfil.setEmail(etEmail.getText().toString());
        perfil.setLastName(etLastName.getText().toString());
        perfil.setFirstName(etName.getText().toString());
        perfil.setUsername(etUsername.getText().toString());

        IAuthentication service = RetrofitClientInstance.getRetrofitInstance().create(IAuthentication.class);
        Call<ResponseDTO> call = service.actualizarPerfil("application/json","Bearer " + vc.getUserToken().getToken(),perfil);

        call.enqueue(new Callback<ResponseDTO>() {

            @Override
            public void onResponse(Call<ResponseDTO> call, Response<ResponseDTO> response) {
                if(response.body()!= null && response.body().getResponse()!= null && response.body().getType().equals("error")){
                    Toast.makeText(getApplicationContext(), response.body().getResponse().toString(), Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(), "SUCCESS --->"+response.body().getResponse().toString(), Toast.LENGTH_LONG).show();
                    /*Intent intent = new Intent( this., Home.class );
                    intent.putExtras(vc.getUserBuble());
                    intent.putExtra("evento", user);
                    startActivity( intent );*/
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
}