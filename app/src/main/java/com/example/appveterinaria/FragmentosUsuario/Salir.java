package com.example.appveterinaria.FragmentosUsuario;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.appveterinaria.Eliminar;
import com.example.appveterinaria.Login;
import com.example.appveterinaria.MainActivity;
import com.example.appveterinaria.PrincipalUsuario;
import com.example.appveterinaria.R;
import com.google.firebase.auth.FirebaseAuth;


public class Salir extends Fragment {
    FirebaseAuth auth;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_salir, container, false);
        return view;
    }}

