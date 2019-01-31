/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

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
  public static final XboxController stick_0 = new XboxController(0);
  public static final XboxController stick_1 = new XboxController(1);

  public static Button stick_0_A = new JoystickButton(stick_0, 1);
  public static Button stick_0_B = new JoystickButton(stick_0, 2);
  public static Button stick_1_A = new JoystickButton(stick_1, 1);
  public static Button stick_1_B = new JoystickButton(stick_1, 2);
  public static Button stick_1_X = new JoystickButton(stick_1, 3);
  public static Button stick_1_Y = new JoystickButton(stick_1, 4);
  
  public OI() {
    stick_0_A.toggleWhenPressed(new IntakeStartCollect());
    stick_0_B.toggleWhenPressed(new IntakeStartPush());
    
    stick_1_A.whenPressed(new PushClaw());
    stick_1_B.toggleWhenPressed(new OpenClaw());
    stick_1_X.toggleWhenPressed(new HookExtend());
    stick_1_Y.toggleWhenPressed(new HookGrab());

  }
}
