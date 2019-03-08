/**
 * Intake.java
 * a very small subsystem.
 */

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Const;
import frc.robot.RobotMap;
import frc.robot.commands.IntakeDefault;

public class Intake extends Subsystem {
  private SpeedController motor_lift = new Spark(RobotMap.p_PWM_intake_lift);
  private SpeedController motor_collector = new Spark(RobotMap.p_PWM_intake_collector);

  private DigitalInput limit_sw = new DigitalInput(RobotMap.p_DIG_intake_limit);
  
  public Intake() {
    super();
    /* upward is positive */
    motor_lift.setInverted(true);
    /* inward is positive */
    motor_collector.setInverted(true);
  }

  public boolean getLimit() {
    return !limit_sw.get();
  }
  
  public void setLiftVel(double vel) {
    if (vel < 0) {
      if (!limit_sw.get()) {
        motor_lift.set(0);
      } else {
        motor_lift.set(Const.global_lift_speed * vel);
      }
    } else {
      motor_lift.set(Const.global_lift_speed * vel);
    }
  }

  public void setCollectorVel(double vel) {
    motor_collector.set(vel);
  }

  public void log() {
    SmartDashboard.putBoolean("Intake Limit", !limit_sw.get());
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new IntakeDefault());
  }
}
