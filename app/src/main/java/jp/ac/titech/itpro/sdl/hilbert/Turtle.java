package jp.ac.titech.itpro.sdl.hilbert;

public class Turtle {
    public interface Drawer {
        void drawLine(double x0, double y0, double x1, double y1);
    }

    public static final double R = Math.PI / 2, L = -R;
    public static final double N = 0.0, E = Math.PI / 2, S = Math.PI, W = E + S;

    protected double x = 0.0, y = 0.0;
    protected double dir = 0.0;

    protected Drawer drawer;

    public Turtle(Drawer drawer) {
        this.drawer = drawer;
    }

    public void setPos(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void setDir(double dir) {
        this.dir = dir;
    }

    public void forward(double step) {
        double x1 = x + Math.sin(dir) * step;
        double y1 = y - Math.cos(dir) * step;
        drawer.drawLine(x, y, x1, y1);
        x = x1;
        y = y1;
    }

    public void rotate(double th) {
        dir = (dir + th + Math.PI * 2) % (Math.PI * 2);
    }
}
