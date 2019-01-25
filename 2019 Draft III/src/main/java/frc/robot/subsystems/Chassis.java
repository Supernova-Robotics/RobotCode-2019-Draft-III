/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Subsystem;


import frc.robot.OI;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class Chassis extends Subsystem {
  
  static private SpeedController left_motor_0 = new WPI_TalonSRX(10);
  static private SpeedController left_motor_1 = new WPI_TalonSRX(11);
  static private SpeedController left_motor_2 = new WPI_TalonSRX(12);
  static private SpeedController right_motor_0 = new WPI_TalonSRX(13);
  static private SpeedController right_motor_1 = new WPI_TalonSRX(14);
  static private SpeedController right_motor_2 = new WPI_TalonSRX(15);

  static private Solenoid chassis_lift = new Solenoid(2);

  public double y_speed = 1.0;
  public double z_speed = 1.0;

  public void setGear(double _y_speed, double _z_speed) {
    y_speed = _y_speed;
    z_speed = _z_speed;
  }

  public void drive(double y, double z) {
    left_motor_0.set(y + z);
    right_motor_0.set(y - z);
  }

  @Override
  public void initDefaultCommand() {
    drive(OI.stick_0.getY(Hand.kLeft), OI.stick_0.getX(Hand.kRight));
    
    if (OI.stick_0.getBackButton()) {
      chassis_lift.set(true);
    } else {
      chassis_lift.set(false);
    }
  }
}
