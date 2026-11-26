package com.example.squad10;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.squad10.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowCompat.enableEdgeToEdge(getWindow());

        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(binding.main, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        setSupportActionBar(binding.toolbar);
        if (getSupportActionBar() != null) getSupportActionBar().setTitle("Squad Helper");

        binding.btnMaps.setOnClickListener(v ->
                startActivity(new Intent(this, MapsActivity.class)));
        binding.btnMortar.setOnClickListener(v ->
                startActivity(new Intent(this, MortarActivity.class)));
        binding.btnReticle.setOnClickListener(v ->
                startActivity(new Intent(this, ReticleActivity.class)));
        binding.btnArmor.setOnClickListener(v ->
                startActivity(new Intent(this, ArmorActivity.class)));
    }
}