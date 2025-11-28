package entities;

public abstract class Obstaculo {
    protected int id;
    protected int x;
    protected int y;

    public Obstaculo(int id, int posicaoHorizontal, int posicaoVertical) {
        this.id = id;
        this.x = posicaoHorizontal;
        this.y = posicaoVertical;
    }

    public abstract String bater(Robo r);

    public int getId() { 
    	return id; 
    }
    public void setId(int id) { 
    	this.id = id; 
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
}
