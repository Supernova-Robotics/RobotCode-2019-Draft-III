/**
 * IntakeDefault.java
 */

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Const;
import frc.robot.Robot;

public class IntakeShoot extends Command {
  public IntakeShoot() {
  }

  @Override
  protected void execute() {
    Robot.intake.setCollectorVel(Const.intake_speed[1]);
    Robot.claw.setShooterVel(Const.shooter_speed[1]);
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

  @Override
  protected void interrupted() {
    end();
  }
}