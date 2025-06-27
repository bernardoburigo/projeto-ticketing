package com.example.AppPublico.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.AppPublico.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class UsuarioActivity extends AppCompatActivity {

    private boolean isLoggedIn;
    private boolean isSeguranca;
    private TextView tvBoasVindas;
    private Button btnHistorico;
    private Button btnLogin;
    private Button btnLogout;
    private BottomNavigationView bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario);

        // Inicializar Views
        tvBoasVindas = findViewById(R.id.tvBoasVindas);
        btnHistorico = findViewById(R.id.btnHistorico);
        btnLogin = findViewById(R.id.btnLogin);
        btnLogout = findViewById(R.id.btnLogout);
        bottomNavigation = findViewById(R.id.bottom_navigation);

        carregarDadosEAtualizarUI();
        configurarListeners();
    }

    private void carregarDadosEAtualizarUI() {
        SharedPreferences prefs = getSharedPreferences("AppPrefs", MODE_PRIVATE);
        isLoggedIn = prefs.getBoolean("isLoggedIn", false);
        isSeguranca = prefs.getBoolean("isSeguranca", false);

        if (isLoggedIn) {
            String nomeCompleto = prefs.getString("userName", "usuário");
            String primeiroNome = nomeCompleto;
            if (nomeCompleto != null && !nomeCompleto.trim().isEmpty()) {
                String[] partes = nomeCompleto.split(" ");
                if (partes.length > 0 && !partes[0].trim().isEmpty()) {
                    String nomeCru = partes[0].trim();
                    primeiroNome = nomeCru.substring(0, 1).toUpperCase() + nomeCru.substring(1).toLowerCase();
                }
            }
            tvBoasVindas.setText("Bem-vindo(a), " + primeiroNome + "!");

            btnLogin.setVisibility(View.GONE);
            btnHistorico.setVisibility(View.VISIBLE);
            btnLogout.setVisibility(View.VISIBLE);
        } else {
            tvBoasVindas.setText("Bem-vindo(a)!");
            btnLogin.setVisibility(View.VISIBLE);
            btnHistorico.setVisibility(View.GONE);
            btnLogout.setVisibility(View.GONE);
        }

        bottomNavigation.setVisibility(View.VISIBLE);
        bottomNavigation.setSelectedItemId(R.id.nav_user);
    }

    private void configurarListeners() {
        btnHistorico.setOnClickListener(v -> {
            if (isSeguranca) {
                startActivity(new Intent(this, VerificacaoIngressoActivity.class));
            } else {
                startActivity(new Intent(this, MeusIngressosActivity.class));
            }
        });

        btnLogin.setOnClickListener(v -> {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        });

        btnLogout.setOnClickListener(v -> {
            new AlertDialog.Builder(UsuarioActivity.this)
                    .setTitle("Sair da Conta")
                    .setMessage("Você tem certeza de que deseja sair da sua conta?")
                    .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            SharedPreferences prefs = getSharedPreferences("AppPrefs", MODE_PRIVATE);
                            SharedPreferences.Editor editor = prefs.edit();
                            editor.clear();
                            editor.apply();

                            carregarDadosEAtualizarUI();
                            Toast.makeText(UsuarioActivity.this, "Você saiu da conta.", Toast.LENGTH_SHORT).show();

                            startActivity(new Intent(UsuarioActivity.this, LoginActivity.class));
                        }
                    })
                    .setNegativeButton("Não", null)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        });

        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
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
                if (id == R.id.nav_user) {
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregarDadosEAtualizarUI();
        bottomNavigation.setSelectedItemId(R.id.nav_user);
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
        if (!(targetActivity.equals(UsuarioActivity.class))) {
            finish();
        }
    }
}