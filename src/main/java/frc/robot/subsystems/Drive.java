package frc.robot.subsystems;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.constants.Constants;

/**
 * Class representing the subsystem of the drive.
 * Initiated using the motors of the drive train,
 * configures the motor controllers accordingly and provides
 * access for arcade driving.
 */
public class Drive {

    /** The first motor on the left side of the robot's drivetrain **/
    private CANSparkMax leftMotor1;
    /** The second motor on the left side of the robot's drivetrain **/
    private CANSparkMax leftMotor2;
    /** The third motor on the left side of the robot's drivetrain **/
    private CANSparkMax leftMotor3;

    /** The first motor on the right side of the robot's drivetrain **/
    private CANSparkMax rightMotor1;
    /** The second motor on the right side of the robot's drivetrain **/
    private CANSparkMax rightMotor2;
    /** The third motor on the right side of the robot's drivetrain **/
    private CANSparkMax rightMotor3;

    /** DifferentialDrive instance used to control the first left and right motors using joystick inputs **/
    private DifferentialDrive m_drive;

    /** The DoubleSolenoid for the gear-changing mechanism */
    private DoubleSolenoid gearShifter;

    /** Whether or not the driving direction is reversed */
    private boolean isReverse = false;

    /**
     * Initilaizes the Drivetrain subsystem given the motor controllers that control the drive motors
     * 
     * Configures one motor from each side as master and the other two as slaves, and uses the two masters
     * in initiating a DifferentialDrive object.
     * 
     * @param leftMotor1 - The first motor controller on the left side (will be configured as master)
     * @param leftMotor2 - The second motor controller on the left side (will be configured as slave)
     * @param leftMotor3 - The third motor controller on the left side (will be configured as slave)
     * 
     * @param rightMotor1 - The first motor controller on the right side (will be configured as master)
     * @param rightMotor2 - The second motor controller on the right side (will be configured as slave)
     * @param rightMotor3 - The third motor controller on the right side (will be configured as slave)
     * 
     */
    public Drive(CANSparkMax leftMotor1, CANSparkMax leftMotor2, CANSparkMax leftMotor3 , 
    CANSparkMax rightMotor1 , CANSparkMax rightMotor2 , CANSparkMax rightMotor3, DoubleSolenoid gearShifter) {
        
        // Sets the motors for the left side of the drive train to those supplied by the arguments
        this.leftMotor1 = leftMotor1;
        this.leftMotor2 = leftMotor2;
        this.leftMotor3 = leftMotor3;
        // Sets the motors for the right side of the drive train to those supplied by the arguments
        this.rightMotor1 = rightMotor1;
        this.rightMotor2 = rightMotor2;
        this.rightMotor3 = rightMotor3;

        // Resets the left motors to factory defaults to avoid any settings from earlier
        this.leftMotor1.restoreFactoryDefaults();
        this.leftMotor2.restoreFactoryDefaults();
        this.leftMotor3.restoreFactoryDefaults();

        // Resets the right motors to factory defaults to avoid any settings from earlier
        this.rightMotor1.restoreFactoryDefaults();
        this.rightMotor2.restoreFactoryDefaults();
        this.rightMotor3.restoreFactoryDefaults();

        // Update the smart current limits on each of the motor controllers (necessary since restore removes the previous setting)
        this.leftMotor1.setSmartCurrentLimit(Constants.DRIVE_POWER_LIMIT);
        this.leftMotor2.setSmartCurrentLimit(Constants.DRIVE_POWER_LIMIT);
        this.leftMotor3.setSmartCurrentLimit(Constants.DRIVE_POWER_LIMIT);
        this.rightMotor1.setSmartCurrentLimit(Constants.DRIVE_POWER_LIMIT);
        this.rightMotor2.setSmartCurrentLimit(Constants.DRIVE_POWER_LIMIT);
        this.rightMotor3.setSmartCurrentLimit(Constants.DRIVE_POWER_LIMIT);

        // Define the second and third motors on the left side to follow the first motor on the left side
        // Therefore, any command supplied to the first motor will also automatically cause the other two
        // motors to operate under the same command.
        this.leftMotor2.follow(this.leftMotor1);
        this.leftMotor3.follow(this.leftMotor1);

        // Define the second and third motors on the right side to follow the first motor on the right side
        // Therefore, any command supplied to the first motor will also automatically cause the other two
        // motors to operate under the same command.
        this.rightMotor2.follow(this.rightMotor1);
        this.rightMotor3.follow(this.rightMotor1);

        // Initiate the differential drive instance with the first left and first right motors
        // There is no need to supply the other four motors as they'll follow these two
        this.m_drive = new DifferentialDrive(this.leftMotor1, this.rightMotor1);

        //Initiate tthe DobuelSolenoid
        this.gearShifter = gearShifter;
        
    }

    /**
     * Drives the robot through the DifferentialDrive interfface at a speed of magnitude and a turn speed of turn
     * @param magnitude - a value betweeen -1 and 1 representing the magnitude of the drive in the forwards/reverse direction
     * @param turn - a value between -1 and 1 represent the magnitude of the turn in the left/right direction
     */
    public void arcadeDrive(double magnitude, double turn){
        this.m_drive.arcadeDrive(isReversed()?-magnitude:magnitude, turn, true);
    }

    /**
     * Set low gear
     */
    public void setLowGear(){
        gearShifter.set(Constants.GEAR_LOW);
    }
    
    /**
     * Set high gear
     */
    public void setHighGear(){
        gearShifter.set(Constants.GEAR_HIGH);
    }

    /**
     * Returns whether the robot drive is in high gear
     * @return
     */
    public boolean isHighGear(){
        return gearShifter.get() == Constants.GEAR_HIGH;
    }

    /**
     * Toggles the gear speed (low -> high or high -> low)
     */
    public void toggleGearSpeed(){
        // Sets the gear speed to the opposite of what it currently is
        gearShifter.set((gearShifter.get() == Constants.GEAR_LOW) ? Constants.GEAR_HIGH : Constants.GEAR_LOW);
    }

    /**
     * Returns whether or not the driving direction is reversed
     * @return
     */
    public boolean isReversed(){
        return this.isReverse;
    }

    /**
     * Sets whether or not the driving direction is reversed
     * @param isReverse
     */
    public void setIsReversed (boolean isReverse){
        this.isReverse = isReverse;
    }

    /**
     * Toggles whether or not the driving direction is reversed
     */
    public void toggleReverse (){
        setIsReversed(!isReversed());
    }

    /**
     * Sets whether or not brake mode is enabled
     * @param enabled
     */
    public void setBrakeMode (boolean enabled){

        this.leftMotor1.setIdleMode(enabled? IdleMode.kBrake : IdleMode.kCoast);
        this.leftMotor2.setIdleMode(enabled? IdleMode.kBrake : IdleMode.kCoast);
        this.leftMotor3.setIdleMode(enabled? IdleMode.kBrake : IdleMode.kCoast);
        this.rightMotor1.setIdleMode(enabled? IdleMode.kBrake : IdleMode.kCoast);
        this.rightMotor2.setIdleMode(enabled? IdleMode.kBrake : IdleMode.kCoast);
        this.rightMotor3.setIdleMode(enabled? IdleMode.kBrake : IdleMode.kCoast);

    }
    
}
