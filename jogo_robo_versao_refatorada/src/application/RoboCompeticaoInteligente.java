package application;

import java.util.Scanner;

import entities.FuncoesDoJogo;
import entities.MundoRobo;
import entities.Robo;
import entities.RoboInteligente;

public class RoboCompeticaoInteligente {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        MundoRobo mundo = new MundoRobo();

        System.out.print("Cor do robô normal: ");
        Robo normal = new Robo(sc.nextLine());

        System.out.print("Cor do robô inteligente: ");
        RoboInteligente inteligente = new RoboInteligente(sc.nextLine());

        int comHorizontal;
        int comVertical;
        do {
            System.out.print("Digite a posição horizontal X da comida [0–" + (mundo.getTamanhoTabuleiro() - 1) + "]: ");
            comHorizontal = sc.nextInt();
            System.out.print("Digite a posição vertical Y da comida [0–" + (mundo.getTamanhoTabuleiro() - 1) + "]: ");
            comVertical = sc.nextInt();
        } while (!mundo.posicaoValida(comHorizontal, comVertical));

        mundo.setComidaX(comHorizontal);
        mundo.setComidaY(comVertical);

        boolean normalComeu = false;
        boolean intelComeu = false;

        while (!normalComeu || !intelComeu) {

            System.out.println(mundo.gerarVisualizacao(normal, inteligente));
            FuncoesDoJogo.aguarda(700);

            if (!normalComeu && normal.isAtivo()) {
                normalComeu = tentarMoverEVerificar(normal, comHorizontal, comVertical);
            }

            if (!intelComeu && inteligente.isAtivo()) {
                intelComeu = tentarMoverEVerificar(inteligente, comHorizontal, comVertical);
            }

            if (normalComeu && intelComeu) {
                System.out.println("Ambos os robôs encontraram o alimento!");
                break;
            }
        }

        System.out.println(mundo.gerarVisualizacao(normal, inteligente));

        sc.close();
    }

    private static boolean tentarMoverEVerificar(Robo robo, int comidaX, int comidaY) {
        FuncoesDoJogo.moverAleatorio(robo);
        if (robo.encontrouComida(comidaX, comidaY)) {
            System.out.println(robo.getCor() + " encontrou o alimento!");
            return true;
        }
        return false;
    }

}
