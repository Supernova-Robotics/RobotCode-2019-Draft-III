/**
 * IntakeDefault.java
 */

package frc.robot.commands;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.Robot;

public class IntakeCollect extends Command {
  public double vel_;

  public IntakeCollect(double vel) {
    vel_ = vel;
  }

  @Override
  protected void execute() {
    Robot.intake.setCollectorVel(vel_);
    Robot.arm.setShooterVel(vel_);
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    Robot.intake.setCollectorVel(0);
    Robot.arm.setShooterVel(0);
  }
}
