
package frc.robot.constants;



import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;


public class Constants{

    // Shooter constants
    public static final double SHOOTER_RAMP_TIME = 2;
    public static final double SHOOTER_SPEED = 1.0;
    //Spinner color constants
    public static final I2C.Port I2C_PORT = I2C.Port.kOnboard;//color sensor port on roborio
    public static final double INTAKE_SPEED = 1;
    public static final double REVERSE_INTAKE_SPEED = -1;
    public static final double STOP_MOTOR = 0;
    public static final double MAX_EXTENTSION_IN_DEGREES = 120;

    //Control Panel Piston values
    public static final Value CONTROL_PANEL_OPEN_ARM = Value.kForward;
    public static final Value CONTROL_PANEL_CLOSE_ARM = Value.kReverse;

    //Gear Piston values
    public static final Value GEAR_LOW = Value.kForward;
    public static final Value GEAR_HIGH = Value.kReverse;

    //elevator locks
    public static final Value ELEVATOR_LOCK = Value.kForward;
    public static final Value ELEVATOR_UNLOCK = Value.kReverse;
}