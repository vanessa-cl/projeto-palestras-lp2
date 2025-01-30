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

    public String exibirPalestras() {
        String palestrasString = "Lista de Palestras:\n";
        for (Palestra palestra : palestras) {
            palestrasString += palestra.toString() + "\n________________________________________________________________________";
        }
        return palestrasString;
    }

    public Palestra buscarPalestraPeloTitulo(String titulo) {
        for (Palestra palestra : palestras) {
            if (palestra.getTitulo().equals(titulo)) {
                return palestra;
            }
        }
        return null;
    }

    // TODO: implementar método para ler arquivo palestras.txt
}
