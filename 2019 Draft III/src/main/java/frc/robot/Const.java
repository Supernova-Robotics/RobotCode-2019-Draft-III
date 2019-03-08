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
public class Const {
  /* ============== Subsystems ================= */
  /* Arm */
  public static final double[] global_arm_speed = {0.6, 0.4};

  /* Chassis */
  public static final double[] global_y_speed = {0.7, 0.3};
  public static final double[] global_z_speed = {0.4, 0.2};

  /* Claw */
  public static final double global_claw_speed = 0.5;
  public static final double adjust_intensity = 20;

  /* Hook */
  public static final double servo_open_ang = 0.3;
  public static final double servo_close_ang = 0.9;

  /* Intake */
  public static final double global_lift_speed = 0.8;

  /* ============== Commands ================= */
  /* intake speed, [collect, shoot] */
  public static final double[] intake_speed = {0.6, -0.6}, shooter_speed = {0.4, -1.0};
}
