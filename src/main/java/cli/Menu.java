package main.java.cli;

import main.java.entities.Evento;
import main.java.entities.Palestra;
import main.java.utils.EventoUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public class Menu {
    EventoUtil eventoUtil = new EventoUtil();
    Scanner s = new Scanner(System.in);

    public void exibirMenuColaborador() {
        int opcao;
        boolean voltar = false;
        while (!voltar) {
            System.out.println("Escolha uma das opções abaixo:");
            System.out.println("1 - Cadastrar evento");
            System.out.println("2 - Cadastrar palestra");
            System.out.println("3 - Voltar");
            opcao = s.nextInt();
            switch (opcao) {
                case 1:
                    System.out.println("Digite o nome do evento:");
                    String nomeEvento = s.next();
                    Evento evento = new Evento(eventoUtil.getEventos().size() + 1, nomeEvento);
                    eventoUtil.adicionarEvento(evento);
                    break;
                case 2:
                    System.out.println("Digite o título da palestra:");
                    String tituloPalestra = s.next();
                    System.out.println("Digite o nome do palestrante:");
                    String palestrante = s.next();
                    System.out.println("Digite a data de realização da palestra:");
                    String data = s.next();
                    System.out.println("Digite o horário de início da palestra:");
                    String horarioInicio = s.next();
                    System.out.println("Digite o horário de término da palestra:");
                    String horarioTermino = s.next();
                    System.out.println("Digite o local da palestra:");
                    String local = s.next();
                    System.out.println("Digite o limite de participantes da palestra:");
                    int limiteOuvintes = s.nextInt();
                    System.out.println("Digite o nome do evento ao qual a palestra pertence:");
                    String nomeEventoPalestra = s.next();
                    Evento eventoPalestra = eventoUtil.buscarEventoPeloNome(nomeEventoPalestra);
                    if (eventoPalestra != null) {
                        Palestra novaPalestra = new Palestra(tituloPalestra, palestrante, LocalDate.parse(data), LocalTime.parse(horarioInicio), LocalTime.parse(horarioTermino), local, limiteOuvintes);
                        eventoUtil.adicionarPalestraAoEvento(nomeEventoPalestra, novaPalestra);
                    } else {
                        System.out.println("Evento não encontrado! Cadastre o evento antes de cadastrar a palestra.");
                    }
                    break;
                case 3:
                    voltar = true;
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        }

    }

    public void exibirMenuParticipante() {
        int opcao;
        boolean voltar = false;
        while (!voltar) {
            System.out.println("Escolha uma das opções abaixo:");
            System.out.println("1 - Realizar inscrição em palestra");
            System.out.println("2 - Exibir lista de eventos e palestras");
            System.out.println("3 - Voltar");
            opcao = s.nextInt();
            switch (opcao) {
                case 1:
                    System.out.println("Digite o nome do evento:");
                    String nomeEvento = s.next();
                    Evento evento = eventoUtil.buscarEventoPeloNome(nomeEvento);
                    if (evento != null) {
                        System.out.println("Digite o título da palestra:");
                        String tituloPalestra = s.next();
                        Palestra palestra = evento.getAgendaPalestras().buscarPalestraPeloTitulo(tituloPalestra);
                        if (palestra != null) {
                            if (palestra.getListaParticipantes().size() < palestra.getLimiteOuvintes()) {
                                System.out.println("Inscrição realizada com sucesso!");
                                // TODO: implementar método para adicionar ouvinte à lista de participantes
                            } else {
                                System.out.println("Limite de ouvintes atingido! Deseja se inscrever na lista de espera da palestra? (S/N)");
                                // TODO: implementar método para adicionar ouvinte à lista de espera
                            }
                        } else {
                            System.out.println("Palestra não encontrada!");
                        }
                    } else {
                        System.out.println("Evento não encontrado!");
                    }

                case 2:
                    eventoUtil.exibirEventos();
                    break;
                case 3:
                    voltar = true;
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");

            }
        }
    }
}
