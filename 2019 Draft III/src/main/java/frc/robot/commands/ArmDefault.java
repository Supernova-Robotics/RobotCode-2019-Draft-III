/** 
 * ArmDefault.java
 */

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.Robot;

public class ArmDefault extends Command {
  public ArmDefault() {
    requires(Robot.arm);
  }

  @Override
  protected void execute() {
    Robot.arm.setVel(OI.getArmAxis());
  }

  @Override
  protected boolean isFinished() {
    return false;
  }
}
