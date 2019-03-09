/**
 * IntakeDefault.java
 */

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ClimbAdvance extends Command {
  public ClimbAdvance() {
    
  }

  @Override
  protected void execute() {
    Robot.chassis.drive(0.1, 0);
    Robot.chassis.setRollerVel(0.2);
    Robot.intake.setCollectorVel(0.3);
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    Robot.chassis.drive(0, 0);
    Robot.chassis.setRollerVel(0);
    Robot.intake.setLiftVel(0);
    Robot.intake.setCollectorVel(0);
  }

  @Override
  protected void interrupted() {
    end();
  }
}
