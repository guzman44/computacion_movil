package co.movil.computacion.controller;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import co.movil.computacion.R;
import co.movil.computacion.assets.utilidades.ViewComponent;

public class ReaderQR extends AppCompatActivity {

    private Button btnScanner;
    private TextView tvBarCode;
    ViewComponent vc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reader_q_r);
        vc = new ViewComponent(this,"QR",null);
        vc.setDatosLogin();

        tvBarCode = findViewById(R.id.tvBarCode);
        btnScanner = findViewById(R.id.btnScanner);
        btnScanner.setOnClickListener(mOnClickListener);


    }

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btnScanner:
                    new IntentIntegrator(ReaderQR.this).initiateScan();
                    break;
            }
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null)
            if (result.getContents() != null){

                Intent intent = new Intent(this, DetailEvent.class);
                intent.putExtra("Title", result.getContents());
                intent.putExtras(vc.getUserBuble());
                startActivity(intent);

            }else{
                tvBarCode.setText("Error al escanear el código de barras");
            }
    }


}
