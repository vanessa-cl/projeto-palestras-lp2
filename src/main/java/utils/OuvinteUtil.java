package main.java.utils;

import main.java.entities.Ouvinte;

import java.util.ArrayList;

public class OuvinteUtil {
    private ArrayList<Ouvinte> listaOuvintes = new ArrayList<>();
    FileUtil fileUtil = new FileUtil();
    String arquivo = "src/main/java/database/ouvintes.txt";

    public void adicionarOuvinte(Ouvinte ouvinte, String tituloPalestra) {
        listaOuvintes.add(ouvinte);
        try {
            boolean resultadoOuvinte = fileUtil.escreverArquivo(arquivo, "Palestra: " + " - " + ouvinte.toString());
            if (resultadoOuvinte) {
                System.out.println("Ouvinte adicionado com sucesso!");
            } else {
                System.out.println("Erro ao adicionar ouvinte!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro ao adicionar ouvinte!");
        }
    }

    public String exibirOuvintes() {
        String ouvintesString = "Lista de Ouvintes:\n";
        for (Ouvinte ouvinte : listaOuvintes) {
            ouvintesString += ouvinte.toString() + "\n________________________________________________________________________";
        }
        return ouvintesString;
    }

    // TODO: implementar método para ler arquivo ouvintes.txt
}
