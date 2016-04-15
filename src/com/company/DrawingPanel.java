package com.company;

import javax.swing.*;
import java.awt.*;

/**
 * Created by krisitown on 15.04.16.
 */
public class DrawingPanel extends JPanel {
    public DrawingPanel() {
        this.validate();
    }

    @Override
    public void paintComponent(Graphics g){
        Graphics2D graphicSettings = (Graphics2D)g;

        super.paintComponent(g);

        graphicSettings.setColor(Color.BLACK);

        graphicSettings.drawLine(Pendulum.getOriginCoordinates()[0], Pendulum.getOriginCoordinates()[1] + Constants.menuPadding,
                Pendulum.getBallCoordinates()[0], Pendulum.getBallCoordinates()[1] + Constants.menuPadding);


        graphicSettings.setColor(Color.DARK_GRAY);

        graphicSettings.fillOval(Pendulum.getBallCoordinates()[0] - (int)Pendulum.getBallRadius()/2, Pendulum.getBallCoordinates()[1] + Constants.menuPadding
                - (int)Pendulum.getBallRadius()/2, (int)Pendulum.getBallRadius(), (int)Pendulum.getBallRadius());

        //turns on anti-aliasing
        graphicSettings.setRenderingHint( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

    }
}
