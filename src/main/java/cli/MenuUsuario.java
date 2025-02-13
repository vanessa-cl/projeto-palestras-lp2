package main.java.cli;

import main.java.entities.Evento;
import main.java.entities.Ouvinte;
import main.java.entities.Palestra;
import main.java.entities.Usuario;

import java.util.ArrayList;

public class MenuUsuario extends Menu {

    public MenuUsuario(Usuario usuarioLogado) {
        super(usuarioLogado);
    }

    public void exibirMenuParticipante() {
        int opcao;
        boolean voltar = false;
        while (!voltar) {
            System.out.println("""
                    ____________________________________________________________________________________________________________________
                    
                    Escolha uma das opções abaixo:
                    1 - Realizar inscrição em palestra
                    2 - Cancelar inscrição em palestra
                    3 - Consultar minhas inscrições
                    4 - Exibir lista de eventos e palestras
                    5 - Voltar
                    
                    Digite a opção desejada:
                    ____________________________________________________________________________________________________________________
                    """);
            opcao = scanner.nextInt();
            scanner.nextLine();
            switch (opcao) {
                case 1:
                    inscricaoEmPalestra();
                    break;
                case 2:
                    cancelarInscricaoEmPalestra();
                    break;
                case 3:
                    exibirInscricoes();
                    break;
                case 4:
                    eventoUtil.exibirAgendaEventos();
                    break;
                case 5:
                    voltar = true;
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");

            }
        }
    }

    public void inscricaoEmPalestra() {
        System.out.println("Digite o ID do evento organizador da palestra desejada:");
        eventoUtil.exibirEventos();
        int idEvento = scanner.nextInt();
        scanner.nextLine();
        Evento evento = eventoUtil.buscarEventoPeloId(idEvento);
        if (evento == null) {
            System.out.println("Evento não encontrado!");
            return;
        }

        System.out.println("Digite o ID da palestra na qual deseja se inscrever:");
        evento.getAgendaPalestras().exibirPalestras();
        int idPalestra = scanner.nextInt();
        scanner.nextLine();
        Palestra palestra = evento.getAgendaPalestras().buscarPalestraPeloId(idPalestra);
        if (palestra == null) {
            System.out.println("Palestra não encontrada!");
            return;
        }

        if (palestra.getListaOuvintes().buscarOuvintePeloId(usuarioLogado.getId()) != null) {
            System.out.println("Você já está inscrito nesta palestra!");
            return;
        }

        if (palestra.getListaOuvintes().getQuantidadeOuvintes() >= palestra.getLimiteOuvintes()) {
            System.out.println("Limite de ouvintes atingido! Deseja se inscrever na lista de espera da palestra? (S/N)");
            String opcaoListaEspera = scanner.next().toUpperCase();
            if (opcaoListaEspera.equalsIgnoreCase("N")) {
                return;
            }

            Ouvinte ouvinteListaEspera = palestra.getFilaEspera().buscarOuvintePeloId(usuarioLogado.getId());
            if (ouvinteListaEspera != null) {
                System.out.println("Você já está na lista de espera desta palestra!");
                return;
            }

            boolean sucessoFilaEspera = palestra.getFilaEspera().adicionarOuvinte(new Ouvinte(usuarioLogado.getId(), palestra.getId()));
            if (!sucessoFilaEspera) {
                System.out.println("Erro ao realizar inscrição na lista de espera!");
                return;
            }
            System.out.println("Inscrição na lista de espera realizada com sucesso!");
            return;
        }

        boolean sucesso = palestra.getListaOuvintes().adicionarOuvinte(new Ouvinte(usuarioLogado.getId(), palestra.getId()));
        if (!sucesso) {
            System.out.println("Erro ao realizar inscrição!");
            return;
        }
        System.out.println("Inscrição realizada com sucesso!");
    }

    public void cancelarInscricaoEmPalestra() {
        System.out.println("Digite o ID do evento organizador da palestra:");
        int idEventoCancelar = scanner.nextInt();
        Evento eventoCancelar = eventoUtil.buscarEventoPeloId(idEventoCancelar);
        if (eventoCancelar == null) {
            System.out.println("Evento não encontrado!");
            return;
        }

        System.out.println("Digite o ID da palestra na qual deseja cancelar a inscrição:");
        int idPalestraCancelar = scanner.nextInt();
        Palestra palestraCancelar = eventoCancelar.getAgendaPalestras().buscarPalestraPeloId(idPalestraCancelar);
        if (palestraCancelar == null) {
            System.out.println("Palestra não encontrada!");
            return;
        }

        if (palestraCancelar.getListaOuvintes().buscarOuvintePeloId(usuarioLogado.getId()) == null) {
            System.out.println("Você não está inscrito nesta palestra!");
            return;
        }

        boolean sucessoPalestra = palestraCancelar.getListaOuvintes().removerOuvinte(usuarioLogado.getId(), palestraCancelar.getId());
        if (!sucessoPalestra) {
            System.out.println("Erro ao cancelar inscrição!");
            return;
        }
        System.out.println("Inscrição cancelada com sucesso!");
        moverFilaDeEspera(palestraCancelar);
    }

    public void moverFilaDeEspera(Palestra palestra) {
        if (palestra.getFilaEspera().getQuantidadeOuvintes() == 0) {
            return;
        }
        Ouvinte ouvinteInicioFilaEspera = palestra.getFilaEspera().removerOuvinteInicio();
        if (ouvinteInicioFilaEspera == null) {
            return;
        }
        palestra.getListaOuvintes().adicionarOuvinte(ouvinteInicioFilaEspera);
    }

    public void exibirInscricoes() {
        ArrayList<Palestra> palestrasInscrito = new ArrayList<>();
        ArrayList<Palestra> palestrasListaEspera = new ArrayList<>();

        for (Evento evento : eventoUtil.getEventos()) {
            for (Palestra palestra : evento.getAgendaPalestras().getPalestras()) {
                for (Ouvinte ouvinte : palestra.getListaOuvintes().getOuvintes()) {
                    if (ouvinte.getId() == usuarioLogado.getId()) {
                        palestrasInscrito.add(palestra);
                    }
                }
                for (Ouvinte ouvinte : palestra.getFilaEspera().getOuvintes()) {
                    if (ouvinte.getId() == usuarioLogado.getId()) {
                        palestrasListaEspera.add(palestra);
                    }
                }
            }
        }
        if (palestrasInscrito.isEmpty() && palestrasListaEspera.isEmpty()) {
            System.out.println("Você não está inscrito em nenhuma palestra!");
            return;
        }
        System.out.println("Minhas inscrições em palestras:");
        for (Palestra palestra : palestrasInscrito) {
            System.out.println(palestra.toString());
        }
        System.out.println("____________________________________________________________________________________________________________________");
        System.out.println("Minhas inscrições na lista de espera:");
        for (Palestra palestra : palestrasListaEspera) {
            System.out.println(palestra.toString());
        }
    }
}
