/**
 * OI.java 
 * for creating joystick objects and assigning buttons to different commands.
 * 在此处初始化控制手柄，并将手柄按键对应到机器人任务 (command) 上面。
 */

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import frc.robot.commands.HookExtend;
import frc.robot.commands.HookGrab;
import frc.robot.commands.IntakeStartCollect;
import frc.robot.commands.IntakeStartPush;
import frc.robot.commands.OpenClaw;
import frc.robot.commands.PushClaw;

public class OI {
  /* initialize two Xbox controller with id 0 and 1 on DS */
  public static final XboxController stick_0 = new XboxController(0);
  public static final XboxController stick_1 = new XboxController(1);

  /* create button objects, the last number is the id of the button, starting from 1 */
  /* 创建按钮对象，最后一个数字对应着 DS 上显示的按钮 ID ，从 1 开始 */
  public static Button stick_0_A = new JoystickButton(stick_0, 1);
  public static Button stick_0_B = new JoystickButton(stick_0, 2);
  public static Button stick_1_A = new JoystickButton(stick_1, 1);
  public static Button stick_1_B = new JoystickButton(stick_1, 2);
  public static Button stick_1_X = new JoystickButton(stick_1, 3);
  public static Button stick_1_Y = new JoystickButton(stick_1, 4);
  
  public OI() {
    /* binding buttons to commands */
    /* 将任务 (command) 绑定到按钮 */
    stick_0_A.toggleWhenPressed(new IntakeStartCollect());
    stick_0_B.toggleWhenPressed(new IntakeStartPush());
    
    stick_1_A.whenPressed(new PushClaw());
    stick_1_B.toggleWhenPressed(new OpenClaw());
    stick_1_X.toggleWhenPressed(new HookExtend());
    stick_1_Y.toggleWhenPressed(new HookGrab());
  }
}
