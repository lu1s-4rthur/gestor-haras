package haras.ui;

import haras.exception.HarasException;
import haras.fachada.Fachada;
import haras.basicas.*;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import com.opencsv.exceptions.CsvValidationException;

public class SistemaHaras {
    private final Fachada fachada;
    private final Scanner scanner;

    public SistemaHaras() {
        this.fachada = new Fachada();
        this.scanner = new Scanner(System.in);
    }

    public void iniciar() throws CsvValidationException, NumberFormatException {
        MenuHaras menu = new MenuHaras(this);
        menu.exibirMenuPrincipal();
    }

    public void cadastrarAnimal() throws HarasException {
        System.out.println("=== Cadastrar Animal ===");
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
        
        Animal animal = new Animal(nome, raca, genero, cor, idade, estado);
        fachada.cadastrarAnimal(animal);
        System.out.println("Animal cadastrado com ID: " + animal.getId());
    }

    public void listarAnimais() {
        System.out.println("=== Lista de Animais ===");
        List<Animal> animais = fachada.listarAnimais();
        if (animais.isEmpty()) {
            System.out.println("Nenhum animal cadastrado.");
        } else {
            animais.forEach(System.out::println);
        }
    }

    public void editarAnimal() throws HarasException {
        System.out.println("=== Editar Animal ===");
        System.out.print("ID do animal para editar: ");
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
        
        Animal animalEdit = new Animal(eNome, eRaca, eGenero, eCor, eIdade, eSaude);
        animalEdit.setId(idEdit);
        animalEdit.setReprodutor(eReprod);
        
        fachada.editarAnimal(animalEdit);
        System.out.println("Animal editado com sucesso.");
    }

    public void excluirAnimal() {
        System.out.println("=== Excluir Animal ===");
        System.out.print("ID do animal para excluir: ");
        int id = Integer.parseInt(scanner.nextLine());
        
        boolean removido = fachada.excluirAnimal(id);
        System.out.println(removido ? "Animal excluído com sucesso." : "Animal não encontrado.");
    }

    public void cadastrarCliente() throws HarasException {
        System.out.println("=== Cadastrar Cliente ===");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Endereço: ");
        String endereco = scanner.nextLine();
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();
        
        Cliente cliente = new Cliente(nome, endereco, telefone);
        fachada.cadastrarCliente(cliente);
        System.out.println("Cliente cadastrado com ID: " + cliente.getId());
    }

