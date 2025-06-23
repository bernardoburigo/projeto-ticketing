package com.example.AppPublico.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import java.util.HashSet;
import java.util.Set;

public class SincronizadorIngressos {

    private static final String PREFS_NAME = "IngressosValidados";

    public static void tentarSincronizar(Context context) {
        if (!temInternet(context)) {
            Toast.makeText(context, "Sem conexão. Aguardando para sincronizar...", Toast.LENGTH_SHORT).show();
            return;
        }

        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        Set<String> ingressos = prefs.getStringSet("validados", new HashSet<>());

        if (ingressos.isEmpty()) {
            Toast.makeText(context, "Nenhum ingresso pendente para sincronizar.", Toast.LENGTH_SHORT).show();
            return;
        }

        // MOCK: simula envio ao backend
        for (String codigo : ingressos) {
            // Futuramente: enviar para o servidor
            System.out.println("Sincronizando ingresso: " + codigo);
        }

        // Após "sucesso"
        prefs.edit().putStringSet("validados", new HashSet<>()).apply();
        Toast.makeText(context, "Ingressos sincronizados com sucesso!", Toast.LENGTH_SHORT).show();
    }

    private static boolean temInternet(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm == null) return false;

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnected();
    }
}
