package co.movil.computacion.controller;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;

import co.movil.computacion.R;
import co.movil.computacion.assets.utilidades.ViewComponent;

public class Menu_Photo_Gallery extends AppCompatActivity {

    ImageView ivPhoto;
    ViewComponent vc;

    private enum Option {
        PICK_IMAGE(1),
        TAKE_PHOTO(2);

        private int value;

        private Option(int value) {
            this.value = value;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode ==  Option.PICK_IMAGE.value){
            Uri Selected_Image_Uri = data.getData();
            ivPhoto.setImageURI(Selected_Image_Uri);
        }

        if(requestCode ==  Option.TAKE_PHOTO.value){
            Bitmap bitmap = (Bitmap)data.getExtras().get("data");
            ivPhoto.setImageBitmap(bitmap);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu__photo__gallery);
        vc = new ViewComponent(this,"PHOTO",null);
        vc.setDatosLogin();

        ivPhoto = findViewById(R.id.ivPhotoMenu);
        ivPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,Option.TAKE_PHOTO.value);
            }
        });

    }
}
