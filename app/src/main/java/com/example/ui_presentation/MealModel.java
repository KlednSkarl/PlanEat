package com.example.ui_presentation;

public class MealModel {

    public MealModel(String meal_Name, String meal_Desc, int image) {
        this.meal_Name = meal_Name;
        this.meal_Desc = meal_Desc;
        this.image = image;
    }

    String meal_Name;

    public String getMeal_Name() {
        return meal_Name;
    }

    public String getMeal_Desc() {
        return meal_Desc;
    }

    public int getImage() {
        return image;
    }

    String meal_Desc;
    int image;
}
