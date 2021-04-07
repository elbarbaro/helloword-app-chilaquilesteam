package com.barbaro.hellochilaquilesteam;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;
import android.view.View;

import android.content.Intent;

public class LoginActivity extends AppCompatActivity {

    // Variables instancia (atributos)
    private EditText editTextEmail; // Lenguaje fuertemente tipado
    private EditText editTextPassword;
    private Button btnLogin;
    private Button btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Buscar los elementos visuales para meterlos a los objetos (inflate)

        // Cajas de texto - interacción: escribir texto y va a ser tomado
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editPassword);

        // Botones - interacción: click para navegar a otra pantalla
        btnLogin = findViewById(R.id.btnLogin);
        btnSignUp = findViewById(R.id.btnSignUp);

        // Asignación de una acción al botón cuando se hace click o toca
        // Asignación de evento
        btnLogin.setOnClickListener(this::clickLogin);
        btnSignUp.setOnClickListener(this::navigateToSignUp);
    }

    private void clickLogin(View view) {
        // Asignación de valores iniciales a las variables
        String email = ""; // asignación de valor vacío
        String password = "";

        // Tomar los valores de las cajas de texto

        // get[NombreAtributo] = obtener el valor
        // set[NombreAtributo]() = guardar el valor
        email = editTextEmail.getText().toString().trim();
        password = editTextPassword.getText().toString();

        // Analisis o procesamiento

        // 'AND' Verdarero| Verdadero = Verdadero
        // 'AND' Verdadero|Falso = Falso
        // 'AND' Falso | Falso = Falso
        // 'OR' Verdadero|Verdadero = Verdadero
        // 'OR' Verdadero|Falso = Verdadero
        // 'OR' Falso |Falso = Falso

        // Operadores lógicos
        // ! not
        // && and
        // || or

        if(email.equals("leo@mail.com") && password.equals("lachida")) {

            // TODO Llamar a una acción para registrar que ya se inició sesión

            // Llamar a la acción para cambiar pantalla
            navigateToHome();
        } else {
            Toast.makeText(this, "Datos incorrectos", Toast.LENGTH_SHORT).show();
        }
    }

    private void navigateToSignUp(View view) {
        Intent intentSignUp = new Intent(LoginActivity.this, SignUpActivity.class);
        startActivity(intentSignUp);
    }

    private void navigateToHome() {
        // Creo la intención de abrir la pantalla MainActivity desde LoginActivity
        Intent intentHome = new Intent(LoginActivity.this, MainActivity.class); // Constructor

        // Ir a la nueva pantalla que quiere abrir
        startActivity(intentHome);
    }
}