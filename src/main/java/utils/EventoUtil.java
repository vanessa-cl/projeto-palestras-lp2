package main.java.utils;

import main.java.entities.Evento;
import main.java.entities.Palestra;

import java.util.ArrayList;

public class EventoUtil {
    ArrayList<Evento> eventos = new ArrayList<>();
    FileUtil fileUtil = new FileUtil();
    String arquivo = "src/main/java/database/eventos.txt";

    public void adicionarEvento(Evento evento) {
        eventos.add(evento);
        try {
            System.out.println(evento.toString());
            boolean resultado = fileUtil.escreverArquivo(arquivo, evento);
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

    public Evento buscarEventoPeloNome(String nome) {
        for (Evento evento : eventos) {
            if (evento.getNome().equalsIgnoreCase(nome)) {
                return evento;
            }
        }
        return null;
    }

    public void adicionarPalestraAoEvento(String nomeEvento, Palestra novaPalestra) {
        Evento evento = buscarEventoPeloNome(nomeEvento);
        if (evento != null) {
            evento.getAgendaPalestras().adicionarPalestra(novaPalestra, nomeEvento);
            // TODO: atualizar arquivo eventos.txt
        }
    }

    // TODO: exibir lista de eventos
    // TODO: implementar método para ler arquivo eventos.txt
}
