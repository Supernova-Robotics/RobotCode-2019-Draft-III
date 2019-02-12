/**
 * Intake.java
 * a very small subsystem.
 */

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.IntakeDefault;

public class Intake extends Subsystem {
  
  public SpeedController motor_lift = new Spark(RobotMap.p_PWM_intake_lift);
  public SpeedController motor_collector = new Spark(RobotMap.p_PWM_intake_collector);
  public AnalogInput lift_sensor = new AnalogInput(RobotMap.p_ANA_intake_encoder);
  public double global_lift_speed = 0.8;
  
  public Intake() {
    super();
    /* upward is positive */
    motor_lift.setInverted(true);
    /* inward is positive */
    motor_collector.setInverted(true);
  }

  public double getLiftPos() {
    return lift_sensor.getAverageValue();
  }
  
  public void setLiftVel(double vel) {
    motor_lift.set(global_lift_speed * vel);
  }

  public void setCollectorVel(double vel) {
    motor_collector.set(vel);
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new IntakeDefault());
  }
}
