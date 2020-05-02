package co.movil.computacion.controller;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import co.movil.computacion.R;
import co.movil.computacion.model.Category;
import co.movil.computacion.model.ModelEvent;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

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
    Context ctx;
/*
    private static final String[] COUNTRIES = new String[]{
            "Afghanistan", "Albania", "Algeria", "Andorra", "Angola"
    };*/


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

  /*  private  void FillSpinner() {
        List<Category> categories = new ArrayList<>();

        Category category0 = new Category("",0);
        Category category1 = new Category("Opcion 1",1);
        Category category2 = new Category("Opcion 2",2);
        Category category3 = new Category("Opcion 3",3);
        Category category4 = new Category("Opcion 4",4);
        Category category5 = new Category("Opcion 5",5);

        categories.add(category0);
        categories.add(category1);
        categories.add(category2);
        categories.add(category3);
        categories.add(category4);
        categories.add(category5);

        ArrayAdapter<Category> adapter =
                new ArrayAdapter<Category>(getApplicationContext(),  android.R.layout.simple_spinner_dropdown_item, categories);
        adapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);

        spCategory.setAdapter(adapter);


    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        ctx = this.getApplicationContext();

        String[] countries = getResources().getStringArray(R.array.categories);

        AutoCompleteTextView editText = findViewById(R.id.acCategory);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.row_category, R.id.text_view_list_item, countries);
        editText.setAdapter(adapter);

        editText.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View arg0, MotionEvent arg1) {
                editText.showDropDown();
                return false;
            }
        });


        ivMiniatura = (ImageView) findViewById(R.id.ivMiniatura);
        ivPhoto = (ImageView) findViewById(R.id.ivPhoto);
       // spCategory = (Spinner) findViewById(R.id.spCategory);
       // FillSpinner();

        etTitle = (EditText)findViewById(R.id.etTitulo);
        etDescription = (EditText)findViewById(R.id.etDescription);
        buttonSave = (Button)findViewById(R.id.btnSave);
        targetImage = (ImageView)findViewById(R.id.ivMiniatura);
        ivPhotoMenu = findViewById(R.id.ivPhotoMenu);
        ivGalleryMenu = findViewById(R.id.ivGalleryMenu);

        Intent intent = getIntent();
        String optionMenu = intent.getStringExtra("event");
        Window w = this.getWindow();

        if(optionMenu != null){
            MenuFragment fragmentDemo = (MenuFragment)getSupportFragmentManager().findFragmentById(R.id.menuFromEvent);
            fragmentDemo.activity(optionMenu);
        }


//        etTitle= findViewById(R.id.etTitulo);
//        etDescription= findViewById(R.id.etDescription);
      //  spCategory= findViewById(R.id.spCategory);



        EditText etStartDate = (EditText) findViewById(R.id.etStartDate);
        etStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "date picker");
            }
        });
        EditText etStartTime = (EditText) findViewById(R.id.etStartTime);
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
                } else {
                 //  Show();
                }
            }
        });

        etDescription.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    Hide();
                } else {

                }
            }
        });



/*        spCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
             @Override
             public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                 findViewById(R.id.etLocation).requestFocus();
             }
             @Override
             public void onNothingSelected(AdapterView<?> adapterView) {

             }
        });*/


        ivPhoto.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                Show();
                hideKeyboardFrom(ctx,v);

                etTitle.clearFocus();
                etDescription.clearFocus();
              //  spCategory.clearFocus();

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
