// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;


public class lift extends SubsystemBase {
  /** Creates a new lift. */
  WPI_VictorSPX motor1 = new WPI_VictorSPX(14);
  WPI_VictorSPX motor2 = new WPI_VictorSPX(15);
  public lift() {}

  public void move(double speed){
    motor1.set(speed);
    motor2.set(speed);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
