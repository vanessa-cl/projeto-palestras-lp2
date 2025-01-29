package main.java.utils;

import main.java.entities.Evento;
import java.util.ArrayList;

public class EventoUtil {
    ArrayList<Evento> eventos = new ArrayList<>();
    FileUtil fileUtil = new FileUtil();

    public void adicionarEvento(Evento evento) {
        eventos.add(evento);
        try {
            System.out.println(evento.toString());
            boolean resultado = fileUtil.escreverArquivo("src/main/java/database/eventos.txt", evento);
            if (resultado) {
                System.out.println("Evento adicionado com sucesso!");
            } else {
                System.out.println("Erro ao adicionar evento!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro ao adicionar evento!");
        }
    }

}
