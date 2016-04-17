package com.company;

import javax.swing.*;
import java.awt.*;

/**
 * Used to draw on the screen
 */
public class DrawingPanel extends JPanel {
    //holds the time that has passed since the start of movement of the pendulum
    public static double time = 0.0;

    public DrawingPanel() {
        this.validate();
    }

    @Override
    public void paintComponent(Graphics g){
        Graphics2D graphicSettings = (Graphics2D)g;

        super.paintComponent(g);

        //set the color to black
        graphicSettings.setColor(Color.BLACK);

        //create a 10x10 square to represent the origin of the pendulum
        graphicSettings.fillRect(Pendulum.getOriginCoordinates()[0] - 5, Pendulum.getOriginCoordinates()[1] + Constants.menuPadding - 5,
                10, 10);

        //draw the arm of the pendulum
        graphicSettings.drawLine(Pendulum.getOriginCoordinates()[0], Pendulum.getOriginCoordinates()[1] + Constants.menuPadding,
                Pendulum.getBallCoordinates()[0], Pendulum.getBallCoordinates()[1] + Constants.menuPadding);

        //set the color to dark gray
        graphicSettings.setColor(Color.DARK_GRAY);

        //draw the ball /bob/ of the pendulum
        graphicSettings.fillOval(Pendulum.getBallCoordinates()[0] - (int)Pendulum.getBallRadius()/2, Pendulum.getBallCoordinates()[1] + Constants.menuPadding
                - (int)Pendulum.getBallRadius()/2, (int)Pendulum.getBallRadius(), (int)Pendulum.getBallRadius());

        //draw the values of the period, frequency and angle of the pendulum
        //note: possible use of string builder to improve performance
        graphicSettings.drawString("T = " + Pendulum.getPeriod() + "s;    ν = " + (Pendulum.getPeriod() != 0 ? (1/Pendulum.getPeriod()) + "Hz;" : "N/A;")
                + "    θ = " + (int)(Pendulum.getAngle()*Constants.radianToDegreeRatio) + "°", 50, Constants.windowHeight - Constants.windowHeight/14);

        //turns on anti-aliasing
        graphicSettings.setRenderingHint( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        //checks if the pendulum has started moving
        if(Pendulum.isStarted){
            //feeds the moveBall function the time that has passed since the start of the pendulum
            Pendulum.moveBall(time);

            //this function is called every 5 milliseconds thus add 5 milliseconds
            time += 0.005;
        }
    }
}
