/**
 * Test.java
 * this command is for testing various things. just ignore it.
 */
package frc.robot.commands;

import edu.wpi.first.wpilibj.command.TimedCommand;

import frc.robot.Robot;

public class Test extends TimedCommand {
  public Test() {
    super(1);
    requires(Robot.arm);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    System.out.println("running");
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.arm.clawLiftAt(0);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    Robot.arm.clawLiftAt(0);
  }
}
