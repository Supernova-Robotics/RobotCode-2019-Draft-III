/**
 * ClawLiftTo.java
 * NOT TESTED! DO NOT USE!
 */

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import frc.robot.Robot;

public class ClawLiftTo extends Command {
  double tar_ang;
  public ClawLiftTo(double target_ang) {
    requires(Robot.arm);
    tar_ang = target_ang;
  }

  @Override
  protected void initialize() {
    Robot.arm.setClawSetpoint(tar_ang);
  }

  @Override
  protected void execute() {
    double output = Robot.arm.calculateClawPID();
    Robot.arm.clawLiftAt(output);
  }

  @Override
  protected boolean isFinished() {
    return Math.abs(Robot.arm.getClawAngle() - tar_ang) < 10;
  }

  @Override
  protected void end() {
    Robot.arm.clawLiftAt(0);
  }

  @Override
  protected void interrupted() {
    Robot.arm.clawLiftAt(0);
  }
}
