package haras.ui;

import haras.exception.HarasException;

import java.io.IOException;
import java.util.Scanner;

import com.opencsv.exceptions.CsvValidationException;

public class MenuHaras {
    private final SistemaHaras sistema;
    private final Scanner scanner;

    public MenuHaras(SistemaHaras sistema) {
        this.sistema = sistema;
        this.scanner = new Scanner(System.in);
    }

    public void exibirMenuPrincipal() throws CsvValidationException, NumberFormatException {
        while (true) {
            System.out.println("\n=== SISTEMA DE GESTÃO DE HARAS ===");
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
            System.out.println("11. Exportar dados para CSV");
            System.out.println("12. Importar dados de CSV");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            
            String escolha = scanner.nextLine();
            
            try {
                switch (escolha) {
                    case "1":
                        menuAnimais();
                        break;
                    case "2":
                        menuClientes();
                        break;
                    case "3":
                        menuServicos();
                        break;
                    case "4":
                        menuColaboradores();
                        break;
                    case "5":
                        menuEventos();
                        break;
                    case "6":
                        menuContratos();
                        break;
                    case "7":
                        menuAtendimentos();
                        break;
                    case "8":
                        menuTreinamentos();
                        break;
                    case "9":
                        menuBaias();
                        break;
                    case "10":
                        menuRelatorios();
                        break;
                    case "11":
                        sistema.exportarCsv();
                        break;
                    case "12":
                        sistema.importarCsv();
                        break;
                    case "0":
                        System.out.println("Saindo do sistema...");
                        sistema.fechar();
                        return;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                }
            } catch (IOException e) {
                System.err.println("Erro de IO: " + e.getMessage());
            } catch (HarasException e) {
                System.err.println("Erro: " + e.getMessage());
            } catch (Exception e) {
                System.err.println("Erro inesperado: " + e.getMessage());
            }
        }
    }

