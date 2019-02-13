/**
 * ChassisDefault.java
 */

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.Robot;

public class ArmLiftTo extends Command {
  private static final double threshold = 10;
  private double tar_;

  public ArmLiftTo(double tar) {
    tar_ = tar;
  }

  @Override
  protected void execute() {
    /* 0212: PID算法，现在只加了P，先试试效果 */
    Robot.arm.setTarget(tar_);
    Robot.arm.setVel(Robot.arm.getPID());
  }


  @Override
  protected boolean isFinished() {
    return Math.abs(tar_ - Robot.arm.getPos()) < threshold
          || OI.getArmAxis() != 0;
  }

  @Override
  protected void end() {
    Robot.arm.setVel(0);
  }

  @Override
  protected void interrupted() {
    end();
  }
}
