package co.movil.computacion.controller;

import androidx.appcompat.app.AppCompatActivity;

import co.movil.computacion.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class Multimedia extends AppCompatActivity {
    static final int REQUEST_IMAGE_CAPTURE = 1;
    EditText etCategory;
    ImageView ivPhoto;
    Context ctx;

    private void Show(){
        findViewById(R.id.MenuOptionPhoto1).setVisibility(View.VISIBLE);
        findViewById(R.id.menuFromMultimedia).setVisibility(View.GONE);
    }
    private void Hide(){
        findViewById(R.id.MenuOptionPhoto1).setVisibility(View.GONE);
        findViewById(R.id.menuFromMultimedia).setVisibility(View.GONE);
    }
    public static void hideKeyboardFrom(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multimedia);

        ivPhoto = (ImageView) findViewById(R.id.ivPhotoMenu);
        etCategory = (EditText) findViewById(R.id.acvCategory);
        ctx = this.getApplicationContext();

        Intent intent = getIntent();
        String optionMenu = intent.getStringExtra("event");

        if (optionMenu != null) {
            MenuFragment fragmentDemo = (MenuFragment)
                    getSupportFragmentManager().findFragmentById(R.id.menuFromMultimedia);
            fragmentDemo.activity(optionMenu);
        }


        etCategory.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    Hide();
                } else {

                }
            }
        });

        ivPhoto.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Show();
                hideKeyboardFrom(ctx, v);
                etCategory.clearFocus();

            }
        });
    }
}
