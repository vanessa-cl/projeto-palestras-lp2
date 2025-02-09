package main.java.utils;

import main.java.entities.Ouvinte;

import java.util.ArrayList;

public class OuvinteUtil {
    private int idPalestra;
    private ArrayList<Ouvinte> listaOuvintes = new ArrayList<>();
    FileUtil fileUtil = new FileUtil();
    String arquivo = "src/main/java/database/ouvintes.txt";

    public OuvinteUtil(int newIdPalestra) {
        lerOuvintes(newIdPalestra);
        idPalestra = newIdPalestra;
    }

    public int getQuantidadeOuvintes() {
        return listaOuvintes.size();
    }

    public boolean adicionarOuvinte(Ouvinte ouvinte) {
        boolean resultado = false;
        listaOuvintes.add(ouvinte);
        try {
            resultado = fileUtil.escreverArquivo(arquivo, ouvinte);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultado;
    }

    public boolean removerOuvinte(int idOuvinte, int idPalestra) {
        boolean resultado = false;
        Ouvinte ouvinteEncontrado = null;
        for (Ouvinte ouvinte : listaOuvintes) {
            if (ouvinte.getId() == idOuvinte && ouvinte.getIdPalestra() == idPalestra) {
                ouvinteEncontrado = ouvinte;
                try {
                    resultado = fileUtil.removerLinhaArquivoPeloId(arquivo, idOuvinte, idPalestra);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Ouvinte não encontrado!");
            }
        }
        listaOuvintes.remove(ouvinteEncontrado);
        return resultado;
    }

    public Ouvinte buscarOuvintePeloId(int idUsuario) {
        Ouvinte ouvinteAux = null;
        for (Ouvinte ouvinte : listaOuvintes) {
            if (ouvinte.getId() == idUsuario) {
                ouvinteAux = ouvinte;
            }
        }
        return ouvinteAux;
    }

    public String exibirOuvintes() {
        String ouvintesString = "Lista de Ouvintes:\n";
        for (Ouvinte ouvinte : listaOuvintes) {
            ouvintesString += ouvinte.toString() + "\n________________________________________________________________________";
        }
        return ouvintesString;
    }

    public void lerOuvintes(int idPalestra) {
        ArrayList<String> ouvintesDB = fileUtil.lerArquivo(arquivo);
        for (String dado : ouvintesDB) {
            String[] dadosOuvinte = dado.split(" - ");
            if (Integer.parseInt(dadosOuvinte[1].split(": ")[1]) == idPalestra) {
                Ouvinte novoOuvinte = new Ouvinte(
                        Integer.parseInt(dadosOuvinte[0].split(": ")[1]),
                        Integer.parseInt(dadosOuvinte[1].split(": ")[1])
                );
                listaOuvintes.add(novoOuvinte);
            }
        }
    }

    public ArrayList<Ouvinte> getOuvintes() {
        return listaOuvintes;
    }

}
