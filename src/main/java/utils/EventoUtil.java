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

    public boolean adicionarEvento(Evento evento) {
        boolean resultado = false;
        eventos.add(evento);
        try {
            resultado = fileUtil.escreverArquivo(arquivo, evento);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro ao adicionar evento!");
        }
        return resultado;
    }

    public Evento buscarEventoPeloNome(String nome) {
        for (Evento evento : eventos) {
            if (evento.getNome().equalsIgnoreCase(nome)) {
                return evento;
            }
        }
        return null;
    }

    public void exibirEventos() {
        System.out.println("____________________________________________________________________________________________________________________");
        for (Evento evento : eventos) {
            System.out.println(evento.toString());
            evento.getAgendaPalestras().exibirPalestras();
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
