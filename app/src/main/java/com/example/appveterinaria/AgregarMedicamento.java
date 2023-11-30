package com.example.appveterinaria;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class AgregarMedicamento extends AppCompatActivity {

    private EditText editTextNombreMed,editTextDosis;
    private Spinner tipDosi;
    private Button btnAgregar;
    private CheckBox checkBoxAlta,checkBoxNormalm,checkBoxPas,checkBoxCap,checkBoxJar,checkBoxCrem,checkBoxGot,checkBoxInha;

    private ArrayList <Animal> animales;
    private ArrayList <Medicamento> medicamentos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_medicamento);

        editTextNombreMed = (EditText) findViewById(R.id.editTextNombreMed);
        editTextDosis = (EditText) findViewById(R.id.editTextDosis);
        tipDosi = findViewById(R.id.tipDosi);
        checkBoxAlta = (CheckBox) findViewById(R.id.checkBoxAlta);
        checkBoxNormalm = (CheckBox) findViewById(R.id.checkBoxNormal);
        checkBoxPas = (CheckBox) findViewById(R.id.checkBoxPas);
        checkBoxCap = (CheckBox) findViewById(R.id.checkBoxCap);
        checkBoxJar = (CheckBox) findViewById(R.id.checkBoxJar);
        checkBoxCrem = (CheckBox) findViewById(R.id.checkBoxCrem);
        checkBoxGot = (CheckBox) findViewById(R.id.checkBoxGot);
        checkBoxInha = (CheckBox) findViewById(R.id.checkBoxInha);
        btnAgregar = (Button) findViewById(R.id.btnAdd);

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
        Intent i=getIntent();
        animales=i.getParcelableArrayListExtra("animales");

        agregarMedicamentoAlAnimal(animal);
    }
    private void agregarMedicamentoAlAnimal(Animal animal) {
        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (animal != null) {
                    // Obtener datos del formulario o de donde sea necesario
                    String nombreMedicamento = editTextNombreMed.getText().toString();
                    String dosisMedicamento = editTextDosis.getText().toString();


                    // Crear un nuevo medicamento
                    Medicamento nuevoMedicamento = new Medicamento(nombreMedicamento, dosisMedicamento);

                    // Agregar el medicamento a la lista de medicamentos del animal
                    animal.agregarMedicamento(nuevoMedicamento);
                    Intent i = new Intent(AgregarMedicamento.this, ImprimirMedicamento.class);
                    i.putParcelableArrayListExtra("animales", animales);
                    Toast.makeText(AgregarMedicamento.this, "Nuevo paciente Medicamento", Toast.LENGTH_LONG).show();
                    startActivity(i);
                    finish();
                } else {
                    Toast.makeText(AgregarMedicamento.this, "Error Medicamento no agregado", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}