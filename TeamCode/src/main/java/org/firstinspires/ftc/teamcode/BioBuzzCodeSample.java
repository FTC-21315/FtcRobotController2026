/*
 * This is a sample for any code related to the BioBuzz season.
 * It contains every important variable needed for the robot to function correctly.
 */
//  DO NOT UPLOAD THIS TO THE ROBOT

package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;

@TeleOp
public class BioBuzzCodeSample extends LinearOpMode {

    public DcMotorEx intake;
    public DcMotorEx singleWheelLauncher;
    public  DcMotorEx doubleWheelLauncher;
    public  DcMotor frontLeftWheel;
    public DcMotor frontRightWheel;
    public DcMotor backLeftWheel;
    public DcMotor backRightWheel;
    public IMU imu;
    public Servo gateServo;

    public final double drivePower = 0.5;
    public final short intakeRpm = 1000;

    @Override
    public void runOpMode() {

//        intake = hardwareMap.get(DcMotorEx.class, "0Intake");
//        singleWheelLauncher = hardwareMap.get(DcMotorEx.class, "1SingleWheelLauncher");
//        doubleWheelLauncher = hardwareMap.get(DcMotorEx.class, "2DoubleWheelLauncher");
//        frontLeftWheel = hardwareMap.get(DcMotor.class, "0FrontLeft");
//        frontRightWheel = hardwareMap.get(DcMotor.class, "1FrontRight");
//        backLeftWheel = hardwareMap.get(DcMotor.class, "2BackLeft");
//        backRightWheel = hardwareMap.get(DcMotor.class, "3BackRight");
//        imu = hardwareMap.get(IMU.class,"imu");
//        gateServo = hardwareMap.get(Servo.class, "GateServo");
        initHardwareVars();

        waitForStart();
        if(opModeIsActive()){
            imu.resetYaw();
            imu.initialize(new IMU.Parameters(new RevHubOrientationOnRobot(
                            imu.getRobotOrientation(
                                    AxesReference.INTRINSIC,
                                    AxesOrder.XYZ,
                                    AngleUnit.DEGREES))));

            intake.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            // Using GoBilda 5203 312rpm
//            intake.setVelocity(setMotorRPM(28,1000));
            intake.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            while(opModeIsActive()){
//                double thing = setMotorRPM(28,128);
                telemetry.update();
            }
        }
    }
    /**
     Sets built in class DcMotorEx to target RPM.
     @param externalGearRatio takes account the gear ratio that is used externally (attached on motor shaft), not
     the gearbox gear ratio built into the motor.
     @param ticksPerRevolution is obtained through the spec sheet provided by GoBilda. For the 5203 series it
     will be named as "at Gearbox Output Shaft: 537.7 Pulses per Revolution".
     Generally the equation is 28 * INTERNAL GEAR RATIO.
     The official naming for this is pulses but we as a team use ticks.
     */
    public double setMotorRPM(float targetRPM, int externalGearRatio, double ticksPerRevolution){
        return (ticksPerRevolution * (targetRPM / externalGearRatio))/60.0;
    }

    /**
     * Initializes all the hardware variables in one call. prevents the need of having a huge chunk of
     * declaring variables under linearOpMode()
     */
    public void initHardwareVars(){
        intake = hardwareMap.get(DcMotorEx.class, "0Intake");
        singleWheelLauncher = hardwareMap.get(DcMotorEx.class, "1SingleWheelLauncher");
        doubleWheelLauncher = hardwareMap.get(DcMotorEx.class, "2DoubleWheelLauncher");
        frontLeftWheel = hardwareMap.get(DcMotor.class, "0FrontLeft");
        frontRightWheel = hardwareMap.get(DcMotor.class, "1FrontRight");
        backLeftWheel = hardwareMap.get(DcMotor.class, "2BackLeft");
        backRightWheel = hardwareMap.get(DcMotor.class, "3BackRight");
        imu = hardwareMap.get(IMU.class,"imu");
        gateServo = hardwareMap.get(Servo.class, "GateServo");
    }
}


