package main.java.utils;

import main.java.entities.Ouvinte;

import java.util.ArrayDeque;
import java.util.ArrayList;

public class FilaEsperaUtil {
    private int idPalestra;
    private ArrayDeque<Ouvinte> filaEspera = new ArrayDeque<>();
    FileUtil fileUtil = new FileUtil();
    String arquivo = "src/main/java/database/filaEspera.txt";

    public FilaEsperaUtil(int idPalestra) {
        lerOuvintesFilaEspera(idPalestra);
        this.idPalestra = idPalestra;
    }

    public void lerOuvintesFilaEspera(int idPalestra) {
        ArrayList<String> ouvintesDB = fileUtil.lerArquivo(arquivo);
        for (String dado : ouvintesDB) {
            String[] dadosOuvinte = dado.split(" - ");
            if (Integer.parseInt(dadosOuvinte[1].split(": ")[1]) == idPalestra) {
                Ouvinte novoOuvinte = new Ouvinte(
                        Integer.parseInt(dadosOuvinte[0].split(": ")[1]),
                        Integer.parseInt(dadosOuvinte[1].split(": ")[1])
                );
                filaEspera.add(novoOuvinte);
            }
        }
    }

    public boolean adicionarOuvinteFim(Ouvinte ouvinte) {
        boolean resultado = false;
        filaEspera.add(ouvinte);
        try {
            resultado = fileUtil.escreverArquivo(arquivo, ouvinte);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultado;
    }

    public Ouvinte removerOuvinteInicio() {
        Ouvinte ouvinteEncontrado = filaEspera.poll();
        if (ouvinteEncontrado == null) {
            return null;
        }
        try {
            fileUtil.removerLinhaArquivoPeloId(arquivo, ouvinteEncontrado.getId(), idPalestra);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ouvinteEncontrado;
    }

    public int getQuantidadeOuvintes() {
        return filaEspera.size();
    }

    public Ouvinte buscarOuvintePeloId(int idUsuario) {
        Ouvinte resultado = null;
        for (Ouvinte ouvinte : filaEspera) {
            if (ouvinte.getId() == idUsuario) {
                resultado = ouvinte;
            }
        }
        return resultado;
    }

    public ArrayDeque<Ouvinte> getOuvintes() {
        return filaEspera;
    }
}
