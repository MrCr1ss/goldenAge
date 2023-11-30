package com.example.appveterinaria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.view.View;
import android.widget.Button;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class MenupacientesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menupacientes);
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

        Button buttonVCuidados = findViewById(R.id.buttonVCuidados);

        // Configura un OnClickListener para el botón
        buttonVCuidados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Crea un Intent para iniciar la nueva actividad
                Intent intent = new Intent(MenupacientesActivity.this, PlantillaCuidado.class);
                intent.putExtra("NOMBRE", nombre);
                intent.putExtra("N_FICHA", run);
                startActivity(intent);
            }
        });
    }
}