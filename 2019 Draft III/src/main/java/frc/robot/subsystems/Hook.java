/**
 * Hook.java
 * an even smaller subsystem.
 */

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class Hook extends Subsystem {
  public Solenoid hook_extender = new Solenoid(RobotMap.p_PEN_hook_extender);
  public Servo hook = new Servo(RobotMap.p_PWM_hook_servo);
  
  public static final double servo_open_ang = 0.3;
  public static final double servo_close_ang = 0.9;

  public Hook() {
    super();
  }

  public void toggleHook(boolean state) {
    if(state) {
      hook.set(servo_open_ang);
    } else {
      hook.set(servo_close_ang);
    }
  }

  public void toggleExtension(boolean state) {
    hook_extender.set(state);
  }
  
  @Override
  public void initDefaultCommand() {
  }
}
