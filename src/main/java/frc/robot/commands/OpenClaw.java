/**
 * OpenClaw.java
 */

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import frc.robot.Robot;

public class OpenClaw extends Command {
  public OpenClaw() {
    super();
    /* DO NOT add requires() here. it will lead to conflict
    to the PushClaw command */
  }

  @Override
  protected void execute() {
    Robot.arm.clawToggle(true);
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    Robot.arm.clawToggle(false);
  }
}
