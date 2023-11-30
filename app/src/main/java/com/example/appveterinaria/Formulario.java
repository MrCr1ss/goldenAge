package com.example.appveterinaria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class Formulario extends AppCompatActivity {

    private EditText nuFicha,nAnimal,edAnimal;
    private Spinner tipAnimal;
    private Button btnAgregar,btnMenu;
    private CheckBox confBtn;

    private ArrayList <Animal> animales;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        nuFicha = (EditText) findViewById(R.id.nuFicha);
        nAnimal = (EditText) findViewById(R.id.nAnimal);
        edAnimal = (EditText) findViewById(R.id.nuEdad);
        tipAnimal = findViewById(R.id.tipAnimal);
        confBtn = (CheckBox) findViewById(R.id.cConf);
        btnAgregar = (Button) findViewById(R.id.btnAdd2);
        btnMenu = (Button) findViewById(R.id.btnMenu);


        Intent i=getIntent();
        animales=i.getParcelableArrayListExtra("animales");

        botonAgregar();
        botonMenu();
}
    private void botonAgregar() {
        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (nuFicha == null ||
                        nAnimal == null ||
                        edAnimal == null ||
                        tipAnimal.getSelectedItemPosition() == 0 ||
                        !confBtn.isChecked()) {
                    Toast.makeText(Formulario.this, "Complete los campos faltantes", Toast.LENGTH_SHORT).show();

                } else {
                    String fich = nuFicha.getText().toString();
                    String nomb = nAnimal.getText().toString();
                    String edad = edAnimal.getText().toString();
                    String tipo = tipAnimal.getSelectedItem().toString();

                    Animal a = new Animal();
                    a.setnFicha(fich);
                    a.setnAnimal(nomb);
                    a.seteAnimal(edad);
                    a.settAnimal(tipo);

                    animales.add(a);

                    // Crear un animal predeterminado
                    Animal animalPredeterminado = new Animal();
                    animalPredeterminado.setnFicha("4");
                    animalPredeterminado.setnAnimal("AnimalPredeterminado");
                    animalPredeterminado.seteAnimal("6");
                    animalPredeterminado.settAnimal("Perro");

                    // Agregar el animal predeterminado a la lista de animales
                    animales.add(animalPredeterminado);

                    nuFicha.setText("");
                    nAnimal.setText("");
                    edAnimal.setText("");
                    tipAnimal.setSelection(0);
                    if (confBtn.isChecked()) {
                        confBtn.setChecked(false);

                    }
                    Toast.makeText(Formulario.this, "Nuevo paciente agregado", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
    private void botonMenu() {
        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Formulario.this, MainActivity.class);
                i.putParcelableArrayListExtra("animales", animales);
                startActivity(i);
                finish();

            }
        });
    }

}

