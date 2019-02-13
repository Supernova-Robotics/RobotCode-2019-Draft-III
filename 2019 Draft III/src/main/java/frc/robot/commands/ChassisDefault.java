/**
 * ChassisDefault.java
 */

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.Robot;

public class ChassisDefault extends Command {
  public ChassisDefault() {
    requires(Robot.chassis);
  }

  @Override
  protected void execute() {
    double[] val = OI.getDriveAxis();
    Robot.chassis.drive(val[0], val[1]);
  }

  @Override
  protected boolean isFinished() {
    return false;
  }
}
