package com.example.appveterinaria;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class Eliminar extends AppCompatActivity {

    private EditText busEliminar;
    private Button btnEliminar,btnMenu;

    private ArrayList<Animal> animales;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar);

        Intent i=getIntent();
        animales=i.getParcelableArrayListExtra("animales");

        busEliminar=findViewById(R.id.buscarElim);
        btnEliminar=findViewById(R.id.btnDelete2);
        btnMenu=findViewById(R.id.btnMenu3);

        botonEliminar();
        botonMenu();
    }
    private void botonEliminar() {
        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int a = 0; a < animales.size(); a++) {
                    if (busEliminar.getText().toString().equals(animales.get(a).getnFicha().toString())) {
                        AlertDialog.Builder b = new AlertDialog.Builder(Eliminar.this);
                        b.setCancelable(false);
                        b.setTitle("Advertencia");
                        b.setMessage("¿Está seguro que quiere eliminar?");
                        b.setIcon(R.drawable.borrar);
                        b.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(Eliminar.this, "No se eliminó", Toast.LENGTH_LONG).show();

                            }
                        });
                        int finalA = a;
                        b.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                animales.remove(finalA);
                                Toast.makeText(Eliminar.this, "Se eliminó con éxito", Toast.LENGTH_LONG).show();

                            }
                        });
                        b.show();
                    } else if (busEliminar == null) {
                        Toast.makeText(Eliminar.this, "Debe Ingresar un n° de ficha", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(Eliminar.this, "No se encontró la ficha", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }private void botonMenu() {
        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Eliminar.this, MainActivity.class);
                i.putParcelableArrayListExtra("animales", animales);
                startActivity(i);
                finish();

            }
        });
    }}
