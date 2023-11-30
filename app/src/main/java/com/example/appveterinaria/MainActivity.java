package com.example.appveterinaria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.appveterinaria.FragmentosUsuario.InicioUsuario;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ImageButton botonAdd,botonPrint,botonDelete,botonEdit,botonInicio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        botonAdd=findViewById(R.id.btnAdd);
        botonPrint=findViewById(R.id.btnPrint);
        botonDelete=findViewById(R.id.btnDelete);
        botonEdit=findViewById(R.id.btnEdit);
        botonInicio=findViewById(R.id.btnInicio);

        Intent i = getIntent();
        ArrayList <Animal> animales = i.getParcelableArrayListExtra("animales");


        botonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,
                        Formulario.class);
                i.putParcelableArrayListExtra("animales",animales);
                startActivity(i);
                finish();
            }
        });

        botonPrint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,
                        Imprimir.class);
                i.putParcelableArrayListExtra("animales",animales);
                startActivity(i);
                finish();
            }
        });
        botonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,
                        Eliminar.class);
                i.putParcelableArrayListExtra("animales",animales);
                startActivity(i);
                finish();
            }
        });
        botonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,
                        Editar.class);
                i.putParcelableArrayListExtra("animales",animales);
                startActivity(i);
                finish();
            }
        });
        botonInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, PrincipalUsuario.class);
                i.putParcelableArrayListExtra("animales",animales);
                startActivity(i);
                finish();
            }
        });

    }

}