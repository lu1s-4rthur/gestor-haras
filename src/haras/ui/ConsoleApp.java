package haras.ui;

import haras.exception.HarasException;
import haras.negocio.*;
import haras.basicas.*;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import com.opencsv.exceptions.CsvValidationException;

public class ConsoleApp {
    private final GerenciadorAnimal gerenciadorAnimal;
    private final GerenciadorCliente gerenciadorCliente;
    private final GerenciadorServico gerenciadorServico;
    private final GerenciadorColaborador gerenciadorColaborador;
    private final GerenciadorContrato gerenciadorContrato;
    private final GerenciadorEvento gerenciadorEvento;
    private final GerenciadorAtendimentoVeterinario gerenciadorAtendimento;
    private final GerenciadorTreinamento gerenciadorTreinamento;

    public ConsoleApp() {
        this.gerenciadorAnimal = new GerenciadorAnimal();
        this.gerenciadorCliente = new GerenciadorCliente();
        this.gerenciadorServico = new GerenciadorServico();
        this.gerenciadorColaborador = new GerenciadorColaborador();
        this.gerenciadorContrato = new GerenciadorContrato();
        this.gerenciadorEvento = new GerenciadorEvento();
        this.gerenciadorAtendimento = new GerenciadorAtendimentoVeterinario();
        this.gerenciadorTreinamento = new GerenciadorTreinamento();
        new GerenciadorReproducao();
    }

