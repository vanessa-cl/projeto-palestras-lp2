package main.java.entities;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayDeque;

public class Palestra {
    private String titulo;
    private String palestrante;
    private LocalDate data;
    private LocalTime horarioInicio;
    private LocalTime horarioFim;
    private String local;
    private int limiteOuvintes;
    private ArrayDeque<Ouvinte> filaEspera;

    public Palestra(String titulo, String palestrante, LocalDate data, LocalTime horarioInicio, LocalTime horarioFim, String local, int limiteOuvintes) {
        this.titulo = titulo;
        this.palestrante = palestrante;
        this.data = data;
        this.horarioInicio = horarioInicio;
        this.horarioFim = horarioFim;
        this.local = local;
        this.limiteOuvintes = limiteOuvintes;
        this.filaEspera = new ArrayDeque<>();
    }

    public String getTitulo() {
        return titulo;
    }

    public String getPalestrante() {
        return palestrante;
    }

    public LocalDate getData() {
        return data;
    }

    public LocalTime getHorarioInicio() {
        return horarioInicio;
    }

    public LocalTime getHorarioFim() {
        return horarioFim;
    }

    public String getLocal() {
        return local;
    }

    public int getLimiteOuvintes() {
        return limiteOuvintes;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setPalestrante(String palestrante) {
        this.palestrante = palestrante;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public void setHorarioInicio(LocalTime horarioInicio) {
        this.horarioInicio = horarioInicio;
    }

    public void setHorarioFim(LocalTime horarioFim) {
        this.horarioFim = horarioFim;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public void setLimiteOuvintes(int limiteOuvintes) {
        this.limiteOuvintes = limiteOuvintes;
    }

    public String toString() {
        return "Palestra: " + titulo + " - Palestrante: " + palestrante + " - Data: " + data + " - Horário de início: " + horarioInicio + " - Horário de fim: " + horarioFim + " - Local: " + local + " - Limite de ouvintes: " + limiteOuvintes;
    }
}
