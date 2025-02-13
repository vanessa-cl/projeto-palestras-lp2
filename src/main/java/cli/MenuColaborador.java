package main.java.cli;

import main.java.entities.Evento;
import main.java.entities.Palestra;
import main.java.entities.Usuario;

import java.time.LocalDate;
import java.time.LocalTime;

public class MenuColaborador extends Menu {

    public MenuColaborador(Usuario usuarioLogado) {
        super(usuarioLogado);
    }

    public void exibirMenuColaborador() {
        int opcao;
        boolean voltar = false;
        while (!voltar) {
            System.out.println("""
                    ____________________________________________________________________________________________________________________
                    
                    Escolha uma das opções abaixo:
                    1 - Cadastrar evento
                    2 - Cadastrar palestra
                    3 - Consultar eventos e palestras
                    4 - Voltar
                    
                    Digite a opção desejada:
                    ____________________________________________________________________________________________________________________
                    """);
            opcao = scanner.nextInt();
            scanner.nextLine();
            switch (opcao) {
                case 1:
                    cadastrarEvento();
                    break;
                case 2:
                    cadastrarPalestra();
                    break;
                case 3:
                    eventoUtil.exibirEventos();
                    break;
                case 4:
                    voltar = true;
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        }

    }

    public void cadastrarEvento() {
        System.out.println("Digite o nome do evento:");
        String nomeEvento = scanner.nextLine();
        Evento evento = new Evento(eventoUtil.getEventos().size() + 1, nomeEvento);

        boolean sucesso = eventoUtil.adicionarEvento(evento);
        if (!sucesso) {
            System.out.println("Erro ao adicionar evento!");
            return;
        }
        System.out.println("Evento adicionado com sucesso!");
    }

    public void cadastrarPalestra() {
        System.out.println("Digite o título da palestra:");
        String tituloPalestra = scanner.nextLine();
        System.out.println("Digite o nome do palestrante:");
        String palestrante = scanner.nextLine();
        System.out.println("Digite a data de realização da palestra (dd/MM/yyyy):");
        String data = scanner.next();
        System.out.println("Digite o horário de início da palestra (HH:MM):");
        String horarioInicio = scanner.next();
        System.out.println("Digite o horário de término da palestra (HH:MM):");
        String horarioTermino = scanner.next();
        scanner.nextLine();
        System.out.println("Digite o local da palestra:");
        String local = scanner.nextLine();
        System.out.println("Digite o limite de participantes da palestra:");
        int limiteOuvintes = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Digite o ID do evento ao qual a palestra pertence:");
        eventoUtil.exibirEventos();
        int idEventoPalestra = scanner.nextInt();
        scanner.nextLine();
        Evento eventoPalestra = eventoUtil.buscarEventoPeloId(idEventoPalestra);

        if (eventoPalestra == null) {
            System.out.println("Evento não encontrado! Cadastre o evento antes de cadastrar a palestra.");
            return;
        }

        int totalPalestras = 0;
        for (Evento evento : eventoUtil.getEventos()) {
            totalPalestras += evento.getAgendaPalestras().getQuantidadePalestras();
        }
        Palestra novaPalestra = new Palestra(
                totalPalestras + 1,
                tituloPalestra,
                palestrante,
                LocalDate.parse(data, formatadorData),
                LocalTime.parse(horarioInicio),
                LocalTime.parse(horarioTermino),
                local,
                limiteOuvintes,
                eventoPalestra.getId()
        );

        boolean sucesso = eventoPalestra.getAgendaPalestras().adicionarPalestra(novaPalestra);
        if (!sucesso) {
            System.out.println("Erro ao cadastrar palestra!");
            return;
        }
        System.out.println("Palestra cadastrada com sucesso!");
    }
}
