package com.barbaro.hellochilaquilesteam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.barbaro.hellochilaquilesteam.models.Book;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

public class CreateBookActivity extends AppCompatActivity {


    private EditText editTextTitle;
    private EditText editTextAuthor;
    private EditText editTextYear;

    private FirebaseDatabase firebaseDatabase; // API - Realtime Database Service
    private DatabaseReference booksReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_book);

        firebaseDatabase = FirebaseDatabase.getInstance(); // Obteniendo la base de datos

        editTextTitle = findViewById(R.id.editTextTitle);
        editTextAuthor = findViewById(R.id.editTextAuthor);
        editTextYear = findViewById(R.id.editTextYear);

        Button btnCreateBook = findViewById(R.id.btnCreateBook);
        btnCreateBook.setOnClickListener(this::createBook);

        Button btnView = findViewById(R.id.btnViewList);
        btnView.setOnClickListener(this::navigateToListBooks);
    }

    public void createBook(View view) {

        String title = editTextTitle.getText().toString().trim();
        String author = editTextAuthor.getText().toString().trim();
        String year = editTextYear.getText().toString().trim();

        // TODO MISSING VALIDATIONS

        Book book = new Book(1, title, author, year);

        createBookInDatabase(book);
    }

    private void createBookInDatabase(Book book) {
        // Obtenemos donde guardamos los datos de libros
        // Si existe la trae y sino la crea y la trae
        booksReference = firebaseDatabase.getReference("books");

        // Obtenemos referencia para crear un nuevo libro (espacio que se llena)
        DatabaseReference newBookRef = booksReference.push();

        String key = newBookRef.getKey(); // un valor alfanúmerico para identificar

        // Guardar los datos del libro en la key que se generó
        booksReference.child(key).setValue(book)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            showMessage("Se guardó el libro con éxito");
                        } else {
                            task.getException().printStackTrace();
                            showMessage("Error al guardar libro");
                        }
                    }
                });
    }

    private void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void navigateToListBooks(View view) {
        Intent intent = new Intent(CreateBookActivity.this, MainActivity.class);
        startActivity(intent);
    }
}