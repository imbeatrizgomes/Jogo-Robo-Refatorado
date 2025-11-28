package entities;

public enum Direcao {
    UP, 
    DOWN, 
    RIGHT, 
    LEFT;

    public static Direcao apartirDeString(String s) {
        if (s == null) return null;
        switch (s.trim().toLowerCase()) {
            case "up":    return UP;
            case "down":  return DOWN;
            case "right": return RIGHT;
            case "left":  return LEFT;
            default:      return null;
        }
    }

    public static Direcao apartirDeNumero(int code) {
        switch (code) {
            case 1: return UP;
            case 2: return DOWN;
            case 3: return RIGHT;
            case 4: return LEFT;
            default: return null;
        }
    }

    @Override
    public String toString() {
        switch (this) {
            case UP: return "up";
            case DOWN: return "down";
            case RIGHT: return "right";
            case LEFT: return "left";
            default: return super.toString();
        }
    }
}

