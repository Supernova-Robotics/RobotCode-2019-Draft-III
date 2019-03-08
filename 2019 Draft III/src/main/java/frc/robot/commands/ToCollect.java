/**
 * ChassisDefault.java
 */

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ToCollect extends CommandGroup {
  public ToCollect() {
    addParallel(new ArmLiftTo(0));
    addParallel(new ClawLiftTo(0));
  }
}
