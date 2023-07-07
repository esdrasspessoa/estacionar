package bob.estacionar;

import bob.estacionar.menu.MenuCadastroVeiculosSwing;
import bob.estacionar.servicos.SistemaCadastroVeiculos;

public class Main {
    public static void main(String[] args) {
        SistemaCadastroVeiculos sistemaCadastroVeiculos = new SistemaCadastroVeiculos(SistemaCadastroVeiculos.LIMITE_MAXIMO_VEICULOS);
        MenuCadastroVeiculosSwing menuCadastroVeiculosSwing = new MenuCadastroVeiculosSwing(sistemaCadastroVeiculos);
        menuCadastroVeiculosSwing.setVisible(true);
    }
}