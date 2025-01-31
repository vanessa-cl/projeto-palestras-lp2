package main.java.utils;

import main.java.entities.Ouvinte;
import main.java.enums.CargoEnum;

import java.util.ArrayList;

public class UsuarioUtil {
    private ArrayList<Ouvinte> usuarios = new ArrayList<>();
    FileUtil fileUtil = new FileUtil();
    String arquivo = "src/main/java/database/usuarios.txt";

    public UsuarioUtil() {
        lerUsuarios();
    }

    public void adicionarUsuario(Ouvinte usuario) {
        usuarios.add(usuario);
        try {
            boolean resultadoUsuario = fileUtil.escreverArquivo(arquivo, usuario.toString());
            if (resultadoUsuario) {
                System.out.println("Usuário cadastrado com sucesso! Entre para continuar");
            } else {
                System.out.println("Erro ao adicionar usuário!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro ao adicionar usuário!");
        }
    }

    public Ouvinte buscarUsuarioPeloEmailESenha(String email, String senha) {
        for (Ouvinte usuario : usuarios) {
            System.out.println(usuario.getEmail());
            if (usuario.getEmail().equals(email) && usuario.getSenha().equals(senha)) {
                return usuario;
            }
        }
        return null;
    }

    public void lerUsuarios() {
        ArrayList<String> usuariosDB = fileUtil.lerArquivo(arquivo);
        for (String dado : usuariosDB) {
            String[] dadosUsuario = dado.split(" - ");
            Ouvinte novoUsuario = new Ouvinte(
                    Integer.parseInt(dadosUsuario[0].split(": ")[1]),
                    dadosUsuario[1].split(": ")[1],
                    dadosUsuario[2].split(": ")[1],
                    dadosUsuario[3].split(": ")[1],
                    CargoEnum.valueOf(dadosUsuario[4].split(": ")[1])
            );
            usuarios.add(novoUsuario);
        }
    }

    public ArrayList<Ouvinte> getUsuarios() {
        return usuarios;
    }

}
