package bob.estacionar.cadastroDeVeiculos.inteface;

import bob.estacionar.cadastroDeVeiculos.dominio.TipoVeiculo;
import bob.estacionar.cadastroDeVeiculos.dominio.Veiculo;
import bob.estacionar.cadastroDeVeiculos.dominio.veiculos.Caminhao;
import bob.estacionar.cadastroDeVeiculos.dominio.veiculos.Onibus;
import bob.estacionar.cadastroDeVeiculos.servicos.SistemaCadastroVeiculos;


import java.util.Scanner;

public class MenuCadastroVeiculos {
    private SistemaCadastroVeiculos sistemaCadastro;
    private Scanner scanner;

    public MenuCadastroVeiculos(SistemaCadastroVeiculos sistemaCadastro, Scanner scanner) {
        this.sistemaCadastro = sistemaCadastro;
        this.scanner = scanner;
    }

    public void executarMenu() {
        int opcao = 0;
        boolean continuar = true;


        do {
            exibirOpcoes();
            System.out.println("Escolha uma opção: ");
            String entrada = scanner.nextLine();

            try {
                opcao = Integer.parseInt(entrada);

                switch (opcao) {
                    case 1:
                        cadastrarVeiculo();
                        break;
                    case 2:
                        removerVeiculo();
                        break;
                    case 3:
                        exibirVeiculos();
                        break;
                    case 0:
                        System.out.println("Saindo do programa...");
                        continuar = false;
                        break;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                        break;
                }
                System.out.println();

            } catch (NumberFormatException e) {
                System.out.println("Opção invalida, digite um numero inteiro");
            }

        } while (continuar);
    }

    private void exibirOpcoes() {
        System.out.println("===== MENU =====");
        System.out.println("1. Cadastrar veículo");
        System.out.println("2. Remover veículo");
        System.out.println("3. Exibir veículos cadastrados");
        System.out.println("0. Sair");
    }

    private void cadastrarVeiculo() {
        System.out.println("====== CADASTRO DE VEICULO ======");
        System.out.println("Informe a placa do veículo");
        String placa = scanner.next();
        System.out.println("Informe o ano do veículo: ");
        int ano = scanner.nextInt();

        System.out.println("Selecione o tipo do veículo: ");
        int index = 1;
        for (TipoVeiculo tipoVeiculo : TipoVeiculo.values()) {
            System.out.println(index + ". " + tipoVeiculo.getDescricao());
            index++;
        }

        TipoVeiculo tipoVeiculo = null;

        while (tipoVeiculo == null) {
            System.out.println("Escolha uma opção: ");
            int opcaoTipo = scanner.nextInt();

            switch (opcaoTipo) {
                case 1:
                    tipoVeiculo = TipoVeiculo.ONIBUS;
                    break;
                case 2:
                    tipoVeiculo = TipoVeiculo.CAMINHAO;
                    break;
                default:
                    System.out.println("Opcão invalida. Por favor, escolha uma opção válida!");
                    break;
            }
        }

        Veiculo veiculo;

        if (tipoVeiculo == TipoVeiculo.ONIBUS) {
            System.out.println("Informe a quantidade de assentos: ");
            int qtdAssentos = scanner.nextInt();
            veiculo = new Onibus(placa, ano, tipoVeiculo);
            ((Onibus) veiculo).setQtdAssentos(qtdAssentos);
        } else {
            System.out.println("Informe a quantidade de eixos: ");
            int qtdEixos = scanner.nextInt();
            veiculo = new Caminhao(placa, ano, tipoVeiculo);
            ((Caminhao) veiculo).setQtdEixos(qtdEixos);
        }

        sistemaCadastro.cadastrarVeiculo(veiculo);
        System.out.println("Veiculo cadastro com sucesso!");
    }

    private void removerVeiculo() {
        System.out.println("====== REMOVER VEÍCULO ======");
        System.out.println("Informe a placa do veículo a ser removido: ");
        String placa = scanner.next();

        sistemaCadastro.removerVeiculo(placa);
    }

    private void exibirVeiculos() {
        System.out.println("====== VEÍCULOS CADASTRADOS ======");
        if (sistemaCadastro.getQtdVeiculos() == 0) {
            System.out.println("Não há veículos cadastrados.");
        } else {
            for (int i = 0; i < sistemaCadastro.getQtdVeiculos(); i++) {
                Veiculo veiculo = sistemaCadastro.getVeiculos()[i];
                veiculo.exibirDados();
                System.out.println("----------------------------");
            }
        }
    }

}
