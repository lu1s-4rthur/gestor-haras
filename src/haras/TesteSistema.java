package haras;

import haras.basicas.*;
import haras.negocio.*;
import haras.exception.HarasException;

import java.util.Date;

public class TesteSistema {

    // ==== Testes de cada funcionalidade ====
    private static void testarCadastroAnimal(GerenciadorAnimal ga) throws HarasException {
        Animal animal = new Animal("Thunder", "Quarto de Milha", "Macho", "Castanho", 5, "Saudável");
        ga.cadastrarAnimal(animal);
        System.out.println("Animais cadastrados: " + ga.listarAnimais());
    }

    private static void testarCadastroCliente(GerenciadorCliente gc) throws HarasException {
        Cliente cliente = new Cliente("João", "Rua das Flores, 123", "12345678900");
        gc.cadastrarCliente(cliente);
        System.out.println("Clientes cadastrados: " + gc.listarClientes());
    }

    private static void testarCadastroColaborador(GerenciadorColaborador gcol) throws HarasException {
        Colaborador colaborador = new Colaborador("Maria", "Rua dos Médicos, 45", "987654321",
                "Veterinária", new Date(), "Medicina Veterinária");
        gcol.cadastrarColaborador(colaborador);
        System.out.println("Colaboradores cadastrados: " + gcol.listarColaboradores());
    }

    private static void testarServicoEBaia(GerenciadorServico gs, GerenciadorBaia gb, Animal animal) throws HarasException {
        Servico servico = new Servico("Hospedagem", "Hospedagem de animal em baia", 500.0);
        gs.cadastrarServico(servico);

        Baia baia = new Baia(1);
        gb.alocarAnimalEmBaia(baia, animal);

        System.out.println("Serviços cadastrados: " + gs.listarServicos());
    }

    private static void testarContrato(GerenciadorContrato gct, Cliente cliente, Animal animal, Servico servico) throws HarasException {
        Date inicio = new Date(System.currentTimeMillis() + 86400000); // amanhã
        Date fim = new Date(System.currentTimeMillis() + 86400000 * 10); // daqui 10 dias
        Contrato contrato = new Contrato(cliente, animal, servico, inicio, fim, "Ativo");
        gct.cadastrarContrato(contrato);

        System.out.println("Contratos cadastrados: " + gct.listarContratos());
    }

    private static void testarAtendimentoVeterinario(GerenciadorAtendimentoVeterinario gav, Animal animal) throws HarasException {
        Veterinario veterinario = new Veterinario("Dr. Paulo", "Rua dos Vets, 99", "999888777",
                "Veterinário", new Date(), "Medicina Veterinária", "CRMV1234");

        AtendimentoVeterinario atendimento = new AtendimentoVeterinario(animal, veterinario, new Date(), "Prescrição: repouso");
        gav.registrarAtendimento(atendimento);

        System.out.println("Atendimentos cadastrados: " + gav.listarAtendimentos());
    }

    private static void testarEvento(GerenciadorEvento ge, Animal animal) throws HarasException {
        Evento evento = new Evento("Treinamento", new Date(System.currentTimeMillis() + 86400000), "Pista Principal");
        ge.cadastrarEvento(evento);
        ge.adicionarParticipante(evento, animal);

        System.out.println(ge.emitirRelatorioEventos());
    }

    private static void testarTreinamento(GerenciadorTreinamento gt, Animal animal) throws HarasException {
        Treinador treinador = new Treinador("Carlos", "Rua dos Treinadores, 77", "888777666",
                "Treinador", new Date(), "Educação Física");

        Treinamento treinamento = new Treinamento(animal, treinador, new Date(), "Salto", "Ótimo desempenho");
        gt.registrarTreinamento(treinamento);

        System.out.println("Treinamentos cadastrados: " + gt.listarTreinamentos());
    }

    // ==== MAIN ====
    public static void main(String[] args) {
        try {
            // Gerenciadores
            GerenciadorAnimal ga = new GerenciadorAnimal();
            GerenciadorCliente gc = new GerenciadorCliente();
            GerenciadorColaborador gcol = new GerenciadorColaborador();
            GerenciadorServico gs = new GerenciadorServico();
            GerenciadorEvento ge = new GerenciadorEvento();
            GerenciadorContrato gct = new GerenciadorContrato();
            GerenciadorBaia gb = new GerenciadorBaia();
            GerenciadorAtendimentoVeterinario gav = new GerenciadorAtendimentoVeterinario();
            GerenciadorTreinamento gt = new GerenciadorTreinamento();

            // Executando os testes
            testarCadastroAnimal(ga);
            testarCadastroCliente(gc);
            testarCadastroColaborador(gcol);

            // Recupera entidades para próximos testes
            Animal animal = ga.listarAnimais().get(0);
            Cliente cliente = gc.listarClientes().get(0);

            testarServicoEBaia(gs, gb, animal);
            Servico servico = gs.listarServicos().get(0);

            testarContrato(gct, cliente, animal, servico);
            testarAtendimentoVeterinario(gav, animal);
            testarEvento(ge, animal);
            testarTreinamento(gt, animal);

        } catch (HarasException e) {
            System.err.println("Erro de negócio: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Erro geral: " + e.getMessage());
        }
    }
}
