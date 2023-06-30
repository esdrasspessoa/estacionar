package bob.estacionar.cadastroDeVeiculos.dominio.veiculos;


import bob.estacionar.cadastroDeVeiculos.dominio.TipoVeiculo;
import bob.estacionar.cadastroDeVeiculos.dominio.Veiculo;

public class Caminhao extends Veiculo {
    private int qtdEixos;

    public Caminhao(String placa, int ano, TipoVeiculo tipoVeiculo) {
        super(placa, ano, tipoVeiculo);
    }

    @Override
    public String exibirDados() {
        StringBuilder dados = new StringBuilder(super.exibirDados());
       dados.append("Eixos: ").append(this.qtdEixos).append("\n");
        return dados.toString();
    }

    public int getQtdEixos() {
        return qtdEixos;
    }

    public void setQtdEixos(int qtdEixos) {
        this.qtdEixos = qtdEixos;
    }
}
