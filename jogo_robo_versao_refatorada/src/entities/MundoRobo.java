package entities;

public class MundoRobo {
    private final int tamanho = DimensaoTabuleiro.SIZE;
    private int comidaX, comidaY;
    private Obstaculo[][] obstaculos = new Obstaculo[tamanho][tamanho];

    public boolean posicaoValida(int x, int y) {
        return x >= 0 && x < tamanho && y >= 0 && y < tamanho;
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

        for (int y = tamanho - 1; y >= 0; y--) {
            for (int x = 0; x < tamanho; x++) {

                boolean roboAqui = robo.isAtivo() &&
                                   robo.getX() == x &&
                                   robo.getY() == y;

                if (roboAqui) {
                    sb.append("[").append(robo.getCor().charAt(0)).append("]");
                }
                else if (comidaX == x && comidaY == y) {
                    sb.append("[X]");
                }
                else if (obstaculos[x][y] instanceof Bomba) {
                    sb.append("[B]");
                }
                else if (obstaculos[x][y] instanceof Rocha) {
                    sb.append("[R]");
                }
                else {
                    sb.append("[·]");
                }
            }
            sb.append("\n");
        }

        sb.append("\n");
        return sb.toString();
    }
    
    public String gerarVisualizacao(Robo robo1, Robo robo2) {
        StringBuilder sb = new StringBuilder("\n");

        for (int y = tamanho - 1; y >= 0; y--) {
            for (int x = 0; x < tamanho; x++) {

                boolean robo1Aqui = robo1.isAtivo() &&
                                    robo1.getX() == x &&
                                    robo1.getY() == y;

                boolean robo2Aqui = robo2.isAtivo() &&
                                    robo2.getX() == x &&
                                    robo2.getY() == y;

                if (robo1Aqui && robo2Aqui) {
                    sb.append("[")
                      .append(robo1.getCor().charAt(0))
                      .append(robo2.getCor().charAt(0))
                      .append("]");
                }
                else if (robo1Aqui) {
                    sb.append("[").append(robo1.getCor().charAt(0)).append("]");
                }
                else if (robo2Aqui) {
                    sb.append("[").append(robo2.getCor().charAt(0)).append("]");
                }
                else if (comidaX == x && comidaY == y) {
                    sb.append("[X]");
                }
                else if (obstaculos[x][y] instanceof Bomba) {
                    sb.append("[B]");
                }
                else if (obstaculos[x][y] instanceof Rocha) {
                    sb.append("[R]");
                }
                else {
                    sb.append("[·]");
                }
            }
            sb.append("\n");
        }
        
        sb.append("\n");
        return sb.toString();
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
    public int getTamanho() { 
    	return tamanho;
    }
}
