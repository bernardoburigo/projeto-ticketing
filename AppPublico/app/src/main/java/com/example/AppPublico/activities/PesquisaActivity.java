package com.example.AppPublico.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.AppPublico.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class PesquisaActivity extends AppCompatActivity {

    private boolean isLoggedIn = true; // usar lógica real futuramente

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesquisa);

        Toast.makeText(this, "Tela de pesquisa em construção!", Toast.LENGTH_SHORT).show();

        BottomNavigationView bottomNavigation = findViewById(R.id.bottom_navigation);

        if (!isLoggedIn) {
            bottomNavigation.setVisibility(BottomNavigationView.GONE);
        } else {
            bottomNavigation.setVisibility(BottomNavigationView.VISIBLE);
        }

        bottomNavigation.setSelectedItemId(R.id.nav_search);

        bottomNavigation.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.nav_home) {
                    startActivity(new Intent(PesquisaActivity.this, MainActivity.class));
                    overridePendingTransition(0, 0);
                    return true;
                }
                if (id == R.id.nav_search) return true;
                if (id == R.id.nav_user) {
                    startActivity(new Intent(PesquisaActivity.this, UsuarioActivity.class));
                    overridePendingTransition(0, 0);
                    return true;
                }
                return false;
            }
        });
    }
}
