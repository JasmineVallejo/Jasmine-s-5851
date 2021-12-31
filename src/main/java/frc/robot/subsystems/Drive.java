// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;


import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;


import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Drive extends SubsystemBase {
  Spark leftRear = new Spark(1);
  WPI_TalonSRX leftFront = new WPI_TalonSRX(12);
  WPI_TalonSRX rightRear = new WPI_TalonSRX(11);
  WPI_VictorSPX rightFront = new WPI_VictorSPX(18);
  SpeedControllerGroup leftDrive = new SpeedControllerGroup(leftRear, leftFront);
  SpeedControllerGroup rightDrive = new SpeedControllerGroup(rightFront, rightRear);
  DifferentialDrive drive = new DifferentialDrive(leftDrive, rightDrive);

  public void move(double leftSpeed, double rightSpeed){
    drive.tankDrive(leftSpeed, rightSpeed);
  }
/** Creates a new Drive. */
  public Drive() {
    leftFront.setSelectedSensorPosition(0);
    rightRear.setSelectedSensorPosition(0);
    rightRear.setSensorPhase(true);




  }
double encoderToFeet;
double rightEncoderToFeet;
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    double leftTraveled = leftFront.getSelectedSensorPosition();
    encoderToFeet = leftTraveled * ((Math.PI*6)/49152);
    double rightTraveled = rightRear.getSelectedSensorPosition();
    rightEncoderToFeet = rightTraveled * ((Math.PI*6)/49152);
  }
  public double leftDistance(){
    return encoderToFeet;
  }
  public double rightDistance(){
    return rightEncoderToFeet;
  }
}
