/**
 * IntakeDefault.java
 */

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.Robot;

public class IntakeLiftTo extends Command {
  private static final double threshold = 3;
  private double angle_;

  public IntakeLiftTo(double angle) {
    angle_ = angle;
  }

  @Override
  protected void execute() {
    if (angle_ < Robot.intake.getLiftPos()) {
      Robot.intake.setLiftVel(-0.4);
    } else {
      Robot.intake.setLiftVel(0.5);
    }
  }

  @Override
  protected boolean isFinished() {
    return Math.abs(angle_ - Robot.intake.getLiftPos()) < threshold
          || OI.getIntakeAxis() != 0;
  }

  @Override
  protected void end() {
    Robot.intake.setLiftVel(0);
  }

  @Override
  protected void interrupted() {
    end();
  }
}
