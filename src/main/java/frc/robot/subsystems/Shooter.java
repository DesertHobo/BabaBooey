package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.Servo;

import frc.robot.constants.Constants;

/** 
 *  This class has all the methods to control motors on the 
 *  mounted shooter on the robot as well as the servos that 
 *  will be used to adjust the path in which the balls are fired.
 */ 
public class Shooter {

    /** The left motor controller for the shooter */
    private CANSparkMax leftShooterMotor;
    /** The right motor controller for the shooter */
    private CANSparkMax rightShooterMotor;

    /** The left servo for adjusting the shooter aim */
    private Servo leftServo; 
    /** The right servo for adjusting the shooter aim */
    private Servo rightServo;

    /** The current angle of the shooter servos in degrees */
    private double currentAngle;

    /**
     * Initializes with the left and right motors for the shooter, and with the left and right servos
     * @param leftShooterMotor
     * @param rightShooterMotor
     * @param leftServo
     * @param rightServo
     */
    public Shooter(CANSparkMax leftShooterMotor, CANSparkMax rightShooterMotor, Servo leftServo, Servo rightServo ){

        this.rightServo = rightServo; // assigns the rightServo taken into the private rightServo variable
        this.leftServo = leftServo; // assigns the leftServo taken into the private leftServo variable
    
        this.leftShooterMotor = leftShooterMotor; // assigns the leftShooterMotor taken into the private leftShooterMotor variable
        this.rightShooterMotor = rightShooterMotor; // assigns the rightShooterMotor taken into the private rightShooterMotor variable
        
        // Resets the motors called to default settings to avoid previous settings
        this.leftShooterMotor.restoreFactoryDefaults();
        this.rightShooterMotor.restoreFactoryDefaults();

        /**
         * In order for both motors to turn in the same direction, the values 
         * fed into one of the motors need to be inverted, to achieve this leftShooterMotor 
         * will be inverted
         */
        this.leftShooterMotor.setInverted(true); // sets the left shooter motor to inverted
        this.rightShooterMotor.setInverted(false);// ensures that the right shooter motor is not inverted

        // Sets the ramp rate to create a lower acceleration while turning on the shooter
        this.rightShooterMotor.setOpenLoopRampRate(Constants.SHOOTER_RAMP_TIME);
        this.leftShooterMotor.setOpenLoopRampRate(Constants.SHOOTER_RAMP_TIME);

        // Enables the smart current limit on the motor controllers
        this.leftShooterMotor.setSmartCurrentLimit(Constants.SHOOTER_POWER_LIMIT);
        this.rightShooterMotor.setSmartCurrentLimit(Constants.SHOOTER_POWER_LIMIT);

    }

    /**
     * Turns on the shooter
     */
    
    public void ShooterOn(){
        //Set the percent output of each of the motors to the constant speed
        rightShooterMotor.set(Constants.SHOOTER_SPEED); 
        leftShooterMotor.set(Constants.SHOOTER_SPEED);
    }

    /**
     * Turns off the shooter
     */
    public void ShooterOff(){
        //turn the shooter off by setting the output of the motors to 0%
        rightShooterMotor.set(0); 
        leftShooterMotor.set(0);
    }

    /**
     * Returns the previously set speed
     * @return
     */
    public double getShooterSpeed(){
        return leftShooterMotor.get();
    }

    /**
     * These angles will be fed into the left and right servos on the shooter. The angles inputed are from 
     * 0-180 and in order to go the same direction the angles inputed into one of the servos must be inverted.
     * By this sense, the angles inputed for one of the servos will be counted back from 0 and the other from 180.
     * When angles are set in this fashion, the servos will extend by the same distance and in the same direction. 
     * 
     * Ex)
     *  leftservo.setangle(0)
     *  rightservo.setangle(120) //120 degrees is the max adjustment
     * 
     * In running this, the servos will extend to the same degree.
     * 
     * @param angleInDegrees - angle that will be fed into the right servo directly and adjusted for the left servo
     */ 
    public void SetShooterAngle(double angleInDegrees){

        // Update the current angle
        this.currentAngle = angleInDegrees;

        // Sets the rightServo to angleInDegrees directly
        rightServo.setAngle(currentAngle);
        // Calculates the angle for the left servo to achieve the same postiton
        leftServo.setAngle(-(currentAngle - Constants.MAX_EXTENTSION_IN_DEGREES));

    }

    /**
     * Returns the current angle of the shooter
     * @return
     */
    public double getShooterAngle(){
        return this.currentAngle;
    }

    /**
     * Increments the shooter angle (should be called every 20ms to achieve desired speed)
     */
    public void incrementShooterAngle(){

        SetShooterAngle(getShooterAngle() - Constants.SERVO_INCREMENT_VALUE);

    }

    /**
     * Decrements the shooter angle (should be called every 20ms to achieve desired speed)
     */
    public void decrementShooterAngle(){

        SetShooterAngle(getShooterAngle() + Constants.SERVO_REDUCTION_VALUE);

    }
    
     
}
