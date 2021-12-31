// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.ExampleCommand;
import frc.robot.commands.aimShooter;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.lift;
import frc.robot.subsystems.shooterWrist;
import edu.wpi.first.wpilibj2.command.Command;
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

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  private final shooterWrist shooterWrist = new shooterWrist();
  private final Drive driveSubsystem = new Drive();
  private final roller rollerSub = new roller();
  private final lift liftSub = new lift();
  private final intake intakeSub = new intake();
  private final indexer indexerSub = new indexer();
  private final shooter shooterSub = new shooter();

  private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);
  private final Joystick angel = new Joystick(Constants.angel);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings

    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    JoystickButton setAngle = new JoystickButton(angel, 1);
    setAngle.whenPressed(new aimShooter(shooterWrist, -15));

    JoystickButton move = new JoystickButton(angel, 2);
    move.whenPressed(new autoMove(driveSubsystem, 3));

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

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return m_autoCommand;
  }
}
