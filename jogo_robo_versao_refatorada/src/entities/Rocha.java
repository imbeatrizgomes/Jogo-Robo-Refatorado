package entities;

public class Rocha extends Obstaculo {
    public Rocha(int id, int posicaoHorizontal, int posicaoVertical) {
        super(id, posicaoHorizontal, posicaoVertical);
    }

    @Override
    public String bater(Robo r) {
        return r.getCor() + " bateu em uma rocha e voltou para trás!";
    }
}