    private void menuAnimais() throws HarasException {
        while (true) {
            System.out.println("\n=== GERENCIAR ANIMAIS ===");
            System.out.println("1. Cadastrar Animal");
            System.out.println("2. Listar Animais");
            System.out.println("3. Editar Animal");
            System.out.println("4. Excluir Animal");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            
            String opcao = scanner.nextLine();
            
            switch (opcao) {
                case "1":
                    sistema.cadastrarAnimal();
                    break;
                case "2":
                    sistema.listarAnimais();
                    break;
                case "3":
                    sistema.editarAnimal();
                    break;
                case "4":
                    sistema.excluirAnimal();
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private void menuClientes() throws HarasException {
        while (true) {
            System.out.println("\n=== GERENCIAR CLIENTES ===");
            System.out.println("1. Cadastrar Cliente");
            System.out.println("2. Listar Clientes");
            System.out.println("3. Excluir Cliente");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            
            String opcao = scanner.nextLine();
            
            switch (opcao) {
                case "1":
                    sistema.cadastrarCliente();
                    break;
                case "2":
                    sistema.listarClientes();
                    break;
                case "3":
                    sistema.excluirCliente();
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private void menuServicos() throws HarasException {
        while (true) {
            System.out.println("\n=== GERENCIAR SERVIÇOS ===");
            System.out.println("1. Cadastrar Serviço");
            System.out.println("2. Listar Serviços");
            System.out.println("3. Editar Serviço");
            System.out.println("4. Excluir Serviço");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            
            String opcao = scanner.nextLine();
            
            switch (opcao) {
                case "1":
                    sistema.cadastrarServico();
                    break;
                case "2":
                    sistema.listarServicos();
                    break;
                case "3":
                    sistema.editarServico();
                    break;
                case "4":
                    sistema.excluirServico();
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private void menuColaboradores() throws HarasException {
        while (true) {
            System.out.println("\n=== GERENCIAR COLABORADORES ===");
            System.out.println("1. Cadastrar Colaborador");
            System.out.println("2. Listar Colaboradores");
            System.out.println("3. Editar Colaborador");
            System.out.println("4. Excluir Colaborador");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            
            String opcao = scanner.nextLine();
            
            switch (opcao) {
                case "1":
                    sistema.cadastrarColaborador();
                    break;
                case "2":
                    sistema.listarColaboradores();
                    break;
                case "3":
                    sistema.editarColaborador();
                    break;
                case "4":
                    sistema.excluirColaborador();
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private void menuEventos() throws HarasException {
        while (true) {
            System.out.println("\n=== GERENCIAR EVENTOS ===");
            System.out.println("1. Cadastrar Evento");
            System.out.println("2. Listar Eventos");
            System.out.println("3. Adicionar Participante");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            
            String opcao = scanner.nextLine();
            
            switch (opcao) {
                case "1":
                    sistema.cadastrarEvento();
                    break;
                case "2":
                    sistema.listarEventos();
                    break;
                case "3":
                    sistema.adicionarParticipanteEvento();
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private void menuContratos() throws HarasException {
        while (true) {
            System.out.println("\n=== GERENCIAR CONTRATOS ===");
            System.out.println("1. Cadastrar Contrato");
            System.out.println("2. Listar Contratos");
            System.out.println("3. Excluir Contrato");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            
            String opcao = scanner.nextLine();
            
            switch (opcao) {
                case "1":
                    sistema.cadastrarContrato();
                    break;
                case "2":
                    sistema.listarContratos();
                    break;
                case "3":
                    sistema.excluirContrato();
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private void menuAtendimentos() throws HarasException {
        while (true) {
            System.out.println("\n=== GERENCIAR ATENDIMENTOS ===");
            System.out.println("1. Registrar Atendimento");
            System.out.println("2. Listar Atendimentos");
            System.out.println("3. Excluir Atendimento");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            
            String opcao = scanner.nextLine();
            
            switch (opcao) {
                case "1":
                    sistema.registrarAtendimento();
                    break;
                case "2":
                    sistema.listarAtendimentos();
                    break;
                case "3":
                    sistema.excluirAtendimento();
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private void menuTreinamentos() throws HarasException {
        while (true) {
            System.out.println("\n=== GERENCIAR TREINAMENTOS ===");
            System.out.println("1. Registrar Treinamento");
            System.out.println("2. Listar Treinamentos");
            System.out.println("3. Excluir Treinamento");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            
            String opcao = scanner.nextLine();
            
            switch (opcao) {
                case "1":
                    sistema.registrarTreinamento();
                    break;
                case "2":
                    sistema.listarTreinamentos();
                    break;
                case "3":
                    sistema.excluirTreinamento();
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private void menuBaias() throws HarasException {
        while (true) {
            System.out.println("\n=== GERENCIAR BAIAS ===");
            System.out.println("1. Criar/Localizar Baia");
            System.out.println("2. Colocar em Manutenção");
            System.out.println("3. Retirar de Manutenção");
            System.out.println("4. Ocupar Baia");
            System.out.println("5. Liberar Baia");
            System.out.println("6. Listar Baias");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            
            String opcao = scanner.nextLine();
            
            switch (opcao) {
                case "1":
                    sistema.criarLocalizarBaia();
                    break;
                case "2":
                    sistema.colocarBaiaEmManutencao();
                    break;
                case "3":
                    sistema.retirarBaiaDeManutencao();
                    break;
                case "4":
                    sistema.ocuparBaia();
                    break;
                case "5":
                    sistema.liberarBaia();
                    break;
                case "6":
                    sistema.listarBaias();
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private void menuRelatorios() {
        while (true) {
            System.out.println("\n=== RELATÓRIOS ===");
            System.out.println("1. Relatório de Serviços");
            System.out.println("2. Relatório de Eventos");
            System.out.println("3. Ocupação de Baias");
            System.out.println("4. Animais Hospedados");
            System.out.println("5. Faturamento Estimado");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            
            String opcao = scanner.nextLine();
            
            switch (opcao) {
                case "1":
                    sistema.emitirRelatorioServicos();
                    break;
                case "2":
                    sistema.emitirRelatorioEventos();
                    break;
                case "3":
                    sistema.relatorioOcupacaoBaias();
                    break;
                case "4":
                    sistema.relatorioAnimaisHospedados();
                    break;
                case "5":
                    sistema.relatorioFaturamento();
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }
}
