package com.example.appveterinaria.FragmentosUsuario;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.appveterinaria.Animal;
import com.example.appveterinaria.Editar;
import com.example.appveterinaria.Eliminar;
import com.example.appveterinaria.Formulario;
import com.example.appveterinaria.Imprimir;
import com.example.appveterinaria.MainActivity;
import com.example.appveterinaria.PrincipalUsuario;
import com.example.appveterinaria.R;

import java.util.ArrayList;


public class MenuVet extends Fragment {
    private ImageButton botonAdd,botonPrint,botonDelete,botonEdit,botonInicio;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_menu_vet, container, false);
        botonAdd = view.findViewById(R.id.btnAdd);
        botonPrint = view.findViewById(R.id.btnPrint);
        botonDelete = view.findViewById(R.id.btnDelete);
        botonEdit = view.findViewById(R.id.btnEdit);
        botonInicio= view.findViewById(R.id.btnInicio);

        ArrayList<Animal> animales = new ArrayList<Animal>();

        botonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
                Intent i = new Intent(getActivity(),
                        Formulario.class);
                i.putParcelableArrayListExtra("animales", animales);
                startActivity(i);
            }
        });

        botonPrint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
                Intent i = new Intent(getActivity(),
                        Imprimir.class);
                i.putParcelableArrayListExtra("animales", animales);
                startActivity(i);
            }
        });
        botonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
                Intent i = new Intent(getActivity(),
                        Eliminar.class);
                i.putParcelableArrayListExtra("animales", animales);
                startActivity(i);
            }
        });
        botonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
                Intent i = new Intent(getActivity(),
                        Editar.class);
                i.putParcelableArrayListExtra("animales", animales);
                startActivity(i);
            }
        });
        botonInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
                Intent i = new Intent(getActivity(),
                        PrincipalUsuario.class);
                i.putParcelableArrayListExtra("animales",animales);
                startActivity(i);
            }
        });

        return view;
    }

}


