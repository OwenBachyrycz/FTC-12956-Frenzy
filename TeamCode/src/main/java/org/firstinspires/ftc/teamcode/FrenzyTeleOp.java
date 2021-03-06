package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Owen Bachyrycz on 12/1/2021.
 * adding a comment to check if github is broke
 */

@TeleOp(name="FrenzyTeleOP", group="default")

public class FrenzyTeleOp extends OpMode {

    //Declares motor variables
    DcMotor frontLeft;
    DcMotor rearLeft;
    DcMotor frontRight;
    DcMotor rearRight;
    DcMotor carouselMover;
    DcMotor liftSpool;
    DcMotor intake;
    Servo carriage;

    //The power each motor should be set to
    double frontLeftPower;
    double rearLeftPower;
    double frontRightPower;
    double rearRightPower;

    @Override
    public void init() {
        //Initializes the motors and assigns them to a motor in the hardwareMap
        frontLeft = hardwareMap.dcMotor.get("frontLeft");
        rearLeft = hardwareMap.dcMotor.get("rearLeft");
        frontRight = hardwareMap.dcMotor.get("frontRight");
        rearRight = hardwareMap.dcMotor.get("rearRight");
        carouselMover = hardwareMap.dcMotor.get("carouselMover");
        liftSpool = hardwareMap.dcMotor.get("liftSpool");
        intake = hardwareMap.dcMotor.get("intakeMotor");

        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rearLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rearRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        rearLeft.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    @Override
    public void loop() {
        //Takes input from controller joysticks and assigns them to
        //variables x, y, and rx
        double x = gamepad1.left_stick_x;
        double y = -gamepad1.left_stick_y;
        double rx = gamepad1.right_stick_x;

        //Calculates the power that should be sent to each motor
        //based on the controller inputs.
        frontLeftPower = (y + x + rx);
        rearLeftPower = (y - x + rx);
        frontRightPower = (y - x - rx);
        rearRightPower = (y + x -rx);

        //Applies the calculated power to the motors
        frontLeft.setPower(frontLeftPower);
        rearLeft.setPower(rearLeftPower);
        frontRight.setPower(frontRightPower);
        rearRight.setPower(rearRightPower);

        if(gamepad1.a) {
            carouselMover.setPower(0.5);
        } else if (gamepad1.b){
            carouselMover.setPower(-0.5);
        } else {
            carouselMover.setPower(0);
        }

        if(gamepad1.left_bumper){
            carriage.setPosition(0.75);
        } else if(gamepad1.right_bumper){
            carriage.setPosition(0.25);
        } else {
            carriage.setPosition(0.5);
        }

        if(gamepad1.left_trigger > 0) {
            liftSpool.setPower(-(gamepad1.left_trigger / 2));
        } else if(gamepad1.right_trigger > 0) {
            liftSpool.setPower(gamepad1.right_trigger / 2);
        } else {
            liftSpool.setPower(0);
        }

        if(gamepad1.y) {
            intake.setPower(1);
        } else if(gamepad1.x){
            intake.setPower(-1);
        } else {
            intake.setPower(0);
        }
    }

    @Override
    public void stop() {
        // Sets all motors to zero, stopping the drivetrain
        frontLeft.setPower(0);
        rearLeft.setPower(0);
        frontRight.setPower(0);
        rearRight.setPower(0);
        intake.setPower(0);
        carouselMover.setPower(0);
        liftSpool.setPower(0);
    }
}
