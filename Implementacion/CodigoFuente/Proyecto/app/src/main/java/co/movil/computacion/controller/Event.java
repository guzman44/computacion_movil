package co.movil.computacion.controller;

import androidx.appcompat.app.AppCompatActivity;
import co.movil.computacion.R;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.FileNotFoundException;

public class Event extends AppCompatActivity {

    ImageView targetImage;
    Button buttonLoadImage;
    Button buttonSave;
    String etUser;
    String etName;
    String etLastName;
    String etEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        Intent intent = getIntent();
        String optionMenu = intent.getStringExtra("event");

        if(optionMenu != null){
            MenuFragment fragmentDemo = (MenuFragment)
                    getSupportFragmentManager().findFragmentById(R.id.menuFromEvent);
            fragmentDemo.activity(optionMenu);
        }

        buttonSave = (Button)findViewById(R.id.btnSave);
        buttonLoadImage = (Button)findViewById(R.id.btnLoadImage);
        targetImage = (ImageView)findViewById(R.id.ivuserprofile);
        etUser = ((EditText)findViewById(R.id.etUser)).toString();
        etName = ((EditText)findViewById(R.id.etName)).toString();
        etLastName = ((EditText)findViewById(R.id.etLastName)).toString();
        etEmail = ((EditText)findViewById(R.id.etEmail)).toString();

        buttonLoadImage.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 0);
            }});

        buttonSave.setOnClickListener( new Button.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent( v.getContext(), Home.class );
                Bundle bundle = new Bundle();
                bundle.putString( "usuario", etUser );
                bundle.putString( "nombre", etName );
                bundle.putString( "apellido", etLastName );
                bundle.putString( "email", etEmail );
                intent.putExtra( "info", bundle );
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
                targetImage.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
