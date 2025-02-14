package main.java.utils;

import main.java.entities.Usuario;
import main.java.enums.CargoEnum;

import java.util.ArrayList;

public class UsuarioUtil {
    private ArrayList<Usuario> usuarios = new ArrayList<>();
    FileUtil fileUtil = new FileUtil();
    String arquivo = "src/main/java/database/usuarios.txt";

    public UsuarioUtil() {
        lerUsuarios();
    }

    public boolean adicionarUsuario(Usuario usuario) {
        boolean resultado = false;
        usuarios.add(usuario);
        try {
            resultado = fileUtil.escreverArquivo(arquivo, usuario);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro ao adicionar usuário!");
        }
        return resultado;
    }

    public Usuario buscarUsuarioPeloEmailESenha(String email, String senha) {
        for (Usuario usuario : usuarios) {
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
            Usuario novoUsuario = new Usuario(
                    Integer.parseInt(dadosUsuario[0].split(": ")[1]),
                    dadosUsuario[1].split(": ")[1],
                    dadosUsuario[2].split(": ")[1],
                    dadosUsuario[3].split(": ")[1],
                    CargoEnum.valueOf(dadosUsuario[4].split(": ")[1])
            );
            usuarios.add(novoUsuario);
        }
    }

    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

}
