package main.java.utils;

import java.io.*;
import java.util.ArrayList;

public class FileUtil {

    public ArrayList<String> lerArquivo(String arquivo) {
        ArrayList<String> linhasLidas = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(arquivo))) {
            String linha = bufferedReader.readLine();
            while (linha != null) {
                linhasLidas.add(linha);
                linha = bufferedReader.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return linhasLidas;
    }

    public boolean escreverArquivo(String arquivo, Object conteudo) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(arquivo, true))) {
            bufferedWriter.write(conteudo.toString() + System.lineSeparator());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean removerLinhaArquivoPeloId(String arquivo, int id, int idOpcional) {
        ArrayList<String> novasLinhas = atualizarLinhasArquivo(arquivo, id, idOpcional);

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(arquivo))) {
            for (String linha : novasLinhas) {
                bufferedWriter.write(linha + System.lineSeparator());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public ArrayList<String> atualizarLinhasArquivo(String arquivo, int id, int idOpcional) {
        ArrayList<String> linhas = lerArquivo(arquivo);
        ArrayList<String> novasLinhas = new ArrayList<>();
        for (String linha : linhas) {
            String[] dados = linha.split(" - ");
            int idLinha = Integer.parseInt(dados[0].split(": ")[1]);
            if (idOpcional != 0) {
                int idLinhaOpcional = Integer.parseInt(dados[1].split(": ")[1]);

                if (idLinha == id && idLinhaOpcional == idOpcional) {
                    continue;
                }
            } else if (idLinha == id) {
                continue;
            }
            novasLinhas.add(linha);
        }
        return novasLinhas;
    }
}


