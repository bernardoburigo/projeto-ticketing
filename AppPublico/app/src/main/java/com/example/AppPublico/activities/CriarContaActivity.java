package com.example.AppPublico.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.OptIn;
import androidx.appcompat.app.AppCompatActivity;
import androidx.media3.common.util.UnstableApi;

import com.example.AppPublico.R;
import com.example.AppPublico.models.UsuarioRequest;
import com.example.AppPublico.network.ApiService;
import com.example.AppPublico.network.RetrofitClient;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CriarContaActivity extends AppCompatActivity {

    private EditText edtNome, edtEmail, edtSenha, edtConfirmarSenha;
    private Button btnCriarConta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_conta);

        edtNome = findViewById(R.id.edtNome);
        edtEmail = findViewById(R.id.edtEmail);
        edtSenha = findViewById(R.id.edtSenha);
        edtConfirmarSenha = findViewById(R.id.edtConfirmarSenha);
        btnCriarConta = findViewById(R.id.btnCriarConta);

        btnCriarConta.setOnClickListener(v -> {
            String nome = edtNome.getText().toString().trim();
            String email = edtEmail.getText().toString().trim();
            String senha = edtSenha.getText().toString();
            String confirmar = edtConfirmarSenha.getText().toString();

            if (nome.isEmpty() || email.isEmpty() || senha.isEmpty() || confirmar.isEmpty()) {
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!senha.equals(confirmar)) {
                Toast.makeText(this, "As senhas não coincidem", Toast.LENGTH_SHORT).show();
                return;
            }

            UsuarioRequest request = new UsuarioRequest(nome, email, senha);

            ApiService apiService = RetrofitClient.getApiService();
            Call<Void> call = apiService.cadastrarUsuarioPublico(request);

            call.enqueue(new Callback<Void>() {
                @OptIn(markerClass = UnstableApi.class)
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(CriarContaActivity.this, "Conta criada com sucesso!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(CriarContaActivity.this, LoginActivity.class));
                        finish();
                    } else {
                        String errorBodyString = "";
                        try {
                            if (response.errorBody() != null) {
                                errorBodyString = response.errorBody().string();
                            }
                        } catch (IOException e) {
                            androidx.media3.common.util.Log.e("CriarContaActivity", "Erro ao ler errorBody", e);
                        }
                        androidx.media3.common.util.Log.e("CriarContaActivity", "Erro na API: Código: " + response.code() + " Mensagem: " + response.message() + " Corpo: " + errorBodyString);
                        Toast.makeText(CriarContaActivity.this, "Erro ao criar conta. Código: " + response.code(), Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    androidx.media3.common.util.Log.e("CriarContaActivity", "Falha na conexão: ", t);
                    Toast.makeText(CriarContaActivity.this, "Erro na conexão: " + t.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        });
    }
}
