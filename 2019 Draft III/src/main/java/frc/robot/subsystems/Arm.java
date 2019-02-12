/**
 * Arm.java
 * contains motors with encoders for both arm and claw
 * 
 */

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.commands.ArmDefault;

public class Arm extends Subsystem {
  public TalonSRX motor_arm_left = new TalonSRX(RobotMap.p_CAN_arm[0]);
  public TalonSRX motor_arm_right = new TalonSRX(RobotMap.p_CAN_arm[1]);
  public TalonSRX motor_claw = new TalonSRX(RobotMap.p_CAN_claw);
  public SpeedController motor_shooter = new VictorSP(RobotMap.p_PWM_arm_shooter);
  public DigitalInput arm_limit_sw = new DigitalInput(RobotMap.p_DIG_limit);
  public double global_arm_speed = 0.6;
  public double global_claw_speed = 0.6;

  public Arm() {
    super();
    motor_arm_left.setInverted(false);
    motor_arm_right.setInverted(true);
    motor_claw.setInverted(true);
    motor_shooter.setInverted(true);
  }

  public double getArmPos() {
    return -motor_arm_left.getSelectedSensorPosition();
  }

  public double getClawPos() {
    return -motor_claw.getSelectedSensorPosition();
  }

  public void setArmVel(double vel) {
    if (vel < 0) {
      if (!arm_limit_sw.get()) {
        motor_arm_left.set(ControlMode.PercentOutput, 0);
        motor_arm_right.set(ControlMode.PercentOutput, 0);
      } else {
        motor_arm_left.set(ControlMode.PercentOutput, global_arm_speed * global_arm_speed * vel);
        motor_arm_right.set(ControlMode.PercentOutput, global_arm_speed * global_arm_speed * vel);
      }
    } else {
      System.out.println("arm "+getArmPos());
      System.out.println("claw "+getClawPos());
      motor_arm_left.set(ControlMode.PercentOutput, global_arm_speed * vel);
      motor_arm_right.set(ControlMode.PercentOutput, global_arm_speed * vel);
    }
  }

  public void setClawVel(double vel) {
    motor_claw.set(ControlMode.PercentOutput, vel);
  }

  public void setShooterVel(double vel) {
    motor_shooter.set(vel);
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new ArmDefault());
  }
}
