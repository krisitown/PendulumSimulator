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
        //initializes the pendulum origin point
        //used so that the pendulum class is loaded before the drawer is trying to access it
        originX = Constants.windowWidth / 2;
        originY = Constants.windowHeight / 4;

        //create a ball with some default values
        ball = new Ball(Pendulum.originX, Pendulum.originY + 120, 50); //default values
    }

    //indicates the start of the movement of the pendulum
    public static void start(){
        //creates the bob with the values given from the GUI
        ball = new Ball(Pendulum.originX, Pendulum.originY + (int)(armLength * 500), mass);

        //computes the values of key variables
        period = 2* Math.PI*Math.sqrt(armLength/g);
        angularVelocity = 2 * Math.PI*(1/period);
        amplitude = Math.sqrt(2*armLength*armLength*(1 - Math.cos(Pendulum.startAngle)));
        dampingRatio = armLength/(2*mass);

        //used to check if the pendulum is started from outside classes
        isStarted = true;
    }

    //a function which gets the angle theta of the pendulum based on a specific point in time
    public static void moveBall(double time){
        double theta = Pendulum.startAngle * Math.sin(Pendulum.angularVelocity*time) * Math.exp(-1*dampingRatio*time);
        Pendulum.setAngle(theta);
    }

    //used to get the coordinates of the origin
    public static int[] getOriginCoordinates(){
        return new int[] {originX, originY};
    }

    //used to get the coordinates of the ball
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

    //sets the angle of the pendulum and changes the position of the ball accordingly
    public static void setAngle(double angle) {
        Pendulum.angle = angle;

        //*500 used because armLength is stored in meters and in order to make it more visible on the screen
        Pendulum.ball.setX((int)(Pendulum.armLength*500*Math.sin(Pendulum.angle)) + originX);
        Pendulum.ball.setY((int)(Pendulum.armLength*500*Math.cos(Pendulum.angle)) + originY);
    }

    //simple setters and getters + encapsulation
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

    public static double getBallMass() {
        return mass;
    }
}
