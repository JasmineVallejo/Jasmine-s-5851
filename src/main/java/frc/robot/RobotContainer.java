// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.aimShooter;
import frc.robot.subsystems.lift;
import frc.robot.subsystems.shooterWrist;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.subsystems.Drive;
import frc.robot.commands.autoMove;
import frc.robot.commands.liftCommand;
import frc.robot.commands.rollerCommand;
import frc.robot.subsystems.roller;
import frc.robot.commands.intakeCommand;
import frc.robot.subsystems.intake;
import frc.robot.subsystems.indexer;
import frc.robot.commands.indexerCommand;
import frc.robot.subsystems.shooter;
import frc.robot.commands.shooterCommand;


public class RobotContainer {
  private final shooterWrist shooterWrist = new shooterWrist();
  private final Drive driveSubsystem = new Drive();
  private final roller rollerSub = new roller();
  private final lift liftSub = new lift();
  private final intake intakeSub = new intake();
  private final indexer indexerSub = new indexer();
  private final shooter shooterSub = new shooter();
  /**
   * Alright so instead of making the objects for the commands later 
   * "rollRight.whenPressed( new rollerCommand(rollerSub, 1 * Constants.rollerSpeed));"
   * I created them below labeled as "move2" and more. So instead of doing the "new rollerCommand(.."
   * I just put the object name like move2.
   */ 
  private final autoMove move2 = new autoMove(driveSubsystem, 2);

  private final Joystick angel = new Joystick(Constants.angel);

  public RobotContainer() {

    /**The smart dashboard is that one page where we put the values 
     * Anyways so i put a bit of code here to start putting some data on the smartdashboard, the first being 
     * the getInstance thing. That just tells me what instances are running (like what commands are running)
     * that way we know if our commands are even running
     * After that i put the liftsub information on. This will just tell us whenever it is being used and
     * and what command it is being used by
     * I finally then put the move2 command. This tells me when its running AND it the library automatically
     * makes a button for it. The button lets you run this command whenever you want and lets you cancel it
     * without the need for a joystick.  
     */
    SmartDashboard.putData(CommandScheduler.getInstance());
    SmartDashboard.putData(liftSub);
    SmartDashboard.putData("some command",move2);
    configureButtonBindings();
    }
  private void configureButtonBindings() {
    JoystickButton setAngle = new JoystickButton(angel, 1);
    setAngle.whenPressed(new aimShooter(shooterWrist, -15));

    JoystickButton move = new JoystickButton(angel, 2);
    move.whenPressed(move2);

    JoystickButton rollLeft = new JoystickButton(angel, 3);
    rollLeft.whenPressed( new rollerCommand(rollerSub, -1 * Constants.rollerSpeed));
    JoystickButton rollRight = new JoystickButton(angel, 4);
    rollRight.whenPressed( new rollerCommand(rollerSub, 1 * Constants.rollerSpeed));

    JoystickButton liftUp = new JoystickButton(angel, 5);
    liftUp.whenPressed( new liftCommand(liftSub, 1 * Constants.liftSpeed));
    JoystickButton liftDown = new JoystickButton(angel, 6);
    liftDown.whenPressed( new liftCommand(liftSub, -1 * Constants.liftSpeed));

    JoystickButton intakeIn = new JoystickButton(angel, 7);
    intakeIn.whenPressed( new intakeCommand(intakeSub, 1 * Constants.intakeSpeed));
    JoystickButton intakeOut = new JoystickButton(angel, 8);
    intakeOut.whenPressed(new intakeCommand(intakeSub, -1 * Constants.intakeSpeed));

    JoystickButton indexerIn = new JoystickButton(angel, 9);
    indexerIn.whenPressed( new indexerCommand(indexerSub, 1 * Constants.indexerSpeed));
    JoystickButton indexerOut = new JoystickButton(angel, 10);
    indexerOut.whenPressed( new indexerCommand(indexerSub, -1 * Constants.indexerSpeed));

    JoystickButton shoot = new JoystickButton(angel, 11);
    shoot.whenPressed( new shooterCommand (shooterSub, 1 * Constants.shooterSpeed));


  }

  
  public Command getAutonomousCommand() {
    return null;
  }
}
