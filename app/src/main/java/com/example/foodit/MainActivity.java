package com.example.foodit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.foodit.classes.Data;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Data.initialize(this);
    }

    public void openRecipeOverview(View view) {
        startActivity(new Intent(MainActivity.this, RecipeOverview.class));
    }
}