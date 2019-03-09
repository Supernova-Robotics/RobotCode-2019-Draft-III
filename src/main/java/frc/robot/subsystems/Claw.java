/**
 * Arm.java
 * contains motors with encoders for both arm and claw
 * 
 */

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.Const;
import frc.robot.commands.ClawDefault;

public class Claw extends Subsystem {
  private TalonSRX lift_motor = new TalonSRX(RobotMap.p_CAN_claw);
  private SpeedController shooter_motor = new VictorSP(RobotMap.p_PWM_claw_shooter);


  private final double kP = 0.005;
  private double setpoint = 800;
  public boolean enable_pid = true;

  public Claw() {
    super();
    lift_motor.setInverted(true);
    shooter_motor.setInverted(true);
  }

  public void resetSensor() {
    lift_motor.setSelectedSensorPosition(-1000);
  }

  public double getPos() {
    return -lift_motor.getSelectedSensorPosition();
  }

  public double getPID() {
    double error = setpoint - getPos();
    double vel = kP * error;
    vel = Math.max(-1.5, Math.min(1.5, vel));
    return vel;
  }

  public void adjustSetpoint(double val) {
    setpoint += Const.adjust_intensity * val;
  }

  public void setSetpoint(double val) {
    setpoint = val;
  }

  public void setVel(double vel) {
    lift_motor.set(ControlMode.PercentOutput, Const.global_claw_speed * vel);
  }

  public void setShooterVel(double vel) {
    shooter_motor.set(vel);
  }

  public void log() {
    SmartDashboard.putNumber("Claw Position", getPos());
    SmartDashboard.putNumber("Claw Setpoint", setpoint);
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new ClawDefault());
  }
}
