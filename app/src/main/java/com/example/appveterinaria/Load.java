package com.example.appveterinaria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

public class Load extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);

        final int TIEMPO=5000;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(Load.this,Login.class);
                startActivity(intent);
                finish();
            }
        }, TIEMPO);

        TextView app_name,bienvenida;
        String ubicacion="fonts/Quicksand-Italic.otf";
        Typeface tf= Typeface.createFromAsset(Load.this.getAssets(),ubicacion);

        app_name=findViewById(R.id.mensaje);
        bienvenida=findViewById(R.id.bienvenida);

        app_name.setTypeface(tf);
        bienvenida.setTypeface(tf);
    }
}