/**
 * ChassisDefault.java
 */

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.Robot;

public class ClawLiftTo extends Command {
  private static final double threshold = 10;
  private double tar_;

  public ClawLiftTo(double tar) {
    super(2.5);
    requires(Robot.claw);
    tar_ = tar;
  }

  @Override
  protected void execute() {
    Robot.claw.setSetpoint(tar_);
    Robot.claw.setVel(Robot.claw.getPID());
  }


  @Override
  protected boolean isFinished() {
    return Math.abs(tar_ - Robot.claw.getPos()) < threshold
          || OI.getClawAxis() != 0 || isTimedOut();
  }

  @Override
  protected void end() {
    Robot.claw.setVel(0);
  }

  @Override
  protected void interrupted() {
    end();
  }
}
