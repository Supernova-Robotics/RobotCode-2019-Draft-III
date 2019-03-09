/**
 * ChassisDefault.java
 */

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.TimedCommand;
import frc.robot.Robot;

public class RobotChangeState extends TimedCommand {
  private boolean state_;
  public RobotChangeState(boolean state) {
    super(0.02);
    state_ = state;
  }
  @Override
  protected void execute() {
    Robot.lift_state = state_;
  }

  @Override
  protected boolean isFinished() {
    return false;
  }
}
