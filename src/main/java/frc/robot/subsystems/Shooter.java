package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.Servo;

import frc.robot.constants.Constants;

public class Shooter {
    //Initalize the Servos and Motors for the shooter

    private CANSparkMax rightShooterMotor;// right shooter motor
    private CANSparkMax leftShooterMotor;// left shooter motor

    private Servo leftServo;// left servo on the shooter
    private Servo rightServo; //right servo on the shooter
    /**
     * Takes in the left and right motors for the shooter
     * @param leftShooterMotor
     * @param rightShooterMotor
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
    }
    public void ShooterOn(){
        //Set the ramp rate to create some time for the motors to accelerate to max speed
        rightShooterMotor.setOpenLoopRampRate(Constants.SHOOTER_RAMP_TIME);// sets the time for the right motor on the shooter to accelerate from 0 to 100% for 2 seconds
        leftShooterMotor.setOpenLoopRampRate(Constants.SHOOTER_RAMP_TIME);// sets the time for the left motor on the shooter to accelerate from 0 to 100% for 2 seconds
        //Set the percent output of each of the motors to 100%
        rightShooterMotor.set(Constants.SHOOTER_SPEED); 
        leftShooterMotor.set(Constants.SHOOTER_SPEED);
    }
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
     *  leftservo.setangle(8)
     *  rightservo.setangle(120)
     * 
     * In running this, the servos will extend to the same degree.
     * 
     * @param leftServoAngle - any angle inputed into the left, based on current tests with the plastic mounted hooks, 
     *                         needs to be ofset by 8 degrees in order to stay parallel to the rightServo. 
     * @param rightServoAngle
     */
    public void SetShooterAngle(double leftServoAngle, double rightServoAngle){
        rightServo.setAngle(rightServoAngle);//- sets the angle of the right servo based on rightServoAngle
        leftServo.setAngle(leftServoAngle); //- sets the angle of the left servo based on leftServoAngle
    }
    
}
