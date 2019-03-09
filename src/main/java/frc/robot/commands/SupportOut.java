/**
 * IntakeDefault.java
 */

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.TimedCommand;
import frc.robot.Robot;

public class SupportOut extends TimedCommand {
  public SupportOut() {
    super(0.02);
  }

  @Override
  protected void execute() {
    Robot.chassis.toggleLift(true);
    Robot.lift_state = true;
    
  }
}
