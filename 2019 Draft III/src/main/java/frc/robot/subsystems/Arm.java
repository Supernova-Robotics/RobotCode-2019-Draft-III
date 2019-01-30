/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Subsystem;

import frc.robot.OI;
import frc.robot.RobotMap;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class Arm extends Subsystem {
  
  static private TalonSRX arm_lift_motor_0 = new TalonSRX(RobotMap.p_arm_lift_0);
  static private TalonSRX arm_lift_motor_1 = new TalonSRX(RobotMap.p_arm_lift_1);
  static private TalonSRX claw_lift_motor = new TalonSRX(RobotMap.p_claw_lift);
  static private DoubleSolenoid claw_solenoid = new DoubleSolenoid(RobotMap.p_claw[0], RobotMap.p_claw[1]);
  static private Solenoid pusher_solenoid = new Solenoid(RobotMap.p_claw_pusher);

  public double global_arm_speed = 1.0;
  public double global_claw_speed = 1.0;


  public enum ClawAction {
    kOpen, kClose, kOff
  }
  public enum PusherAction {
    kRetrive, kPush
  }

  public Arm() {
    reset();
  }

  public void reset() {

  }

  public void setGear(double arm_speed, double claw_speed) {
    global_arm_speed = arm_speed;
    global_claw_speed = claw_speed;
  }

  public void armLiftAt(double vel) {
    arm_lift_motor_0.set(ControlMode.PercentOutput, global_arm_speed * vel);
    // arm_lift_motor_1.set(ControlMode.PercentOutput, global_arm_speed * vel);
  }

  public void crawLiftAt(double vel) {
    claw_lift_motor.set(ControlMode.PercentOutput, global_claw_speed * vel);
  }

  public void clawToggle(ClawAction action) {
    if (action == ClawAction.kClose) {
      claw_solenoid.set(Value.kForward);
    } else if (action == ClawAction.kOpen) {
      claw_solenoid.set(Value.kReverse);
    }
  }
  public void pusherToggle(PusherAction action) {
    if (action == PusherAction.kRetrive) {
      pusher_solenoid.set(true);
    } else if (action ==PusherAction.kRetrive) {
      pusher_solenoid.set(false);
    }
  }

  @Override
  public void initDefaultCommand() {
    armLiftAt(-OI.stick_1.getY(Hand.kLeft));
    crawLiftAt(-OI.stick_1.getY(Hand.kRight));
  }
}
