package frc.robot;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Chassis;
import frc.robot.subsystems.Claw;
import frc.robot.subsystems.Hook;
import frc.robot.subsystems.Intake;

public class Robot extends TimedRobot {
  // create subsystem objects
  public static Chassis chassis = new Chassis();
  public static Arm arm = new Arm();
  public static Claw claw = new Claw();
  public static Intake intake = new Intake();
  public static Hook hook = new Hook();

  public static OI oi = new OI();

  Command m_autonomousCommand;
  SendableChooser<Command> m_chooser = new SendableChooser<>();


  @Override
  public void robotInit() {
    /* initialize two cameras, one for vision processing and one for the driver. */
    UsbCamera aliment_cam = CameraServer.getInstance().startAutomaticCapture(0);
    // codes for CV.

    // UsbCamera driver_cam = CameraServer.getInstance().startAutomaticCapture(1);
    // driver_cam.setResolution(640, 480);

    // m_chooser.setDefaultOption("Default Auto", new ExampleCommand());
    // chooser.addOption("My Auto", new MyAutoCommand());
    SmartDashboard.putData("Auto mode", m_chooser);


    arm.resetSensor();
  }


  @Override
  public void robotPeriodic() {
    arm.log();
    claw.log();
    chassis.log();
    hook.log();
    intake.log();
  }

  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_chooser.getSelected();

    /*
     * String autoSelected = SmartDashboard.getString("Auto Selector",
     * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
     * = new MyAutoCommand(); break; case "Default Auto": default:
     * autonomousCommand = new ExampleCommand(); break; }
     */

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.start();
    }
  }

  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
    claw.enable_pid = true;
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void testPeriodic() {
    claw.enable_pid = false;
    arm.setVel(-0.3 * OI.stick_1.getY(Hand.kLeft));
    claw.setVel(-0.7 * OI.stick_1.getY(Hand.kRight));
    arm.resetSensor();
    claw.resetSensor();
  }
}
