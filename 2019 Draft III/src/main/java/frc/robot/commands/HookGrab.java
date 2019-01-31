/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class HookGrab extends Command {
  public HookGrab() {
    super();
    // Cannot use requires(subsystem) here, as the program needs to be run parallely
  }
  
  @Override
  protected void execute() {
    Robot.hook.toggleGrab(true);
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    Robot.hook.toggleGrab(false);
  }
}
