package com.example.AppPublico.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.AppPublico.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class UsuarioActivity extends AppCompatActivity {

    private boolean isLoggedIn = true; // lógica futura

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario);

        Button btnHistorico = findViewById(R.id.btnHistorico);
        Button btnLogin = findViewById(R.id.btnLogin);

        btnHistorico.setOnClickListener(v -> {
            startActivity(new Intent(this, MeusIngressosActivity.class));
        });

        btnLogin.setOnClickListener(v -> {
            // lógica futura de login
        });

        BottomNavigationView bottomNavigation = findViewById(R.id.bottom_navigation);

        if (!isLoggedIn) {
            bottomNavigation.setVisibility(BottomNavigationView.GONE);
        } else {
            bottomNavigation.setVisibility(BottomNavigationView.VISIBLE);
        }

        bottomNavigation.setSelectedItemId(R.id.nav_user);

        bottomNavigation.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.nav_home) {
                    startActivity(new Intent(UsuarioActivity.this, MainActivity.class));
                    overridePendingTransition(0, 0);
                    return true;
                }
                if (id == R.id.nav_search) {
                    startActivity(new Intent(UsuarioActivity.this, PesquisaActivity.class));
                    overridePendingTransition(0, 0);
                    return true;
                }
                if (id == R.id.nav_user) return true;
                return false;
            }
        });
    }
}
