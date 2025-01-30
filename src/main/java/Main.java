package main.java;

import main.java.cli.Menu;
import main.java.entities.Evento;
import main.java.enums.CargoEnum;
import main.java.utils.EventoUtil;

import java.util.Scanner;

import static main.java.enums.CargoEnum.COLABORADOR;

public class Main {

    public static void main(String[] args) {
        Menu menu = new Menu();
        Scanner s = new Scanner(System.in);
        boolean encerrar = false;
        String opcao;

        while (!encerrar) {
            System.out.println("Bem-vindo ao sistema de Gerenciamento de Palestras em Eventos!");
            System.out.println("Para começar, escolha uma das opções abaixo:");
            System.out.println("A - Entrar como Colaborador");
            System.out.println("B - Entrar como Participante");
            System.out.println("C - Encerrar o programa");

            opcao = s.next().toUpperCase();
            switch (opcao) {
                case "A":
                    menu.exibirMenuColaborador();
                    break;
                case "B":
                    //menu.exibirMenuParticipante();
                    break;
                case "C":
                default:
                    encerrar = true;
                    System.out.println("Encerrando o programa...");
                    break;
            }
        }

    }
}
