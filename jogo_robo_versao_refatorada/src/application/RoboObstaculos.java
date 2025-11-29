package application;

import java.util.Scanner;

import entities.Bomba;
import entities.FuncoesDoJogo;
import entities.MundoRobo;
import entities.Obstaculo;
import entities.Robo;
import entities.RoboInteligente;
import entities.Rocha;

public class RoboObstaculos {
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
        	System.out.print("Digite a posição horizontal X da comida [0–" + (mundo.getTamanhoTabuleiro() - 1) + "]: ");
            comHorizontal = sc.nextInt();
            System.out.print("Digite a posição vertical Y da comida [0–" + (mundo.getTamanhoTabuleiro() - 1) + "]: ");
            comVertical = sc.nextInt();
        } while (!mundo.posicaoValida(comHorizontal, comVertical));

        mundo.setComidaX(comHorizontal);
        mundo.setComidaY(comVertical);
        
        System.out.print("Quantas bombas deseja? ");
        int qtdBombas = sc.nextInt();
        for (int i = 0; i < qtdBombas; i++) {
            int bombaHorizontal;
            int bombaVertical;
            do {
                System.out.println("Bomba " + (i + 1));
                System.out.print("Digite a posição X [0–3]: ");
                bombaHorizontal = sc.nextInt();
                System.out.print("Digite a posição Y [0–3]: ");
                bombaVertical = sc.nextInt();
            } while (!mundo.posicaoValida(bombaHorizontal, bombaVertical) || (bombaHorizontal == comHorizontal && bombaVertical == comVertical));
            mundo.addObstaculo(new Bomba(i, bombaHorizontal, bombaVertical));
        }

        System.out.print("Quantas rochas deseja? ");
        int qtdRochas = sc.nextInt();
        for (int i = 0; i < qtdRochas; i++) {
            int rochaHorizontal;
            int rochaVertical;
            do {
                System.out.println("Rocha " + (i + 1));
                System.out.print("Digite a posição X [0–3]: ");
                rochaHorizontal = sc.nextInt();
                System.out.print("Digite a posição Y [0–3]: ");
                rochaVertical = sc.nextInt();
            } while (!mundo.posicaoValida(rochaHorizontal, rochaVertical) || (rochaHorizontal == comHorizontal && rochaVertical == comVertical));
            mundo.addObstaculo(new Rocha(i, rochaHorizontal, rochaVertical));
        }

        while (true) {
            System.out.println(mundo.gerarVisualizacao(normal, intel));
            FuncoesDoJogo.aguarda(700);

            if (normal.isAtivo()) {
                moverRobo(mundo, normal);

                if (normal.encontrouComida(comHorizontal, comVertical)) {
                    System.out.println(mundo.gerarVisualizacao(normal, intel));
                    System.out.println(normal.getCor() + " encontrou o alimento!");
                    break;
                }

                if (!normal.isAtivo() && !intel.isAtivo()) {
                    System.out.println(mundo.gerarVisualizacao(normal, intel));
                    System.out.println("Todos os robô explodiram nas bombas!");
                    break;
                }
            }

            if (intel.isAtivo()) {
                moverRobo(mundo, intel);
                
                if (intel.encontrouComida(comHorizontal, comVertical)) {
                    System.out.println(mundo.gerarVisualizacao(normal, intel));
                    System.out.println(intel.getCor() + " encontrou o alimento!");
                    break;
                }

                if (!normal.isAtivo() && !intel.isAtivo()) {
                    System.out.println(mundo.gerarVisualizacao(normal, intel));
                    System.out.println("Todos os robôs explodiram nas bombas!");
                    break;
                }
            }

        }

        System.out.println(mundo.gerarVisualizacao(normal, intel));
        sc.close();
    }

    private static void moverRobo(MundoRobo mundo, Robo robo) {
        if (!robo.isAtivo()) return;

        FuncoesDoJogo.moverAleatorio(robo);

        Obstaculo obs = mundo.getObstaculo(robo.getX(), robo.getY());
        if (obs != null) {
            System.out.println(obs.bater(robo));
            if (obs instanceof Bomba) mundo.removerObstaculo(obs);
        }
    }
}



