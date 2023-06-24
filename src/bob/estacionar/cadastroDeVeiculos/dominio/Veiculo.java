package bob.estacionar.cadastroDeVeiculos.dominio;

public abstract class Veiculo {
    private String placa;
    private int ano;
    private TipoVeiculo tipoVeiculo;

    public Veiculo(String placa, int ano, TipoVeiculo tipoVeiculo) {
        this.placa = placa;
        this.ano = ano;
        this.tipoVeiculo = tipoVeiculo;
    }

    public void exibirDados(){
       // System.out.println("------------------------");
        System.out.println("Tipo de Veiculo: " + this.tipoVeiculo.getDescricao());
        System.out.println("Placa: " + this.placa);
        System.out.println("Ano: " + this.ano);
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }
}
