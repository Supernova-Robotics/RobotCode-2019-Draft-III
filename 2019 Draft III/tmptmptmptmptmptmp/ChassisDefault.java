/**
 * ChassisDefault.java
 */

package frc.robot.commands;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;

import frc.robot.OI;
import frc.robot.Robot;

public class ChassisDefault extends Command {
  public ChassisDefault() {
    requires(Robot.chassis);
  }

  @Override
  protected void execute() {
    Robot.chassis.drive(-OI.stick_0.getY(Hand.kLeft), OI.stick_0.getTriggerAxis(Hand.kLeft) - OI.stick_0.getTriggerAxis(Hand.kRight));
  }

  @Override
  protected boolean isFinished() {
    return false;
  }
}
