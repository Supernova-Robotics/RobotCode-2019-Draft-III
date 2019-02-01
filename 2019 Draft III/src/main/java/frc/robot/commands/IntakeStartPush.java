/**
 * IntakeStartPush.java
 */

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import frc.robot.Robot;

public class IntakeStartPush extends Command {
  public IntakeStartPush() {
    requires(Robot.intake);
  }

  @Override
  protected void execute() {
    Robot.intake.collectAt(-0.4);
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    Robot.intake.collectAt(0);
  }

  @Override
  protected void interrupted() {
    Robot.intake.collectAt(0);
  }
}
