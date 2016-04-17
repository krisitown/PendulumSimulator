package com.company;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Class for the Ball swinging on the pendulum
 */
public class Ball {
    private int X, Y;
    private double radius;

    public Ball(int x, int y, double mass){
        setX(x);
        setY(y);
        setMass(mass);
    }

    public int getX() {
        return X;
    }

    public void setX(int x) {
        this.X = x;
    }

    public int getY() {
        return Y;
    }

    public void setY(int y) {
        this.Y = y;
    }

    public void setMass(double mass) {
        if(mass < 0){
            throw new IllegalArgumentException("The Ball's mass must be positive");
        }
        Pendulum.setBallMass(mass);
        this.computeRadius();
    }

    public double getRadius(){
        return radius;
    }

    //computed only once since it is a reasonably heavy operation
    private void computeRadius() {
        double v = Constants.ironDensity * Pendulum.getBallMass();

        //get radius from volume = (4 Pi radius^3) / 3
        radius = Math.abs(Math.pow(3*v/4*Math.PI, 1.0/3.0));
        radius *= 4; //make it more visible
    }
}
