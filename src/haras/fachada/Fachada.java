package haras.fachada;

import java.io.IOException;
import java.util.List;
import java.util.function.Function;

import com.opencsv.exceptions.CsvValidationException;

import haras.basicas.*;
import haras.exception.HarasException;
import haras.negocio.*;

public class Fachada {
    private GerenciadorAnimal gerenciadorAnimal;
    private GerenciadorCliente gerenciadorCliente;
    private GerenciadorServico gerenciadorServico;
    private GerenciadorColaborador gerenciadorColaborador;
    private GerenciadorContrato gerenciadorContrato;
    private GerenciadorEvento gerenciadorEvento;
    private GerenciadorAtendimentoVeterinario gerenciadorAtendimento;
    private GerenciadorTreinamento gerenciadorTreinamento;
    private GerenciadorBaia gerenciadorBaia;

    public Fachada() {
        gerenciadorAnimal = new GerenciadorAnimal();
        gerenciadorCliente = new GerenciadorCliente();
        gerenciadorServico = new GerenciadorServico();
        gerenciadorColaborador = new GerenciadorColaborador();
        gerenciadorContrato = new GerenciadorContrato();
        gerenciadorEvento = new GerenciadorEvento();
        gerenciadorAtendimento = new GerenciadorAtendimentoVeterinario();
        gerenciadorTreinamento = new GerenciadorTreinamento();
        gerenciadorBaia = new GerenciadorBaia();
        new GerenciadorReproducao();
    }

    public void cadastrarAnimal(Animal animal) throws HarasException {
        gerenciadorAnimal.cadastrarAnimal(animal);
    }
    
    public void editarAnimal(Animal animal) throws HarasException {
        gerenciadorAnimal.editarAnimal(animal);
    }
    
    public boolean excluirAnimal(int id) {
        return gerenciadorAnimal.excluirAnimal(id);
    }
    
    public List<Animal> listarAnimais() {
        return gerenciadorAnimal.listarAnimais();
    }

    public void cadastrarCliente(Cliente cliente) throws HarasException {
        gerenciadorCliente.cadastrarCliente(cliente);
    }
    
    public void editarCliente(Cliente cliente) throws HarasException {
        gerenciadorCliente.editarCliente(cliente);
    }
    
    public boolean excluirCliente(int id) {
        return gerenciadorCliente.excluirCliente(id);
    }
    
    public List<Cliente> listarClientes() {
        return gerenciadorCliente.listarClientes();
    }
    public void cadastrarServico(Servico servico) throws HarasException {
        gerenciadorServico.cadastrarServico(servico);
    }
    
    public void editarServico(Servico servico) throws HarasException {
        gerenciadorServico.editarServico(servico);
    }
    
    public boolean excluirServico(int id) {
        return gerenciadorServico.excluirServico(id);
    }
    
    public List<Servico> listarServicos() {
        return gerenciadorServico.listarServicos();
    }
    
    public String emitirRelatorioServicos() {
        return gerenciadorServico.emitirRelatorioServicos();
    }

    public void cadastrarColaborador(Colaborador colaborador) throws HarasException {
        gerenciadorColaborador.cadastrarColaborador(colaborador);
    }
    
    public void editarColaborador(Colaborador colaborador) throws HarasException {
        gerenciadorColaborador.editarColaborador(colaborador);
    }
    
    public boolean excluirColaborador(int id) {
        return gerenciadorColaborador.excluirColaborador(id);
    }
    
    public List<Colaborador> listarColaboradores() {
        return gerenciadorColaborador.listarColaboradores();
    }

    public void cadastrarContrato(Contrato contrato) throws HarasException {
        gerenciadorContrato.cadastrarContrato(contrato);
    }
    
    public void editarContrato(Contrato contrato) throws HarasException {
        gerenciadorContrato.editarContrato(contrato);
    }
    
    public boolean excluirContrato(int id) {
        return gerenciadorContrato.excluirContrato(id);
    }
    
    public List<Contrato> listarContratos() {
        return gerenciadorContrato.listarContratos();
    }

    public void cadastrarEvento(Evento evento) throws HarasException {
        gerenciadorEvento.cadastrarEvento(evento);
    }
    
    public List<Evento> listarEventos() {
        return gerenciadorEvento.listarEventos();
    }
    
    public void adicionarParticipante(Evento evento, Animal animal) throws HarasException {
        gerenciadorEvento.adicionarParticipante(evento, animal);
    }
    
    public String emitirRelatorioEventos() {
        return gerenciadorEvento.emitirRelatorioEventos();
    }

    public void registrarAtendimento(AtendimentoVeterinario atendimento) throws HarasException {
        gerenciadorAtendimento.registrarAtendimento(atendimento);
    }
    
    public List<AtendimentoVeterinario> listarAtendimentos() {
        return gerenciadorAtendimento.listarAtendimentos();
    }
    
    public boolean excluirAtendimento(int id) {
        return gerenciadorAtendimento.excluirAtendimento(id);
    }

