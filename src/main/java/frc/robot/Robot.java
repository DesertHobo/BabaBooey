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


  //Dirve variables//
  double magnitude = 0; // representing of the magnitude parameter for arcadeDrive
  double turn = 0; // representing of the turn parameter for arcadeDrive
  boolean isHighGear = false; // this value keeps track of whether the robot is in high gear

  //Shooter variables//
  double shooterAngle = 0; // this value keeps track of whether the robot's servos are extended
  boolean shooterIsActive = false; // this value keeps track of whether the robot's shooter is on
  //Spinner variables//
  boolean spinnerDeployed = false; // this value keeps track of whether the robot's spinner is deployed
  //elevator variable//
  boolean elevatorExtended = false; //this tells you if the elevator is extended 
  boolean elevatorMoving = false;
  boolean elevatorLocked = false; //this tells you if the elevator is locked

  //mode variables
  boolean forwardEnabled = true;
  boolean climbEnabled = false;

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {

    //Drivers Controls//
    // Needs to use forwardEnabled (note that also turns needs to be reversed in opposite face)
    if (Math.abs(pilot.getY()) >= Constants.AXIS_THRESHOLD){ /** If the value on the Y-axis is above the required threshold then the y value will be fed into the ArcadeDrive */
      magnitude = pilot.getY();                                                                                       
    }
    else{
      magnitude = 0;
    }
    if(pilot.getX() >= Constants.AXIS_THRESHOLD || pilot.getX() <= -Constants.AXIS_THRESHOLD){ /** If the value on the X-axis is above the required threshold then the y value will be fed into the ArcadeDrive */
      if(isHighGear){
        turn =  Constants.X_VALUE_REDUCTION * pilot.getX(); 
      }
      else{
        turn =  pilot.getX();         
      }                                                                                 
    }
    else{
      turn = 0;
    }
    drive.arcadeDrive(magnitude, turn); //After the magnitude and turn values are updated, they are fed into the arcade drive

    if (pilot.getBumperPressed(Hand.kLeft)){
      drive.toggleGearSpeed();
    }
    
    // if(pilot.getBumperPressed(Hand.kLeft) && isHighGear){ // If the left bumper is pressed the robot switches into low gear
    //   isHighGear = !isHighGear;
    //   drive.setLowGear();
    // }
    // if(pilot.getBumperPressed(Hand.kRight) && !isHighGear){ // if the right bumper is pressed the robot switches into high gear
    //   isHighGear = !isHighGear;
    //   drive.setHighGear();

    // }

    //only in climb mode//

    //--- Hook --- // CLIMB
    if(climbEnabled) {
      if(pilot.getPOV() == 270 ){ //Hook moves Left if this combination is recorded
        hook.moveLeft();
      }
      if(pilot.getPOV() == 90){ //Hook moves Right if this combination is recorded 
        hook.moveRight();
      }
      if(pilot.getPOV() == -1){ // Hook stops if this combination is recorded 
        hook.stop();
      }
    }
    
    //Co Pilot Controls//
    
    //mode switch between forward and backward
    if(coPilot.getYButtonPressed()) {
      forwardEnabled = !forwardEnabled;
    }

    //mode switch to climb
    if(coPilot.getStartButtonPressed() && !climbEnabled) {
      forwardEnabled = true;
      climbEnabled = true;
    }
   
    //--- Elevator ---// CLIMB
    if(climbEnabled && !forwardEnabled) {
        if(coPilot.getXButtonPressed()){ //Elevator is toggled when the x button is pressed (X)
          elevator.toggleElevatorLock();
        }
       if(coPilot.getTriggerAxis(Hand.kRight) >= Constants.AXIS_THRESHOLD && coPilot.getTriggerAxis(Hand.kLeft) < Constants.AXIS_THRESHOLD 
          && !elevatorLocked) {
          elevator.setSpeed(Constants.ELEVATOR_EXTEND_SPEED);
          elevatorMoving = true;
       } 
       else if(coPilot.getTriggerAxis(Hand.kRight) < Constants.AXIS_THRESHOLD && coPilot.getTriggerAxis(Hand.kLeft) >= Constants.AXIS_THRESHOLD 
          && !elevatorLocked) {
          elevator.setSpeed(Constants.ELEVATOR_RETRACT_SPEED);
          elevatorMoving = true;
       }
       else{
          elevator.setSpeed(0);
          elevatorMoving = false;
       }
    }
  
    // --- Shooter --- // BACKWARD

    if(!climbEnabled && !forwardEnabled) {
      if(coPilot.getTriggerAxis(Hand.kRight) >= Constants.AXIS_THRESHOLD ){ //Turns the Shooter on if the right trigger is pressed (RT)
        shooter.ShooterOn();
        loader.setSpeed(Constants.LOADER_SPEED);
       shooterIsActive = true;
      }
      else{//Turns the Shooter off if the right trigger is released 
        shooter.ShooterOff();
        shooterIsActive = false;
      }

    //SERVOS// BACKWARD
     if(!climbEnabled && coPilot.getPOV() == 90){ 
        shooterAngle = shooterAngle + Constants.SERVO_INCREMENT_VALUE;
        shooter.SetShooterAngle(shooterAngle);
     }
     if(!climbEnabled && coPilot.getPOV() == 0){ 
        shooterAngle = shooterAngle - Constants.SERVO_REDUCTION_VALUE;
        shooter.SetShooterAngle(shooterAngle);
     }
    }

    // --- Intake --- //  FORWARD
    if(!climbEnabled && forwardEnabled) {
      if(coPilot.getTriggerAxis(Hand.kRight) >= Constants.AXIS_THRESHOLD && !coPilot.getXButton()){ 
        intake.intake();
      }
      else if(coPilot.getTriggerAxis(Hand.kRight) >= Constants.AXIS_THRESHOLD && coPilot.getXButton()){ 
        intake.reverseIntake();
      }
      else{
        intake.stopIntake();
      }
    }

    // --- Loader --- //
    if(!climbEnabled) {
      if(coPilot.getTriggerAxis(Hand.kRight) >= Constants.AXIS_THRESHOLD && !coPilot.getXButton()) {
        loader.setSpeed(Constants.LOADER_SPEED);
      }
      else if(coPilot.getTriggerAxis(Hand.kRight) >= Constants.AXIS_THRESHOLD && coPilot.getXButton()) {
        loader.setSpeedReverse(Constants.LOADER_SPEED_REVERSE);
      }
      else{
        loader.setSpeed(0.0);
      }

    }

    // --- Spinner --- // FORWARD
    if(!climbEnabled && !forwardEnabled) {
    if(coPilot.getBumper(Hand.kLeft) && spinnerDeployed){ //Turns the Spinner motor on once the left bumper is pressed 
      spinner.spinnerOn();
    }
    else{ //Turns the Spinner motor off once the left bumper is released 
      spinner.spinnerOff();
    }
    if(coPilot.getXButtonPressed()){ //Toggle for the piston which extends the shooter onto the control pannel 
      spinner.toggleArmExtended();
    }
   }
  


    // --- Loader --- // FORWARD
    //supposed to be automatic
     
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }

}
