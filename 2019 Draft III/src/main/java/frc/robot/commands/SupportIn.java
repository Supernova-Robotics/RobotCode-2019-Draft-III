/**
 * IntakeDefault.java
 */

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.TimedCommand;
import frc.robot.Robot;

public class SupportIn extends TimedCommand {

  public SupportIn() {
    super(0.02);
  }

  @Override
  protected void execute() {
    Robot.chassis.toggleLift(false);
    Robot.lift_state = false;
    
  }
}
