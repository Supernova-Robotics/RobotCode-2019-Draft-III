/**
 * RobotMap.java 
 * provides mapping for the I/O ports. not used.
 */

package frc.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
  /* Chassis */
  public static final int[] p_CAN_chassis_left = {10, 11, 12};
  public static final int[] p_CAN_chassis_right = {13, 14, 15};
  public static final int p_PWM_chassis_roller = 0;
  public static final int p_PEN_lift_up = 0;
  public static final double chassis_speed_z = 0.4;
  public static final double chassis_speed_y = 0.7;

  /* Arm */
  public static final int[] p_CAN_arm = {20, 21};
  public static final double[] arm_speed = {0.6, 0.4};
  public static final double arm_kP = 0.003, arm_kD = 0.0001;

  /* Claw */
  public static final int p_CAN_claw = 22;
  public static final int p_PWM_claw_shooter = 1;
  public static final int p_DIG_limit = 0; 
  public static final double claw_speed = 0.6;
  public static final double claw_kP = 0.01;
  public static final double claw_adjust_intensity = 10;

  /* Hook */
  public static final int p_PWM_hook_servo = 9;
  public static final int p_PEN_hook_extender = 0;
  public static final double hook_servo_open_ang = 0.3;
  public static final double hook_servo_close_ang = 0.9;
    
  /* Intake */
  public static final int p_PWM_intake_lift = 2;
  public static final int p_PWM_intake_collector = 3;
  public static final int p_ANA_intake_encoder = 0;
  public static final double intake_global_lift_speed = 0.8;
}
