/**
 * IntakeDefault.java
 */

package frc.robot.commands;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.Robot;

public class IntakeLiftTo extends Command {
  public static final double threshold = 3;
  public double angle_;

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
     || Math.abs(OI.stick_0.getY(Hand.kRight)) > 0.2;
  }

  @Override
  protected void end() {
    Robot.intake.setLiftVel(0);
  }
}
