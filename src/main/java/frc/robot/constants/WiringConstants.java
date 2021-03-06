package frc.robot.constants;

public class WiringConstants{
    
    /* ---- OI PORTS ---- */
    
    public static final int CO_PILOT_XBOXCONTROLLER_PORT = 1;
    public static final int PILOT_XBOXCONTROLLER_PORT = 0;


    /* ---- Motor Controllers ---- */

    // Drive subsystem motor controller ports (SPARK MAX) //
    public static final int DRIVE_LEFT_1_PORT = 5;
    public static final int DRIVE_LEFT_2_PORT = 9;
    public static final int DRIVE_LEFT_3_PORT = 8;
    public static final int DRIVE_RIGHT_1_PORT = 6;
    public static final int DRIVE_RIGHT_2_PORT = 12;
    public static final int DRIVE_RIGHT_3_PORT = 13;

    // Elevator (SPARK MAX) //
    public static final int ELEVATOR_LEFT_PORT = 10;
    public static final int ELEVATOR_RIGHT_PORT = 20;

    // Shooter (SPARK MAX) //
    public static final int SHOOTER_LEFT_PORT = 1;
    public static final int SHOOTER_RIGHT_PORT = 2;

    // Intake (VICTOR SPX) //
    public static final int INTAKE_PORT = 21;

    // Color Panel (VICTOR SPX) //
    public static final int COLOR_PANEL_PORT = 11;

    // Loader (SPARK MAX) //
    public static final int HORIZONTAL_LOADER_PORT = 4;
    public static final int VERTICAL_LOADER_PORT = 3;

    // Hook (SPARK MAX) //
    public static final int HOOK_PORT = 7;

    /* ---- Pneumatics ---- */

    // Drive (gear change) //
    public static final int DRIVE_GEAR_CHANGE_A = 0;
    public static final int DRIVE_GEAR_CHANGE_B = 7;

    // Control Panel //
    public static final int CONTROL_PANEL_A = 1;
    public static final int CONTROL_PANEL_B = 6;

    // Elevator (locking mechanism)
    public static final int ELEVATOR_LOCK_A = 2;
    public static final int ELEVATOR_LOCK_B = 5;

    /* ---- PWM ---- */
    
    // Shooter //
    public static final int LEFT_SERVO_PWM_PORT = 0;
    public static final int RIGHT_SERVO_PWM_PORT = 1;
    // Compressor //
    public static final int COMPRESSOR_CAN_ID = 0;
}