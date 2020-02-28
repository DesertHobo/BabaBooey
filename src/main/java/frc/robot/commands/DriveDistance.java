/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.subsystems.AutoDrive;;

public class DriveDistance extends CommandBase {
  private final AutoDrive m_drive;
  private final double m_distance;
  private final double m_speed;

  /**
   * Creates a new DriveDistance.
   *
   * @param inches The number of inches the robot will drive
   * @param speed The speed at which the robot will drive
   * @param drive The drive subsystem on which this command will run
   */
  public DriveDistance(double meters, double speed, AutoDrive drive) {
    m_distance = meters;
    m_speed = speed;
    m_drive = drive;
  }

  @Override
  public void initialize() {
    m_drive.resetEncoders();
    m_drive.arcadeDrive(m_speed, 0);
  }

  /**
   * When isFinished is true, this method is called.
   * Stop the robot
   */
  @Override
  public void end(boolean interrupted) {
    m_drive.arcadeDrive(0, 0);
  }

  /**
   * If the robot passes a certain distance, return true
   */
  @Override
  public boolean isFinished() {
    return Math.abs(m_drive.getAverageEncoderPosition()) >= m_distance;
  }
}
