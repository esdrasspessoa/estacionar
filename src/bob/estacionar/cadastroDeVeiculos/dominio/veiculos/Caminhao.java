package bob.estacionar.cadastroDeVeiculos.dominio.veiculos;


import bob.estacionar.cadastroDeVeiculos.dominio.TipoVeiculo;
import bob.estacionar.cadastroDeVeiculos.dominio.Veiculo;

public class Caminhao extends Veiculo {
    private int qtdEixos;

    public Caminhao(String placa, int ano, TipoVeiculo tipoVeiculo) {
        super(placa, ano, tipoVeiculo);
    }

    @Override
    public void exibirDados() {
        super.exibirDados();
        System.out.println("Eixos: " + this.qtdEixos);
    }

    public int getQtdEixos() {
        return qtdEixos;
    }

    public void setQtdEixos(int qtdEixos) {
        this.qtdEixos = qtdEixos;
    }
}
