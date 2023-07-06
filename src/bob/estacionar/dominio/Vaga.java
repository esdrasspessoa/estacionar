package bob.estacionar.dominio;

public class Vaga {
    private int numero;
    private boolean ocupada;
    private Veiculo veiculo;

    public Vaga(int numero) {
        this.numero = numero;
        this.ocupada = false;
        this.veiculo = null;
    }

    public int getNumero() {
        return numero;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public boolean isOcupada(){
        return ocupada;
    }

    public void estacionarVeiculo(Veiculo veiculo){
        this.veiculo = veiculo;
        this.ocupada = true;
    }

    public void liberarVaga(){
        this.veiculo = null;
        this.ocupada = false;
    }
}
