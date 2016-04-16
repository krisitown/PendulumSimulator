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
    private static double startAngle;
    private static double g;
    private static double mass, angularVelocity, period, amplitude;
    public static boolean isStarted;

    public static void Initialize(){
        originX = Constants.windowWidth / 2;
        originY = Constants.windowHeight / 4;
        ball = new Ball(Pendulum.originX, Pendulum.originY + 120, 50); //default values
    }

    public static void start(){
        ball = new Ball(Pendulum.originX, Pendulum.originY + armLength, mass);
        period = 2* Math.PI*Math.sqrt(armLength/g);
        angularVelocity = 2 * Math.PI*(1/period);
        amplitude = Math.sqrt(2*armLength*armLength*(1 - Math.cos(Pendulum.startAngle)));
        isStarted = true;
    }

    public static void moveBall(double time){
        //x = А * sin(w*t + f)

        double x = Pendulum.startAngle * Math.sin(Pendulum.angularVelocity*time);
        double cosTheta = 1 - ((x*x)/(2*(armLength*armLength)));
        double theta = Math.acos(cosTheta);
        Pendulum.setAngle(x);
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
        Pendulum.angle = angle;

        //change the ball's position when changing the angle
        Pendulum.ball.setX((int)(Pendulum.armLength*Math.sin(Pendulum.angle)) + originX);
        Pendulum.ball.setY((int)(Pendulum.armLength*Math.cos(Pendulum.angle)) + originY);
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

    public static void setStartAngle(double startAngle){
        if(startAngle < 0){
            throw new IllegalArgumentException("The angle must be a positive number");
        }

        Pendulum.startAngle = startAngle;
        Pendulum.setAngle(startAngle);
    }
}
