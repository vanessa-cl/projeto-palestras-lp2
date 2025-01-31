package main.java;

import main.java.cli.Menu;
import main.java.entities.Ouvinte;
import main.java.enums.CargoEnum;
import main.java.utils.UsuarioUtil;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        UsuarioUtil usuarioUtil = new UsuarioUtil();
        Ouvinte usuarioLogado = null;
        Menu menu = new Menu();
        Scanner s = new Scanner(System.in);
        boolean encerrar = false;
        String opcao;
        String email;
        String senha;

        while (!encerrar) {
            System.out.println("Bem-vindo ao sistema de Gerenciamento de Palestras em Eventos!");
            System.out.println("Para começar, escolha uma das opções abaixo:");
            System.out.println("A - Entrar");
            System.out.println("B - Cadastrar-se");
            System.out.println("C - Encerrar o programa");
            opcao = s.next().toUpperCase();
            switch (opcao) {
                case "A":
                    System.out.println("Digite seu e-mail:");
                    email = s.next();
                    System.out.println("Digite sua senha:");
                    senha = s.next();
                    usuarioLogado = usuarioUtil.buscarUsuarioPeloEmailESenha(email, senha);
                    if (usuarioLogado != null) {
                        if (usuarioLogado.getCargo() == CargoEnum.PARTICIPANTE) {
                            menu.exibirMenuParticipante();
                        } else if (usuarioLogado.getCargo() == CargoEnum.COLABORADOR) {
                            menu.exibirMenuColaborador();
                        }
                    } else {
                        System.out.println("Usuário não encontrado ou e-mail/senha incorretos! Cadastre-se antes de logar.");
                    }
                    break;
                case "B":
                    System.out.println("Digite seu e-mail:");
                    email = s.next();
                    System.out.println("Digite seu nome:");
                    String nome = s.next();
                    System.out.println("Digite sua senha:");
                    senha = s.next();
                    usuarioUtil.adicionarUsuario(new Ouvinte(usuarioUtil.getUsuarios().size() + 1, nome, email, senha, CargoEnum.PARTICIPANTE));
                    break;
                case "C":
                    encerrar = true;
                    System.out.println("Encerrando o programa...");
                    break;
                default:
                    break;
            }
        }

    }
}
