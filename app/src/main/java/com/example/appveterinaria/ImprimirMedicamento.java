package com.example.appveterinaria;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class ImprimirMedicamento extends AppCompatActivity {

    private ListView lAnimales;
    private Button botonMenu;

    private ArrayList<Animal> animales;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imprimir_medicamento);

        lAnimales = findViewById(R.id.lAnimales);
        botonMenu = findViewById(R.id.btnMenu2);

        Intent i = getIntent();
        animales = i.getParcelableArrayListExtra("animales");

        listarAnimales();
        irMenu();
    }

    private void listarAnimales() {
        List<Medicamento> todosLosMedicamentos = new ArrayList<>();

        for (Animal animal : animales) {
            todosLosMedicamentos.addAll(animal.getMedicamentos());
        }

        // Crear un adaptador para mostrar los nombres de los medicamentos
        ArrayAdapter<Medicamento> adapter = new ArrayAdapter<>(ImprimirMedicamento.this, android.R.layout.simple_list_item_1, todosLosMedicamentos);
        lAnimales.setAdapter(adapter);

        lAnimales.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // Aquí puedes realizar acciones adicionales si es necesario
            }
        });
    }

    private void irMenu() {
        botonMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ImprimirMedicamento.this, Medicamento.class);
                i.putParcelableArrayListExtra("animales", animales);
                startActivity(i);
                finish();
            }
        });
    }
}
