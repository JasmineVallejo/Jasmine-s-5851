// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;


import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drive;
import frc.robot.Constants;

public class autoMove extends CommandBase {
  /** Creates a new autoMove. */

  private final Drive driveSubsystem;
  double desiredDistance;
  double pastTime;
  double integral;
  double oldError;
  public autoMove(Drive driveSub, double targetDistance) {
    driveSubsystem = driveSub;
    desiredDistance = targetDistance;
    addRequirements(driveSubsystem);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    pastTime = Timer.getFPGATimestamp();
    oldError = 0;
    integral = 0;
    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double currentDistance = driveSubsystem.rightDistance();
    double error = currentDistance - desiredDistance;
    double proportion = error * Constants.autoMoveKP;
    double dt = Timer.getFPGATimestamp() - pastTime;
    pastTime = Timer.getFPGATimestamp();
    integral += (error * dt) * Constants.autoMoveKI;
    double derivative = ((error - oldError)/dt) * Constants.autoMoveKD;
    double speed = proportion + integral + derivative;
    driveSubsystem.move(speed, speed);
    SmartDashboard.putNumber("Error", error);
    SmartDashboard.putNumber("speed", speed);
    SmartDashboard.putNumber("right distance", driveSubsystem.rightDistance());
    SmartDashboard.putNumber("left distnance", driveSubsystem.leftDistance());
  




    
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    driveSubsystem.move(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
