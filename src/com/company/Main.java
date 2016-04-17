package com.company;

public class Main {

    public static void main(String[] args) {
        //creates the pendulum with its default values
        Pendulum.initialize();

        //creates the main window for the program
	    new SimulationWindow();
    }
}
