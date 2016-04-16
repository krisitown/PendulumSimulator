package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * The program's window
 */

public class SimulationWindow extends JFrame  {

    public SimulationWindow(){

        //create the window for the program
        this.setSize(Constants.windowWidth, Constants.windowHeight);
        this.setTitle("Pendulum Simulation");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.validate();

        //create the panel in which the pendulum will be painted
        DrawingPanel drawingPanel = new DrawingPanel();
        drawingPanel.setSize(Constants.windowWidth, Constants.windowHeight - Constants.windowHeight/15);

        //create a panel where the arguments for the simulation will be entered
        JPanel variablePanel = new JPanel();
        variablePanel.setSize(Constants.windowWidth, Constants.menuPadding);

        //create all the variable label + text field pairs
        variablePanel.add(new JLabel("Starting Angle"));
        JTextField startAngle = new JTextField(4);
        variablePanel.add(startAngle);

        variablePanel.add(new JLabel("Arm Length"));
        JTextField armLength = new JTextField(4);
        variablePanel.add(armLength);

        variablePanel.add(new JLabel("Ball Mass"));
        JTextField ballMass = new JTextField(4);
        variablePanel.add(ballMass);

        variablePanel.add(new JLabel("g"));
        JTextField g = new JTextField(4);
        variablePanel.add(g);


        //create a button to submit the values given
        JButton button = new JButton("Start");

        //add an action listener to the button so it accepts the arguments when it's clicked
        button.addActionListener(e -> {
            Pendulum.setArmLength(Integer.parseInt(armLength.getText())*6);
            Pendulum.setBallMass(Double.parseDouble(ballMass.getText()));
            Pendulum.setG(Double.parseDouble(g.getText()));

            int angle = Integer.parseInt(startAngle.getText());

            Pendulum.setStartAngle(angle*Constants.degreeToRadianRatio); //convert the degrees into radians
            Pendulum.start();
        });

        //add the button to the panel accepting the values
        variablePanel.add(button);

        //add the variable and drawing panel to the window
        this.add(variablePanel);
        this.add(drawingPanel);

        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(5);
        executor.scheduleAtFixedRate(new RepaintThePendulum(this), 0L, 16L, TimeUnit.MILLISECONDS); //todo find the best arguments

        this.setVisible(true);
    }
}
