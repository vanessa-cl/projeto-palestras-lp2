package main.java.entities;

import java.util.ArrayList;

public class Evento {
    private String nome;
    private ArrayList<Palestra> agendaPalestras;

    public Evento(String nome) {
        this.nome = nome;
        this.agendaPalestras = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public String getAgendaPalestras() {
        String agenda = "";
        for (Palestra palestra : agendaPalestras) {
            agenda += palestra.getTitulo() + " ";
        }
        return agenda;
    }

    public String toString() {
        return "Evento: " + nome + " - Palestras: " + getAgendaPalestras();
    }
}
