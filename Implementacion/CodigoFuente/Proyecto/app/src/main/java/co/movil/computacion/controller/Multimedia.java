package co.movil.computacion.controller;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import co.movil.computacion.R;
import co.movil.computacion.assets.utilidades.ViewComponent;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class Multimedia extends AppCompatActivity {
    static final int REQUEST_IMAGE_CAPTURE = 1;
    EditText etCategory;
    ImageView ivPhoto;
    ImageView ivPhotoMenu;
    ImageView ivGalleryMenu;
    ImageView ivMiniatura;
    AutoCompleteTextView acEvent;
    Context ctx;
    final int  ACCESS_CAMERA = 113;
    View view;
    ViewComponent vc;

    private enum Option {
        PICK_IMAGE(1),
        TAKE_PHOTO(2);

        private int value;
        private Option(int value) {
            this.value = value;
        }
    }

    private void Show(){
        findViewById(R.id.containerPhoto).setVisibility(View.VISIBLE);
        findViewById(R.id.menuFromMultimedia).setVisibility(View.GONE);
    }
    private void Hide(){
        findViewById(R.id.containerPhoto).setVisibility(View.GONE);
        findViewById(R.id.menuFromMultimedia).setVisibility(View.GONE);
    }
    public void hideKeyboardFrom(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
}

    public void requestPermission(Context context, String permission, int id) {
        boolean result = false;
        if (ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED) {
            ExecuteAction(id,true);
        }else{
            ActivityCompat.requestPermissions(this, new String[]{permission}, id);
        }
    }
    private  void StartCamera(){
        Show();
        hideKeyboardFrom(ctx, view);
        etCategory.clearFocus();
    }

    private void ExecuteAction(int requestCode, boolean resultRequestPermission){
        switch (requestCode) {
            case ACCESS_CAMERA: {
                if (resultRequestPermission) {
                    StartCamera();
                } else {
                    //Intent intent = new Intent(getApplicationContext(), e.class );
                    //startActivity(intent);
                }
                return;
            }
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        boolean result = false;
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            result = true;
        }
        ExecuteAction(requestCode, result);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(data != null){
            if(requestCode ==  Option.PICK_IMAGE.value){
                Uri Selected_Image_Uri = data.getData();
                ivMiniatura.setImageURI(Selected_Image_Uri);
                Hide();
            }

            if(requestCode ==  Option.TAKE_PHOTO.value){
                Bitmap bitmap = (Bitmap)data.getExtras().get("data");
                ivMiniatura.setImageBitmap(bitmap);
                Hide();
            }


        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multimedia);
        ctx = this.getApplicationContext();
        vc = new ViewComponent(this,"MULTIMEDIA",null);
        vc.setDatosLogin();

        Intent intent = getIntent();
        String optionMenu = intent.getStringExtra("event");

        if (optionMenu != null) {
            MenuFragment fragmentDemo = (MenuFragment)
                    getSupportFragmentManager().findFragmentById(R.id.menuFromMultimedia);
            fragmentDemo.activity(optionMenu);
        }

        String[] events = getResources().getStringArray(R.array.events);
        acEvent = findViewById(R.id.acEvent);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.row_category, R.id.text_view_list_item, events);
        acEvent.setAdapter(adapter);

        ivPhoto = (ImageView) findViewById(R.id.ivPhoto1);
        etCategory = (EditText) findViewById(R.id.acEvent);
        ivPhotoMenu = findViewById(R.id.ivPhotoMenu);
        ivGalleryMenu = findViewById(R.id.ivGalleryMenu);
        ivMiniatura = (ImageView) findViewById(R.id.ivMiniatura);

        acEvent.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View arg0, MotionEvent arg1) {
                acEvent.showDropDown();
                return false;
            }
        });
        ivPhoto.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {

                view = v;
                requestPermission(ctx, Manifest.permission.CAMERA,  ACCESS_CAMERA);


            }
        });

        ivPhotoMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, Option.TAKE_PHOTO.value);
            }
        });
        ivGalleryMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), Option.PICK_IMAGE.value);
            }
        });
    }


}
