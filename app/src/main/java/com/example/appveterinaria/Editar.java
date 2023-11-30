package com.example.appveterinaria;

import androidx.appcompat.app.AppCompatActivity;

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

public class Editar extends AppCompatActivity {
    private TextView nFicha,tipAnimal2;
    private EditText nAnimal2,edAnimal2,busAnimal;
    private Spinner tipAnimal3;
    private Button btnBuscar,btnActualizar,btnMenu;
    private CheckBox confBtn;

    private ArrayList<Animal> animales;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);

        nFicha=findViewById(R.id.nFicha2);
        nAnimal2 = findViewById(R.id.nAnimal2);
        edAnimal2 = findViewById(R.id.nuEdad2);
        tipAnimal2=findViewById(R.id.tipAnimal2);
        tipAnimal3 = findViewById(R.id.tipAnimal3);
        confBtn = findViewById(R.id.cConf);
        busAnimal=findViewById(R.id.buscar);
        btnBuscar= findViewById(R.id.btnBuscar);
        btnMenu=findViewById(R.id.btnMenu4);
        btnActualizar=findViewById(R.id.btnActualizar);

        Intent i=getIntent();
        animales=i.getParcelableArrayListExtra("animales");

        botonBuscar();
        botonActualizar();
        botonMenu();
    }
    private void botonBuscar() {
        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int va=0;
                for (int a = 0; a < animales.size(); a++) {
                    if (busAnimal.getText().toString().equals(animales.get(a).getnFicha().toString())) {
                        va=1;
                        String nF=animales.get(a).getnFicha().toString();
                        String nA=animales.get(a).getnAnimal().toString();
                        String eA=animales.get(a).geteAnimal().toString();
                        String tA=animales.get(a).gettAnimal().toString();

                        nFicha.setText(nF);
                        nAnimal2.setText(nA);
                        edAnimal2.setText(eA);
                        tipAnimal2.setText(tA);

                        Toast.makeText(Editar.this, "Ficha encontrada", Toast.LENGTH_LONG).show();

                    } else if (busAnimal.length()==0) {
                        Toast.makeText(Editar.this, "Debe Ingresar un n° de ficha", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(Editar.this, "No se encontró la ficha", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
    private void botonActualizar(){
        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int a=0; a<animales.size(); a++){
                    if(nFicha.getText().toString().equals(animales.get(a).getnFicha())){
                        if(nAnimal2.length()==0||edAnimal2.length()==0||tipAnimal3.getSelectedItemPosition()==0||!confBtn.isChecked()){
                            Toast.makeText(Editar.this,"Ingrese los datos solicitados",Toast.LENGTH_LONG).show();
                        }else {
                            animales.get(a).setnAnimal(nAnimal2.getText().toString());
                            animales.get(a).seteAnimal(edAnimal2.getText().toString());
                            animales.get(a).settAnimal(tipAnimal3.getSelectedItem().toString());

                            Toast.makeText(Editar.this,"Cambios realizados con éxito",Toast.LENGTH_LONG).show();
                        }
                    }else {
                        Toast.makeText(Editar.this, "No se encontró la ficha", Toast.LENGTH_LONG).show();
                    }
                }

            }
        });

    }
    private void botonMenu(){
        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Editar.this,MainActivity.class);
                i.putParcelableArrayListExtra("animales",animales);
                startActivity(i);
                finish();
            }
        });
    }

}