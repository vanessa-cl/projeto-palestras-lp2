package main.java.entities;

import main.java.utils.FilaEsperaUtil;
import main.java.utils.OuvinteUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Palestra {
    private int id;
    private String titulo;
    private String palestrante;
    private LocalDate data;
    private LocalTime horarioInicio;
    private LocalTime horarioFim;
    private String local;
    private int limiteOuvintes;
    private int idEvento;
    private OuvinteUtil listaOuvintes;
    private FilaEsperaUtil filaEspera;
    DateTimeFormatter formatadorData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    DateTimeFormatter formatadorHora = DateTimeFormatter.ofPattern("ha");

    public Palestra(int id, String titulo, String palestrante, LocalDate data, LocalTime horarioInicio, LocalTime horarioFim, String local, int limiteOuvintes, int idEvento) {
        this.id = id;
        this.titulo = titulo;
        this.palestrante = palestrante;
        this.data = data;
        this.horarioInicio = horarioInicio;
        this.horarioFim = horarioFim;
        this.local = local;
        this.limiteOuvintes = limiteOuvintes;
        this.idEvento = idEvento;
        this.listaOuvintes = new OuvinteUtil(id);
        this.filaEspera = new FilaEsperaUtil(id);
    }

    public int getId() {
        return id;
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

    public OuvinteUtil getListaOuvintes() {
        return listaOuvintes;
    }

    public FilaEsperaUtil getFilaEspera() {
        return filaEspera;
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
        return "ID: " + id +
                " - Palestra: " + titulo +
                " - Palestrante: " + palestrante +
                " - Data: " + data.format(formatadorData) +
                " - Horário de início: " + horarioInicio.format(formatadorHora) +
                " - Horário de fim: " + horarioFim.format(formatadorHora) +
                " - Local: " + local +
                " - Limite de ouvintes: " + limiteOuvintes +
                " - ID Evento: " + idEvento;
    }
}
