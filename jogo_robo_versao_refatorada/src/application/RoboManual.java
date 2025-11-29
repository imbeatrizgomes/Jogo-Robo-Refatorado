package application;

import java.util.Scanner;

import entities.Direcao;
import entities.FuncoesDoJogo;
import entities.MovimentoInvalidoException;
import entities.MundoRobo;
import entities.Robo;

public class RoboManual {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        MundoRobo mundo = new MundoRobo();

        System.out.print("Digite a cor do robô: ");
        Robo robo = new Robo(sc.nextLine());

        int comHorizontal;
        int comVertical;
        do {
            System.out.print("Digite a posição horizontal X da comida [0–" + (mundo.getTAMANHO_TABULEIRO() - 1) + "]: ");
            comHorizontal = sc.nextInt();
            System.out.print("Digite a posição vertical Y da comida [0–" + (mundo.getTAMANHO_TABULEIRO() - 1) + "]: ");
            comVertical = sc.nextInt();
        } while (!mundo.posicaoValida(comHorizontal, comVertical));

        mundo.setComidaX(comHorizontal);
        mundo.setComidaY(comVertical);

        while (!robo.encontrouComida(comHorizontal, comVertical)) {
            System.out.println(mundo.gerarVisualizacao(robo));
            System.out.println("1 - Up");
            System.out.println("2 - Down");
            System.out.println("3 - Right");
            System.out.println("4 - Left");
            System.out.print("Mover: ");
            int mov = sc.nextInt();

            Direcao dir = Direcao.apartirDeNumero(mov);
            try {
                if (dir == null) {
                    throw MovimentoInvalidoException.codigoInvalido(mov);
                }
                String mensagem = robo.mover(dir);
                System.out.println(mensagem);
            } catch (MovimentoInvalidoException exception) {
                System.out.println(exception.getMessage());
            }

            FuncoesDoJogo.aguarda(100);
        }

        System.out.println(mundo.gerarVisualizacao(robo));
        System.out.println("O robô encontrou o alimento!");
        System.out.println("\n=== RESULTADOS ===");
        System.out.println(robo.getCor() + " = válidos: " + robo.getMovimentosValidos() +
                           " | inválidos: " + robo.getMovimentosInvalidos());

        sc.close();
    }
}

