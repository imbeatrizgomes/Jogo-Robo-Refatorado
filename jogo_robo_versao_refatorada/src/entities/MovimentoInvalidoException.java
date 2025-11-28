package entities;

public class MovimentoInvalidoException extends Exception {
    public MovimentoInvalidoException(String mensagem) {
        super(mensagem);
    }

    public static MovimentoInvalidoException codigoInvalido(int codigo) {
        return new MovimentoInvalidoException("Código inválido: " + codigo);
    }

    public static MovimentoInvalidoException direcaoInvalida(String direcao) {
        return new MovimentoInvalidoException("Movimento inválido: " + direcao);
    }

    public static MovimentoInvalidoException foraDoTabuleiro(String direcao) {
        return new MovimentoInvalidoException(direcao + " (fora do tabuleiro)");
    }
}
