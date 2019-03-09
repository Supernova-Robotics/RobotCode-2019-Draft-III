/**
 * ChassisDefault.java
 */

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ToMiddle extends CommandGroup {
  public ToMiddle() {
    addParallel(new ArmLiftTo(2900));
    addSequential(new Delay(1));
    addParallel(new ClawLiftTo(-800));
  }
}
