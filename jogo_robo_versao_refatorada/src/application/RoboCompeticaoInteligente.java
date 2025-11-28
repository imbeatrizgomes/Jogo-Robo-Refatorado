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
        RoboInteligente intel = new RoboInteligente(sc.nextLine());

        int cx, cy;
        do {
            System.out.print("Digite a posição horizontal (X) da comida [0–3]: ");
            cx = sc.nextInt();
            System.out.print("Digite a posição vertical (Y) da comida [0–3]: ");
            cy = sc.nextInt();
        } while (!mundo.posicaoValida(cx, cy));

        mundo.setComidaX(cx);
        mundo.setComidaY(cy);

        boolean normalComeu = false;
        boolean intelComeu = false;

        while (!normalComeu || !intelComeu) {

            System.out.println(mundo.gerarVisualizacao(normal, intel));
            FuncoesDoJogo.aguarda(700);

            if (!normalComeu && normal.isAtivo()) {
                FuncoesDoJogo.moverAleatorio(normal);
                if (normal.encontrouComida(cx, cy)) {
                    normalComeu = true;
                    System.out.println(normal.getCor() + " encontrou o alimento!");
                }
            }

            if (!intelComeu && intel.isAtivo()) {
                FuncoesDoJogo.moverAleatorio(intel);
                if (intel.encontrouComida(cx, cy)) {
                    intelComeu = true;
                    System.out.println(intel.getCor() + " encontrou o alimento!");
                }
            }

            if (!normal.isAtivo() && !intel.isAtivo()) {
                System.out.println("Ambos ficaram inativos!");
                break;
            }
        }

        System.out.println(mundo.gerarVisualizacao(normal, intel));

        sc.close();
    }
}