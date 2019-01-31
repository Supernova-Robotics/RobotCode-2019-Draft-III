/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.TimedCommand;
import frc.robot.Robot;

/**
 * An example command.  You can replace me with your own command.
 */
public class PushClaw extends TimedCommand {
  boolean _state;
  public PushClaw() {
    super(0.5);
  }

  // Called just before this Command runs the first time
  @Override
  protected void execute() {
    Robot.arm.setPusher(true);
  }

  @Override
  protected void end() {
    Robot.arm.setPusher(false);
  }

  @Override
  protected void interrupted() {
    Robot.arm.setPusher(false);
  }
}
