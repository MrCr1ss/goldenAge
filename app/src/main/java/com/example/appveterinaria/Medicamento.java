package com.example.appveterinaria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Medicamento extends AppCompatActivity {

    public Medicamento(String nombreMedicamento, String dosisMedicamento) {
    }
    public Medicamento() {
        // Constructor vacío
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicamento);
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
        ImageView imageButton1 = findViewById(R.id.btnAddMed);
        ImageView imageButton2 = findViewById(R.id.btnPrintMed);

        // Configura un OnClickListener para cada imagen
        imageButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Llama al método para manejar el clic en la imagen 1
                onClickImage1();
            }
        });
        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Llama al método para manejar el clic en la imagen 1
                onClickImage2();
            }
        });

    }
    private void onClickImage1() {
        // Crea un Intent para iniciar la nueva actividad
        Intent intent = new Intent(this, AgregarMedicamento.class);
        // Recupera los datos del Intent
        Intent intentOrigen = getIntent();
        String nombre = intentOrigen.getStringExtra("NOMBRE");
        String run = intentOrigen.getStringExtra("N_FICHA");
        //
        Animal animal = intentOrigen.getParcelableExtra("ANIMAL_SELECCIONADO");

        // Pasa los datos a través del Intent
        intent.putExtra("NOMBRE", nombre);
        intent.putExtra("N_FICHA", run);
        //
        // Pasa el objeto Animal a través del Intent
        intent.putExtra("ANIMAL_SELECCIONADO", animal);
        startActivity(intent);
    }
    private void onClickImage2() {
        // Crea un Intent para iniciar la nueva actividad
        Intent intent = new Intent(this, ImprimirMedicamento.class);
        // Recupera los datos del Intent
        Intent intentOrigen = getIntent();
        String nombre = intentOrigen.getStringExtra("NOMBRE");
        String run = intentOrigen.getStringExtra("N_FICHA");
        //
        Animal animal = intentOrigen.getParcelableExtra("ANIMAL_SELECCIONADO");

        // Pasa los datos a través del Intent
        intent.putExtra("NOMBRE", nombre);
        intent.putExtra("N_FICHA", run);
        //
        // Pasa el objeto Animal a través del Intent
        intent.putExtra("ANIMAL_SELECCIONADO", animal);
        startActivity(intent);
    }
}