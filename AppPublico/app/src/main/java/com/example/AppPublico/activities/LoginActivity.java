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
import com.example.AppPublico.network.ApiService;
import com.example.AppPublico.network.LoginRequest;
import com.example.AppPublico.network.LoginResponse;
import com.example.AppPublico.network.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

            LoginRequest request = new LoginRequest(email, senha);
            ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);

            apiService.login(request).enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        String token = response.body().getToken();
                        String nome = response.body().getNome();

                        salvarLogin(token, false, nome); // ajuste conforme resposta futura p/ tipo de usuário
                        redirecionarParaUsuario();
                    } else {
                        Toast.makeText(LoginActivity.this, "Credenciais inválidas", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {
                    Toast.makeText(LoginActivity.this, "Erro na conexão: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        });

        TextView tvCriarConta = findViewById(R.id.tvCriarConta);
        tvCriarConta.setOnClickListener(v -> {
            startActivity(new Intent(LoginActivity.this, CriarContaActivity.class));
        });
    }

    private void salvarLogin(String token, boolean isSeguranca, String nome) {
        SharedPreferences prefs = getSharedPreferences("AppPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("isLoggedIn", true);
        editor.putBoolean("isSeguranca", isSeguranca);
        editor.putString("jwtToken", token); // armazenando token JWT
        editor.putString("userName", nome);
        editor.apply();
    }

    private void redirecionarParaUsuario() {
        Intent intent = new Intent(this, UsuarioActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        overridePendingTransition(0, 0);
        finish();
    }
}
