package com.example.appveterinaria;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Scanner;

public class Imprimir extends AppCompatActivity {
    private ListView lAnimales;
    private TextView verAnimal;
    private Button botonMenu;

    private ArrayList<Animal> animales;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imprimir);


        lAnimales = findViewById(R.id.lAnimales);
        botonMenu = findViewById(R.id.btnMenu2);
        verAnimal= findViewById(R.id.impAnimal);

        Intent i = getIntent();
        animales = i.getParcelableArrayListExtra("animales");

        listarAnimales();
        irMenu();
    }

    private void listarAnimales() {

        ArrayAdapter<Animal> adapter = new ArrayAdapter<Animal>(Imprimir.this, android.R.layout.simple_list_item_1, animales);
        lAnimales.setAdapter(adapter);

        lAnimales.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Animal a = animales.get(i);
                Intent intent = new Intent(Imprimir.this, MenupacientesActivity.class);
                intent.putExtra("N_FICHA", a.getnFicha());
                intent.putExtra("NOMBRE", a.getnAnimal());
                intent.putExtra("EDAD", a.geteAnimal());
                intent.putExtra("TIPO", a.gettAnimal());

                startActivity(intent);
            }
        });
    }

    private void irMenu() {
        botonMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Imprimir.this, MainActivity.class);
                i.putParcelableArrayListExtra("animales", animales);
                Intent intentOrigen = getIntent();
                Animal animal = intentOrigen.getParcelableExtra("ANIMAL_SELECCIONADO");
                i.putExtra("ANIMAL_SELECCIONADO", animal);
                startActivity(i);
                finish();
            }
        });
    }
}