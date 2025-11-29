package entities;

public class MundoRobo {
    private static final int TAMANHO_TABULEIRO = DimensaoTabuleiro.SIZE;
    private int comidaX;
    private int comidaY;
    private Obstaculo[][] obstaculos = new Obstaculo[TAMANHO_TABULEIRO][TAMANHO_TABULEIRO];

    public boolean posicaoValida(int x, int y) {
        return x >= 0 && x < TAMANHO_TABULEIRO && y >= 0 && y < TAMANHO_TABULEIRO;
    }

    public void addObstaculo(Obstaculo obstaculo) {
        int x = obstaculo.getX();
        int y = obstaculo.getY();
        if (posicaoValida(x, y)) {
            obstaculos[x][y] = obstaculo;
        }
    }

    public void removerObstaculo(Obstaculo obstaculo) {
        int x = obstaculo.getX();
        int y = obstaculo.getY();
        if (posicaoValida(x, y)) {
            obstaculos[x][y] = null;
        }
    }

    public Obstaculo getObstaculo(int x, int y) {
        if (!posicaoValida(x, y)) return null;
        return obstaculos[x][y];
    }

    public String gerarVisualizacao(Robo robo) {
        StringBuilder sb = new StringBuilder("\n");

        for (int y = TAMANHO_TABULEIRO - 1; y >= 0; y--) {
            for (int x = 0; x < TAMANHO_TABULEIRO; x++) {
                sb.append(addSimboloUmRobo(x, y, robo));
            }
            sb.append("\n");
        }

        sb.append("\n");
        return sb.toString();
    }

    public String gerarVisualizacao(Robo robo1, Robo robo2) {
        StringBuilder sb = new StringBuilder("\n");

        for (int y = TAMANHO_TABULEIRO - 1; y >= 0; y--) {
            for (int x = 0; x < TAMANHO_TABULEIRO; x++) {
                sb.append(addSimboloDoisRobos(x, y, robo1, robo2));
            }
            sb.append("\n");
        }

        sb.append("\n");
        return sb.toString();
    }

    private String addSimboloUmRobo(int x, int y, Robo robo) {
        boolean roboAqui = robo != null && robo.isAtivo() && robo.getX() == x && robo.getY() == y;

        if (roboAqui) {
            return "[" + robo.getCor().charAt(0) + "]";
        }
        if (comidaX == x && comidaY == y) {
            return "[X]";
        }
        Obstaculo ob = obstaculos[x][y];
        if (ob instanceof Bomba) {
            return "[B]";
        }
        if (ob instanceof Rocha) {
            return "[R]";
        }
        return "[·]";
    }

    private String addSimboloDoisRobos(int x, int y, Robo r1, Robo r2) {
        boolean r1Aqui = r1 != null && r1.isAtivo() && r1.getX() == x && r1.getY() == y;
        boolean r2Aqui = r2 != null && r2.isAtivo() && r2.getX() == x && r2.getY() == y;

        if (r1Aqui && r2Aqui) {
            return "[" + r1.getCor().charAt(0) + r2.getCor().charAt(0) + "]";
        }
        if (r1Aqui) {
            return "[" + r1.getCor().charAt(0) + "]";
        }
        if (r2Aqui) {
            return "[" + r2.getCor().charAt(0) + "]";
        }
        if (comidaX == x && comidaY == y) {
            return "[X]";
        }
        Obstaculo ob = obstaculos[x][y];
        if (ob instanceof Bomba) {
            return "[B]";
        }
        if (ob instanceof Rocha) {
            return "[R]";
        }
        return "[·]";
    }

    public int getComidaX() {
        return comidaX;
    }

    public void setComidaX(int comidaX) {
        this.comidaX = comidaX;
    }

    public int getComidaY() {
        return comidaY;
    }

    public void setComidaY(int comidaY) {
        this.comidaY = comidaY;
    }

    public int getTamanhoTabuleiro() {
        return TAMANHO_TABULEIRO;
    }
}
