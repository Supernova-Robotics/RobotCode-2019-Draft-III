/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.Robot;

/**
 * An example command.  You can replace me with your own command.
 */
public class ArmLiftTo extends Command {
  double tar_ang;
  public ArmLiftTo(double target_ang) {
    requires(Robot.arm);
    tar_ang = target_ang;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.arm.setArmSetpoint(tar_ang);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double output = Robot.arm.calculateArmPID();
    Robot.arm.armLiftAt(output);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return Math.abs(Robot.arm.getArmAngle() - tar_ang) < 10;
  }

  @Override
  protected void end() {
    Robot.arm.armLiftAt(0);
  }

  @Override
  protected void interrupted() {
    Robot.arm.armLiftAt(0);
  }
}
