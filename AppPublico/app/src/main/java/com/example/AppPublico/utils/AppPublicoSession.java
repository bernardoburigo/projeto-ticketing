package com.example.AppPublico.utils;

import com.example.AppPublico.models.Ingresso;

import java.util.ArrayList;
import java.util.List;

public class AppPublicoSession {
    private static List<Ingresso> ingressos = new ArrayList<>();

    public static List<Ingresso> getIngressos() {
        return ingressos;
    }

    public static void setIngressos(List<Ingresso> lista) {
        ingressos = lista;
    }
}