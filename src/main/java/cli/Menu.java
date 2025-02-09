package main.java.cli;

import main.java.entities.Usuario;
import main.java.utils.EventoUtil;

import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Menu {
    Usuario usuarioLogado;
    EventoUtil eventoUtil = new EventoUtil();
    Scanner scanner = new Scanner(System.in);
    DateTimeFormatter formatadorData = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public Menu(Usuario usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }
}
