package entities;

public final class FuncoesDoJogo {

    public static void aguarda(int tempoMs) {
        try {
            Thread.sleep(tempoMs);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void moverAleatorio(Robo robo) {
        Direcao[] direcoes = Direcao.values();
        Direcao direcao = direcoes[ RandomJogo.random.nextInt(direcoes.length) ];

        try {
            System.out.println(robo.mover(direcao));
        } catch (MovimentoInvalidoException e) {
            System.out.println(e.getMessage());
        }
    }
}
