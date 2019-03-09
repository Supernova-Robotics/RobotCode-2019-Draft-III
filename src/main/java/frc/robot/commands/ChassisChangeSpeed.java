/**
 * ChassisDefault.java
 */

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ChassisChangeSpeed extends Command {
  int speed_;
  public ChassisChangeSpeed(int speed) {
    speed_ = speed;
  }

  @Override
  protected void execute() {
    Robot.chassis.speed_mode = speed_;
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    Robot.chassis.speed_mode = 0;
  }

  @Override
  protected void interrupted() {
    end();
  }
}
