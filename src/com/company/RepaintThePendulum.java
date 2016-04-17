package com.company;

//simple class which implements runnable, used for repainting the window each n amount of milliseconds
public class RepaintThePendulum implements Runnable {

    //keeps a reference to the window which needs to be repainted
    SimulationWindow window;

    public RepaintThePendulum(SimulationWindow window) {
        this.window = window;
    }

    @Override
    public void run() {

        //redraws the pendulum
        this.window.repaint();
    }
}
