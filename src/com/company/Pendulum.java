package com.company;

/**
 * Static model of the pendulum
 */
public class Pendulum {
    private static Ball ball;
    private static int armLength;
    public static int originX;
    public static int originY;
    private static double angle;
    private static double g;
    private static double mass;

    public static void Initialize(){
        originX = Constants.windowWidth / 2;
        originY = 0;
        ball = new Ball(Pendulum.originX, Pendulum.originY + 300, 50); //default values
    }

    public static void Start(){
        ball = new Ball(Pendulum.originX, Pendulum.originY + armLength, mass);
    }

    public static void moveBall(){
        //ico code goes here, may god help me

    }

    public static int[] getOriginCoordinates(){
        return new int[] {originX, originY};
    }

    public static int[] getBallCoordinates(){
        return new int[] {ball.getX(), ball.getY()};
    }

    public static double getBallRadius(){
        return ball.getRadius();
    }

    public static void setArmLength(int armLength) {
        if(armLength <= 0){
            throw new IllegalArgumentException("The length of the arm must be a positive integer");
        }
        Pendulum.armLength = armLength;
    }

    public static void setAngle(double angle) {
        if(angle < 0){
            throw new IllegalArgumentException("The angle must be a positive integer");
        }

        //converts the degrees into radians for use in the cin and cos functions
        angle *= Constants.degreeToRadianRatio;
        Pendulum.angle = angle;

        //change the ball's position when changing the angle
        Pendulum.ball.setX((int)(Pendulum.armLength*Math.sin(angle)) + originX);
        Pendulum.ball.setY((int)(Pendulum.armLength*Math.cos(angle)) + originY);
    }


    public static void setG(double g) {
        if(g <= 0){
            throw new IllegalArgumentException("g must be a positive number");
        }
        Pendulum.g = g;
    }

    public static void setBallMass(double mass){
        if(mass <= 0){
            throw new IllegalArgumentException("The mass of the ball must be a positive value");
        }
        Pendulum.mass = mass;
        Pendulum.ball.setMass(mass);
    }
}
