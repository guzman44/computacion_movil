package co.movil.computacion.controller;

import androidx.appcompat.app.AppCompatActivity;
import co.movil.computacion.R;
import co.movil.computacion.assets.utilidades.ViewComponent;
import co.movil.computacion.model.User;

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
import android.widget.TextView;

import java.io.FileNotFoundException;

public class Profile extends AppCompatActivity {

    private final String SHARED_PREFS = "sharedPreferences";

    private User user;
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
                Intent intent = new Intent( v.getContext(), Home.class );
                user.setUsername( etUsername.getText().toString() );
                user.setName( etName.getText().toString() );
                user.setLastName( etLastName.getText().toString() );
                user.setEmail( etEmail.getText().toString() );
                user.setProfilePic( ivProfilePic.getDrawable() );
                updateData();
                //TODO: Persis info
                intent.putExtra("evento", user);
                startActivity( intent );
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
        etName.setText( sharedPreferences.getString( "username", "undefined" ) );
        Log.i("username: ", etName.getText().toString());
        //TODO: Load from DB
    }

    private void updateData()
    {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString( "username", user.getUsername() );
        //TODO: Persist to DB
    }
}