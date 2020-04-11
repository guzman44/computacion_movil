package co.movil.computacion.controller;

import androidx.appcompat.app.AppCompatActivity;

import co.movil.computacion.R;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Multimedia extends AppCompatActivity {
    static final int REQUEST_IMAGE_CAPTURE = 1;
    ImageView imagen;
    TextView btnGrid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multimedia);

        Intent intent = getIntent();
        String optionMenu = intent.getStringExtra("event");
        imagen= (ImageView) findViewById(R.id.ivPostPic);
        btnGrid = (TextView) findViewById(R.id.btnGrid);

        if(optionMenu != null){
            MenuFragment fragmentDemo = (MenuFragment)
                    getSupportFragmentManager().findFragmentById(R.id.menuFromMultimedia);
            fragmentDemo.activity(optionMenu);
        }

        btnGrid.setEnabled(true);
    }

    public void onClick(View view) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imagen.setImageBitmap(imageBitmap);
        }
    }

}
