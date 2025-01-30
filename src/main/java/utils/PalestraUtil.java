package main.java.utils;

import main.java.entities.Palestra;

import java.util.ArrayList;

public class PalestraUtil {
    ArrayList<Palestra> palestras = new ArrayList<>();
    FileUtil fileUtil = new FileUtil();
    String arquivo = "src/main/java/database/palestras.txt";

    public void adicionarPalestra(Palestra palestra, String nomeEvento) {
        palestras.add(palestra);
        try {
            boolean resultadoPalestra = fileUtil.escreverArquivo(arquivo, "Evento: " + nomeEvento + " - " + palestra.toString());
            if (resultadoPalestra) {
                System.out.println("Palestra adicionada com sucesso!");

            } else {
                System.out.println("Erro ao adicionar palestra!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro ao adicionar palestra!");
        }
    }

    // TODO: exibir lista de palestras de determinado evento
    // TODO: implementar método para ler arquivo palestras.txt
}
