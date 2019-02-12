/**
 * ChassisDefault.java
 */

package frc.robot.commands;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;

import frc.robot.OI;
import frc.robot.Robot;

public class ArmLiftTo extends Command {
  public final double threshold = 10;   // 当实际位置和设定位置在这个误差以内，结束这段命令
  public final double kP = 0.0001, kI = 0., kD = 0.;    // PID 系数，现在只用比例P
  public double tar_, vel, error;
  public ArmLiftTo(double tar) {
    tar_ = tar;
  }

  @Override
  protected void execute() {
    /* 0212: PID算法，现在只加了P，先试试效果 */
    error = tar_ - Robot.arm.getArmPos();
    vel = kP * error;

    /* 先 Print 出来值，不传给电机； 调试完之后可以把最后一行取消注释 */
    System.out.println(vel);
    // Robot.arm.setArmVel(vel);
  }


  @Override
  protected boolean isFinished() {
    /* */
    return Math.abs(tar_ - Robot.arm.getArmPos()) < threshold;
  }

  @Override
  protected void end() {
    
  }
}
