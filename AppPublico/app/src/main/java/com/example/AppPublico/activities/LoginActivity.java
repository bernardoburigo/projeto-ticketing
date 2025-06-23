package com.example.AppPublico.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.AppPublico.R;

public class LoginActivity extends AppCompatActivity {

    private EditText edtEmail, edtSenha;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtEmail = findViewById(R.id.edtEmail);
        edtSenha = findViewById(R.id.edtSenha);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(v -> {
            String email = edtEmail.getText().toString().trim();
            String senha = edtSenha.getText().toString().trim();

            if (email.isEmpty() || senha.isEmpty()) {
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
                return;
            }

            // Simulação de login
            if (email.equals("usuario@example.com") && senha.equals("123")) {
                salvarLogin(false); // usuário comum
                redirecionarParaUsuario();
            } else if (email.equals("seguranca@example.com") && senha.equals("123")) {
                salvarLogin(true); // segurança
                redirecionarParaUsuario();
            } else {
                Toast.makeText(this, "Credenciais inválidas", Toast.LENGTH_SHORT).show();
            }
        });

        TextView tvCriarConta = findViewById(R.id.tvCriarConta);
        tvCriarConta.setOnClickListener(v -> {
            startActivity(new Intent(LoginActivity.this, CriarContaActivity.class));
        });

    }

    private void salvarLogin(boolean isSeguranca) {
        SharedPreferences prefs = getSharedPreferences("AppPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("isLoggedIn", true);
        editor.putBoolean("isSeguranca", isSeguranca);
        editor.apply();
    }

    private void redirecionarParaUsuario() {
        Intent intent = new Intent(this, UsuarioActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); // limpa histórico de atividades
        startActivity(intent);
        overridePendingTransition(0, 0);
        finish();
    }
}
