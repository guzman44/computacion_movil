package co.movil.computacion.controller;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import co.movil.computacion.R;
import co.movil.computacion.model.ModelEvent;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import java.text.DateFormat;
import java.util.Calendar;


public class Event extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    ModelEvent eventObject;
    ImageView targetImage;
    Button buttonSave;
    EditText etTitle;
    EditText etDescription;
    Spinner spCategory;
    ImageView ivPhoto;
    ImageView ivMiniatura;
    ImageView ivPhotoMenu;
    ImageView ivGalleryMenu;
    EditText etStartDate;
    EditText etStartTime;
    AutoCompleteTextView acCategory;
    Context ctx;
    View view;

    final int  ACCESS_CAMERA = 114;


    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());
        TextView textView = (TextView) findViewById(R.id.etStartDate);
        textView.setText(currentDateString);
    }
    @Override
    public void onTimeSet(TimePicker timePicker, int hour, int minute) {
        Calendar c = Calendar.getInstance();
        c.get(Calendar.HOUR_OF_DAY);
        c.get(Calendar.MINUTE);
        TextView textView = (TextView) findViewById(R.id.etStartTime);
        textView.setText(hour + ":" + minute);
    }
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
        findViewById(R.id.menuFromEvent).setVisibility(View.GONE);
    }
    private void Hide(){
        findViewById(R.id.containerPhoto).setVisibility(View.GONE);
        findViewById(R.id.menuFromEvent).setVisibility(View.GONE);
    }
    public void hideKeyboardFrom(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
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

    public void requestPermission(Context context, String permission, int id) {
        boolean result = false;
        if (ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED) {
            ExecuteAction(id,true);
        }else{
            ActivityCompat.requestPermissions(this, new String[]{permission}, id);
        }
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
    private void StartCamera(){
        Show();
        hideKeyboardFrom(ctx,view);
        etTitle.clearFocus();
        etDescription.clearFocus();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        ctx = this.getApplicationContext();

        Intent intent = getIntent();
        String optionMenu = intent.getStringExtra("event");
        Window w = this.getWindow();

        if(optionMenu != null){
            MenuFragment fragmentDemo = (MenuFragment)getSupportFragmentManager().findFragmentById(R.id.menuFromEvent);
            fragmentDemo.activity(optionMenu);
        }



        String[] categories = getResources().getStringArray(R.array.categories);
        acCategory = findViewById(R.id.acCategory);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.row_category, R.id.text_view_list_item, categories);
        acCategory.setAdapter(adapter);

        ivMiniatura = (ImageView) findViewById(R.id.ivMiniatura);
        ivPhoto = (ImageView) findViewById(R.id.ivPhoto1);
        etTitle = (EditText)findViewById(R.id.etTitulo);
        etDescription = (EditText)findViewById(R.id.etDescription);
        buttonSave = (Button)findViewById(R.id.btnSave);
        targetImage = (ImageView)findViewById(R.id.ivMiniatura);
        ivPhotoMenu = findViewById(R.id.ivPhotoMenu);
        ivGalleryMenu = findViewById(R.id.ivGalleryMenu);
        etStartDate = (EditText) findViewById(R.id.etStartDate);
        etStartTime = (EditText) findViewById(R.id.etStartTime);

        acCategory.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View arg0, MotionEvent arg1) {
                acCategory.showDropDown();
                return false;
            }
        });
        etStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "date picker");
            }
        });
        etStartTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment time = new TimePickerFragment();
                time.show(getSupportFragmentManager(), "date picker");
            }
        });
        etTitle.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    Hide();
                }
            }
        });
        etDescription.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    Hide();
                }
            }
        });

        ivPhoto.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                view = v;
                requestPermission(getApplicationContext(), Manifest.permission.CAMERA,  ACCESS_CAMERA);


        }});
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
        buttonSave.setOnClickListener( new Button.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent( v.getContext(), Home.class );
                    eventObject.setTitle( etTitle.getText().toString() );
                    eventObject.setDescription( etDescription.getText().toString() );
                    eventObject.setThumbnail( targetImage.getBaseline() ); //TO CHECK
                    intent.putExtra("evento", eventObject);
                    startActivity( intent );
                }
            });
        }
}
