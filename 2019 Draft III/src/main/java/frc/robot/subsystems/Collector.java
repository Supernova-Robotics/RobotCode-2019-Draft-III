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

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class Collector extends Subsystem {
  
  static private SpeedController collector_lift_motor = new Spark(2);
  static private SpeedController collector_motor = new Spark(3);

  public double collector_speed = 1.0;
  public double collect_speed = 1.0;

  public void setGear(double _collector_speed, double _collect_speed) {
    collector_speed = _collector_speed;
    collect_speed = _collect_speed;
  }

  public void collectorLiftAt(double vel) {
    collector_lift_motor.set(collector_speed * vel);
  }

  public void collectorTurnAt(double vel) {
    collector_motor.set(collect_speed * vel);
  }

  @Override
  public void initDefaultCommand() {
    // collectorLiftAt(OI.stick_0.getY(Hand.kRight));
  }
}
