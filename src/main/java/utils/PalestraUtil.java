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
    DateTimeFormatter formatadorData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    DateTimeFormatter formatadorHora = DateTimeFormatter.ofPattern("ha");

    public PalestraUtil(int newIdEvento) {
        lerPalestras(newIdEvento);
        this.idEvento = newIdEvento;
    }

    public int getQuantidadePalestras() {
        return palestras.size();
    }

    public boolean adicionarPalestra(Palestra palestra) {
        boolean resultado = false;
        palestras.add(palestra);
        try {
            resultado = fileUtil.escreverArquivo(arquivo, palestra);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultado;
    }

    public boolean removerPalestra(int id) {
        boolean resultado = false;
        for (Palestra palestra : palestras) {
            if (palestra.getId() == id) {
                palestras.remove(palestra);
                try {
                    resultado = fileUtil.removerLinhaArquivoPeloId(arquivo, id, 0);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Palestra não encontrada!");
            }
        }
        return resultado;
    }

    public void exibirPalestras() {
        System.out.println("Palestras do evento:");
        for (Palestra palestra : palestras) {
            System.out.println(palestra.toString());
        }
        System.out.println("____________________________________________________________________________________________________________________");
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
            if (Integer.parseInt(dadosPalestra[8].split(": ")[1]) == newIdEvento) {
                Palestra novaPalestra = new Palestra(
                        Integer.parseInt(dadosPalestra[0].split(": ")[1]),
                        dadosPalestra[1].split(": ")[1],
                        dadosPalestra[2].split(": ")[1],
                        LocalDate.parse(dadosPalestra[3].split(": ")[1], formatadorData),
                        LocalTime.parse(dadosPalestra[4].split(": ")[1], formatadorHora),
                        LocalTime.parse(dadosPalestra[5].split(": ")[1], formatadorHora),
                        dadosPalestra[6].split(": ")[1],
                        Integer.parseInt(dadosPalestra[7].split(": ")[1]),
                        Integer.parseInt(dadosPalestra[8].split(": ")[1])
                );
                palestras.add(novaPalestra);
            }
        }
    }

    public ArrayList<Palestra> getPalestras() {
        return palestras;
    }

}
