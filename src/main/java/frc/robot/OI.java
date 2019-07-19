/**
 * OI.java 
 * for creating joystick objects and assigning buttons to different commands.
 * 在此处初始化控制手柄，并将手柄按键对应到机器人任务 (command) 上面。
 */

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.ClimbAdvance;
import frc.robot.commands.ChassisChangeSpeed;
import frc.robot.commands.ExtensionToggle;
import frc.robot.commands.HookToggle;
import frc.robot.commands.SupportIn;
import frc.robot.commands.SupportOut;
import frc.robot.commands.IntakeCollect;
import frc.robot.commands.IntakeShoot;
import frc.robot.commands.OneButtonClimbUp;
import frc.robot.commands.ToCollect;
import frc.robot.commands.ToMiddle;
import frc.robot.commands.ToTop;


public class OI {
  /* initialize two Xbox controller with id 0 and 1 on DS */
  public static final XboxController stick_0 = new XboxController(0);
  public static final XboxController stick_1 = new XboxController(1);

  /* create button objects, the last number is the id of the button, starting from 1 */
  /* 创建按钮对象，最后一个数字对应着 DS 上显示的按钮 ID ，从 1 开始 */
  public static Button stick_0_A = new JoystickButton(stick_0, 1);
  public static Button stick_0_B = new JoystickButton(stick_0, 2);
  public static Button stick_0_X = new JoystickButton(stick_0, 3);
  public static Button stick_0_Y = new JoystickButton(stick_0, 4);
  public static Button stick_0_LBumper = new JoystickButton(stick_0, 5);
  public static Button stick_0_RBumper = new JoystickButton(stick_0, 6);
  public static Button stick_0_back = new JoystickButton(stick_0, 7);
  public static Button stick_0_start = new JoystickButton(stick_0, 8);
  public static Button stick_0_stickBt = new JoystickButton(stick_0, 9);
  public static XboxPOV stick_0_UP = new XboxPOV(stick_0, 0);
  public static XboxPOV stick_0_DOWN = new XboxPOV(stick_0, 180);
  

  public static Button stick_1_A = new JoystickButton(stick_1, 1);
  public static Button stick_1_B = new JoystickButton(stick_1, 2);
  public static Button stick_1_X = new JoystickButton(stick_1, 3);
  public static Button stick_1_Y = new JoystickButton(stick_1, 4);
  public static XboxPOV stick_1_UP = new XboxPOV(stick_1, 0);
  public static XboxPOV stick_1_RIGHT = new XboxPOV(stick_1, 90);
  public static XboxPOV stick_1_DOWN = new XboxPOV(stick_1, 180);
  public static double joystick_threshold = 0.15;

  public OI() {
    /* binding buttons to commands */
    /* 将任务 (command) 绑定到按钮 */
    // stick_0_A.whenPressed(new SupportOut());
    // stick_0_B.whenPressed(new SupportIn());
    // stick_0_X.whenPressed(new IntakeLiftTo(3924));
    // stick_0_Y.whenPressed(new IntakeLiftTo(3850));
    
    stick_0_UP.whenPressed(new SupportIn());
    stick_0_DOWN.whileHeld(new SupportOut());
    stick_0_back.whenPressed(new OneButtonClimbUp());
    
    stick_0_start.whileHeld(new ChassisChangeSpeed(1)); // slow mode
    stick_0_stickBt.whileHeld(new ChassisChangeSpeed(2)); // fast mode

    stick_0_LBumper.whileHeld(new ClimbAdvance());

    stick_1_UP.whenPressed(new ToTop());
    stick_1_RIGHT.whenPressed(new ToMiddle());
    stick_1_DOWN.whenPressed(new ToCollect());
    
    stick_1_A.whileHeld(new IntakeCollect());
    stick_1_B.whileHeld(new IntakeShoot());
    stick_1_X.toggleWhenPressed(new HookToggle());
    stick_1_Y.toggleWhenPressed(new ExtensionToggle());
  }

  public static double unify(double val) {
    if (Math.abs(val) < joystick_threshold) {
      val = 0;
    }
    return val;
  }

  public static double[] getDriveAxis() {
    double[] result = {
      unify(-stick_0.getY(Hand.kLeft)), 
      unify(stick_0.getTriggerAxis(Hand.kLeft) - stick_0.getTriggerAxis(Hand.kRight))
    };
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
