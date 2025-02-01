package main.java.utils;

import main.java.entities.Palestra;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class PalestraUtil {
    private int idEvento;
    ArrayList<Palestra> palestras = new ArrayList<>();
    FileUtil fileUtil = new FileUtil();
    String arquivo = "src/main/java/database/palestras.txt";
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ha");

    public PalestraUtil(int newIdEvento) {
        lerPalestras(newIdEvento);
        this.idEvento = newIdEvento;
    }

    public int getQuantidadePalestras() {
        return palestras.size();
    }

    public void adicionarPalestra(Palestra palestra) {
        palestras.add(palestra);
        try {
            boolean resultadoPalestra = fileUtil.escreverArquivo(arquivo, palestra.toString());
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

    public void exibirPalestras() {
        for (Palestra palestra : palestras) {
            System.out.println(palestra.toString());
        }
    }

    public Palestra buscarPalestraPeloTitulo(String titulo) {
        for (Palestra palestra : palestras) {
            if (palestra.getTitulo().equals(titulo)) {
                return palestra;
            }
        }
        return null;
    }

    public void lerPalestras(int newIdEvento) {
        ArrayList<String> palestrasDB = fileUtil.lerArquivo(arquivo);
        for (String dado : palestrasDB) {
            String[] dadosPalestra = dado.split(" - ");
            System.out.println("idEvento: " + newIdEvento);
            System.out.println("dadosPalestra[8]: " + dadosPalestra[8]);
            if (Integer.parseInt(dadosPalestra[8].split(": ")[1]) == newIdEvento) {
                Palestra novaPalestra = new Palestra(
                        Integer.parseInt(dadosPalestra[0].split(": ")[1]),
                        dadosPalestra[1].split(": ")[1],
                        dadosPalestra[2].split(": ")[1],
                        LocalDate.parse(dadosPalestra[3].split(": ")[1]),
                        LocalTime.parse(dadosPalestra[4].split(": ")[1], formatter),
                        LocalTime.parse(dadosPalestra[5].split(": ")[1], formatter),
                        dadosPalestra[6].split(": ")[1],
                        Integer.parseInt(dadosPalestra[7].split(": ")[1]),
                        Integer.parseInt(dadosPalestra[8].split(": ")[1])
                );
                palestras.add(novaPalestra);
            }
        }
        exibirPalestras();
    }

    // TODO: implementar método para ler arquivo palestras.txt
}
