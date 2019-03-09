/**
 * Arm.java
 * contains motors with encoders for both arm and claw
 * 
 */

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.commands.ArmDefault;

public class Arm extends Subsystem {
  public TalonSRX motor_arm_left_0 = new TalonSRX(20);
  public TalonSRX motor_arm_right_0 = new TalonSRX(21);
  public TalonSRX motor_claw = new TalonSRX(22);

  public Solenoid pusher = new Solenoid(1);
  public DoubleSolenoid claw = new DoubleSolenoid(2, 3);
  
  public double global_arm_speed = 0.6;
  public double global_claw_speed = 0.6;

  /* the encoder-readout limit for both direction */
  public boolean limit_enabled = false;
  public double[] arm_limit = {100, 6000};
  public double[] claw_limit = {-1400, 1550};

  public double claw_setpoint = 0;
  public double claw_error = 0;
  public double claw_output = 0;

  public double arm_setpoint = 500;
  public double arm_error = 0;
  public double arm_output = 0;

  private boolean claw_open_state = true;

  public Arm() {
    super();
    motor_arm_left_0.setInverted(true);
    motor_arm_right_0.setInverted(false);
    motor_claw.setInverted(true);
  }

  public double getArmAngle() {
    /* the number 0 is the id of the sensor on the controller */
    return motor_arm_left_0.getSelectedSensorPosition(0);
  }

  public double getArmVel() {
    /* the number 0 is the id of the sensor on the controller */
    return motor_arm_left_0.getSelectedSensorVelocity(0);
  }

  public void setArmSetpoint(double setpoint) {
    arm_setpoint = setpoint;
  }

  /** 
   * self-written PD control
   */
  public double calculateArmPID() {
    arm_error = arm_setpoint - getArmAngle();
    arm_output = 0.005 * arm_error - 0.001 * getArmVel();
    return arm_output;
  }

  public void armLiftAt(double vel) {
    double angle = getArmAngle();
    if (!limit_enabled || (!(vel < 0 && angle < arm_limit[0]) && !(vel > 0 && angle > arm_limit[1]))){
      motor_arm_left_0.set(ControlMode.PercentOutput, global_arm_speed * vel);
      motor_arm_right_0.set(ControlMode.PercentOutput, global_arm_speed * vel);
    } else {
      motor_arm_left_0.set(ControlMode.PercentOutput, 0);
      motor_arm_right_0.set(ControlMode.PercentOutput, 0);
    }
  }

  public double getClawAngle() {
    return -motor_claw.getSelectedSensorPosition(0);
  }

  public double getClawVel() {
    return -motor_claw.getSelectedSensorVelocity(0);
  }

  public void setClawSetpoint(double setpoint) {
    claw_setpoint = setpoint;
  }

  public double calculateClawPID() {
    claw_error = claw_setpoint - getClawAngle();
    claw_output = 0.01 * claw_error - 0.005 * getClawVel();
    return claw_output;
  }

  public void clawLiftAt(double vel) {
    double angle = getClawAngle();
    if (!(vel < 0 && angle < claw_limit[0]) && !(vel > 0 && angle > claw_limit[1])){
      motor_claw.set(ControlMode.PercentOutput, global_arm_speed * vel);
    } else {
      motor_claw.set(ControlMode.PercentOutput, 0);
    }
  }

  public void setPusher(boolean state) {
    pusher.set(state);
  }
  
  public void clawToggle() {
    claw_open_state = !claw_open_state;
    if (claw_open_state) {
      claw.set(Value.kForward);
    } else {
      claw.set(Value.kReverse);
    }
  }
  
  public void clawToggle(boolean state) {
    claw_open_state = state;
    if (state) {
      claw.set(Value.kForward);
    } else {
      claw.set(Value.kReverse);
    }
  }

  /**
   * to log the relevant information to SD
   */
  public void log() {
    SmartDashboard.putNumber("Arm Pos", motor_arm_left_0.getSelectedSensorPosition(0));
    SmartDashboard.putNumber("Claw Pos", getClawAngle());
    SmartDashboard.putNumber("Claw Vel", getClawVel());
    SmartDashboard.putNumber("Claw Setpoint", claw_setpoint);
    SmartDashboard.putNumber("Claw Error", claw_error);
    SmartDashboard.putNumber("Claw PID Out", claw_output);
    SmartDashboard.putNumber("Arm Pos", getArmAngle());
    SmartDashboard.putNumber("Arm Vel", getArmVel());
    SmartDashboard.putNumber("Arm Setpoint", arm_setpoint);
    SmartDashboard.putNumber("Arm Error", arm_error);
    SmartDashboard.putNumber("Arm PID Out", arm_output);
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new ArmDefault());
  }
}
