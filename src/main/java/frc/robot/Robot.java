/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import frc.robot.subsystems.*;
import frc.robot.constants.Constants;
import frc.robot.constants.WiringConstants;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {

  // The spinner subsystem
  private Spinner spinner;
  // The elevator subsystem
  private Elevator elevator;
  // The feeder subsystem
  private Intake intake;
  // The hook subsystem
  private Hook hook;
  // The loader subsystem
  private Loader loader;
  // The shooter subsystem
  private Shooter shooter;
  // The Drive subsystem
  private Drive drive;
  // The pilot controllers
  private XboxController pilot;
  private XboxController coPilot;
  //Compressor
  private Compressor compressor;

  /**
   * This function is run when the robot is first started up and should be used
   * for any initialization code.
   */
  @Override
  public void robotInit() {

    // Spinner constructor
    this.spinner = new Spinner(
      new WPI_VictorSPX(WiringConstants.COLOR_PANEL_PORT),
      new DoubleSolenoid(WiringConstants.CONTROL_PANEL_A, WiringConstants.CONTROL_PANEL_B));

    // Elevator constructor
    this.elevator = new Elevator(
      new CANSparkMax(WiringConstants.ELEVATOR_LEFT_PORT, MotorType.kBrushless), 
      new CANSparkMax(WiringConstants.ELEVATOR_RIGHT_PORT, MotorType.kBrushless), 
      new DoubleSolenoid(WiringConstants.ELEVATOR_LOCK_A, WiringConstants.ELEVATOR_LOCK_B));

    // Feeder constructor 
    this.intake = new Intake(
      new WPI_VictorSPX(WiringConstants.INTAKE_PORT));

    // Hook constructor
    this.hook = new Hook(
      new CANSparkMax(WiringConstants.HOOK_PORT, MotorType.kBrushless));
    
    // Loader constructor
    this.loader = new Loader(
      new WPI_TalonSRX(WiringConstants.VERTICAL_LOADER_PORT), 
      new WPI_TalonSRX(WiringConstants.HORIZONTAL_LOADER_PORT));

    // Shooter constructor
    this.shooter = new Shooter(
      new CANSparkMax(WiringConstants.SHOOTER_LEFT_PORT, MotorType.kBrushless),
      new CANSparkMax(WiringConstants.SHOOTER_RIGHT_PORT, MotorType.kBrushless),
      new Servo(WiringConstants.LEFT_SERVO_PWM_PORT), 
      new Servo(WiringConstants.RIGHT_SERVO_PWM_PORT));

    // Drive constructor
    this.drive = new Drive(
      new CANSparkMax(WiringConstants.DRIVE_LEFT_1_PORT, MotorType.kBrushless), 
      new CANSparkMax(WiringConstants.DRIVE_LEFT_2_PORT, MotorType.kBrushless), 
      new CANSparkMax(WiringConstants.DRIVE_LEFT_3_PORT, MotorType.kBrushless), 
      new CANSparkMax(WiringConstants.DRIVE_RIGHT_1_PORT, MotorType.kBrushless), 
      new CANSparkMax(WiringConstants.DRIVE_RIGHT_2_PORT, MotorType.kBrushless), 
      new CANSparkMax(WiringConstants.DRIVE_RIGHT_3_PORT, MotorType.kBrushless),
      new DoubleSolenoid(WiringConstants.DRIVE_GEAR_CHANGE_A, WiringConstants.DRIVE_GEAR_CHANGE_B));
    // Xbox controller constructor
    this.pilot = new XboxController(WiringConstants.PILOT_XBOXCONTROLLER_PORT);
    this.coPilot = new XboxController(WiringConstants.CO_PILOT_XBOXCONTROLLER_PORT);

    // Compressor
    this.compressor = new Compressor(WiringConstants.COMPRESSOR_CAN_ID);
  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to
   * the switch structure below with additional strings. If using the
   * SendableChooser make sure to add them to the chooser code above as well.
   */
  @Override
  public void autonomousInit() {

  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    //Drivers Controls//

    if(pilot.getY() >= Constants.AXIS_THRESHOLD || pilot.getY() <= -Constants.AXIS_THRESHOLD){  /** If the value on the Y-axis is above the required threshold 
                                                                                                 then the y value will be fed into the ArcadeDrive */

    }
    if(pilot.getX() >= Constants.AXIS_THRESHOLD || pilot.getX() <= -Constants.AXIS_THRESHOLD){ /** If the value on the X-axis is above the required threshold 
                                                                                                then the y value will be fed into the ArcadeDrive */

    }
    
    if(pilot.getBumper(Hand.kLeft)){ // If the left bumper is pressed the robot switches into low gear

    }
    if(pilot.getBumper(Hand.kRight)){ // if the right bumper is pressed the robot switches into high gear

    }

    if(pilot.getStartButton()){ // The robot remains unusable until the start button the pilot's end is pressed

    }
    //Co Pilot Controls//

    //--- Elevator ---//

    if(coPilot.getXButton()){ //Elevator is toggled when the x button is pressed (X)

    }
    //--- Hook --- //
    if(coPilot.getBButton()){ //Hook moves Left if this combination is recorded (B)

    }
    if(coPilot.getYButton()){ //Hook moves Right if this combination is recorded (Y)

    }
    if(coPilot.getYButton() && coPilot.getBButton()){ // Hook stops if this combination is recorded (Y and B)

    }
    // --- Shooter --- //
    if(coPilot.getTriggerAxis(Hand.kRight) >= Constants.AXIS_THRESHOLD ){ //Turns the Shooter on if the right trigger is pressed (RT)

    }
    if(coPilot.getTriggerAxis(Hand.kRight) < Constants.AXIS_THRESHOLD ){ //Turns the Shooter off if the right trigger is released (RT)

    }
    
    if(pilot.getBumper(Hand.kRight)){ //Servo toggle, starts retracted and is toggled to extend or retract by a desired number of degrees if the right bumper is pressed (RB)

    }

    // --- Feeder --- // 
    if(coPilot.getTriggerAxis(Hand.kLeft) >= 0.01 ){ //Turns the Feeder on if the left trigger is pressed (LT)

    }
    if(coPilot.getTriggerAxis(Hand.kLeft) < 0.01 ){ //Turns the Feeder off if the left trigger is released (LT)

    }
    // --- Spinner --- //
    if(coPilot.getBumper(Hand.kLeft)){ //Turns the Spinner motor on once the left bumper is pressed (LB)

    }
    if(!coPilot.getBumper(Hand.kLeft)){ //Turns the Spinner motor off once the left bumper is released (LB)

    }
    if(coPilot.getAButton()){ //Toggle for the piston which extends the shooter onto the control pannel (A)

    }


    // --- Loader --- //
    //supposed to be automatic
     
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
