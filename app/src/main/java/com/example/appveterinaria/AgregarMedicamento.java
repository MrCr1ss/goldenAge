package com.example.appveterinaria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class AgregarMedicamento extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_medicamento);



        // Recuperar datos del Intent
        Intent intent = getIntent();
        String nombre = intent.getStringExtra("NOMBRE");
        String run = intent.getStringExtra("N_FICHA");
        Animal animal = intent.getParcelableExtra("ANIMAL_SELECCIONADO");

        // Mostrar el nombre en un TextView
        TextView textViewNombre = findViewById(R.id.textViewNombre);
        textViewNombre.setText("Nombre del Paciente: " + nombre);

        // Mostrar el Run en un TextView
        TextView textViewRun = findViewById(R.id.textViewRun);
        textViewRun.setText("Run del Paciente: " + run);

        // Lógica para agregar medicamento al animal
        agregarMedicamentoAlAnimal(animal);
    }
    private void agregarMedicamentoAlAnimal(Animal animal) {
        // Obtener datos del formulario o de donde sea necesario
        String nombreMedicamento = obtenerNombreMedicamentoDelFormulario();
        String dosisMedicamento = obtenerDosisMedicamentoDelFormulario();

        // Crear un nuevo medicamento
        Medicamento nuevoMedicamento = new Medicamento(idMedicamento, nombreMedicamento, dosisMedicamento);

        // Agregar el medicamento a la lista de medicamentos del animal
        animal.agregarMedicamento(nuevoMedicamento);

    }

}