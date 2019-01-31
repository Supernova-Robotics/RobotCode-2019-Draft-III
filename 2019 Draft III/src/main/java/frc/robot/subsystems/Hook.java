/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.commands.ChassisDefault;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class Hook extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  public Solenoid hook_extender = new Solenoid(4);
  public Solenoid hook = new Solenoid(5);
  
  public Hook() {
    super();
  }

  public void toggleGrab(boolean state) {
    hook.set(state);
  }

  public void toggleExtension(boolean state) {
    hook_extender.set(state);
  }
  
  
  @Override
  public void initDefaultCommand() {
  }
}
