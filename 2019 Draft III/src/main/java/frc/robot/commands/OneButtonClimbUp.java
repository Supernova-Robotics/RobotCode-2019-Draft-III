/**
 * IntakeDefault.java
 */

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class OneButtonClimbUp extends Command {
  public OneButtonClimbUp() {
    super(4);
  }

  @Override
  protected void execute() {
    Robot.chassis.toggleLift(true);
    Robot.lift_state = true;
    Robot.intake.setLiftVel(-1);
  }

  @Override
  protected boolean isFinished() {
    return Robot.intake.getLimit() || isTimedOut();
  }

  @Override
  protected void end() {
  }

  @Override
  protected void interrupted() {
    end();
  }
}
