/**
 * Hook.java
 * an even smaller subsystem.
 */

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Hook extends Subsystem {
  public Solenoid hook_extender = new Solenoid(4);
  public Solenoid hook = new Solenoid(5);
  
  public Hook() {
    super();
  }

  public void toggleGrab(boolean state) {
    hook.set(state);
  }

  public void toggleExtension(boolean state) {
    hook_extender.set(state);
  }
  
  @Override
  public void initDefaultCommand() {
  }
}
