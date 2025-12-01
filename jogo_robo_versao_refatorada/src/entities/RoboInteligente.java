package entities;

import java.util.Random;

public class RoboInteligente extends Robo {

    private String ultimoMovInvalido = "";

    public RoboInteligente(String cor) {
        super(cor);
    }

    @Override
    public String mover(Direcao dir) throws MovimentoInvalidoException {
        Random rand = RandomJogo.random;
        StringBuilder mensagens = new StringBuilder();

        Direcao tentativa = dir;

        while (true) { 
            try {
                if (tentativa == null) {
                    movimentosInvalidos++;
                    throw MovimentoInvalidoException.direcaoInvalida("null");
                }

                if (tentativa.toString().equalsIgnoreCase(ultimoMovInvalido)) {
                    Direcao[] vals = Direcao.values();
                    tentativa = vals[rand.nextInt(vals.length)];
                    continue;
                }

                super.mover(tentativa);
                
                ultimoMovInvalido = "";
                mensagens.append(cor)
                         .append(" (inteligente) moveu para (")
                         .append(getX()).append(", ").append(getY()).append(")\n");

                return mensagens.toString();

            } catch (MovimentoInvalidoException ex) {
            	
                ultimoMovInvalido = tentativa.toString();

                mensagens.append(cor)
                         .append(" tentou mover para '").append(tentativa)
                         .append("' e falhou: ").append(ex.getMessage()).append("\n");

                Direcao[] vals = Direcao.values();
                tentativa = vals[rand.nextInt(vals.length)];
            }
        }
    }
}
