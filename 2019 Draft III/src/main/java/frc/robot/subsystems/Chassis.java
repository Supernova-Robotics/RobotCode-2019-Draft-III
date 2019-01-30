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
import frc.robot.RobotMap;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class Chassis extends Subsystem {
  
  static private SpeedController left_motor_0 = new WPI_TalonSRX(RobotMap.p_chassis_left_0);
  static private SpeedController left_motor_1 = new WPI_TalonSRX(RobotMap.p_chassis_left_1);
  static private SpeedController left_motor_2 = new WPI_TalonSRX(RobotMap.p_chassis_left_2);
  static private SpeedController right_motor_0 = new WPI_TalonSRX(RobotMap.p_chassis_right_0);
  static private SpeedController right_motor_1 = new WPI_TalonSRX(RobotMap.p_chassis_right_1);
  static private SpeedController right_motor_2 = new WPI_TalonSRX(RobotMap.p_chassis_right_2);
  static private SpeedController roller_motor = new Spark(RobotMap.p_chassis_roller);

  static private Solenoid chassis_lift = new Solenoid(RobotMap.p_lift);

  public double global_y_speed;
  public double global_z_speed;

  public Chassis() {
    reset();
  }

  public void reset() {
    global_y_speed = 1.0;
    global_z_speed = 1.0;
  }

  public void setSpeed(double y_speed, double z_speed) {
    global_y_speed = y_speed;
    global_z_speed = z_speed;
  }

  public void drive(double y, double z) {
    left_motor_0.set(y + z);
    right_motor_0.set(y - z);
  }

  public void liftToggle(boolean state) {
    chassis_lift.set(state);
  }

  @Override
  public void initDefaultCommand() {
    drive(-OI.stick_0.getY(Hand.kLeft), OI.stick_0.getX(Hand.kRight));
  }
}
