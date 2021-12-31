// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.Spark;


public class shooter extends SubsystemBase {
  Spark motor1 = new Spark(19);
  Spark motor2 = new Spark(4);
  /** Creates a new shooter. */
  public shooter() {}

  public void shoot(double speed){
    motor1.set(speed);
    motor2.set(speed * -1);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