    public void iniciar() throws CsvValidationException, NumberFormatException {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("=== Gestao Haras ===");
            System.out.println("1. Gerenciar Animais");
            System.out.println("2. Gerenciar Clientes");
            System.out.println("3. Gerenciar Serviços");
            System.out.println("4. Gerenciar Colaboradores");
            System.out.println("5. Gerenciar Eventos");
            System.out.println("6. Gerenciar Contratos");
            System.out.println("7. Gerenciar Atendimentos Veterinários");
            System.out.println("8. Gerenciar Treinamentos");
            System.out.println("9. Gerenciar Baias");
            System.out.println("10. Relatórios");
            System.out.println("11. Exportar todos dados para CSV");
            System.out.println("12. Importar todos dados de CSV");
            System.out.println("0. Sair");
            System.out.print("Escolha: ");
            String escolha = scanner.nextLine();
            try {
                switch (escolha) {
                    case "1":
                        menuAnimais(scanner);
                        break;
                    case "2":
                        menuClientes(scanner);
                        break;
                    case "3":
                        menuServicos(scanner);
                        break;
                    case "4":
                        menuColaboradores(scanner);
                        break;
                    case "5":
                        menuEventos(scanner);
                        break;
                    case "6":
                        menuContratos(scanner);
                        break;
                    case "7":
                        menuAtendimentos(scanner);
                        break;
                    case "8":
                        menuTreinamentos(scanner);
                        break;
                    case "9":
                        menuBaias(scanner);
                        break;
                    case "10":
                        menuRelatorios(scanner);
                        break;
                    case "11":
                        exportarTodosCsv();
                        System.out.println("CSV exportado em dados/data.");
                        break;
                    case "12":
                        importarTodosCsv();
                        System.out.println("CSV importado de dados/data.");
                        break;
                    case "0":
                        System.out.println("Saindo...");
                        return;
                    default:
                        System.out.println("Opção inválida.");
                }
            } catch (IOException e) {
                System.err.println("Erro de IO: " + e.getMessage());
            } catch (HarasException e) {
                System.err.println("Erro: " + e.getMessage());
            }
        }
    }

    private void exportarTodosCsv() throws IOException {
        gerenciadorAnimal.exportarCsv();
        gerenciadorCliente.exportarCsv();
        gerenciadorServico.exportarCsv();
        gerenciadorColaborador.exportarCsv();
        gerenciadorEvento.exportarCsv();
        gerenciadorAtendimento.exportarCsv();
        gerenciadorTreinamento.exportarCsv();
    }

    private void importarTodosCsv() throws IOException, CsvValidationException, NumberFormatException {
        gerenciadorAnimal.importarCsv();
        gerenciadorCliente.importarCsv();
        gerenciadorServico.importarCsv();
        gerenciadorColaborador.importarCsv();

        haras.basicas.Animal.ajustarContadorAPartirDoMaximo(
                gerenciadorAnimal.listarAnimais().stream().mapToInt(Animal::getId).max().orElse(0));
        haras.basicas.Cliente.ajustarContadorAPartirDoMaximo(
                gerenciadorCliente.listarClientes().stream().mapToInt(Cliente::getId).max().orElse(0));
        haras.basicas.Servico.ajustarContadorAPartirDoMaximo(
                gerenciadorServico.listarServicos().stream().mapToInt(Servico::getId).max().orElse(0));
        haras.basicas.Colaborador.ajustarContadorAPartirDoMaximo(
                gerenciadorColaborador.listarColaboradores().stream().mapToInt(Colaborador::getId).max().orElse(0));

        gerenciadorEvento.importarCsv(
                id -> gerenciadorAnimal.listarAnimais().stream().filter(a -> a.getId() == id).findFirst().orElse(null));
        gerenciadorContrato.importarCsv(
                id -> gerenciadorCliente.listarClientes().stream().filter(c -> c.getId() == id).findFirst()
                        .orElse(null),
                id -> gerenciadorAnimal.listarAnimais().stream().filter(a -> a.getId() == id).findFirst().orElse(null),
                id -> gerenciadorServico.listarServicos().stream().filter(s -> s.getId() == id).findFirst()
                        .orElse(null));
        gerenciadorAtendimento.importarCsv(
                id -> gerenciadorAnimal.listarAnimais().stream().filter(a -> a.getId() == id).findFirst().orElse(null),
                id -> (haras.basicas.Veterinario) gerenciadorColaborador.listarColaboradores().stream()
                        .filter(c -> c.getId() == id && c instanceof haras.basicas.Veterinario).findFirst()
                        .orElse(null));
        gerenciadorTreinamento.importarCsv(
                id -> gerenciadorAnimal.listarAnimais().stream().filter(a -> a.getId() == id).findFirst().orElse(null),
                id -> (haras.basicas.Treinador) gerenciadorColaborador.listarColaboradores().stream()
                        .filter(c -> c.getId() == id && c instanceof haras.basicas.Treinador).findFirst().orElse(null));
    }

    private void menuAnimais(Scanner scanner) throws HarasException {
        System.out.println("=== Animais ===");
        System.out.println("1. Cadastrar");
        System.out.println("2. Listar");
        System.out.println("3. Editar por ID (inclui reprodutor e saúde)");
        System.out.println("4. Excluir por ID");
        System.out.print("Escolha: ");
        String op = scanner.nextLine();
        switch (op) {
            case "1":
                System.out.print("Nome: ");
                String nome = scanner.nextLine();
                System.out.print("Raça: ");
                String raca = scanner.nextLine();
                System.out.print("Gênero: ");
                String genero = scanner.nextLine();
                System.out.print("Cor: ");
                String cor = scanner.nextLine();
                System.out.print("Idade: ");
                int idade = Integer.parseInt(scanner.nextLine());
                System.out.print("Estado de Saúde: ");
                String estado = scanner.nextLine();
                Animal a = new Animal(nome, raca, genero, cor, idade, estado);
                gerenciadorAnimal.cadastrarAnimal(a);
                System.out.println("Cadastrado com ID: " + a.getId());
                break;
            case "2":
                List<Animal> animais = gerenciadorAnimal.listarAnimais();
                animais.forEach(System.out::println);
                break;
            case "3":
                System.out.print("ID para editar: ");
                int idEdit = Integer.parseInt(scanner.nextLine());
                System.out.print("Nome: ");
                String eNome = scanner.nextLine();
                System.out.print("Raça: ");
                String eRaca = scanner.nextLine();
                System.out.print("Gênero: ");
                String eGenero = scanner.nextLine();
                System.out.print("Cor: ");
                String eCor = scanner.nextLine();
                System.out.print("Idade: ");
                int eIdade = Integer.parseInt(scanner.nextLine());
                System.out.print("Estado de Saúde: ");
                String eSaude = scanner.nextLine();
                System.out.print("É reprodutor? (true/false): ");
                boolean eReprod = Boolean.parseBoolean(scanner.nextLine());
                Animal aEdit = new Animal(eNome, eRaca, eGenero, eCor, eIdade, eSaude);
                aEdit.setId(idEdit);
                aEdit.setReprodutor(eReprod);
                gerenciadorAnimal.editarAnimal(aEdit);
                System.out.println("Editado.");
                break;
            case "4":
                System.out.print("ID para excluir: ");
                int id = Integer.parseInt(scanner.nextLine());
                boolean ok = gerenciadorAnimal.excluirAnimal(id);
                System.out.println(ok ? "Excluído." : "Não encontrado.");
                break;
            default:
                System.out.println("Opção inválida.");
        }
    }

    private void menuClientes(Scanner scanner) throws HarasException {
        System.out.println("=== Clientes ===");
        System.out.println("1. Cadastrar");
        System.out.println("2. Listar");
        System.out.println("3. Excluir por ID");
        System.out.print("Escolha: ");
        String op = scanner.nextLine();
        switch (op) {
            case "1":
                System.out.print("Nome: ");
                String nome = scanner.nextLine();
                System.out.print("Endereço: ");
                String endereco = scanner.nextLine();
                System.out.print("Telefone: ");
                String telefone = scanner.nextLine();
                Cliente c = new Cliente(nome, endereco, telefone);
                gerenciadorCliente.cadastrarCliente(c);
                System.out.println("Cadastrado com ID: " + c.getId());
                break;
            case "2":
                List<Cliente> clientes = gerenciadorCliente.listarClientes();
                clientes.forEach(System.out::println);
                break;
            case "3":
                System.out.print("ID para excluir: ");
                int id = Integer.parseInt(scanner.nextLine());
                boolean ok = gerenciadorCliente.excluirCliente(id);
                System.out.println(ok ? "Excluído." : "Não encontrado.");
                break;
            default:
                System.out.println("Opção inválida.");
        }
    }

    private void menuReproducao(Scanner scanner) {
        System.out.println("=== Reprodução ===");
        System.out.print("ID Pai (animal): ");
        int paiId = Integer.parseInt(scanner.nextLine());
        System.out.print("ID Mãe (animal): ");
        int maeId = Integer.parseInt(scanner.nextLine());
        Animal pai = gerenciadorAnimal.listarAnimais().stream().filter(x -> x.getId() == paiId).findFirst()
                .orElse(null);
        Animal mae = gerenciadorAnimal.listarAnimais().stream().filter(x -> x.getId() == maeId).findFirst()
                .orElse(null);
        if (pai == null || mae == null) {
            System.out.println("Animais inválidos.");
            return;
        }
        if (!pai.isReprodutor() || !mae.isReprodutor()) {
            System.out.println("Reprodução apenas para animais marcados como reprodutores.");
            return;
        }
    }

    private void menuServicos(Scanner scanner) throws HarasException {
        System.out.println("=== Serviços ===");
        System.out.println("1. Cadastrar");
        System.out.println("2. Listar");
        System.out.println("3. Editar por ID");
        System.out.println("4. Excluir por ID");
        System.out.print("Escolha: ");
        String op = scanner.nextLine();
        switch (op) {
            case "1":
                System.out.print("Tipo: ");
                String tipo = scanner.nextLine();
                if ("Reproducao".equalsIgnoreCase(tipo) || "Reprodução".equalsIgnoreCase(tipo)) {
                    menuReproducao(scanner);
                }
                System.out.print("Valor: ");
                double valor = Double.parseDouble(scanner.nextLine());
                Servico s = new Servico(tipo, valor);
                gerenciadorServico.cadastrarServico(s);
                System.out.println("Servico cadastrado com ID: " + s.getId());
                break;
            case "2":
                List<Servico> servicos = gerenciadorServico.listarServicos();
                servicos.forEach(System.out::println);
                break;
            case "3":
                System.out.print("ID para editar: ");
                int idEdit = Integer.parseInt(scanner.nextLine());
                System.out.print("Novo tipo: ");
                String nTipo = scanner.nextLine();
                System.out.print("Novo valor: ");
                double nValor = Double.parseDouble(scanner.nextLine());
                Servico sEdit = new Servico(nTipo, nValor);
                sEdit.setId(idEdit);
                gerenciadorServico.editarServico(sEdit);
                System.out.println("Editado.");
                break;
            case "4":
                System.out.print("ID para excluir: ");
                int id = Integer.parseInt(scanner.nextLine());
                boolean ok = gerenciadorServico.excluirServico(id);
                System.out.println(ok ? "Excluído." : "Não encontrado.");
                break;
            default:
                System.out.println("Opção inválida.");
        }
    }

    private void menuColaboradores(Scanner scanner) throws HarasException {
        System.out.println("=== Colaboradores ===");
        System.out.println("1. Cadastrar");
        System.out.println("2. Listar");
        System.out.println("3. Editar por ID");
        System.out.println("4. Excluir por ID");
        System.out.print("Escolha: ");
        String op = scanner.nextLine();
        switch (op) {
            case "1":
                System.out.print("Nome: ");
                String nome = scanner.nextLine();
                System.out.print("Endereço: ");
                String endereco = scanner.nextLine();
                System.out.print("Telefone: ");
                String telefone = scanner.nextLine();
                System.out.print("Cargo: ");
                String cargo = scanner.nextLine();
                System.out.print("Admissão (yyyy-MM-dd): ");
                java.util.Date adm;
                try {
                    adm = new java.text.SimpleDateFormat("yyyy-MM-dd").parse(scanner.nextLine());
                } catch (java.text.ParseException e) {
                    System.out.println("Data inválida.");
                    return;
                }
                System.out.print("Formação: ");
                String formacao = scanner.nextLine();
                Colaborador col = new Colaborador(nome, endereco, telefone, cargo, adm, formacao);
                gerenciadorColaborador.cadastrarColaborador(col);
                System.out.println("Cadastrado com ID: " + col.getId());
                break;
            case "2":
                List<Colaborador> cols = gerenciadorColaborador.listarColaboradores();
                cols.forEach(System.out::println);
                break;
            case "3":
                System.out.print("ID para editar: ");
                int idEdit = Integer.parseInt(scanner.nextLine());
                System.out.print("Nome: ");
                String eNome = scanner.nextLine();
                System.out.print("Endereço: ");
                String eEndereco = scanner.nextLine();
                System.out.print("Telefone: ");
                String eTelefone = scanner.nextLine();
                System.out.print("Cargo: ");
                String eCargo = scanner.nextLine();
                System.out.print("Admissão (yyyy-MM-dd): ");
                java.util.Date eAdm;
                try {
                    eAdm = new java.text.SimpleDateFormat("yyyy-MM-dd").parse(scanner.nextLine());
                } catch (java.text.ParseException e) {
                    System.out.println("Data inválida.");
                    return;
                }
                System.out.print("Formação: ");
                String eForm = scanner.nextLine();
                Colaborador colE = new Colaborador(eNome, eEndereco, eTelefone, eCargo, eAdm, eForm);
                colE.setId(idEdit);
                gerenciadorColaborador.editarColaborador(colE);
                System.out.println("Editado.");
                break;
            case "4":
                System.out.print("ID para excluir: ");
                int id = Integer.parseInt(scanner.nextLine());
                boolean ok = gerenciadorColaborador.excluirColaborador(id);
                System.out.println(ok ? "Excluído." : "Não encontrado.");
                break;
            default:
                System.out.println("Opção inválida.");
        }
    }

    private void menuEventos(Scanner scanner) throws HarasException {
        System.out.println("=== Eventos ===");
        System.out.println("1. Cadastrar");
        System.out.println("2. Listar");
        System.out.println("3. Adicionar participante");
        System.out.print("Escolha: ");
        String op = scanner.nextLine();
        switch (op) {
            case "1":
                System.out.print("Tipo: ");
                String tipo = scanner.nextLine();
                System.out.print("Data (yyyy-MM-dd): ");
                java.util.Date data;
                try {
                    data = new java.text.SimpleDateFormat("yyyy-MM-dd").parse(scanner.nextLine());
                } catch (java.text.ParseException e) {
                    System.out.println("Data inválida.");
                    return;
                }
                System.out.print("Local: ");
                String local = scanner.nextLine();
                haras.basicas.Evento e = new haras.basicas.Evento(tipo, data, local);
                gerenciadorEvento.cadastrarEvento(e);
                System.out.println("Cadastrado com ID: " + e.getId());
                break;
            case "2":
                gerenciadorEvento.listarEventos().forEach(System.out::println);
                break;
            case "3":
                System.out.print("ID do evento: ");
                int evtId = Integer.parseInt(scanner.nextLine());
                haras.basicas.Evento evt = gerenciadorEvento.listarEventos().stream().filter(ev -> ev.getId() == evtId)
                        .findFirst().orElse(null);
                if (evt == null) {
                    System.out.println("Evento não encontrado.");
                    return;
                }
                System.out.print("ID do animal: ");
                int anId = Integer.parseInt(scanner.nextLine());
                Animal an = gerenciadorAnimal.listarAnimais().stream().filter(a -> a.getId() == anId).findFirst()
                        .orElse(null);
                if (an == null) {
                    System.out.println("Animal não encontrado.");
                    return;
                }
                gerenciadorEvento.adicionarParticipante(evt, an);
                System.out.println("Participante adicionado.");
                break;
            default:
                System.out.println("Opção inválida.");
        }
    }

    private void menuContratos(Scanner scanner) throws HarasException {
        System.out.println("=== Contratos ===");
        System.out.println("1. Cadastrar (C1: ocupa baia se Hospedagem)");
        System.out.println("2. Listar");
        System.out.println("3. Excluir por ID (libera baia se hospedagem)");
        System.out.print("Escolha: ");
        String op = scanner.nextLine();
        switch (op) {
            case "1":
                System.out.print("ID Cliente: ");
                int cId = Integer.parseInt(scanner.nextLine());
                Cliente cli = gerenciadorCliente.listarClientes().stream().filter(c -> c.getId() == cId).findFirst()
                        .orElse(null);
                System.out.print("ID Animal: ");
                int aId = Integer.parseInt(scanner.nextLine());
                Animal ani = gerenciadorAnimal.listarAnimais().stream().filter(a -> a.getId() == aId).findFirst()
                        .orElse(null);
                System.out.print("ID Serviço: ");
                int sId = Integer.parseInt(scanner.nextLine());
                Servico serv = gerenciadorServico.listarServicos().stream().filter(s -> s.getId() == sId).findFirst()
                        .orElse(null);
                if (cli == null || ani == null || serv == null) {
                    System.out.println("Dados inválidos.");
                    return;
                }
                System.out.print("Início (yyyy-MM-dd): ");
                java.util.Date dtIni;
                try {
                    dtIni = new java.text.SimpleDateFormat("yyyy-MM-dd").parse(scanner.nextLine());
                } catch (java.text.ParseException e) {
                    System.out.println("Data inválida.");
                    return;
                }
                System.out.print("Fim (yyyy-MM-dd): ");
                java.util.Date dtFim;
                try {
                    dtFim = new java.text.SimpleDateFormat("yyyy-MM-dd").parse(scanner.nextLine());
                } catch (java.text.ParseException e) {
                    System.out.println("Data inválida.");
                    return;
                }
                System.out.print("Status: ");
                String status = scanner.nextLine();
                haras.basicas.Contrato ct = new haras.basicas.Contrato(cli, ani, serv, dtIni, dtFim, status);
                gerenciadorContrato.cadastrarContrato(ct);
                if ("Hospedagem".equalsIgnoreCase(serv.getTipo())) {
                    System.out.print("Número da baia para ocupar: ");
                    int num = Integer.parseInt(scanner.nextLine());
                    Baia b = localizarOuCriarBaia(num);
                    try {
                        gerenciadorBaia.alocarAnimalEmBaia(b, ani);
                        System.out.println("Baia ocupada.");
                    } catch (HarasException ex) {
                        System.out.println(ex.getMessage());
                    }
                }
                System.out.println("Contrato cadastrado com ID: " + ct.getId());
                break;
            case "2":
                gerenciadorContrato.listarContratos().forEach(System.out::println);
                break;
            case "3":
                System.out.print("ID do contrato: ");
                int idc = Integer.parseInt(scanner.nextLine());
                haras.basicas.Contrato cto = gerenciadorContrato.listarContratos().stream()
                        .filter(c -> c.getId() == idc).findFirst().orElse(null);
                if (cto == null) {
                    System.out.println("Não encontrado.");
                    return;
                }
                if ("Hospedagem".equalsIgnoreCase(cto.getServico().getTipo())) {
                    System.out.print("Número da baia a liberar: ");
                    int num = Integer.parseInt(scanner.nextLine());
                    Baia b = localizarOuCriarBaia(num);
                    try {
                        gerenciadorBaia.liberarBaia(b);
                        System.out.println("Baia liberada.");
                    } catch (HarasException ex) {
                        System.out.println(ex.getMessage());
                    }
                }
                boolean rem = gerenciadorContrato.excluirContrato(idc);
                System.out.println(rem ? "Excluído." : "Não removido.");
                break;
            default:
                System.out.println("Opção inválida.");
        }
    }

    private void menuAtendimentos(Scanner scanner) throws HarasException {
        System.out.println("=== Atendimentos ===");
        System.out.println("1. Registrar");
        System.out.println("2. Listar");
        System.out.println("3. Excluir por ID");
        System.out.print("Escolha: ");
        String op = scanner.nextLine();
        switch (op) {
            case "1":
                System.out.print("ID Animal: ");
                int aId = Integer.parseInt(scanner.nextLine());
                Animal an = gerenciadorAnimal.listarAnimais().stream().filter(x -> x.getId() == aId).findFirst()
                        .orElse(null);
                System.out.print("ID Veterinário (ID de Colaborador do tipo Veterinario): ");
                int vId = Integer.parseInt(scanner.nextLine());
                Colaborador vet = gerenciadorColaborador.listarColaboradores()
                        .stream()
                        .filter(c -> c.getId() == vId && "Veterinario".equalsIgnoreCase(c.getCargo()))
                        .findFirst()
                        .orElse(null);
                if (an == null || vet == null) {
                    System.out.println("Dados inválidos.");
                    return;
                }
                System.out.print("Data (yyyy-MM-dd): ");
                java.util.Date dt;
                try {
                    dt = new java.text.SimpleDateFormat("yyyy-MM-dd").parse(scanner.nextLine());
                } catch (java.text.ParseException e) {
                    System.out.println("Data inválida.");
                    return;
                }
                System.out.print("Descrição: ");
                String desc = scanner.nextLine();
                haras.basicas.AtendimentoVeterinario at = new haras.basicas.AtendimentoVeterinario(an, (Veterinario) vet, dt, desc);
                gerenciadorAtendimento.registrarAtendimento(at);
                System.out.println("Registrado com ID: " + at.getId());
                break;
            case "2":
                gerenciadorAtendimento.listarAtendimentos().forEach(System.out::println);
                break;
            case "3":
                System.out.print("ID para excluir: ");
                int id = Integer.parseInt(scanner.nextLine());
                boolean ok = gerenciadorAtendimento.excluirAtendimento(id);
                System.out.println(ok ? "Excluído." : "Não encontrado.");
                break;
            default:
                System.out.println("Opção inválida.");
        }
    }

    private void menuTreinamentos(Scanner scanner) throws HarasException {
        System.out.println("=== Treinamentos ===");
        System.out.println("1. Registrar");
        System.out.println("2. Listar");
        System.out.println("3. Excluir por ID");
        System.out.print("Escolha: ");
        String op = scanner.nextLine();
        switch (op) {
            case "1":
                System.out.print("ID Animal: ");
                int aId = Integer.parseInt(scanner.nextLine());
                Animal an = gerenciadorAnimal.listarAnimais().stream().filter(x -> x.getId() == aId).findFirst()
                        .orElse(null);
                System.out.print("ID Treinador (ID de Colaborador do tipo Treinador): ");
                int tId = Integer.parseInt(scanner.nextLine());
                haras.basicas.Treinador tr = (haras.basicas.Treinador) gerenciadorColaborador.listarColaboradores()
                        .stream().filter(c -> c.getId() == tId && c instanceof haras.basicas.Treinador).findFirst()
                        .orElse(null);
                if (an == null || tr == null) {
                    System.out.println("Dados inválidos.");
                    return;
                }
                System.out.print("Data (yyyy-MM-dd): ");
                java.util.Date dt;
                try {
                    dt = new java.text.SimpleDateFormat("yyyy-MM-dd").parse(scanner.nextLine());
                } catch (java.text.ParseException e) {
                    System.out.println("Data inválida.");
                    return;
                }
                System.out.print("Tipo: ");
                String tipo = scanner.nextLine();
                System.out.print("Observações: ");
                String obs = scanner.nextLine();
                haras.basicas.Treinamento trn = new haras.basicas.Treinamento(an, tr, dt, tipo, obs);
                gerenciadorTreinamento.registrarTreinamento(trn);
                System.out.println("Registrado com ID: " + trn.getId());
                break;
            case "2":
                gerenciadorTreinamento.listarTreinamentos().forEach(System.out::println);
                break;
            case "3":
                System.out.print("ID para excluir: ");
                int id = Integer.parseInt(scanner.nextLine());
                boolean ok = gerenciadorTreinamento.excluirTreinamento(id);
                System.out.println(ok ? "Excluído." : "Não encontrado.");
                break;
            default:
                System.out.println("Opção inválida.");
        }
    }

    // Baias: simples catálogo em memória (não persistido)
    private final java.util.Map<Integer, Baia> baias = new java.util.HashMap<>();
    private final GerenciadorBaia gerenciadorBaia = new GerenciadorBaia();

    private Baia localizarOuCriarBaia(int numero) {
        return baias.computeIfAbsent(numero, Baia::new);
    }

    private void menuBaias(Scanner scanner) throws HarasException {
        System.out.println("=== Baias ===");
        System.out.println("1. Criar/Localizar baia");
        System.out.println("2. Colocar em manutenção");
        System.out.println("3. Retirar de manutenção");
        System.out.println("4. Ocupar por Animal");
        System.out.println("5. Liberar");
        System.out.println("6. Listar");
        System.out.print("Escolha: ");
        String op = scanner.nextLine();
        switch (op) {
            case "1":
                System.out.print("Número: ");
                int num = Integer.parseInt(scanner.nextLine());
                Baia b = localizarOuCriarBaia(num);
                System.out.println("Baia: " + b);
                break;
            case "2":
                System.out.print("Número: ");
                num = Integer.parseInt(scanner.nextLine());
                b = localizarOuCriarBaia(num);
                gerenciadorBaia.colocarBaiaEmManutencao(b);
                System.out.println("Em manutenção.");
                break;
            case "3":
                System.out.print("Número: ");
                num = Integer.parseInt(scanner.nextLine());
                b = localizarOuCriarBaia(num);
                gerenciadorBaia.retirarBaiaDeManutencao(b);
                System.out.println("Disponível.");
                break;
            case "4":
                System.out.print("Número: ");
                num = Integer.parseInt(scanner.nextLine());
                b = localizarOuCriarBaia(num);
                System.out.print("ID Animal: ");
                int aId = Integer.parseInt(scanner.nextLine());
                Animal a = gerenciadorAnimal.listarAnimais().stream().filter(x -> x.getId() == aId).findFirst()
                        .orElse(null);
                if (a == null) {
                    System.out.println("Animal inválido.");
                    return;
                }
                gerenciadorBaia.alocarAnimalEmBaia(b, a);
                System.out.println("Ocupada.");
                break;
            case "5":
                System.out.print("Número: ");
                num = Integer.parseInt(scanner.nextLine());
                b = localizarOuCriarBaia(num);
                gerenciadorBaia.liberarBaia(b);
                System.out.println("Liberada.");
                break;
            case "6":
                baias.values().forEach(System.out::println);
                break;
            default:
                System.out.println("Opção inválida.");
        }
    }

    private void menuRelatorios(Scanner scanner) {
        System.out.println("=== Relatórios ===");
        System.out.println("1. Serviços");
        System.out.println("2. Eventos");
        System.out.println("3. Ocupação de Baias");
        System.out.println("4. Animais hospedados (baia ocupada)");
        System.out.println("5. Faturamento estimado (soma valores de serviços em contratos)");
        System.out.print("Escolha: ");
        String op = scanner.nextLine();
        switch (op) {
            case "1":
                System.out.println(gerenciadorServico.emitirRelatorioServicos());
                break;
            case "2":
                System.out.println(gerenciadorEvento.emitirRelatorioEventos());
                break;
            case "3":
                long ocupadas = baias.values().stream().filter(b -> b.getAnimalOcupante() != null).count();
                System.out.println("Baias ocupadas: " + ocupadas + "/" + baias.size());
                break;
            case "4":
                baias.values().stream().filter(b -> b.getAnimalOcupante() != null).forEach(b -> System.out.println(b));
                break;
            case "5":
                double total = gerenciadorContrato.listarContratos().stream()
                        .mapToDouble(c -> c.getServico().getValor()).sum();
                System.out.println("Faturamento estimado: R$ " + total);
                break;
            default:
                System.out.println("Opção inválida.");
        }
    }

    public static void main(String[] args) throws CsvValidationException, NumberFormatException {
        new ConsoleApp().iniciar();
    }
}
