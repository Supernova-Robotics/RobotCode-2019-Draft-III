/**
 * ChassisDefault.java
 */

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ToTop extends CommandGroup {
  public ToTop() {
    addParallel(new ArmLiftTo(5000));
    addSequential(new Delay(1));
    addParallel(new ClawLiftTo(0));
  }
}
