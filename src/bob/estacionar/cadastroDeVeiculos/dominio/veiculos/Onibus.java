package bob.estacionar.cadastroDeVeiculos.dominio.veiculos;

import bob.estacionar.cadastroDeVeiculos.dominio.TipoVeiculo;
import bob.estacionar.cadastroDeVeiculos.dominio.Veiculo;

public class Onibus extends Veiculo {
    private int qtdAssentos;

    public Onibus(String placa, int ano, TipoVeiculo tipoVeiculo) {
        super(placa, ano, tipoVeiculo);
    }

    @Override
    public void exibirDados() {
        super.exibirDados();
        System.out.println("Assentos: " + this.qtdAssentos);
    }

    public int getQtdAssentos() {
        return qtdAssentos;
    }

    public void setQtdAssentos(int qtdAssentos) {
        this.qtdAssentos = qtdAssentos;
    }
}
