/**
 * IntakeDefault.java
 */

package frc.robot.commands;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;

import frc.robot.OI;
import frc.robot.Robot;

public class IntakeDefault extends Command {
  public IntakeDefault() {
    requires(Robot.intake);
  }

  @Override
  protected void execute() {
    Robot.intake.liftAt(-OI.stick_0.getY(Hand.kRight));
  }

  @Override
  protected boolean isFinished() {
    return false;
  }
}
