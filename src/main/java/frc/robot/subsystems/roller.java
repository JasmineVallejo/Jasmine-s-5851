// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;


public class roller extends SubsystemBase {
  /** Creates a new roller. */
  WPI_VictorSPX roller = new WPI_VictorSPX(16);

  public void move(double speed){
    roller.set(speed);
  }

  public roller() {}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
