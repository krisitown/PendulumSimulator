package com.company;

public class RepaintThePendulum implements Runnable {

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
