/**
 * Hook.java
 * an even smaller subsystem.
 */

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;

public class Hook extends Subsystem {
  private Solenoid hook_extender = new Solenoid(RobotMap.p_PEN_hook_extender);
  private Servo hook = new Servo(RobotMap.p_PWM_hook_servo);
  
  public static final double servo_open_ang = RobotMap.hook_servo_open_ang;
  public static final double servo_close_ang = RobotMap.hook_servo_close_ang;

  public Hook() {
    super();
  }

  public boolean getHookState() {
    return hook.get() < (servo_close_ang + servo_open_ang) / 2;
  }

  public boolean getExtensionState() {
    return hook_extender.get();
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

  public void log() {
    SmartDashboard.putBoolean("Hook Open", getHookState());
    SmartDashboard.putBoolean("Extension Out", getExtensionState());
  }
  
  @Override
  public void initDefaultCommand() {
  }
}
