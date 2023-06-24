package bob.estacionar;

import bob.estacionar.cadastroDeVeiculos.inteface.MenuCadastroVeiculos;
import bob.estacionar.cadastroDeVeiculos.servicos.SistemaCadastroVeiculos;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SistemaCadastroVeiculos sistemaCadastroVeiculos = new SistemaCadastroVeiculos(SistemaCadastroVeiculos.LIMITE_MAXIMO_VEICULOS);

        MenuCadastroVeiculos menuCadastroVeiculos = new MenuCadastroVeiculos(sistemaCadastroVeiculos, scanner);
        menuCadastroVeiculos.executarMenu();

        scanner.close();
    }
}