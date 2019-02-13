/** 
 * ArmDefault.java
 */

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.Robot;

public class ClawDefault extends Command {
  public ClawDefault() {
    requires(Robot.claw);
  }

  @Override
  protected void execute() {
    if (Robot.claw.enable_pid) {
      Robot.claw.adjustSetpoint(OI.getClawAxis());
      Robot.claw.setVel(Robot.claw.getPID());
    } else {
      Robot.claw.setVel(OI.getClawAxis());
    }
  }

  @Override
  protected boolean isFinished() {
    return false;
  }
}
