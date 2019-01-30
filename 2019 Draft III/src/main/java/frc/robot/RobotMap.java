/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
  /** PWM ports **/
  public static final int p_chassis_roller = 0;
  public static final int p_intake_lift = 1;
  public static final int p_intake_collector = 2;

  /** CAN IDs **/
  public static final int p_chassis_left_0 = 10;
  public static final int p_chassis_left_1 = 11;
  public static final int p_chassis_left_2 = 12;
  public static final int p_chassis_right_0 = 13;
  public static final int p_chassis_right_1 = 14;
  public static final int p_chassis_right_2 = 15;
  public static final int p_arm_lift_0 = 20;
  public static final int p_arm_lift_1 = 21;
  public static final int p_claw_lift = 22;

  /** Solenoids **/
  public static final int p_lift = 0;
  public static final int p_claw_pusher = 1;
  public static final int[] p_claw = {2, 3};
}
