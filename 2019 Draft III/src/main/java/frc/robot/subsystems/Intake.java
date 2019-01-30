/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Subsystem;

import frc.robot.OI;
import frc.robot.RobotMap;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class Intake extends Subsystem {
  
  static private SpeedController collector_lift_motor = new Spark(RobotMap.p_intake_lift);
  static private SpeedController collector_motor = new Spark(RobotMap.p_intake_collector);

  public double global_lift_speed;
  public double global_collect_speed;

  public Intake() {
    reset();
  }

  public void reset() {
    global_lift_speed = 1.0;
    global_collect_speed = 1.0;
  }

  public void setGear(double collector_speed, double collect_speed) {
    global_lift_speed = collector_speed;
    global_collect_speed = collect_speed;
  }

  public void collectorLiftAt(double vel) {
    collector_lift_motor.set(global_lift_speed * vel);
  }

  public void collectorRunAt(double vel) {
    collector_motor.set(global_collect_speed * vel);
  }

  @Override
  public void initDefaultCommand() {
    collectorLiftAt(-OI.stick_0.getY(Hand.kRight));
  }
}
