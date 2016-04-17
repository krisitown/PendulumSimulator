package com.company;

import javax.swing.*;
import java.awt.*;

/**
 * Used to draw on the screen
 */
public class DrawingPanel extends JPanel {
    public static double time = 0.0;

    public DrawingPanel() {
        this.validate();
    }

    @Override
    public void paintComponent(Graphics g){
        Graphics2D graphicSettings = (Graphics2D)g;

        super.paintComponent(g);

        graphicSettings.setColor(Color.BLACK);

        graphicSettings.fillRect(Pendulum.getOriginCoordinates()[0] - 5, Pendulum.getOriginCoordinates()[1] + Constants.menuPadding - 5,
                10, 10);

        graphicSettings.drawLine(Pendulum.getOriginCoordinates()[0], Pendulum.getOriginCoordinates()[1] + Constants.menuPadding,
                Pendulum.getBallCoordinates()[0], Pendulum.getBallCoordinates()[1] + Constants.menuPadding);


        graphicSettings.setColor(Color.DARK_GRAY);

        graphicSettings.fillOval(Pendulum.getBallCoordinates()[0] - (int)Pendulum.getBallRadius()/2, Pendulum.getBallCoordinates()[1] + Constants.menuPadding
                - (int)Pendulum.getBallRadius()/2, (int)Pendulum.getBallRadius(), (int)Pendulum.getBallRadius());

        graphicSettings.drawString("T = " + Pendulum.getPeriod() + "s;    ν = " + (Pendulum.getPeriod() != 0 ? (1/Pendulum.getPeriod()) + "Hz;" : "N/A;")
                + "    θ = " + (int)(Pendulum.getAngle()*Constants.radianToDegreeRatio) + "°", 50, Constants.windowHeight - Constants.windowHeight/14);

        //turns on anti-aliasing
        graphicSettings.setRenderingHint( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if(Pendulum.isStarted){
            Pendulum.moveBall(time);
            time += 0.005;
        }
    }
}
