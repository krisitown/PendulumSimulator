package com.company;

/**
 * Static model of the pendulum
 */
public class Pendulum {
    private static Ball ball;
    private static double armLength;
    public static int originX;
    public static int originY;
    private static double angle;
    private static double startAngle;
    private static double g;
    private static double mass, angularVelocity, period, amplitude;
    private static double dampingRatio;
    public static boolean isStarted;


    public static void initialize(){
        originX = Constants.windowWidth / 2;
        originY = Constants.windowHeight / 4;
        ball = new Ball(Pendulum.originX, Pendulum.originY + 120, 50); //default values
    }

    public static void start(){
        ball = new Ball(Pendulum.originX, Pendulum.originY + (int)(armLength * 250), mass);
        period = 2* Math.PI*Math.sqrt(armLength/g);
        angularVelocity = 2 * Math.PI*(1/period);
        amplitude = Math.sqrt(2*armLength*armLength*(1 - Math.cos(Pendulum.startAngle)));
        dampingRatio = 0.05;
        DrawingPanel.time = 0.0;


        isStarted = true;
    }

    public static void moveBall(double time){
        //x = А * sin(w*t + f)


        //I am a math magician boooo
        double x = Pendulum.startAngle * Math.sin(Pendulum.angularVelocity*time);// * Math.exp(-1*dampingRatio*time);
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

    public static void setArmLength(double armLength) {
        if(armLength <= 0){
            throw new IllegalArgumentException("The length of the arm must be a positive integer");
        }
        Pendulum.armLength = armLength;
    }

    public static void setAngle(double angle) {
        Pendulum.angle = angle;

        //change the ball's position when changing the angle
        Pendulum.ball.setX((int)(Pendulum.armLength*250*Math.sin(Pendulum.angle)) + originX);
        Pendulum.ball.setY((int)(Pendulum.armLength*250*Math.cos(Pendulum.angle)) + originY);
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

    public static double getPeriod() {
        return period;
    }

    public static double getAngle() {
        return angle;
    }
}
