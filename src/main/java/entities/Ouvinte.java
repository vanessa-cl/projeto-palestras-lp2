package main.java.entities;

public class Ouvinte {
    private int id;
    private int idPalestra;

    public Ouvinte(int id, int idPalestra) {
        this.id = id;
        this.idPalestra = idPalestra;
    }

    public int getId() {
        return id;
    }

    public int getIdPalestra() {
        return idPalestra;
    }

    public String toString() {
        return "ID Usuario: " + id +
                " - ID Palestra: " + idPalestra;
    }
}
