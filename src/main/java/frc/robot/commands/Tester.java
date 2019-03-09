/**
 * IntakeDefault.java
 */

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

public class Tester extends Command {
  public Tester() {
  }

  @Override
  protected void execute() {
    System.out.println("tes");
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
  }
}
