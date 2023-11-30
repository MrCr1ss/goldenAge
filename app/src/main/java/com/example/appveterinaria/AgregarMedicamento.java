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


public class AgregarMedicamento extends AppCompatActivity {

    private EditText editTextNombreMed,editTextDosis;
    private Spinner tipDosi;
    private Button btnAgregar;
    private CheckBox checkBoxAlta,checkBoxNormalm,checkBoxPas,checkBoxCap,checkBoxJar,checkBoxCrem,checkBoxGot,checkBoxInha;

    @SuppressLint("MissingInflatedId")
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
        btnAgregar = (Button) findViewById(R.id.btnAdd2);


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

        btnAgregar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // Lógica para agregar medicamento al animal
                agregarMedicamentoAlAnimal(animal);
            }
        });
    }
    private void agregarMedicamentoAlAnimal(Animal animal) {
        if (animal != null) {
            // Obtener datos del formulario o de donde sea necesario
            String nombreMedicamento = editTextNombreMed.getText().toString();
            String dosisMedicamento = editTextDosis.getText().toString();


            // Crear un nuevo medicamento
            Medicamento nuevoMedicamento = new Medicamento(nombreMedicamento, dosisMedicamento);

            // Agregar el medicamento a la lista de medicamentos del animal
            animal.agregarMedicamento(nuevoMedicamento);
        } else {
            Toast.makeText(this, "Animal es nulo", Toast.LENGTH_SHORT).show();
        }
        // Crea un Intent para iniciar la nueva actividad
        Intent intent = new Intent(this, ImprimirMedicamento.class);
        // Recupera los datos del Intent
        Intent intentOrigen = getIntent();
        if (intent != null) {
            String nombre = intentOrigen.getStringExtra("NOMBRE");
            String run = intentOrigen.getStringExtra("N_FICHA");

            // Pasa los datos a través del Intent
            intent.putExtra("NOMBRE", nombre);
            intent.putExtra("N_FICHA", run);
        } else {
            Toast.makeText(this, "Intent es nulo", Toast.LENGTH_SHORT).show();
        }
        //
        // Pasa el objeto Animal a través del Intent
        intent.putExtra("ANIMAL_SELECCIONADO", animal);
        startActivity(intent);

    };

}