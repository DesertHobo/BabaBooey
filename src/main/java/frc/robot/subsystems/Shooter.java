package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMax.IdleMode;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.constants.Constants;

import edu.wpi.first.wpilibj2.command.SubsystemBase;


/** 
 *  This class has all the methods to control motors on the 
 *  mounted shooter on the robot as well as the servos that 
 *  will be used to adjust the path in which the balls are fired.
 */ 
public class Shooter extends SubsystemBase {

    /** The left motor controller for the shooter */
    private CANSparkMax leftShooterMotor;
    /** The right motor controller for the shooter */
    private CANSparkMax rightShooterMotor;

    private CANPIDController pidController;
    private CANEncoder encoder;

    /** The left servo for adjusting the shooter aim */
    private Servo leftServo; 
    /** The right servo for adjusting the shooter aim */
    private Servo rightServo;

    /** The current angle of the shooter servos in degrees */
    private double currentAngle;

    // PID coefficients
    double kP = 3e-4; 
    double kI = 0.0000005;
    double kD = 0; 
    double kIz = 0; 
    double kFF = 0.000015; 
    double kMaxOutput = 1; 
    double kMinOutput = 0;
    double maxRPM = 5700;

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

        // Enables the smart current limit on the motor controllers
        this.leftShooterMotor.setSmartCurrentLimit(Constants.SHOOTER_POWER_LIMIT);
        this.rightShooterMotor.setSmartCurrentLimit(Constants.SHOOTER_POWER_LIMIT);

        this.leftShooterMotor.setInverted(false);
        this.rightShooterMotor.follow(this.leftShooterMotor, true);

        pidController = leftShooterMotor.getPIDController();

        encoder = leftShooterMotor.getEncoder();

        // set PID coefficients
        pidController.setP(kP);
        pidController.setI(kI);
        pidController.setD(kD);
        pidController.setIZone(kIz);
        pidController.setFF(kFF);
        pidController.setOutputRange(kMinOutput, kMaxOutput);

        // Sets the ramp rate to create a lower acceleration while turning on the shooter
        //this.rightShooterMotor.setOpenLoopRampRate(Constants.SHOOTER_RAMP_TIME);
        //this.leftShooterMotor.setOpenLoopRampRate(Constants.SHOOTER_RAMP_TIME);

    }

    /**
     * Turns on the shooter
     */
    
    public void ShooterOn(){
        setShooterSpeed(Constants.SHOOTER_SPEED);
    }

    /**
     * Turns off the shooter
     */
    public void ShooterOff(){
        setShooterSpeed(0.0);
    }

    /**
     * Sets the speed of the shooter (range 0 to 1)
     * @param speed - the speed of the shooter from 0 to 1
     */
    public void setShooterSpeed(double speed){

        pidController.setReference(speed * maxRPM, ControlType.kVelocity);
    
        SmartDashboard.putNumber("Shooter SetPoint", Constants.SHOOTER_SPEED * maxRPM);
        SmartDashboard.putNumber("Shooter ProcessVariable", encoder.getVelocity());

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

    /**
     * Sets whether or not brake mode is enabled
     * @param enabled
     */
    public void setBrakeMode (boolean enabled){

        this.leftShooterMotor.setIdleMode(IdleMode.kCoast);//enabled? IdleMode.kBrake : IdleMode.kCoast);
        this.rightShooterMotor.setIdleMode(IdleMode.kCoast);//enabled? IdleMode.kBrake : IdleMode.kCoast);

    }
    
     
}
