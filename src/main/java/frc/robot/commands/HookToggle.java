/**
 * IntakeDefault.java
 */

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class HookToggle extends Command {
  public HookToggle() {
  }

  @Override
  protected void execute() {
    Robot.hook.toggleHook(true);
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    Robot.hook.toggleHook(false);
  }
}
