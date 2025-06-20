package com.example.AppPublico.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.AppPublico.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private boolean isLoggedIn = true; // substituir futuramente por lógica real

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigation = findViewById(R.id.bottom_navigation);

        if (!isLoggedIn) {
            bottomNavigation.setVisibility(BottomNavigationView.GONE);
            // futuramente: exibir botão de login
        } else {
            bottomNavigation.setVisibility(BottomNavigationView.VISIBLE);
        }

        bottomNavigation.setSelectedItemId(R.id.nav_home); // manter selecionado

        bottomNavigation.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.nav_home) return true;
                if (id == R.id.nav_search) {
                    startActivity(new Intent(MainActivity.this, PesquisaActivity.class));
                    overridePendingTransition(0, 0);
                    return true;
                }
                if (id == R.id.nav_user) {
                    startActivity(new Intent(MainActivity.this, UsuarioActivity.class));
                    overridePendingTransition(0, 0);
                    return true;
                }
                return false;
            }
        });
    }
}
