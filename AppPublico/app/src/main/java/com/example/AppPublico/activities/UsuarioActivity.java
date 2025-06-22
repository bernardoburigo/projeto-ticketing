package com.example.AppPublico.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.AppPublico.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class UsuarioActivity extends AppCompatActivity {

    private boolean isLoggedIn;
    private boolean isSeguranca;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario);

        SharedPreferences prefs = getSharedPreferences("AppPrefs", MODE_PRIVATE);
        isLoggedIn = prefs.getBoolean("isLoggedIn", false);
        isSeguranca = prefs.getBoolean("isSeguranca", false);

        Button btnHistorico = findViewById(R.id.btnHistorico);
        Button btnLogin = findViewById(R.id.btnLogin);
        Button btnLogout = findViewById(R.id.btnLogout);

        if (isLoggedIn) {
            btnLogin.setVisibility(View.GONE);
            btnHistorico.setVisibility(View.VISIBLE);
            btnLogout.setVisibility(View.VISIBLE);
        } else {
            btnLogin.setVisibility(View.VISIBLE);
            btnHistorico.setVisibility(View.GONE);
            btnLogout.setVisibility(View.GONE);
        }

        btnHistorico.setOnClickListener(v -> {
            if (isSeguranca) {
                startActivity(new Intent(this, VerificacaoIngressoActivity.class));
            } else {
                startActivity(new Intent(this, MeusIngressosActivity.class));
            }
        });

        btnLogin.setOnClickListener(v -> {
            startActivity(new Intent(this, LoginActivity.class));
        });

        btnLogout.setOnClickListener(v -> {
            SharedPreferences.Editor editor = prefs.edit();
            editor.clear();
            editor.apply();
            recreate(); // Recarrega a tela com o estado não logado
        });

        BottomNavigationView bottomNavigation = findViewById(R.id.bottom_navigation);
        bottomNavigation.setVisibility(View.VISIBLE);
        bottomNavigation.setSelectedItemId(R.id.nav_user);

        bottomNavigation.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.nav_home) {
                    navigateTo(MainActivity.class);
                    return true;
                }
                if (id == R.id.nav_search) {
                    navigateTo(PesquisaActivity.class);
                    return true;
                }
                if (id == R.id.nav_user) return true;
                return false;
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        navigateTo(MainActivity.class);
    }

    private void navigateTo(Class<?> targetActivity) {
        Intent intent = new Intent(this, targetActivity);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
        overridePendingTransition(0, 0);
        finish();
    }
}