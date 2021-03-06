package co.movil.computacion.controller;

import com.google.android.gms.maps.model.LatLng;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;

import co.movil.Helper.RetrofitClientInstance;
import co.movil.computacion.R;
import co.movil.computacion.assets.utilidades.ViewComponent;
import co.movil.computacion.dtos.CategoriaDTO;
import co.movil.computacion.dtos.Evento.EventDTO;
import co.movil.computacion.dtos.LocalizacionDTO;
import co.movil.computacion.dtos.ResponseDTO;
import co.movil.computacion.interfaces.IAuthentication;
import co.movil.computacion.model.ModelEvent;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Base64;
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
import android.widget.Toast;

import com.google.gson.Gson;

import org.joda.time.DateTime;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.io.ByteArrayOutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;



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
    EditText etDuration;
    AutoCompleteTextView acCategory;
    Context ctx;
    View view;
    ViewComponent vc;
    EditText etLocation;
    DateTime dtStart;

    final int  ACCESS_CAMERA = 114;


    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());
        DateTime dt = new DateTime(year,month,dayOfMonth,0,0);
        dtStart = dt;
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
        vc = new ViewComponent(this,"EVENTO",null);
        vc.setDatosLogin();

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
        etDuration =  (EditText) findViewById(R.id.etDuration);
        etLocation =  (EditText) findViewById(R.id.etLocation);

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

                    LocalizacionDTO localization = new LocalizacionDTO();

                    LatLng latLng = getLocationFromAddress(etLocation.getText().toString());
                    if(latLng != null){
                        localization.setLatitud(latLng.latitude);
                        localization.setLongitud(latLng.longitude);
                    }

                    localization.setDireccion(etLocation.getText().toString());

                    List<LocalizacionDTO> localizationList = new ArrayList<>();
                        localizationList.add(localization);

                    CategoriaDTO categoriaDTO = new CategoriaDTO();
                    List<CategoriaDTO> categoryList = new ArrayList<>();

                    EventDTO event = new EventDTO();
                    event.setNombre(etTitle.getText().toString());
                    event.setDescripcion(etDescription.getText().toString());

                    BitmapDrawable drawable = (BitmapDrawable) ivMiniatura.getDrawable();
                    Bitmap bitmap = drawable.getBitmap();
                    ByteArrayOutputStream stream=new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG, 90, stream);
                    byte[] image=stream.toByteArray();
                    String imgBase64 = Base64.encodeToString(image, 0);

                    event.setImagenMiniatura(imgBase64);

                    //DateTime startDate = new DateTime(etStartDate.getText().toString());
                    String time = etStartTime.getText().toString();
                    String[] values =time.split(":");
                    int startHour = 0;
                    int startMinute = 0;

                    if(values.length >0){
                        startHour = Integer.parseInt( values[0]);
                    }
                    if(values.length >1){
                        startMinute = Integer.parseInt( values[0]);
                    }

                    dtStart = dtStart.plusHours(startHour);
                    dtStart = dtStart.plusMinutes(startMinute);

                    int hours = Integer.parseInt( etDuration.getText().toString());
                    DateTime endDate = dtStart.plusHours(hours);

                    DateTimeFormatter timeFormatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss.SSS");
                    String inicio = dtStart.toString(timeFormatter);
                    String fin = endDate.toString(timeFormatter);

                    event.setFechaInicio(inicio);
                    event.setFechaFin(fin);

                    event.setLocalizacion(localizationList);
                    String category = acCategory.getText().toString();
                    int idCategory = 1;

                    if(category != ""){
                        idCategory = GetValue(category);
                    }

                    event.setIdTipo(idCategory);
                    event.setIdLogin(vc.getUserToken().getIdLogin());

                    Gson gson = new Gson();
                    String json = gson.toJson(event);

                    IAuthentication service = RetrofitClientInstance.getRetrofitInstance().create(IAuthentication.class);
                    Call<ResponseDTO> call = service.crearEvento("application/json","Bearer " + vc.getUserToken().getToken(), event);

                    call.enqueue(new Callback<ResponseDTO>() {

                        @Override
                        public void onResponse(Call<ResponseDTO> call, Response<ResponseDTO> response) {

                            if(response.body()!= null && response.body().getResponse()!= null && response.body().getType().equals("error")){
                                Toast.makeText(getApplicationContext(), response.body().getResponse().toString(), Toast.LENGTH_LONG).show();
                            }else{
                                Toast.makeText(getApplicationContext(), "Evento creado satisfactoriamente " , Toast.LENGTH_LONG).show();

                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        Event.this.startActivity(new Intent(Event.this,Search.class).putExtras(vc.getUserBuble()));
                                        vc.progressBarProcess(R.id.loading,false);
                                        Event.this.finish();
                                    }
                                },2000);

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
            });
        }

    public LatLng getLocationFromAddress(String strAddress){

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            int a =1;

        }


        Geocoder mGeocoder = new Geocoder(getApplicationContext()); // getBaseContext()
        LatLng position = null;

        if(mGeocoder.isPresent()){
            if(!strAddress.isEmpty())
                try{
                    List<Address> addresses = mGeocoder.getFromLocationName(strAddress + ",Bogota, Colombia", 2 );

                    if(addresses.size() > 0){
                        Address addressResult = addresses.get( 0);
                        position = new LatLng (addressResult.getLatitude(), addressResult.getLongitude());
                    }

                }
                catch (Exception e) {
int a = 1;
                }
        }


        return position;
    }
    private int GetValue(String name){
        String[] keys = getResources().getStringArray(R.array.categories);
        String[] values = getResources().getStringArray(R.array.categoriesId);
        int index = 0;

        for (String key: keys) {

            if(key.equals(name)){
                break;
            }
            index++;
        }
        return Integer.parseInt(values[index]);
    }
}
