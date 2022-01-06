// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.ctre.phoenix.sensors.PigeonIMU;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Drive extends SubsystemBase {
  Spark leftRear = new Spark(1);
  WPI_TalonSRX wrist = new WPI_TalonSRX(13);

  PigeonIMU gyro = new PigeonIMU(wrist);
  WPI_TalonSRX leftFront = new WPI_TalonSRX(12);
  WPI_TalonSRX rightRear = new WPI_TalonSRX(11);
  WPI_VictorSPX rightFront = new WPI_VictorSPX(18);
  SpeedControllerGroup leftDrive = new SpeedControllerGroup(leftRear, leftFront);
  SpeedControllerGroup rightDrive = new SpeedControllerGroup(rightFront, rightRear);
  DifferentialDrive drive = new DifferentialDrive(leftDrive, rightDrive);
  double[] yprGyro;
  PigeonIMU.GeneralStatus genStatus = new PigeonIMU.GeneralStatus();

  public void move(double leftSpeed, double rightSpeed){
    drive.tankDrive(leftSpeed, rightSpeed);
  }
/** Creates a new Drive. */
  public Drive() {
    rightRear.setSelectedSensorPosition(0);
    leftFront.setSelectedSensorPosition(0);
    rightRear.setSensorPhase(true);
    leftFront.setSensorPhase(false);




  }
double encoderToFeet;
double rightEncoderToFeet;

  @Override
  public void periodic() { 
yprGyro = new double[3];
gyro.getYawPitchRoll(yprGyro);
    // This method will be called once per scheduler run
    double leftTraveled = leftFront.getSelectedSensorPosition();
    encoderToFeet = leftTraveled * ((Math.PI*6)/49152);
    double rightTraveled = rightRear.getSelectedSensorPosition();
    rightEncoderToFeet = rightTraveled * ((Math.PI*6)/49152);
    SmartDashboard.putNumberArray("gyroarray", yprGyro );
    SmartDashboard.putNumber("gyro0", yprGyro[0]);
  }
  public double leftDistance(){
    return encoderToFeet;
  }
  public double rightDistance(){
    return rightEncoderToFeet;
  }
  
  public double gyroYaw(){
    return yprGyro[0];
  }
  public double gyroRoll(){
    return yprGyro[1];
  }
  public double gyroPitch(){
    return yprGyro[2];
  }
}
