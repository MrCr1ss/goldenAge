package com.example.appveterinaria.FragmentosUsuario;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.appveterinaria.Animal;
import com.example.appveterinaria.Load;
import com.example.appveterinaria.R;

import java.util.ArrayList;


public class InicioUsuario extends Fragment {

    TextView inicioApp, clinicaApp;


    private ArrayList<Animal> animales;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_inicio_usuario, container, false);

        inicioApp= view.findViewById(R.id.inicioApp);
        clinicaApp = view.findViewById(R.id.clinicaApp);



        String ubicacion="fonts/Quicksand-Italic.otf";
        Typeface tf= Typeface.createFromAsset(getActivity().getAssets(),ubicacion);

        inicioApp.setTypeface(tf);
        clinicaApp.setTypeface(tf);

        return view;
}}