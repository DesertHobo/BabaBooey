/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

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

  //private double GameTime; // consider this
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
   * 
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
      new CANSparkMax(WiringConstants.VERTICAL_LOADER_PORT, MotorType.kBrushless), 
      new CANSparkMax(WiringConstants.HORIZONTAL_LOADER_PORT, MotorType.kBrushless));

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

  //Shooter variables//
  double shooterAngle = 0; // this value keeps track of whether the robot's servos are extended

  @Override
  public void teleopInit() {
    
    // Set the servo height to default
    shooter.SetShooterAngle(Constants.SERVO_START_ANGLE);
    // Locks the elevator
    elevator.lockElevator();
    // Closes the arm
    spinner.closeArm();

  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {

    /* ---- Drive ---- */

    // If the pilot presses the left bumper, then toggle the gear speed
    if (pilot.getBumperPressed(Hand.kLeft)){
      drive.toggleGearSpeed();
    }

    // If the copilot presses the y button, then toggle the robot face
    if(coPilot.getYButtonPressed()) {
      drive.toggleReverse();
    }

    // Depending on whether or not the robot is in high gear, either leave turn unchanged or multiply by constant
    double turn = drive.isHighGear() ? pilot.getX(Hand.kRight) * Constants.X_VALUE_REDUCTION : pilot.getX(Hand.kRight);
    // Drive according to the calculated turn along with the y value for drive
    drive.arcadeDrive(pilot.getY(Hand.kLeft), turn);

    /* ---- Intake ---- */

    // If the coPilot wishes to intake/outtake (the left bumper is pressed)
    if (coPilot.getBumper(Hand.kLeft)){
      // If the X button is pressed, then reverse the intake
      if (coPilot.getXButton()){
        intake.reverseIntake();
      }
      // Otherwise intake regularly
      else{
        intake.intake();
      }
    }
    // Otherwise turn off the intake
    else{
      intake.stopIntake();
    }

    /* ---- Shooter ---- */
    // If the right trigger is pressed, turn on the shooter
    if(coPilot.getTriggerAxis(Hand.kRight) >= Constants.AXIS_THRESHOLD ){ 
      shooter.ShooterOn();
    }
    // Otherwise turn it off
    else{
      shooter.ShooterOff();
    }

    // Elevator servo control
    if (coPilot.getPOV() == Constants.POV_NORTH){
      shooter.incrementShooterAngle();
    }
    else if (coPilot.getPOV() == Constants.POV_SOUTH){
      shooter.decrementShooterAngle();
    }
    
    /* ---- Loader ---- */
    // If the coPilot wishes to load/unload (the right bumper is pressed)
    if (coPilot.getBumper(Hand.kRight)){ 
      // If the X button is pressed, then reverse the loader
      if (coPilot.getXButton()){
        loader.setSpeedReverse(Constants.LOADER_SPEED_REVERSE);
      }
      // Otherwise, load regularly
      else{
        loader.setSpeed(Constants.LOADER_SPEED);
      }
    }
    // Otherwise, turn off the loader
    else{
      loader.setSpeed(0.0);
    }

    /* ---- Elevator ---- */

    // If the copilot presses both back and start, then unlock
    if (coPilot.getBackButton() && coPilot.getStartButton()){
      elevator.unlockElevator();
    }
    // If the copilot presses the b button, then lock
    else if (coPilot.getBButton()){
      elevator.lockElevator();
    }
    
    // If the elevator isn't locked, 
    if (!elevator.isLocked()){
      // Then control the elevator speed according to the copilot right y axis
      elevator.setSpeed(coPilot.getY(Hand.kRight));
    }
    // Otherwise, turn off the elevator
    else{
      elevator.setSpeed(0.0);
    }

    /* ---- Hook ---- */

    // If the pilot presses the left POV, move the hook left
    if(pilot.getPOV() == Constants.POV_EAST){
      hook.moveLeft();
    }
    // If the pilot presses the right POV, move the hook right
    else if(pilot.getPOV() == Constants.POV_WEST){
      hook.moveRight();
    }
    // Otherwise, turn off the hook
    else{
      hook.stop();
    }

    /* ---- Spinner ---- */

    // If the coPilot presses the a button, toggle the arm
    if(coPilot.getAButtonPressed()){
      spinner.toggleArmExtended();
    }

    // If the spinner is extended
    if(spinner.isExtended()){
      // Then set the speed according to the copilot
      spinner.setSpinnerSpeed(coPilot.getX(Hand.kLeft));
    }
    // Otherwise turn off the motor
    else{
      spinner.setSpinnerSpeed(0.0);
    }
   
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }

}
