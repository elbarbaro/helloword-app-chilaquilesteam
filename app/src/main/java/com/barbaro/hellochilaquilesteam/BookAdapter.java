package com.barbaro.hellochilaquilesteam;

import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.TextView;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.barbaro.hellochilaquilesteam.models.Book;

import java.util.ArrayList;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder> {

    private ArrayList<Book> list;

    public BookAdapter(ArrayList<Book> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Aquí indicamos el archivo del diseño a utilizar en la vistita
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Poner los datos en las vistita

        Book book = list.get(position);

        holder.txtName.setText(book.getName());
        holder.txtAuthor.setText(book.getAuthor());
        holder.txtYear.setText(book.getYear());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    // Es la clase que modela los elementos visuales de la vistita
    public class ViewHolder extends RecyclerView.ViewHolder {

        // Elemento visuales de interacción de la vistita
        private TextView txtName;
        private TextView txtAuthor;
        private TextView txtYear;
        private Button btnView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            // Buscar los elementos visuales de las vistita y guardarlos en objetos
            txtName = itemView.findViewById(R.id.txtName);
            txtAuthor = itemView.findViewById(R.id.txtAuthor);
            txtYear = itemView.findViewById(R.id.txtYear);
            btnView = itemView.findViewById(R.id.btnView);
        }
    }

    public void addBook(Book book) {
        list.add(book);
        notifyDataSetChanged();
    }
}
