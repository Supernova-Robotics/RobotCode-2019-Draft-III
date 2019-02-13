/**
 * Chassis.java
 * contains motors on the chassis and a gyro for navigation.
 * 定义了底盘电机和一个陀螺仪。
 */
package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.ChassisDefault;

public class Chassis extends Subsystem {
  private SpeedController motor_left_0 = new WPI_VictorSPX(RobotMap.p_CAN_chassis_left[0]);
  private SpeedController motor_left_1 = new WPI_VictorSPX(RobotMap.p_CAN_chassis_left[1]);
  private SpeedController motor_left_2 = new WPI_VictorSPX(RobotMap.p_CAN_chassis_left[2]);
  private SpeedController motor_right_0 = new WPI_VictorSPX(RobotMap.p_CAN_chassis_right[0]);
  private SpeedController motor_right_1 = new WPI_VictorSPX(RobotMap.p_CAN_chassis_right[1]);
  private SpeedController motor_right_2 = new WPI_VictorSPX(RobotMap.p_CAN_chassis_right[2]);
  private SpeedController motor_roller = new Spark(RobotMap.p_PWM_chassis_roller);

  /* using the 'default FRC gyro' */
  private ADXRS450_Gyro gyro = new ADXRS450_Gyro();

  /* the coefficient for the speed of the chassis motor */
  public double global_y_speed = 0.7;
  public double global_z_speed = 0.4;
  
  public Chassis() {
    super();
    
    /* correct the direction of motor: forward is positive */
    motor_left_0.setInverted(false);
    motor_left_1.setInverted(false);
    motor_left_2.setInverted(false);
    motor_right_0.setInverted(true);
    motor_right_1.setInverted(true);
    motor_right_2.setInverted(true);
  }
  
  /**
   * method for driving the chassis.
   * @param y: forward is positive
   * @param z: CCW is positive
   */
  public void drive(double y, double z) {
    motor_left_0.set(global_y_speed * y - global_z_speed * z);
    motor_left_1.set(global_y_speed * y - global_z_speed * z);
    motor_left_2.set(global_y_speed * y - global_z_speed * z);
    motor_right_0.set(global_y_speed * y + global_z_speed * z);
    motor_right_1.set(global_y_speed * y + global_z_speed * z);
    motor_right_2.set(global_y_speed * y + global_z_speed * z);
  }

  public void log() {
  }

  @Override
  public void initDefaultCommand() {
    /* the default command of this subsystem is to drive according
    to joystick input. */
    setDefaultCommand(new ChassisDefault());
  }
}
