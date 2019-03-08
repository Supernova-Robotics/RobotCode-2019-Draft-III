/**
 * IntakeDefault.java
 */

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ExtensionToggle extends Command {
  public ExtensionToggle() {
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
