package application;

import java.util.Scanner;

import entities.FuncoesDoJogo;
import entities.MundoRobo;
import entities.Robo;

public class RoboCompeticaoNormal {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        MundoRobo mundo = new MundoRobo();

        System.out.print("Digite a cor do robô 1: ");
        Robo r1 = new Robo(sc.nextLine());

        System.out.print("Digite a cor do robô 2: ");
        Robo r2 = new Robo(sc.nextLine());

        int cx, cy;
        do {
        	System.out.print("Digite a posição horizontal (X) da comida [0–3]: ");
            cx = sc.nextInt();
            System.out.print("Digite a posição vertical (Y) da comida [0–3]: ");
            cy = sc.nextInt();
        } while (!mundo.posicaoValida(cx, cy));

        mundo.setComidaX(cx);
        mundo.setComidaY(cy);

        while (true) {
            System.out.println(mundo.gerarVisualizacao(r1, r2));
            FuncoesDoJogo.aguarda(700);

            FuncoesDoJogo.moverAleatorio(r1);
            if (r1.encontrouComida(cx, cy)) break;

            FuncoesDoJogo.moverAleatorio(r2);
            if (r2.encontrouComida(cx, cy)) break;
        }

        System.out.println("\n=== RESULTADOS ===");
        System.out.println(r1.getCor() + " → válidos: " + r1.getMovimentosValidos() + " | inválidos: " + r1.getMovimentosInvalidos());
        System.out.println(r2.getCor() + " → válidos: " + r2.getMovimentosValidos() + " | inválidos: " + r2.getMovimentosInvalidos());

        sc.close();
    }
}