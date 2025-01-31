package main.java.utils;

import main.java.entities.Ouvinte;

import java.util.ArrayList;

public class UsuarioUtil {
    private ArrayList<Ouvinte> listaUsuarios = new ArrayList<>();
    FileUtil fileUtil = new FileUtil();
    String arquivo = "src/main/java/database/usuarios.txt";

    public void adicionarUsuario(Ouvinte usuario) {
        listaUsuarios.add(usuario);
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
        System.out.println(email);
        System.out.println(senha);
        System.out.println(listaUsuarios.get(0).getEmail());
        for (Ouvinte usuario : listaUsuarios) {
            System.out.println(usuario.getEmail());
            if (usuario.getEmail().equals(email) && usuario.getSenha().equals(senha)) {
                return usuario;
            }
        }
        return null;
    }

    // TODO: implementar método para ler arquivo ouvintes.txt
}
