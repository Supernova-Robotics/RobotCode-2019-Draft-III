/**
 * IntakeDefault.java
 */

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class IntakeCollect extends Command {
  private double vel_;

  public IntakeCollect(double vel) {
    vel_ = vel;
  }

  @Override
  protected void execute() {
    Robot.intake.setCollectorVel(vel_);
    Robot.claw.setShooterVel(vel_);
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    Robot.intake.setCollectorVel(0);
    Robot.claw.setShooterVel(0);
  }
}
