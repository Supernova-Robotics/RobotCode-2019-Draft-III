/**
 * Arm.java
 * contains motors with encoders for both arm and claw
 * 
 */

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Const;
import frc.robot.RobotMap;
import frc.robot.commands.ArmDefault;

public class Arm extends Subsystem {
  private TalonSRX motor_arm_left = new TalonSRX(RobotMap.p_CAN_arm[0]);
  private TalonSRX motor_arm_right = new TalonSRX(RobotMap.p_CAN_arm[1]);
  private DigitalInput arm_limit_sw = new DigitalInput(RobotMap.p_DIG_arm_limit);
  

  private final double kP = 0.004, kD = 0.00015;
  private double setpoint = 0;

  public Arm() {
    super();
    motor_arm_left.setInverted(false);
    motor_arm_right.setInverted(false);
  }

  public void resetSensor() {
    motor_arm_left.setSelectedSensorPosition(0);
  }

  public double getPos() {
    return -motor_arm_left.getSelectedSensorPosition();
  }

  public double getVel() {
    return -motor_arm_left.getSelectedSensorVelocity();
  }


  public double getPID() {
    /* 0212: PID算法，现在只加了P，先试试效果 */
    double error = setpoint - getPos();
    double vel = kP * error - kD * getVel();
    vel = Math.max(-1.2, Math.min(1.2, vel));
    return vel;
  }

  public void setSetpoint(double val) {
    setpoint = val;
  }

  public void setVel(double vel) {
    if (vel > 0) {
      /* upward */
      motor_arm_left.set(ControlMode.PercentOutput, Const.global_arm_speed[0] * vel);
      motor_arm_right.set(ControlMode.PercentOutput, Const.global_arm_speed[0] * vel);
    } else {
      /* downward */
      if (arm_limit_sw.get()) {
        motor_arm_left.set(ControlMode.PercentOutput, 0);
        motor_arm_right.set(ControlMode.PercentOutput, 0);
      } else {
        motor_arm_left.set(ControlMode.PercentOutput, Const.global_arm_speed[1] * vel);
        motor_arm_right.set(ControlMode.PercentOutput, Const.global_arm_speed[1] * vel);
      }
    }
  }

  public void log() {
    SmartDashboard.putNumber("Arm Position", getPos());
    SmartDashboard.putNumber("Arm Setpoint", setpoint);
    SmartDashboard.putBoolean("Arm Limit", arm_limit_sw.get());
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new ArmDefault());
  }
}
