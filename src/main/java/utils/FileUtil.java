package main.java.utils;

import java.io.*;
import java.util.ArrayList;

public class FileUtil {

    public ArrayList<Object> lerArquivo(String arquivo) {
        ArrayList<Object> linhasLidas = new ArrayList<>();
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
            bufferedWriter.write(conteudo.toString());
            bufferedWriter.newLine();
            System.out.println("Arquivo escrito com sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


}
