/**
 * OI.java 
 * for creating joystick objects and assigning buttons to different commands.
 * 在此处初始化控制手柄，并将手柄按键对应到机器人任�? (command) 上面�?
 */

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.Button;

public class XboxPOV extends Button {
  private XboxController stick_;
  private int dir_;
  public XboxPOV(XboxController stick, int dir) {
    stick_ = stick;
    dir_ = dir;
  }

  public boolean get() {
    return stick_.getPOV() == dir_;
  }
}
