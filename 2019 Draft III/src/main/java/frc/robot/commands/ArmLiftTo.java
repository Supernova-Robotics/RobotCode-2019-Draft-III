/**
 * ArmLiftTo.java
 */

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import frc.robot.Robot;

public class ArmLiftTo extends Command {
  double tar_ang;
  public ArmLiftTo(double target_ang) {
    requires(Robot.arm);
    tar_ang = target_ang;
  }

  @Override
  protected void initialize() {
    Robot.arm.setArmSetpoint(tar_ang);
  }

  @Override
  protected void execute() {
    double output = Robot.arm.calculateArmPID();
    Robot.arm.armLiftAt(output);
  }

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
