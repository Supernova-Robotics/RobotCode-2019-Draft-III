/**
 * HookExtend.java
 */
package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import frc.robot.Robot;

public class HookExtend extends Command {
  public HookExtend() {
    super();
    /* DO NOT add requires() here. it will lead to conflict
    to the HookGrab command */
  }

  @Override
  protected void execute() {
    Robot.hook.toggleExtension(true);
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    Robot.hook.toggleExtension(false);
  }
}
