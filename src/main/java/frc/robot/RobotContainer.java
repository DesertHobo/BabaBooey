/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.*;
import frc.robot.constants.*;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj2.command.Command;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {


  // The robot's subsystems and commands are defined here...
  //private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  private final AutoDrive m_AutoDrive = new AutoDrive(
    new CANSparkMax(WiringConstants.DRIVE_LEFT_1_PORT, MotorType.kBrushless), 
    new CANSparkMax(WiringConstants.DRIVE_LEFT_2_PORT, MotorType.kBrushless), 
    new CANSparkMax(WiringConstants.DRIVE_LEFT_3_PORT, MotorType.kBrushless), 
    new CANSparkMax(WiringConstants.DRIVE_RIGHT_1_PORT, MotorType.kBrushless), 
    new CANSparkMax(WiringConstants.DRIVE_RIGHT_2_PORT, MotorType.kBrushless), 
    new CANSparkMax(WiringConstants.DRIVE_RIGHT_3_PORT, MotorType.kBrushless),
    new DoubleSolenoid(WiringConstants.DRIVE_GEAR_CHANGE_A, WiringConstants.DRIVE_GEAR_CHANGE_B));

  //private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);
  private final DriveDistance driveStraight = new DriveDistance(Constants.AUTO_DRIVE_DISTANCE, Constants.AUTO_DRIVE_SPEED, m_AutoDrive);



  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return driveStraight;
  }
}
