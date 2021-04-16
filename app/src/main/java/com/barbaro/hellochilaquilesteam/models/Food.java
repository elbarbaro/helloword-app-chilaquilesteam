package com.barbaro.hellochilaquilesteam.models;

// POJO Plain Old Java Object
public class Food {

    private int id;
    private String name;
    private String description;
    private float price;
    private int imageId; // camelCase

    public Food() {} // Constructor predeterminado

    public Food(int id, String name, String description, float price, int imageId) { // Constructo parametrizado
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageId = imageId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}
