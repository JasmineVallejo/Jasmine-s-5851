// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class shooterWrist extends SubsystemBase {
  Spark wristShooter = new Spark(2);
  Encoder encoder = new Encoder(2, 3,false,EncodingType.k4X);
  double angle;

  /** Creates a new shooterWrist. */
  public shooterWrist() {}

  public void wristShooter(double speed){
    wristShooter.set(speed);
  }
 

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    encoder.setMinRate(10);
    encoder.setDistancePerPulse(1);
    encoder.setReverseDirection(true);
    encoder.setSamplesToAverage(127);
    encoder.setMaxPeriod(.1);
    angle = encoder.getDistance() * (25/391.5);
  
  }
  public double angle(){
    return angle;
  }
}
