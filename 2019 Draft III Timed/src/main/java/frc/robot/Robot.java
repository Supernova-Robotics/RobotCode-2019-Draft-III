/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends TimedRobot {
  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();
  
  XboxController stick_0 = new XboxController(0);
  XboxController stick_1 = new XboxController(1);

  ADXRS450_Gyro gyro = new ADXRS450_Gyro();
  Encoder intake_angle = new Encoder(2, 3);

  SpeedController chassis_left_0 = new WPI_VictorSPX(10);
  SpeedController chassis_left_1 = new WPI_VictorSPX(11);
  SpeedController chassis_left_2 = new WPI_VictorSPX(12);
  SpeedController chassis_right_0 = new WPI_VictorSPX(13);
  SpeedController chassis_right_1 = new WPI_VictorSPX(14);
  SpeedController chassis_right_2 = new WPI_VictorSPX(15);
  SpeedController chassis_roller = new Spark(2);
  Solenoid chassis_lift = new Solenoid(2);
  
  SpeedController intake_lift = new Spark(3);
  SpeedController intake_collector = new Spark(4);

  TalonSRX arm_lift_0 = new TalonSRX(16);
  TalonSRX arm_lift_1 = new TalonSRX(17);
  SpeedController claw_lift = new Spark(6);
  DoubleSolenoid claw_open = new DoubleSolenoid(0, 1);
  Solenoid claw_push = new Solenoid(3);
  
  double intake_target = 30;

  boolean chassis_lift_state = false;

  double intake_lower_limit = -29;

  @Override
  public void robotInit() {
    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);

    intake_lift.setInverted(true);
    intake_angle.setReverseDirection(true);
  }

  public void move(double y, double z) {
    chassis_left_0.set(y - z);
    chassis_right_0.set(-y - z);
    /*chassis_left_1.set(y - z);
    chassis_right_1.set(-y - z);
    chassis_left_2.set(y - z);
    chassis_right_2.set(-y - z);
    */
  }

  public void intake_set(double vel) {
    if (intake_angle.get() < intake_lower_limit && vel < 0) {
      intake_lift.set(0);
    } else {
      intake_lift.set(vel);
    }
  }

  @Override
  public void teleopInit() {
    chassis_lift_state = false;
    intake_target = 30;
  }

  @Override
  public void teleopPeriodic() {
    arm_lift_0.set(ControlMode.PercentOutput, -stick_1.getY(Hand.kLeft));
    arm_lift_0.set(ControlMode.PercentOutput, -stick_1.getY(Hand.kLeft));
    claw_lift.set(0.5 * stick_1.getY(Hand.kRight));
    if (stick_1.getAButton()) {
      claw_open.set(Value.kForward);
    } else if (stick_1.getYButton()) {
      claw_open.set(Value.kReverse);
    } else {
      claw_open.set(Value.kOff);
    }
    claw_push.set(stick_1.getBButton());

    /*
    if (stick_0.getBumper(Hand.kLeft)) {
      intake_collector.set(0.8);
    } else if (stick_0.getBumper(Hand.kRight)) {
      intake_collector.set(-0.8);
    } else {
      intake_collector.set(0);
    }
    */

    if (stick_0.getAButton()) {
      chassis_lift_state = true;
    } else if (stick_0.getBButton()) {
      chassis_lift_state = false;
    }
    chassis_lift.set(chassis_lift_state);

    if (chassis_lift_state) {
      chassis_roller.set(-stick_0.getY(Hand.kLeft));
      intake_collector.set(-0.8 * stick_0.getY(Hand.kLeft));
    }
    move(-stick_0.getY(Hand.kLeft), stick_0.getTriggerAxis(Hand.kLeft) - stick_0.getTriggerAxis(Hand.kRight));
    // System.out.println("intake"+intake_angle.get());

    if (stick_0.getYButton()) {
      intake_target = 30;
    }
    
    double error = intake_target - intake_angle.get();
    /** pid loop for intake */
    double intake_pid = 0;
    intake_pid += 0.1 * error;
    System.out.println(intake_target);
    SmartDashboard.putNumber("intake_target", intake_target);
    SmartDashboard.putNumber("intake_pid", intake_pid);
    SmartDashboard.putNumber("intake_error", error);
    SmartDashboard.putNumber("intake_angle", intake_angle.get());
    if (stick_0.getXButton()) {
      if (Math.abs(stick_0.getY(Hand.kRight)) > 0.1) {
        intake_target += 1 * -stick_0.getY(Hand.kRight);
        if (intake_target < intake_lower_limit) {
          intake_target = intake_lower_limit;
        }
      }
      intake_set(intake_pid);
    } else if (stick_0.getBackButton() || stick_0.getBumper(Hand.kLeft)) {
      intake_set(-0.21 + 0.5 * -stick_0.getY(Hand.kRight));
    } else {
      intake_set(0.7 * -stick_0.getY(Hand.kRight));
    }
    // System.out.println("arm"+arm_lift_0.getSelectedSensorPosition(1));
  }

  @Override
  public void testPeriodic() {
    intake_angle.reset();
    intake_lift.set(0.3 * -stick_0.getY(Hand.kRight));
  }
}
