package com.example.appveterinaria;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.auth.FirebaseAuthException;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class RegistroUsuario extends AppCompatActivity {

    TextView fecharegistro;
    EditText correo,contraseña,nombre,edad;
    Button btnGuardar;
    ProgressDialog progressDialog;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_usuario);

        fecharegistro=findViewById(R.id.fecharegistro);
        correo = findViewById(R.id.correo);
        contraseña = findViewById(R.id.contraseña);
        nombre = findViewById(R.id.nombre);
        edad = findViewById(R.id.edad);
        btnGuardar = findViewById(R.id.btnGuardar);

        auth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(this);

        Date date = new Date();
        SimpleDateFormat fecha = new SimpleDateFormat("d 'de' MMMM 'del' yyyy");
        String sfecha = fecha.format(date);
        fecharegistro.setText(sfecha);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mail = correo.getText().toString();
                String password = contraseña.getText().toString();
                String name = nombre.getText().toString();
                String age = edad.getText().toString();
                if (mail.equals("") || password.equals("") || name.equals("") || age.equals("")) {
                    Toast.makeText(RegistroUsuario.this, "Debe llenar todos los campos", Toast.LENGTH_SHORT).show();
                } else {
                    if (password.length() < 6) {
                        contraseña.setError("La contraseña debe ser mayor o igual a 6 caracteres");
                        contraseña.setFocusable(true);
                    } else {
                        GuardarAdministrador(mail, password);
                    }
                }
            }
        });
        progressDialog.setMessage("Ingresando nuevo registro...");
    }


    private void GuardarAdministrador(String mail, String password){
        progressDialog.show();
        auth.createUserWithEmailAndPassword(mail, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){
                            progressDialog.dismiss();
                            FirebaseUser user= auth.getCurrentUser();
                            assert user != null;
                            String UID=user.getUid();
                            String mail=correo.getText().toString();
                            String pass=contraseña.getText().toString();
                            String name = nombre.getText().toString();
                            String age = edad.getText().toString();
                            int ageint=Integer.parseInt(age);
                            HashMap<Object, Object> Usuarios = new HashMap<>();//Con HashMap podemos enviar datos, por ej. al servidor de Firebase
                            Usuarios.put("UID",UID);//el primero será el campo en firebase.
                            Usuarios.put("CORREO",mail);
                            Usuarios.put("PASSWORD",pass);
                            Usuarios.put("NOMBRE",name);
                            Usuarios.put("EDAD",age);
                            Usuarios.put("IMAGEN","");

                            FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
                            DatabaseReference reference= firebaseDatabase.getReference("BDUSUARIOS");
                            reference.child(UID).setValue(Usuarios);
                            startActivity(new Intent(RegistroUsuario.this, Login.class));//enviarlo a la p{agina administrador
                            Toast.makeText(RegistroUsuario.this,"Se ha registrado el usuario con email: "+correo.getText(),Toast.LENGTH_LONG).show();
                            RegistroUsuario.this.finish();
                        }else {
                            progressDialog.dismiss();
                            String errorCode = ((FirebaseAuthException) task.getException()).getErrorCode();
                            mandaError(errorCode);
                        }
                    }
                });
    }
    private void mandaError(String error) {
        switch (error){
            case "ERROR_INVALID_CUSTOM_TOKEN":
                Toast.makeText(RegistroUsuario.this, "El formato del token personalizado es incorrecto. Por favor revise la documentación", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_INVALID_CREDENTIAL":
                Toast.makeText(RegistroUsuario.this, "La credencial de autenticación proporcionada tiene un formato incorrecto o ha caducado.", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_INVALID_EMAIL":
                correo.setError("Formato de correo electrónico inválido.");
                correo.requestFocus();
                break;

            case "ERROR_WRONG_PASSWORD":
                Toast.makeText(RegistroUsuario.this, "La contraseña no es válida o el usuario no tiene contraseña.", Toast.LENGTH_LONG).show();
                contraseña.setError("la contraseña es incorrecta ");
                contraseña.requestFocus();
                contraseña.setText("");
                break;

            case "ERROR_ACCOUNT_EXISTS_WITH_DIFFERENT_CREDENTIAL":
                Toast.makeText(RegistroUsuario.this, "Ya existe una cuenta con la misma dirección de correo electrónico pero diferentes credenciales de inicio de sesión.", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_EMAIL_ALREADY_IN_USE":
                correo.setError("La dirección de correo electrónico ya está siendo utilizada por otra cuenta.");
                correo.requestFocus();
                break;


            case "ERROR_USER_NOT_FOUND":
                Toast.makeText(RegistroUsuario.this, "No hay ningún registro de usuario que corresponda a este identificador. Es posible que se haya eliminado al usuario.", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_INVALID_USER_TOKEN":
                Toast.makeText(RegistroUsuario.this, "La credencial del usuario ya no es válida. El usuario debe iniciar sesión nuevamente.", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_OPERATION_NOT_ALLOWED":
                Toast.makeText(RegistroUsuario.this, "Esta operación no está permitida. Debes habilitar este servicio en la consola.", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_WEAK_PASSWORD":
                Toast.makeText(RegistroUsuario.this, "La contraseña proporcionada no es válida..", Toast.LENGTH_LONG).show();
                contraseña.setError("La contraseña no es válida, debe tener al menos 6 caracteres");
                contraseña.requestFocus();
                break;

        }

    }
    }
