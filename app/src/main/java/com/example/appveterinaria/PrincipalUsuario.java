package com.example.appveterinaria;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.widget.Toolbar;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.appveterinaria.FragmentosUsuario.InicioUsuario;
import com.example.appveterinaria.FragmentosUsuario.MenuVet;
import com.example.appveterinaria.FragmentosUsuario.Salir;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class PrincipalUsuario extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    FirebaseAuth auth;
    Toolbar toolbar;
    private ArrayList<Animal> animales;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal_usuario);

        Intent i = getIntent();
        animales = i.getParcelableArrayListExtra("animales");

        toolbar = findViewById(R.id.toolbarUsuario);

        auth = FirebaseAuth.getInstance();

        drawerLayout = findViewById(R.id.drawerLayout_usuario);

        NavigationView navigationView = findViewById(R.id.nav_view_usuario);

        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        if (savedInstanceState == null) {//establecer un fragment por defecto
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container_usuario,//reemplaza al fragment del menú
                            new InicioUsuario()).commit();
            navigationView.setCheckedItem(R.id.Inicio);
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.Inicio:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container_usuario,
                                new InicioUsuario()).commit();
                break;
            case R.id.MenuVet:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container_usuario,
                                new MenuVet()).commit();

                break;
            case R.id.Salir:
                cerrarSesion();
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;


    }
    private void cerrarSesion(){
        AlertDialog.Builder a = new AlertDialog.Builder(PrincipalUsuario.this);
        a.setCancelable(false);
        a.setTitle("Advertencia");
        a.setMessage("¿Está seguro que desea cerrar su sesión?");
        a.setIcon(R.drawable.clinica);
        a.setNegativeButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                auth.getInstance().signOut();
                Toast.makeText(PrincipalUsuario.this, "Se ha cerrado sesión.", Toast.LENGTH_LONG).show();
                finish();
            }
        });
        a.setPositiveButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(PrincipalUsuario.this,
                        PrincipalUsuario.class);
                startActivity(intent);
                Toast.makeText(PrincipalUsuario.this, "No se ha cerrado sesión.", Toast.LENGTH_LONG).show();
            }
        });
        a.show();
    }
}

