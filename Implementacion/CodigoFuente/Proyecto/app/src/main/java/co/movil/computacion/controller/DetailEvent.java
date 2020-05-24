package co.movil.computacion.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.WriterException;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;
import co.movil.computacion.R;
import co.movil.computacion.assets.utilidades.ViewComponent;

public class DetailEvent extends AppCompatActivity {

    private TextView tvtitle,tvdescription,tvcategory;
    private ImageView img;
    private Menu menu;
    private  ImageView ivShare;
    ViewComponent vc;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_event);
        vc = new ViewComponent(this,"DETALLE_EVENTO",null);
        vc.setDatosLogin();

        tvtitle = (TextView) findViewById(R.id.txttitle);
        tvdescription = (TextView) findViewById(R.id.txtDesc);
        tvcategory = (TextView) findViewById(R.id.txtCat);
        img = (ImageView) findViewById(R.id.bookthumbnail);

        // Recieve data
        Intent intent = getIntent();
        String Title = intent.getExtras().getString("Title");
        String Description = intent.getExtras().getString("Description");
        int image = intent.getExtras().getInt("Thumbnail") ;



        tvtitle.setText(Title);
        tvdescription.setText(Description);
        img.setImageResource(image);


        ivShare = (ImageView) findViewById(R.id.ivShare);

        ivShare.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Bitmap bitmap = generateQR("Evento:" + Title, Title);
                shareImage(bitmap,"Evento:" + Title);
            }
        });

    }

    private Bitmap generateQR(String title, String message){

        Bitmap bitmap = null;
        WindowManager manager = (WindowManager) getSystemService(WINDOW_SERVICE);
        Display display = manager.getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);
        int width = point.x;
        int height = point.y;
        int smallerDimension = width < height ? width : height;
        smallerDimension = smallerDimension * 3 / 4;

        QRGEncoder qrgEncoder = new QRGEncoder(message, null, QRGContents.Type.TEXT, smallerDimension);
        try {
            // Getting QR-Code as Bitmap
            bitmap = qrgEncoder.encodeAsBitmap();
            // Setting Bitmap to ImageView
            //qrImage.setImageBitmap(bitmap);
            shareImage(bitmap,title);


        } catch (WriterException e) {
            Log.v("Error:", e.toString());
        }
        return  bitmap;
    }

    private void shareImage(Bitmap bitmap, String text){
        Context context = getApplicationContext();

        try {
            String pathofBmp= MediaStore.Images.Media.insertImage(getContentResolver(),bitmap,"title", null);
            Uri uri = Uri.parse(pathofBmp);
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("image/*");
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Iniciar App");
            shareIntent.putExtra(Intent.EXTRA_TEXT, text);
            shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
            startActivity(Intent.createChooser(shareIntent, "Compartir evento"));
        }catch (Exception ex){
            Toast.makeText(context,"Whatsapp have not been installed.",Toast.LENGTH_LONG);

        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.principal_menu, menu);
        this.menu = menu;
        return true;
    }
}
