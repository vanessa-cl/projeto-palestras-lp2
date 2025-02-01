package main.java.entities;

import main.java.utils.PalestraUtil;

public class Evento {
    private int id;
    private String nome;
    private PalestraUtil agendaPalestras;

    public Evento(int id, String nome) {
        this.id = id;
        this.nome = nome;
        this.agendaPalestras = new PalestraUtil(id);
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public PalestraUtil getAgendaPalestras() {
        return agendaPalestras;
    }

    public String toString() {
        return "ID: " + id + " - Evento: " + nome;
    }
}
