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

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.TimedRobot;
import frc.robot.subsystems.*;
import frc.robot.constants.WiringConstants;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {

  private Spinner spinner;
  private Elevator elevator;
  private Feeder feeder;
  private Hook hook;
  private Loader loader;
  private Shooter shooter;
  private Drivetrain drivetrain;


  /**
   * This function is run when the robot is first started up and should be used
   * for any initialization code.
   */
  @Override
  public void robotInit() {

    this.spinner = new Spinner(new WPI_VictorSPX(WiringConstants.COLOR_PANEL_PORT));

    this.elevator = new Elevator(new CANSparkMax(WiringConstants.ELEVATOR_LEFT_PORT, MotorType.kBrushless), 
      new CANSparkMax(WiringConstants.ELEVATOR_RIGHT_PORT, MotorType.kBrushless), 
      new DoubleSolenoid(WiringConstants.ELEVATOR_LOCK_A, WiringConstants.ELEVATOR_LOCK_B));

    this.feeder = new Feeder(new WPI_VictorSPX(WiringConstants.INTAKE_PORT));

    this.hook = new Hook(new CANSparkMax(WiringConstants.HOOK_PORT, MotorType.kBrushless));
    
    this.loader = new Loader(new WPI_TalonSRX(WiringConstants.VERTICAL_LOADER_PORT), 
      new WPI_TalonSRX(WiringConstants.HORIZONTAL_LOADER_PORT));

    this.shooter = new Shooter(new CANSparkMax(WiringConstants.SHOOTER_LEFT_PORT, MotorType.kBrushless),
      new CANSparkMax(WiringConstants.SHOOTER_RIGHT_PORT, MotorType.kBrushless),
      new Servo(WiringConstants.LEFT_SERVO_PWM_PORT), 
      new Servo(WiringConstants.LEFT_SERVO_PWM_PORT));

    this.drivetrain = new Drivetrain(new CANSparkMax(WiringConstants.DRIVE_LEFT_1_PORT, MotorType.kBrushless), 
      new CANSparkMax(WiringConstants.DRIVE_LEFT_2_PORT, MotorType.kBrushless), 
      new CANSparkMax(WiringConstants.DRIVE_LEFT_3_PORT, MotorType.kBrushless), 
      new CANSparkMax(WiringConstants.DRIVE_RIGHT_1_PORT, MotorType.kBrushless), 
      new CANSparkMax(WiringConstants.DRIVE_RIGHT_2_PORT, MotorType.kBrushless), 
      new CANSparkMax(WiringConstants.DRIVE_RIGHT_3_PORT, MotorType.kBrushless));
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
    
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
