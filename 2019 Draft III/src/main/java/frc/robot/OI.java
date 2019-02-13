package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.ArmLiftTo;
import frc.robot.commands.ClawLiftTo;
import frc.robot.commands.HookToggle;
import frc.robot.commands.IntakeCollect;
import frc.robot.commands.IntakeLiftTo;


public class OI {
  /* initialize two Xbox controller with id 0 and 1 on DS */
  public static final XboxController stick_0 = new XboxController(0);
  public static final XboxController stick_1 = new XboxController(1);

  /* create button objects, the last number is the id of the button, starting from 1 */
  public static Button stick_0_A = new JoystickButton(stick_0, 1);
  public static Button stick_0_B = new JoystickButton(stick_0, 2);
  public static Button stick_0_X = new JoystickButton(stick_0, 3);
  public static Button stick_0_Y = new JoystickButton(stick_0, 4);
  
  public static Button stick_1_A = new JoystickButton(stick_1, 1);
  public static Button stick_1_B = new JoystickButton(stick_1, 2);
  public static Button stick_1_X = new JoystickButton(stick_1, 3);
  public static Button stick_1_Y = new JoystickButton(stick_1, 4);
  
  public static double joystick_threshold = 0.15;

  public OI() {
    /* binding buttons to commands */
    stick_0_A.whileHeld(new IntakeCollect(0.4));
    stick_0_B.whileHeld(new IntakeCollect(-0.8));
    stick_0_X.whenPressed(new IntakeLiftTo(3849));
    stick_0_Y.toggleWhenPressed(new HookToggle());
    
    stick_1_X.whenPressed(new ArmLiftTo(100));
    stick_1_Y.whenPressed(new ClawLiftTo(100));

  }

  public static double unify(double val) {
    if (Math.abs(val) < joystick_threshold) {
      val = 0;
    }
    return val;
  }

  public static double[] getDriveAxis() {
    double[] result = {unify(-stick_0.getY(Hand.kLeft)), unify(stick_0.getTriggerAxis(Hand.kLeft) - stick_0.getTriggerAxis(Hand.kRight))};
    return result;
  }

  public static double getIntakeAxis() {
    return unify(-stick_0.getY(Hand.kRight));
  }

  public static double getClawAxis() {
    return unify(-stick_1.getY(Hand.kRight));
  }

  public static double getArmAxis() {
    return unify(-stick_1.getY(Hand.kLeft));
  }
}
