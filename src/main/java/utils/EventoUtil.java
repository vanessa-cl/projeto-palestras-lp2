package main.java.utils;

import main.java.entities.Evento;
import main.java.entities.Palestra;

import java.util.ArrayList;

public class EventoUtil {
    ArrayList<Evento> eventos = new ArrayList<>();
    FileUtil fileUtil = new FileUtil();
    String arquivo = "src/main/java/database/eventos.txt";

    public EventoUtil() {
        lerEventos();
    }

    public void adicionarEvento(Evento evento) {
        eventos.add(evento);
        try {
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

    public void exibirEventos() {
        for (Evento evento : eventos) {
            System.out.println(evento.toString());
            System.out.println("________________________________________________________________________");
        }
    }

    public void lerEventos() {
        ArrayList<String> eventosDB = fileUtil.lerArquivo(arquivo);
        for (String dado : eventosDB) {
            String[] dadosEvento = dado.split(" - ");
            Evento novoEvento = new Evento(
                    Integer.parseInt(dadosEvento[0].split(": ")[1]),
                    dadosEvento[1].split(": ")[1]
            );
            eventos.add(novoEvento);
        }
    }

    public ArrayList<Evento> getEventos() {
        return eventos;
    }
}
