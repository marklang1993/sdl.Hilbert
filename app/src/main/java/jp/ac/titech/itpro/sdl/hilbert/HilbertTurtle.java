package jp.ac.titech.itpro.sdl.hilbert;

public class HilbertTurtle extends Turtle implements Runnable{

    private int mOrder;
    private double mStep;
    private double mTurn;

    public HilbertTurtle(Drawer drawer, int order, double step, double turn) {
        super(drawer);

        mOrder = order;
        mStep = step;
        mTurn = turn;
    }

    public void draw(int order, double step, double turn) {
//        if (order > 0) {
//            rotate(-turn);
//            draw(order - 1, step, -turn);
//            forward(step);
//
//            rotate(turn);
//            draw(order - 1, step, turn);
//            forward(step);
//
//            draw(order - 1, step, turn);
//            rotate(turn);
//            forward(step);
//
//            draw(order - 1, step, -turn);
//            rotate(-turn);
//        }

        /**
         * Improved by using multi-threading techniques:
         * In the former code, only the main thread is used to do drawing and calculation, while
         * the improved version is creating 1 more thread once the sub-graph drawing is required.
         * Finally, almost all the CPU resource can be used to draw the Hilbert curve.
         */
        if (order > 0) {
            rotate(-turn);

            HilbertTurtle h1 = new HilbertTurtle(drawer, order - 1, step, -turn);
            h1.setDir(dir);
            h1.setPos(x, y);
            h1.run();
            x = h1.x;
            y = h1.y;
            dir = h1.dir;
            forward(step);

            rotate(turn);
            HilbertTurtle h2 = new HilbertTurtle(drawer, order - 1, step, turn);
            h2.setDir(dir);
            h2.setPos(x, y);
            h2.run();
            x = h2.x;
            y = h2.y;
            dir = h2.dir;
            forward(step);

            HilbertTurtle h3 = new HilbertTurtle(drawer, order - 1, step, turn);
            h3.setDir(dir);
            h3.setPos(x, y);
            h3.run();
            x = h3.x;
            y = h3.y;
            dir = h3.dir;
            rotate(turn);
            forward(step);

            HilbertTurtle h4 = new HilbertTurtle(drawer, order - 1, step, -turn);
            h4.setDir(dir);
            h4.setPos(x, y);
            h4.run();
            x = h4.x;
            y = h4.y;
            dir = h4.dir;
            rotate(-turn);
        }
    }

    @Override
    public void run() {
        draw(mOrder, mStep, mTurn);
    }
}
