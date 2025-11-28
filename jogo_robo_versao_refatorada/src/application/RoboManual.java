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

        int comX, comY;
        do {
            System.out.print("Digite a posição horizontal X da comida [0–" + (mundo.getTamanho() - 1) + "]: ");
            comX = sc.nextInt();
            System.out.print("Digite a posição vertical Y da comida [0–" + (mundo.getTamanho() - 1) + "]: ");
            comY = sc.nextInt();
        } while (!mundo.posicaoValida(comX, comY));

        mundo.setComidaX(comX);
        mundo.setComidaY(comY);

        while (!robo.encontrouComida(comX, comY)) {
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

            FuncoesDoJogo.aguarda(100); // pequena pausa para melhor experiência no console
        }

        System.out.println(mundo.gerarVisualizacao(robo));
        System.out.println("O robô encontrou o alimento!");
        System.out.println("\n=== RESULTADOS ===");
        System.out.println(robo.getCor() + " = válidos: " + robo.getMovimentosValidos() +
                           " | inválidos: " + robo.getMovimentosInvalidos());

        sc.close();
    }
}
