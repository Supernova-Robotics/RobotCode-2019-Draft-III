/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Subsystem;

import frc.robot.OI;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class Arm extends Subsystem {
  
  static private SpeedController arm_lift_motor = new Spark(4);
  static private SpeedController craw_lift_motor = new Spark(5);
  static private DoubleSolenoid claw_solenoid = new DoubleSolenoid(0, 1);

  public double arm_speed = 1.0;
  public double craw_speed = 1.0;

  public enum ClawAction {
    kOpen,
    kClose,
    kOff
  }

  public void setGear(double _arm_speed, double _craw_speed) {
    arm_speed = _arm_speed;
    craw_speed = _craw_speed;
  }

  public void armLiftAt(double vel) {
    arm_lift_motor.set(arm_speed * vel);
  }

  public void crawLiftAt(double vel) {
    craw_lift_motor.set(craw_speed * vel);
  }

  public void clawSet(ClawAction action) {
    if (action == ClawAction.kClose) {
      claw_solenoid.set(Value.kForward);
    } else if (action == ClawAction.kOpen) {
      claw_solenoid.set(Value.kReverse);
    }
  }

  @Override
  public void initDefaultCommand() {
    armLiftAt(OI.stick_1.getY(Hand.kLeft));
    crawLiftAt(OI.stick_1.getY(Hand.kRight));
    
    if (OI.stick_1.getAButton()) {
      clawSet(ClawAction.kOpen);
    }
    if (OI.stick_1.getYButton()) {
      clawSet(ClawAction.kClose);
    }
  }
}
