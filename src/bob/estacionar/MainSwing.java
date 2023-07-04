package bob.estacionar;

import bob.estacionar.cadastroDeVeiculos.inteface.MenuCadastroVeiculosSwing;
import bob.estacionar.cadastroDeVeiculos.servicos.SistemaCadastroVeiculos;

public class MainSwing {
    public static void main(String[] args) {
        SistemaCadastroVeiculos sistemaCadastroVeiculos = new SistemaCadastroVeiculos(SistemaCadastroVeiculos.LIMITE_MAXIMO_VEICULOS);
        MenuCadastroVeiculosSwing menuCadastroVeiculosSwing = new MenuCadastroVeiculosSwing(sistemaCadastroVeiculos);
        menuCadastroVeiculosSwing.setVisible(true);
    }
}