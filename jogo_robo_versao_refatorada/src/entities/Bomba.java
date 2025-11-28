package entities;

public class Bomba extends Obstaculo {

    public Bomba(int id, int posicaoHorizontal, int posicaoVertical) {
        super(id, posicaoHorizontal, posicaoVertical);
    }

    @Override
    public String bater(Robo r) {
        r.setAtivo(false);
        return r.getCor() + " explodiu ao bater em uma bomba!";
    }
}
