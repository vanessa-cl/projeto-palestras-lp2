package main.java;

import main.java.cli.*;
import main.java.entities.Usuario;
import main.java.enums.CargoEnum;
import main.java.utils.UsuarioUtil;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        UsuarioUtil usuarioUtil = new UsuarioUtil();
        Scanner scanner = new Scanner(System.in);
        boolean encerrar = false;
        Usuario usuarioLogado = null;
        MenuUsuario menuUsuario;
        MenuColaborador menuColaborador;
        String opcao;
        String email;
        String nome;
        String senha;

        while (!encerrar) {
            System.out.println("""
                    ____________________________________________________________________________________________________________________
                    Bem-vindo ao sistema de Gerenciamento de Palestras em Eventos!
                    
                    Para começar, escolha uma das opções abaixo:
                    A - Entrar com seu e-mail e senha
                    B - Cadastrar-se no sistema
                    C - Encerrar o programa
                    
                    Digite a opção desejada:
                    ____________________________________________________________________________________________________________________
                    """);
            opcao = scanner.next().toUpperCase();
            scanner.nextLine();
            switch (opcao) {
                case "A":
                    System.out.println("Digite seu e-mail:");
                    email = scanner.next();
                    System.out.println("Digite sua senha:");
                    senha = scanner.next();
                    usuarioLogado = usuarioUtil.buscarUsuarioPeloEmailESenha(email, senha);
                    if (usuarioLogado == null) {
                        System.out.println("Usuário não encontrado ou e-mail/senha incorretos! Cadastre-se antes de logar.");
                        break;
                    }
                    System.out.println("Usuário logado com sucesso!");

                    if (usuarioLogado.getCargo() == CargoEnum.COLABORADOR) {
                        menuColaborador = new MenuColaborador(usuarioLogado);
                        menuColaborador.exibirMenuColaborador();
                    } else {
                        menuUsuario = new MenuUsuario(usuarioLogado);
                        menuUsuario.exibirMenuParticipante();
                    }
                    break;
                case "B":
                    System.out.println("Digite seu e-mail:");
                    email = scanner.next();
                    scanner.nextLine();
                    System.out.println("Digite seu nome:");
                    nome = scanner.nextLine();
                    System.out.println("Digite sua senha:");
                    senha = scanner.next();
                    boolean sucesso = usuarioUtil.adicionarUsuario(
                            new Usuario(usuarioUtil.getUsuarios().size() + 1, nome, email, senha, CargoEnum.PARTICIPANTE));
                    if (!sucesso) {
                        System.out.println("Erro ao cadastrar usuário!");
                        break;
                    }
                    System.out.println("Erro ao cadastrar usuário!");
                    break;
                case "C":
                    encerrar = true;
                    System.out.println("Encerrando o programa...");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
                    break;
            }
        }

    }
}
