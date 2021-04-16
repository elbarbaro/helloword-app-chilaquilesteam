package com.barbaro.hellochilaquilesteam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.barbaro.hellochilaquilesteam.models.Food;

import java.util.ArrayList;

public class FoodListActivity extends AppCompatActivity {

    private RecyclerView recyclerListFood;
    private ArrayList<Food> listFood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_list);

        recyclerListFood = findViewById(R.id.listFood);

        LinearLayoutManager acomodador = new LinearLayoutManager(this);
        recyclerListFood.setLayoutManager(acomodador);

        Food chilaquiles = new Food(1, "Chilaquiles Rojos", "Muy picantes", 20.8424f, 0);

        Food quekas = new Food();
        quekas.setId(2);
        quekas.setName("Quekas de queso");
        quekas.setDescription("Ultra mega queso");
        quekas.setPrice(18.934f);
        //quekas.setImageId(R.drawable.quekas);

        listFood = new ArrayList<>();

        listFood.add(chilaquiles);
        listFood.add(quekas);

        FoodAdapter adapter = new FoodAdapter(listFood);
        recyclerListFood.setAdapter(adapter);
    }
}