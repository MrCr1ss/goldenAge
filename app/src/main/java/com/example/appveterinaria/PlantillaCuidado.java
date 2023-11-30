package com.example.appveterinaria;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.ImageView;
import android.view.View;
import android.content.Intent;

import android.os.Bundle;
import android.widget.TextView;

public class PlantillaCuidado extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plantilla_cuidado);
        // Recuperar datos del Intent
        Intent intent = getIntent();
        String nombre = intent.getStringExtra("NOMBRE");
        String run = intent.getStringExtra("N_FICHA");

        // Mostrar el nombre en un TextView
        TextView textViewNombre = findViewById(R.id.textViewNombre);
        textViewNombre.setText("Nombre del Paciente: " + nombre);

        // Mostrar el Run en un TextView
        TextView textViewRun = findViewById(R.id.textViewRun);
        textViewRun.setText("Run del Paciente: " + run);

        // Obtén referencias a las imágenes por sus ID
        ImageView imageButton1 = findViewById(R.id.imageButtonMed);

        // Configura un OnClickListener para cada imagen
        imageButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onClickImage1();
            }
        });


    }
    private void onClickImage1() {
        // Crea un Intent para iniciar la nueva actividad (reemplaza OtraPaginaActivity.class con el nombre de tu actividad de destino)
        Intent intent = new Intent(PlantillaCuidado.this, Medicamento.class);
        // Recupera los datos del Intent
        Intent intentOrigen = getIntent();
        String nombre = intentOrigen.getStringExtra("NOMBRE");
        String run = intentOrigen.getStringExtra("N_FICHA");

        // Pasa los datos a través del Intent
        intent.putExtra("NOMBRE", nombre);
        intent.putExtra("N_FICHA", run);
        Animal animal = intentOrigen.getParcelableExtra("ANIMAL_SELECCIONADO");
        intent.putExtra("ANIMAL_SELECCIONADO", animal);
        startActivity(intent);
    }

}