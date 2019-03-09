/**
 * Intake.java
 * a very small subsystem.
 */

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

import frc.robot.commands.IntakeDefault;

public class Intake extends Subsystem {
  
  public SpeedController motor_lift = new Spark(1);
  public SpeedController motor_collector = new Spark(2);
  
  public double global_lift_speed = 0.8;
  
  public Intake() {
    super();
    motor_lift.setInverted(true);
    motor_collector.setInverted(true);
  }
  
  public void liftAt(double vel) {
    motor_lift.set(global_lift_speed * vel);
  }

  public void collectAt(double vel) {
    motor_collector.set(vel);
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new IntakeDefault());
  }
}
