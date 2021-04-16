package com.barbaro.hellochilaquilesteam;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.barbaro.hellochilaquilesteam.models.Food;

import java.util.ArrayList;

public class FoodAdapter extends
        RecyclerView.Adapter<FoodAdapter.ViewHolder> {

    private ArrayList<Food> list;

    public FoodAdapter(ArrayList<Food> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        // Buscamos un archivo de diseño que será la vistita de lista
        // Guardar esa vista como un objeto
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_food, parent, false);
        return new ViewHolder(view); // utilizando constructor
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Poner datos, obtener datos y agregar acciones (clicks)
        Food food = list.get(position);
        holder.imageFood.setImageResource(food.getImageId());
        holder.txtName.setText(food.getName());
        holder.txtDescription.setText(food.getDescription());

        String stringPrice = String.format("%.00f", food.getPrice());
        holder.txtPrice.setText(stringPrice);
    }

    @Override
    public int getItemCount() {
        // Indicar cuanto elementos tiene mi lista
        return list.size();
    }

    // Esta clase modela el diseño de la vistita
    // Aquí buscamos por separado los elementos visuales de interacción
    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageFood;
        private TextView txtName;
        private TextView txtDescription;
        private TextView txtPrice;
        private Button btnAdd;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageFood = itemView.findViewById(R.id.imageFood);
            txtName = itemView.findViewById(R.id.txtName);
            txtDescription = itemView.findViewById(R.id.txtDescription);
            txtPrice = itemView.findViewById(R.id.txtPrice);
            btnAdd = itemView.findViewById(R.id.btnAdd);
        }
    }
}
