package com.example.appveterinaria;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.TypedArrayUtils;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class Login extends AppCompatActivity {
    private TextView ing,usuario,passw;
    private EditText etUsuario, etPass;
    private Button btnIngresar,btnRegistrar;

    FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etUsuario = findViewById(R.id.etUsuario);
        etPass = findViewById(R.id.etPass);
        btnIngresar= findViewById(R.id.btnIngresar);
        btnRegistrar=findViewById(R.id.btnRegistrar);

        ing=findViewById(R.id.ingreso);
        usuario=findViewById(R.id.textView3);
        passw=findViewById(R.id.textView);

        auth = FirebaseAuth.getInstance();
        //Estilo letras
        String ubicacion="fonts/Quicksand-Italic.otf";
        Typeface tf= Typeface.createFromAsset(Login.this.getAssets(),ubicacion);
        ing.setTypeface(tf);
        usuario.setTypeface(tf);
        passw.setTypeface(tf);
        /////////

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Login.this,
                        RegistroUsuario.class);
                startActivity(i);
            }
        });
        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etUsuario.length() == 0 || etPass.length() == 0) {
                    Toast.makeText(Login.this, "Debe ingresar correo y contraseña", Toast.LENGTH_LONG).show();
                } else {
                    String mail =etUsuario.getText().toString();
                    String pass= etPass.getText().toString();
                    auth.signInWithEmailAndPassword(mail, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                irInicio();
                            } else {
                                String errorCode = ((FirebaseAuthException) task.getException()).getErrorCode();
                                mandaError(errorCode);
                            }
                        }
                    });
                }
            }
        });

    }
    private void mandaError(String error){
        switch (error) {

            case "ERROR_INVALID_CUSTOM_TOKEN":
                Toast.makeText(Login.this, "El formato del token personalizado es incorrecto. Por favor revise la documentación", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_CUSTOM_TOKEN_MISMATCH":
                Toast.makeText(Login.this, "El token personalizado corresponde a una audiencia diferente.", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_INVALID_CREDENTIAL":
                Toast.makeText(Login.this, "La credencial de autenticación proporcionada tiene un formato incorrecto o ha caducado.", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_INVALID_EMAIL":
                etUsuario.setError("Formato de correo electrónico inválido");
                etUsuario.requestFocus();
                break;

            case "ERROR_WRONG_PASSWORD":
                etPass.setError("La contraseña no es válida");
                etPass.requestFocus();
                etPass.setText("");
                break;

            case "ERROR_USER_MISMATCH":
                Toast.makeText(Login.this, "Las credenciales proporcionadas no corresponden al usuario que inició sesión anteriormente..", Toast.LENGTH_LONG).show();
                break;


            case "ERROR_USER_TOKEN_EXPIRED":
                Toast.makeText(Login.this, "La credencial del usuario ya no es válida. El usuario debe iniciar sesión nuevamente.", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_USER_NOT_FOUND":
                Toast.makeText(Login.this, "Usuario no registrado.", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_WEAK_PASSWORD":
                Toast.makeText(Login.this, "La contraseña proporcionada no es válida..", Toast.LENGTH_LONG).show();
                etPass.setError("La contraseña no es válida, debe tener al menos 6 caracteres");
                etPass.requestFocus();
                break;

        }
    }
    private void irInicio(){
        Toast.makeText(this,"Ha ingresado con éxito",Toast.LENGTH_LONG).show();
        Intent i = new Intent(Login.this, PrincipalUsuario.class);
        startActivity(i);
    }
}

