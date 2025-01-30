package main.java.entities;

import main.java.utils.PalestraUtil;

import java.util.ArrayList;

public class Evento {
    private String nome;
    private PalestraUtil agendaPalestras;

    public Evento(String nome) {
        this.nome = nome;
        this.agendaPalestras = new PalestraUtil();
    }

    public String getNome() {
        return nome;
    }

    public PalestraUtil getAgendaPalestras() {
        return agendaPalestras;
    }

    public String toString() {
        return "Evento: " + nome + "\n" + agendaPalestras.exibirPalestras();
    }
}
