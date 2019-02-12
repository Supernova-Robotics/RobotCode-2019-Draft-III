/**
 * HookGrab.java
 */
package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import frc.robot.Robot;

public class HookGrab extends Command {
  public HookGrab() {
    super();
    /* DO NOT add requires() here. it will lead to conflict
    to the HookExtend command */
  }
  
  @Override
  protected void execute() {
    Robot.hook.toggleGrab(true);
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    Robot.hook.toggleGrab(false);
  }
}
