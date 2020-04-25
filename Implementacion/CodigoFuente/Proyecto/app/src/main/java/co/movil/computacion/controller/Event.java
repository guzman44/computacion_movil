package co.movil.computacion.controller;

import androidx.appcompat.app.AppCompatActivity;
import co.movil.computacion.R;
import co.movil.computacion.model.ModelEvent;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.FileNotFoundException;

public class Event extends AppCompatActivity {

    ModelEvent eventObject;
    ImageView targetImage;
    TextView buttonLoadImage;
    Button buttonSave;
    EditText etTitle;
    EditText etDescription;
    EditText etCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        etTitle = (EditText)findViewById(R.id.etTitulo);
        etDescription = (EditText)findViewById(R.id.etDescription);
        etCategory = (EditText)findViewById(R.id.etCategory);
        Intent intent = getIntent();
        String optionMenu = intent.getStringExtra("event");

        if(optionMenu != null){
            MenuFragment fragmentDemo = (MenuFragment)
                    getSupportFragmentManager().findFragmentById(R.id.menuFromEvent);
            fragmentDemo.activity(optionMenu);
        }

        buttonSave = (Button)findViewById(R.id.btnSave);
        buttonLoadImage = (TextView)findViewById(R.id.btnLoadImage);
        targetImage = (ImageView)findViewById(R.id.miniatura);

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
                eventObject.setTitle( etTitle.getText().toString() );
                eventObject.setDescription( etDescription.getText().toString() );
                eventObject.setCategory( etCategory.getText().toString() );
                eventObject.setThumbnail( targetImage.getBaseline() ); //TO CHECK
                intent.putExtra("evento", eventObject);
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
