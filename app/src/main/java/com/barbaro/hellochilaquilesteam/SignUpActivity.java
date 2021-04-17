package com.barbaro.hellochilaquilesteam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.widget.EditText;
import android.widget.CheckBox;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpActivity extends AppCompatActivity {

    private EditText editTextEmail;
    private EditText editTextPassword;
    private EditText editTextConfirmPassword;
    private CheckBox checkBoxTerms;
    private Button btnSignUp;

    private FirebaseAuth firebaseAuth; // API - Firebase Authentication

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        firebaseAuth = FirebaseAuth.getInstance(); // Necesitamos recuperar una instancia del servicio

        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextConfirmPassword = findViewById(R.id.editTextConfirmPassword);

        btnSignUp = findViewById(R.id.btnSignUp);
        btnSignUp.setOnClickListener(this::signUp);
    }

    public void signUp(View view) {

        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String confirmPassword = editTextConfirmPassword.getText().toString().trim();

        if(email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            showMessage("Por favor llena todo los campos");
        } else {
            if(confirmPassword.equals(password)) {
                if(password.length() >= 8) {
                    registerUser(email, password);
                } else {
                    showMessage("Ingres una contraseña de más 8 caractéres");
                }
            } else {
                showMessage("Las constraseñas no son iguales");
            }
        }
    }

    public void registerUser(String email, String password) {

        // Lógica para registrar un nuevo usuario
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() /* Contracto */{
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        // Preguntamos si se registro con exito el usuario con correo y contraseña
                        if(task.isSuccessful()) {
                            navigateToMain();
                        } else {
                            showMessage("Existió un problema");
                        }
                    }
                });
    }

    public void navigateToMain(View view) {
        Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public void navigateToMain() {
        Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
        startActivity(intent);
    }

    private void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}