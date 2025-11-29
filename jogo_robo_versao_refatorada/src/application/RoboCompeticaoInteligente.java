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

        int comHorizontal; 
        int comVertical;
        do {
        	System.out.print("Digite a posição horizontal X da comida [0–" + (mundo.getTamanhodotabuleiro() - 1) + "]: ");
            comHorizontal = sc.nextInt();
            System.out.print("Digite a posição vertical Y da comida [0–" + (mundo.getTamanhodotabuleiro() - 1) + "]: ");
            comVertical = sc.nextInt();
        } while (!mundo.posicaoValida(comHorizontal, comVertical));

        mundo.setComidaX(comHorizontal);
        mundo.setComidaY(comVertical);

        boolean normalComeu = false;
        boolean intelComeu = false;

        while (!normalComeu || !intelComeu) {

            System.out.println(mundo.gerarVisualizacao(normal, intel));
            FuncoesDoJogo.aguarda(700);

            if (!normalComeu && normal.isAtivo()) {
                FuncoesDoJogo.moverAleatorio(normal);
                if (normal.encontrouComida(comHorizontal, comVertical)) {
                    normalComeu = true;
                    System.out.println(normal.getCor() + " encontrou o alimento!");
                }
            }

            if (!intelComeu && intel.isAtivo()) {
                FuncoesDoJogo.moverAleatorio(intel);
                if (intel.encontrouComida(comHorizontal, comVertical)) {
                    intelComeu = true;
                    System.out.println(intel.getCor() + " encontrou o alimento!");
                }
            }

            if (normalComeu && intelComeu) {
                System.out.println("Ambos os robôs encontraram o alimento!");
            }
        }

        System.out.println(mundo.gerarVisualizacao(normal, intel));

        sc.close();
    }
}

