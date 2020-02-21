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

    //Initalize the Servos and Motors for the shooter
    private CANSparkMax rightShooterMotor; // right shooter motor
    private CANSparkMax leftShooterMotor; // left shooter motor

    private Servo leftServo; // left servo on the shooter
    private Servo rightServo; //right servo on the shooter

    /**
     * Initializes with the left and right motors for the shooter, and with the left and right servos
     * @param leftShooterMotor
     * @param rightShooterMotor
     * @param leftServo
     * @param rightServo
     */
    public Shooter(CANSparkMax leftShooterMotor, CANSparkMax rightShooterMotor, Servo leftServo, Servo rightServo ){

        this.rightServo = rightServo; //-assigns the rightServo taken into the private rightServo variable
        this.leftServo = leftServo; //-assigns the leftServo taken into the private leftServo variable
    
        this.leftShooterMotor = leftShooterMotor;//-assigns the leftShooterMotor taken into the private leftShooterMotor variable
        this.rightShooterMotor = rightShooterMotor;//-assigns the rightShooterMotor taken into the private rightShooterMotor variable
        
        //set the motors called to default settings
        leftShooterMotor.restoreFactoryDefaults();
        rightShooterMotor.restoreFactoryDefaults();
        /**
         * In order for both motors to turn in the same direction, the values 
         * fed into one of the motors need to be inverted, to achieve this leftShooterMotor 
         * will be inverted
         */
        leftShooterMotor.setInverted(true); // sets the left shooter motor to inverted
        rightShooterMotor.setInverted(false);// ensures that the right shooter motor is not inverted

        //Set the ramp rate to create some time for the motors to accelerate to max speed
        rightShooterMotor.setOpenLoopRampRate(Constants.SHOOTER_RAMP_TIME);// sets the time for the right motor on the shooter to accelerate from 0 to 100% for 2 seconds
        leftShooterMotor.setOpenLoopRampRate(Constants.SHOOTER_RAMP_TIME);// sets the time for the left motor on the shooter to accelerate from 0 to 100% for 2 seconds

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
        rightServo.setAngle(angleInDegrees);//- sets the angle of the right servo based on rightServoAngle
        leftServo.setAngle(-(angleInDegrees - 120)); //- sets the angle of the left servo based on leftServoAngle
    }
     
}
