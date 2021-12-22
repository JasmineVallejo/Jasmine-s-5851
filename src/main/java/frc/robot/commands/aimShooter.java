// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.shooterWrist;

public class aimShooter extends CommandBase {
  /** Creates a new aimShooter. */
  private final shooterWrist shooterWrist;
  double ta;
  double pastTime;
  double oldError;
  double integral;
  
  public aimShooter(shooterWrist angle, double targetAngle) {
    shooterWrist = angle;
    targetAngle = ta;
    addRequirements(shooterWrist);
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
    double currentAngle = shooterWrist.angle();
    double error = ta - currentAngle;
    double proportion = error * Constants.kP;
    double dt = Timer.getFPGATimestamp() - pastTime;
    pastTime = Timer.getFPGATimestamp();
    integral =+ (error * dt) * Constants.kI;
    double derivative = ((error - oldError)/ dt) * Constants.kD;
    oldError = error;
    double speed = proportion + integral + derivative;
    shooterWrist.wristShooter(speed);
    
    
  


  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    shooterWrist.wristShooter(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
