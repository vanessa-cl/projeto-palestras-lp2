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

//    public String imprimirAgendaPalestras() {
//        String agenda = "";
//        for (Palestra palestra : agendaPalestras) {
//            agenda += palestra.getTitulo() + " ";
//        }
//        return agenda;
//    }

//    public String toString() {
//        return "Evento: " + nome + " - Palestras: " + imprimirAgendaPalestras();
//    }
}
