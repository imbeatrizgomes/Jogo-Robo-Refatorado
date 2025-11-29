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
	
    private static final Scanner sc = new Scanner(System.in);

    private static int comHorizontal;
    private static int comVertical;

    public static void main(String[] args) {
        MundoRobo mundo = new MundoRobo();

        System.out.print("Cor do robô normal: ");
        Robo normal = new Robo(sc.nextLine());

        System.out.print("Cor do robô inteligente: ");
        RoboInteligente inteligente = new RoboInteligente(sc.nextLine());

        lerPosicaoComida(mundo);
        mundo.setComidaX(comHorizontal);
        mundo.setComidaY(comVertical);

        adicionarBombas(mundo, comHorizontal, comVertical);
        adicionarRochas(mundo, comHorizontal, comVertical);

        while (true) {
            System.out.println(mundo.gerarVisualizacao(normal, inteligente));
            FuncoesDoJogo.aguarda(700);

            if (normal.isAtivo()) {
                moverRobo(mundo, normal);

                if (normal.encontrouComida(comHorizontal, comVertical)) {
                    System.out.println(mundo.gerarVisualizacao(normal, inteligente));
                    System.out.println(normal.getCor() + " encontrou o alimento!");
                    break;
                }

                if (!normal.isAtivo() && !inteligente.isAtivo()) {
                    System.out.println(mundo.gerarVisualizacao(normal, inteligente));
                    System.out.println("Todos os robô explodiram nas bombas!");
                    break;
                }
            }

            if (inteligente.isAtivo()) {
                moverRobo(mundo, inteligente);

                if (inteligente.encontrouComida(comHorizontal, comVertical)) {
                    System.out.println(mundo.gerarVisualizacao(normal, inteligente));
                    System.out.println(inteligente.getCor() + " encontrou o alimento!");
                    break;
                }

                if (!normal.isAtivo() && !inteligente.isAtivo()) {
                    System.out.println(mundo.gerarVisualizacao(normal, inteligente));
                    System.out.println("Todos os robôs explodiram nas bombas!");
                    break;
                }
            }
        }

        System.out.println(mundo.gerarVisualizacao(normal, inteligente));

        System.out.println("\n=== RESULTADOS ===");
        System.out.println(normal.getCor() + " = válidos: " 
                           + normal.getMovimentosValidos()
                           + " | inválidos: " 
                           + normal.getMovimentosInvalidos());
        System.out.println(inteligente.getCor() + " = válidos: " 
                           + inteligente.getMovimentosValidos()
                           + " | inválidos: " 
                           + inteligente.getMovimentosInvalidos());

        sc.close();
    }

    private static void lerPosicaoComida(MundoRobo mundo) {
        int x;
        int y;
        do {
            System.out.print("Digite a posição horizontal X da comida [0–" + (mundo.getTamanhoTabuleiro() - 1) + "]: ");
            x = sc.nextInt();
            System.out.print("Digite a posição vertical Y da comida [0–" + (mundo.getTamanhoTabuleiro() - 1) + "]: ");
            y = sc.nextInt();
        } while (!mundo.posicaoValida(x, y));
        sc.nextLine();
        comHorizontal = x;
        comVertical = y;
    }

    private static void adicionarBombas(MundoRobo mundo, int comidaX, int comidaY) {
        System.out.print("Quantas bombas deseja? ");
        int qtd = sc.nextInt();
        sc.nextLine();
        for (int i = 0; i < qtd; i++) {
            int bombaHorizontal;
            int bombaVertical;
            do {
                System.out.println("Bomba " + (i + 1));
                System.out.print("Digite a posição X [0–3]: ");
                bombaHorizontal = sc.nextInt();
                System.out.print("Digite a posição Y [0–3]: ");
                bombaVertical = sc.nextInt();
                sc.nextLine(); 
            } while (!mundo.posicaoValida(bombaHorizontal, bombaVertical) || (bombaHorizontal == comidaX && bombaVertical == comidaY));
            mundo.addObstaculo(new Bomba(i, bombaHorizontal, bombaVertical));
        }
    }

    private static void adicionarRochas(MundoRobo mundo, int comidaX, int comidaY) {
        System.out.print("Quantas rochas deseja? ");
        int qtd = sc.nextInt();
        sc.nextLine(); 
        for (int i = 0; i < qtd; i++) {
            int rochaHorizontal;
            int rochaVertical;
            do {
                System.out.println("Rocha " + (i + 1));
                System.out.print("Digite a posição X [0–3]: ");
                rochaHorizontal = sc.nextInt();
                System.out.print("Digite a posição Y [0–3]: ");
                rochaVertical = sc.nextInt();
                sc.nextLine();
            } while (!mundo.posicaoValida(rochaHorizontal, rochaVertical) || (rochaHorizontal == comidaX && rochaVertical == comidaY));
            mundo.addObstaculo(new Rocha(i, rochaHorizontal, rochaVertical));
        }
    }

    private static void moverRobo(MundoRobo mundo, Robo robo) {
        if (!robo.isAtivo()) {
        	return;
        }

        FuncoesDoJogo.moverAleatorio(robo);

        Obstaculo obs = mundo.getObstaculo(robo.getX(), robo.getY());
        if (obs != null) {
            System.out.println(obs.bater(robo));
            if (obs instanceof Bomba) mundo.removerObstaculo(obs);
        }
    }
}
