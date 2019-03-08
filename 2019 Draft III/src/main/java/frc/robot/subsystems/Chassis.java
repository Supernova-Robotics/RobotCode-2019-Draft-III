/**
 * Chassis.java
 * contains motors on the chassis and a gyro for navigation.
 * 定义了底盘电机和一个陀螺仪。
 */
package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Const;
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

  private Solenoid lift_up = new Solenoid(RobotMap.p_PEN_lift_up);

  /* using the 'default FRC gyro' */
  /* 使用的是默认的插在RIO上的陀螺仪 */
  // private ADXRS450_Gyro gyro = new ADXRS450_Gyro();

  /* the coefficient for the speed of the chassis motor */
  /* 电机速度系数，用来控制全局电机最大转速 */

  public int speed_mode = 0;
  
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
    motor_left_0.set(Const.global_y_speed[speed_mode] * y - Const.global_z_speed[speed_mode] * z);
    motor_left_1.set(Const.global_y_speed[speed_mode] * y - Const.global_z_speed[speed_mode] * z);
    motor_left_2.set(Const.global_y_speed[speed_mode] * y - Const.global_z_speed[speed_mode] * z);
    motor_right_0.set(Const.global_y_speed[speed_mode] * y + Const.global_z_speed[speed_mode] * z);
    motor_right_1.set(Const.global_y_speed[speed_mode] * y + Const.global_z_speed[speed_mode] * z);
    motor_right_2.set(Const.global_y_speed[speed_mode] * y + Const.global_z_speed[speed_mode] * z);
  }

  public void toggleLift(boolean state) {
    lift_up.set(state);
  }

  public void setRollerVel(double vel) {
    motor_roller.set(vel);
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
