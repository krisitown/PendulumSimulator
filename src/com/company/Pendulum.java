package com.company;

/**
 * Static model of the pendulum
 */
public class Pendulum {
    private static Ball ball;
    private static int armLength;
    public static int originX;
    public static int originY;
    private static int angle;
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

    public static void setAngle(int angle) {
        if(angle < 0){
            throw new IllegalArgumentException("The angle must be a positive integer");
        }
        Pendulum.angle = angle;

        //need fixin'
        //change the ball's position when changing the angle
        Pendulum.ball.setX(Math.abs((int)(Pendulum.armLength*Math.sin(angle)) + originX));
        Pendulum.ball.setY(Math.abs((int)(Pendulum.armLength*Math.cos(angle))));
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
