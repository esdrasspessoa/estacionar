package bob.estacionar.exception;

public class CancelarEntradaException extends Exception{
    public CancelarEntradaException(){
        super("Entrada cancelada pelo usuário!");
    }
}
