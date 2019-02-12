/** 
 * ArmDefault.java
 */

package frc.robot.commands;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;

import frc.robot.OI;
import frc.robot.Robot;

public class ArmDefault extends Command {
  public ArmDefault() {
    requires(Robot.arm);
  }

  @Override
  protected void execute() {
    
    Robot.arm.setArmVel(-OI.stick_1.getY(Hand.kLeft));
    Robot.arm.setClawVel(0.15 + -0.5 * OI.stick_1.getY(Hand.kRight));
  }

  @Override
  protected boolean isFinished() {
    return false;
  }
}