    public void registrarTreinamento(Treinamento treinamento) throws HarasException {
        gerenciadorTreinamento.registrarTreinamento(treinamento);
    }
    
    public List<Treinamento> listarTreinamentos() {
        return gerenciadorTreinamento.listarTreinamentos();
    }
    
    public boolean excluirTreinamento(int id) {
        return gerenciadorTreinamento.excluirTreinamento(id);
    }

    public Baia localizarOuCriarBaia(int numero) throws HarasException {
        return gerenciadorBaia.localizarOuCriarBaia(numero);
    }

    public void alocarAnimalEmBaia(Baia baia, Animal animal) throws HarasException {
        gerenciadorBaia.alocarAnimalEmBaia(baia, animal);
    }
    
    public void liberarBaia(Baia baia) throws HarasException {
        gerenciadorBaia.liberarBaia(baia);
    }
    
    public void colocarBaiaEmManutencao(Baia baia) throws HarasException {
        gerenciadorBaia.colocarBaiaEmManutencao(baia);
    }
    
    public void retirarBaiaDeManutencao(Baia baia) throws HarasException {
        gerenciadorBaia.retirarBaiaDeManutencao(baia);
    }

    public List<Baia> listarBaias() {
        return gerenciadorBaia.listarBaias();
    }

    public void exportarTodosCsv() throws IOException {
        gerenciadorAnimal.exportarCsv();
        gerenciadorCliente.exportarCsv();
        gerenciadorServico.exportarCsv();
        gerenciadorColaborador.exportarCsv();
        gerenciadorEvento.exportarCsv();
        gerenciadorAtendimento.exportarCsv();
        gerenciadorTreinamento.exportarCsv();
    }

    public void importarTodosCsv() throws IOException, CsvValidationException, NumberFormatException {
        gerenciadorAnimal.importarCsv();
        gerenciadorCliente.importarCsv();
        gerenciadorServico.importarCsv();
        gerenciadorColaborador.importarCsv();

        int maxAnimalId = 0;
        for (Animal animal : listarAnimais()) {
            if (animal.getId() > maxAnimalId) {
                maxAnimalId = animal.getId();
            }
        }
        Animal.ajustarContadorAPartirDoMaximo(maxAnimalId);

        int maxClienteId = 0;
        for (Cliente cliente : listarClientes()) {
            if (cliente.getId() > maxClienteId) {
                maxClienteId = cliente.getId();
            }
        }
        Cliente.ajustarContadorAPartirDoMaximo(maxClienteId);

        int maxServicoId = 0;
        for (Servico servico : listarServicos()) {
            if (servico.getId() > maxServicoId) {
                maxServicoId = servico.getId();
            }
        }
        Servico.ajustarContadorAPartirDoMaximo(maxServicoId);

        int maxColaboradorId = 0;
        for (Colaborador colaborador : listarColaboradores()) {
            if (colaborador.getId() > maxColaboradorId) {
                maxColaboradorId = colaborador.getId();
            }
        }
        Colaborador.ajustarContadorAPartirDoMaximo(maxColaboradorId);
        Function<Integer, Animal> resolveAnimal = new Function<Integer, Animal>() {
            @Override
            public Animal apply(Integer id) {
                for (Animal animal : listarAnimais()) {
                    if (animal.getId() == id) {
                        return animal;
                    }
                }
                return null;
            }
        };

        Function<Integer, Cliente> resolveCliente = new Function<Integer, Cliente>() {
            @Override
            public Cliente apply(Integer id) {
                for (Cliente cliente : listarClientes()) {
                    if (cliente.getId() == id) {
                        return cliente;
                    }
                }
                return null;
            }
        };

        Function<Integer, Servico> resolveServico = new Function<Integer, Servico>() {
            @Override
            public Servico apply(Integer id) {
                for (Servico servico : listarServicos()) {
                    if (servico.getId() == id) {
                        return servico;
                    }
                }
                return null;
            }
        };

        gerenciadorEvento.importarCsv(resolveAnimal);
        gerenciadorContrato.importarCsv(resolveCliente, resolveAnimal, resolveServico);
        
        gerenciadorAtendimento.importarCsv(resolveAnimal, new Function<Integer, Veterinario>() {
            @Override
            public Veterinario apply(Integer id) {
                for (Colaborador colaborador : listarColaboradores()) {
                    if (colaborador.getId() == id && colaborador instanceof Veterinario) {
                        return (Veterinario) colaborador;
                    }
                }
                return null;
            }
        });
        
        gerenciadorTreinamento.importarCsv(resolveAnimal, new Function<Integer, Treinador>() {
            @Override
            public Treinador apply(Integer id) {
                for (Colaborador colaborador : listarColaboradores()) {
                    if (colaborador.getId() == id && colaborador instanceof Treinador) {
                        return (Treinador) colaborador;
                    }
                }
                return null;
            }
        });
    }
}


 