
package frc.robot.constants;



import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;


public class Constants{
    // OI
    public static final double AXIS_THRESHOLD = 0.01;
    public static final double X_VALUE_REDUCTION = 0.7;
    // Shooter constants
    public static final double SHOOTER_RAMP_TIME = 2;
    public static final double SHOOTER_SPEED = 1.0;
    //Spinner constants
    public static final I2C.Port I2C_PORT = I2C.Port.kOnboard;
    public static final double SPINNER_SPEED = 0.5;

    //Intake constants
    public static final double INTAKE_FORWARD_SPEED = 1.0;
    public static final double INTAKE_REVERSE_SPEED = -1.0;
    public static final double INTAKE_STOP_MOTOR = 0.0;
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

    // Elevator constants
    public static final double ELEVATOR_EXTEND_SPEED = 0.3;
    public static final double ELEVATOR_RETRACT_SPEED = -0.3;
}