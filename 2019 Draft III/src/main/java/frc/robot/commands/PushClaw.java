/**
 * PushClaw.java
 */

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.TimedCommand;

import frc.robot.Robot;

public class PushClaw extends TimedCommand {
  boolean _state;
  public PushClaw() {
    super(0.5);
    /* DO NOT add requires() here. it will lead to conflict
    to the OpenClaw command */
  }

  @Override
  protected void execute() {
    Robot.arm.setPusher(true);
  }

  @Override
  protected void end() {
    Robot.arm.setPusher(false);
  }

  @Override
  protected void interrupted() {
    Robot.arm.setPusher(false);
  }
}
