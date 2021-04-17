package com.barbaro.hellochilaquilesteam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.barbaro.hellochilaquilesteam.models.Book;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerListBooks;

    // Objeto lista para guardar libros
    private ArrayList<Book> listBooks;

    // Firebase services
    private FirebaseDatabase firebaseDatabase; // API
    private DatabaseReference booksReference;

    private BookAdapter adapter; // FoodAdapter

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseDatabase = FirebaseDatabase.getInstance();

        recyclerListBooks = findViewById(R.id.listBooks);

        LinearLayoutManager acomodador = new LinearLayoutManager(this);
        recyclerListBooks.setLayoutManager(acomodador);

        // Creando una lista
        listBooks = new ArrayList<>();

        Book howToCodeBook =
                new Book(1, "How to code in Java", "Deitel & Deitel", "2012");

        Book whatIsInternet =
                new Book(2, "Qué hace internet con nuestra mentes", "Nicholas Carr", "2013");

        Book book3 = new Book(3, "Otro libro", "Otro", "2015");

        Book book4 = new Book(3, "Libro de boleto", "Otro más", "2020");

        // Guardar en la lista los libros
        listBooks.add(howToCodeBook); // 1
        listBooks.add(whatIsInternet); // 2
        listBooks.add(book3); // 3
        listBooks.add(book4);

        // Crear un objeto que enlaza los datos de libros con el diseño de una vista
        adapter = new BookAdapter(listBooks);

        recyclerListBooks.setAdapter(adapter); // La lista carga los datos y sus vistitas

        getBooks();
    }

    private void getBooks() {
        booksReference = firebaseDatabase.getReference("books");

        // Quien lee los datos
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Iterable<DataSnapshot> snapshots = snapshot.getChildren();
                for(DataSnapshot data : snapshots) {
                    adapter.addBook(data.getValue(Book.class));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };

        // Registrar para que escuche los cambios de nuevos datos
        booksReference.addValueEventListener(valueEventListener);
    }
}