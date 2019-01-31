/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.commands.ChassisDefault;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class Chassis extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  public SpeedController motor_left_0 = new WPI_VictorSPX(10);
  public SpeedController motor_left_1 = new WPI_VictorSPX(11);
  public SpeedController motor_left_2 = new WPI_VictorSPX(12);
  public SpeedController motor_right_0 = new WPI_VictorSPX(13);
  public SpeedController motor_right_1 = new WPI_VictorSPX(14);
  public SpeedController motor_right_2 = new WPI_VictorSPX(15);
  public SpeedController motor_roller = new Spark(0);

  public double global_y_speed = 1.0;
  public double global_z_speed = 0.4;
  
  public Chassis() {
    super();
    motor_left_0.setInverted(false);
    motor_left_1.setInverted(false);
    motor_left_2.setInverted(false);
    motor_right_0.setInverted(true);
    motor_right_1.setInverted(true);
    motor_right_2.setInverted(true);
  }
  
  public void drive(double y, double z) {
    motor_left_0.set(global_y_speed * y - global_z_speed * z);
    motor_left_1.set(global_y_speed * y - global_z_speed * z);
    motor_left_2.set(global_y_speed * y - global_z_speed * z);
    motor_right_0.set(global_y_speed * y + global_z_speed * z);
    motor_right_1.set(global_y_speed * y + global_z_speed * z);
    motor_right_2.set(global_y_speed * y + global_z_speed * z);
  }
  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new ChassisDefault());
  }
}
