package entities;

public class Robo {
    protected int x;
    protected int y;
    protected String cor;
    protected boolean ativo = true;
    protected int movimentosValidos = 0;
    protected int movimentosInvalidos = 0;

    public Robo(String cor) {
        this.cor = cor;
        this.x = 0;
        this.y = 0;
    }

    public String mover(Direcao dir) throws MovimentoInvalidoException {
        if (dir == null) {
            movimentosInvalidos++;
            throw MovimentoInvalidoException.direcaoInvalida("null");
        }

        int novoX = x;
        int novoY = y;

        switch (dir) {
            case UP: novoY += 1; break;
            case DOWN: novoY -= 1; break;
            case RIGHT: novoX += 1; break;
            case LEFT: novoX -= 1; break;
            default:
                movimentosInvalidos++;
                throw MovimentoInvalidoException.direcaoInvalida(dir.toString());
        }

        if (!posicaoValida(novoX, novoY)) {
            movimentosInvalidos++;
            throw MovimentoInvalidoException.foraDoTabuleiro(dir.toString());
        }

        this.x = novoX;
        this.y = novoY;
        movimentosValidos++;
        return cor + " moveu para (" + x + "," + y + ")";
    }

    public String mover(int codigo) throws MovimentoInvalidoException {
    	Direcao d = Direcao.apartirDeNumero(codigo);
        if (d == null) {
            movimentosInvalidos++;
            throw MovimentoInvalidoException.codigoInvalido(codigo);
        }
        return mover(d);
    }

    protected boolean posicaoValida(int x, int y) {
        return x >= 0 && y >= 0 && x < DimensaoTabuleiro.SIZE && y < DimensaoTabuleiro.SIZE;
    }

    public boolean encontrouComida(int comidaX, int comidaY) {
        return this.x == comidaX && this.y == comidaY;
    }


    public int getX() { 
    	return x; 
    }
    public void setX(int x) { 
    	this.x = x; 
    }
    public int getY() { 
    	return y; 
    }
    public void setY(int y) { 
    	this.y = y; 
    }
    public String getCor() { 
    	return cor; 
    }
    public void setCor(String cor) { 
    	this.cor = cor; 
    }
    public boolean isAtivo() { 
    	return ativo; 
    }
    public void setAtivo(boolean ativo) { 
    	this.ativo = ativo; 
    }
    public int getMovimentosValidos() { 
    	return movimentosValidos; 
    }
    public int getMovimentosInvalidos() { 
    	return movimentosInvalidos; 
    }
}
