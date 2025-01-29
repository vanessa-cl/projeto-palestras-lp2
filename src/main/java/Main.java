package main.java;

import main.java.entities.Evento;
import main.java.utils.EventoUtil;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        EventoUtil eventoUtil = new EventoUtil();
        Scanner s = new Scanner(System.in);
        int opcao = 0;
        while (opcao != 4) {
            System.out.println("Bem-vindo ao sistema de Gerenciamento de Palestras em Eventos!");
            System.out.println("Para começar, escolha uma das opções abaixo:");
            System.out.println("1 - Cadastrar Evento");
            opcao = s.nextInt();
            switch (opcao) {
                case 1:
                    Evento evento = new Evento("Evento Teste");
                    eventoUtil.adicionarEvento(evento);
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }

    }
}