    public void listarClientes() {
        System.out.println("=== Lista de Clientes ===");
        List<Cliente> clientes = fachada.listarClientes();
        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
        } else {
            clientes.forEach(System.out::println);
        }
    }

    public void excluirCliente() {
        System.out.println("=== Excluir Cliente ===");
        System.out.print("ID do cliente para excluir: ");
        int id = Integer.parseInt(scanner.nextLine());
        
        boolean removido = fachada.excluirCliente(id);
        System.out.println(removido ? "Cliente excluído com sucesso." : "Cliente não encontrado.");
    }

    public void cadastrarServico() throws HarasException {
        System.out.println("=== Cadastrar Serviço ===");
        System.out.print("Tipo: ");
        String tipo = scanner.nextLine();
        System.out.print("Valor: ");
        double valor = Double.parseDouble(scanner.nextLine());
        
        Servico servico = new Servico(tipo, valor);
        fachada.cadastrarServico(servico);
        System.out.println("Serviço cadastrado com ID: " + servico.getId());
    }

    public void listarServicos() {
        System.out.println("=== Lista de Serviços ===");
        List<Servico> servicos = fachada.listarServicos();
        if (servicos.isEmpty()) {
            System.out.println("Nenhum serviço cadastrado.");
        } else {
            servicos.forEach(System.out::println);
        }
    }

    public void editarServico() throws HarasException {
        System.out.println("=== Editar Serviço ===");
        System.out.print("ID do serviço para editar: ");
        int idEdit = Integer.parseInt(scanner.nextLine());
        System.out.print("Novo tipo: ");
        String nTipo = scanner.nextLine();
        System.out.print("Novo valor: ");
        double nValor = Double.parseDouble(scanner.nextLine());
        
        Servico servicoEdit = new Servico(nTipo, nValor);
        servicoEdit.setId(idEdit);
        
        fachada.editarServico(servicoEdit);
        System.out.println("Serviço editado com sucesso.");
    }

    public void excluirServico() {
        System.out.println("=== Excluir Serviço ===");
        System.out.print("ID do serviço para excluir: ");
        int id = Integer.parseInt(scanner.nextLine());
        
        boolean removido = fachada.excluirServico(id);
        System.out.println(removido ? "Serviço excluído com sucesso." : "Serviço não encontrado.");
    }

    public void cadastrarColaborador() throws HarasException {
        System.out.println("=== Cadastrar Colaborador ===");
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
        
        Colaborador colaborador = new Colaborador(nome, endereco, telefone, cargo, adm, formacao);
        fachada.cadastrarColaborador(colaborador);
        System.out.println("Colaborador cadastrado com ID: " + colaborador.getId());
    }

    public void listarColaboradores() {
        System.out.println("=== Lista de Colaboradores ===");
        List<Colaborador> colaboradores = fachada.listarColaboradores();
        if (colaboradores.isEmpty()) {
            System.out.println("Nenhum colaborador cadastrado.");
        } else {
            colaboradores.forEach(System.out::println);
        }
    }

    public void editarColaborador() throws HarasException {
        System.out.println("=== Editar Colaborador ===");
        System.out.print("ID do colaborador para editar: ");
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
        
        Colaborador colaboradorEdit = new Colaborador(eNome, eEndereco, eTelefone, eCargo, eAdm, eForm);
        colaboradorEdit.setId(idEdit);
        
        fachada.editarColaborador(colaboradorEdit);
        System.out.println("Colaborador editado com sucesso.");
    }

    public void excluirColaborador() {
        System.out.println("=== Excluir Colaborador ===");
        System.out.print("ID do colaborador para excluir: ");
        int id = Integer.parseInt(scanner.nextLine());
        
        boolean removido = fachada.excluirColaborador(id);
        System.out.println(removido ? "Colaborador excluído com sucesso." : "Colaborador não encontrado.");
    }

    public void cadastrarEvento() throws HarasException {
        System.out.println("=== Cadastrar Evento ===");
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
        
        Evento evento = new Evento(tipo, data, local);
        fachada.cadastrarEvento(evento);
        System.out.println("Evento cadastrado com ID: " + evento.getId());
    }

    public void listarEventos() {
        System.out.println("=== Lista de Eventos ===");
        List<Evento> eventos = fachada.listarEventos();
        if (eventos.isEmpty()) {
            System.out.println("Nenhum evento cadastrado.");
        } else {
            eventos.forEach(System.out::println);
        }
    }

    public void adicionarParticipanteEvento() throws HarasException {
        System.out.println("=== Adicionar Participante ao Evento ===");
        System.out.print("ID do evento: ");
        int evtId = Integer.parseInt(scanner.nextLine());
        Evento evento = fachada.listarEventos().stream()
            .filter(ev -> ev.getId() == evtId)
            .findFirst().orElse(null);
        
        if (evento == null) {
            System.out.println("Evento não encontrado.");
            return;
        }
        
        System.out.print("ID do animal: ");
        int anId = Integer.parseInt(scanner.nextLine());
        Animal animal = fachada.listarAnimais().stream()
            .filter(a -> a.getId() == anId)
            .findFirst().orElse(null);
        
        if (animal == null) {
            System.out.println("Animal não encontrado.");
            return;
        }
        
        fachada.adicionarParticipante(evento, animal);
        System.out.println("Participante adicionado com sucesso.");
    }

    public void cadastrarContrato() throws HarasException {
        System.out.println("=== Cadastrar Contrato ===");
        System.out.print("ID Cliente: ");
        int cId = Integer.parseInt(scanner.nextLine());
        Cliente cliente = fachada.listarClientes().stream()
            .filter(c -> c.getId() == cId)
            .findFirst().orElse(null);
        
        System.out.print("ID Animal: ");
        int aId = Integer.parseInt(scanner.nextLine());
        Animal animal = fachada.listarAnimais().stream()
            .filter(a -> a.getId() == aId)
            .findFirst().orElse(null);
        
        System.out.print("ID Serviço: ");
        int sId = Integer.parseInt(scanner.nextLine());
        Servico servico = fachada.listarServicos().stream()
            .filter(s -> s.getId() == sId)
            .findFirst().orElse(null);
        
        if (cliente == null || animal == null || servico == null) {
            System.out.println("Dados inválidos. Verifique os IDs informados.");
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
        
        Contrato contrato = new Contrato(cliente, animal, servico, dtIni, dtFim, status);
        fachada.cadastrarContrato(contrato);
        System.out.println("Contrato cadastrado com ID: " + contrato.getId());
    }

    public void listarContratos() {
        System.out.println("=== Lista de Contratos ===");
        List<Contrato> contratos = fachada.listarContratos();
        if (contratos.isEmpty()) {
            System.out.println("Nenhum contrato cadastrado.");
        } else {
            contratos.forEach(System.out::println);
        }
    }

    public void excluirContrato() {
        System.out.println("=== Excluir Contrato ===");
        System.out.print("ID do contrato para excluir: ");
        int id = Integer.parseInt(scanner.nextLine());
        
        boolean removido = fachada.excluirContrato(id);
        System.out.println(removido ? "Contrato excluído com sucesso." : "Contrato não encontrado.");
    }

    public void registrarAtendimento() throws HarasException {
        System.out.println("=== Registrar Atendimento Veterinário ===");
        System.out.print("ID Animal: ");
        int aId = Integer.parseInt(scanner.nextLine());
        Animal animal = fachada.listarAnimais().stream()
            .filter(x -> x.getId() == aId)
            .findFirst().orElse(null);
        
        System.out.print("ID Veterinário: ");
        int vId = Integer.parseInt(scanner.nextLine());
        Colaborador veterinario = fachada.listarColaboradores().stream()
            .filter(c -> c.getId() == vId && "Veterinario".equalsIgnoreCase(c.getCargo()))
            .findFirst().orElse(null);
        
        if (animal == null || veterinario == null) {
            System.out.println("Dados inválidos. Verifique os IDs informados.");
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
        
        AtendimentoVeterinario atendimento = new AtendimentoVeterinario(animal, (Veterinario) veterinario, dt, desc);
        fachada.registrarAtendimento(atendimento);
        System.out.println("Atendimento registrado com ID: " + atendimento.getId());
    }

    public void listarAtendimentos() {
        System.out.println("=== Lista de Atendimentos ===");
        List<AtendimentoVeterinario> atendimentos = fachada.listarAtendimentos();
        if (atendimentos.isEmpty()) {
            System.out.println("Nenhum atendimento registrado.");
        } else {
            atendimentos.forEach(System.out::println);
        }
    }

    public void excluirAtendimento() {
        System.out.println("=== Excluir Atendimento ===");
        System.out.print("ID do atendimento para excluir: ");
        int id = Integer.parseInt(scanner.nextLine());
        
        boolean removido = fachada.excluirAtendimento(id);
        System.out.println(removido ? "Atendimento excluído com sucesso." : "Atendimento não encontrado.");
    }

    public void registrarTreinamento() throws HarasException {
        System.out.println("=== Registrar Treinamento ===");
        System.out.print("ID Animal: ");
        int aId = Integer.parseInt(scanner.nextLine());
        Animal animal = fachada.listarAnimais().stream()
            .filter(x -> x.getId() == aId)
            .findFirst().orElse(null);
        
        System.out.print("ID Treinador: ");
        int tId = Integer.parseInt(scanner.nextLine());
        Treinador treinador = (Treinador) fachada.listarColaboradores().stream()
            .filter(c -> c.getId() == tId && c instanceof Treinador)
            .findFirst().orElse(null);
        
        if (animal == null || treinador == null) {
            System.out.println("Dados inválidos. Verifique os IDs informados.");
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
        
        Treinamento treinamento = new Treinamento(animal, treinador, dt, tipo, obs);
        fachada.registrarTreinamento(treinamento);
        System.out.println("Treinamento registrado com ID: " + treinamento.getId());
    }

    public void listarTreinamentos() {
        System.out.println("=== Lista de Treinamentos ===");
        List<Treinamento> treinamentos = fachada.listarTreinamentos();
        if (treinamentos.isEmpty()) {
            System.out.println("Nenhum treinamento registrado.");
        } else {
            treinamentos.forEach(System.out::println);
        }
    }

    public void excluirTreinamento() {
        System.out.println("=== Excluir Treinamento ===");
        System.out.print("ID do treinamento para excluir: ");
        int id = Integer.parseInt(scanner.nextLine());
        
        boolean removido = fachada.excluirTreinamento(id);
        System.out.println(removido ? "Treinamento excluído com sucesso." : "Treinamento não encontrado.");
    }

    private final java.util.Map<Integer, Baia> baias = new java.util.HashMap<>();

    private Baia localizarOuCriarBaia(int numero) {
        return baias.computeIfAbsent(numero, Baia::new);
    }

    public void criarLocalizarBaia() throws HarasException {
        System.out.println("=== Criar/Localizar Baia ===");
        System.out.print("Número da baia: ");
        int num = Integer.parseInt(scanner.nextLine());
        Baia baia = fachada.localizarOuCriarBaia(num);
        System.out.println("Baia: " + baia);
    }

    public void colocarBaiaEmManutencao() throws HarasException {
        System.out.println("=== Colocar Baia em Manutenção ===");
        System.out.print("Número da baia: ");
        int num = Integer.parseInt(scanner.nextLine());
        Baia baia = fachada.localizarOuCriarBaia(num);
        fachada.colocarBaiaEmManutencao(baia);
        System.out.println("Baia colocada em manutenção.");
    }

    public void retirarBaiaDeManutencao() throws HarasException {
        System.out.println("=== Retirar Baia de Manutenção ===");
        System.out.print("Número da baia: ");
        int num = Integer.parseInt(scanner.nextLine());
        Baia baia = fachada.localizarOuCriarBaia(num);
        fachada.retirarBaiaDeManutencao(baia);
        System.out.println("Baia retirada de manutenção.");
    }

    public void ocuparBaia() throws HarasException {
        System.out.println("=== Ocupar Baia ===");
        System.out.print("Número da baia: ");
        int num = Integer.parseInt(scanner.nextLine());
        Baia baia = fachada.localizarOuCriarBaia(num);
        
        System.out.print("ID do animal: ");
        int aId = Integer.parseInt(scanner.nextLine());
        Animal animal = fachada.listarAnimais().stream()
            .filter(x -> x.getId() == aId)
            .findFirst().orElse(null);
        
        if (animal == null) {
            System.out.println("Animal não encontrado.");
            return;
        }
        
        fachada.alocarAnimalEmBaia(baia, animal);
        System.out.println("Baia ocupada com sucesso.");
    }

    public void liberarBaia() throws HarasException {
        System.out.println("=== Liberar Baia ===");
        System.out.print("Número da baia: ");
        int num = Integer.parseInt(scanner.nextLine());
        Baia baia = localizarOuCriarBaia(num);
        fachada.liberarBaia(baia);
        System.out.println("Baia liberada com sucesso.");
    }

    public void listarBaias() {
        System.out.println("=== Lista de Baias ===");
        if (baias.isEmpty()) {
            System.out.println("Nenhuma baia cadastrada.");
        } else {
            baias.values().forEach(System.out::println);
        }
    }

    public void emitirRelatorioServicos() {
        System.out.println("=== Relatório de Serviços ===");
        System.out.println(fachada.emitirRelatorioServicos());
    }

    public void emitirRelatorioEventos() {
        System.out.println("=== Relatório de Eventos ===");
        System.out.println(fachada.emitirRelatorioEventos());
    }

    public void relatorioOcupacaoBaias() {
        System.out.println("=== Relatório de Ocupação de Baias ===");
        long ocupadas = baias.values().stream()
            .filter(b -> b.getAnimalOcupante() != null)
            .count();
        System.out.println("Baias ocupadas: " + ocupadas + "/" + baias.size());
    }

    public void relatorioAnimaisHospedados() {
        System.out.println("=== Animais Hospedados ===");
        baias.values().stream()
            .filter(b -> b.getAnimalOcupante() != null)
            .forEach(System.out::println);
    }

    public void relatorioFaturamento() {
        System.out.println("=== Faturamento Estimado ===");
        double total = fachada.listarContratos().stream()
            .mapToDouble(c -> c.getServico().getValor())
            .sum();
        System.out.println("Faturamento estimado: R$ " + total);
    }

    public void exportarCsv() throws IOException {
        System.out.println("=== Exportando dados para CSV ===");
        fachada.exportarTodosCsv();
        System.out.println("Dados exportados com sucesso para dados/data/");
    }

    public void importarCsv() throws IOException, CsvValidationException, NumberFormatException {
        System.out.println("=== Importando dados de CSV ===");
        fachada.importarTodosCsv();
        System.out.println("Dados importados com sucesso de dados/data/");
    }

    public void fechar() {
        scanner.close();
    }
}
