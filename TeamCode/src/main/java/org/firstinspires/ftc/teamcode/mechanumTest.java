package org.firstinspires.ftc.teamcode;

import java.lang.annotation.Target;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import static com.qualcomm.robotcore.util.Range.clip;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "MechanumTest")
public class mechanumTest extends LinearOpMode {

    private DcMotorEx frontRight;
    private DcMotorEx backRight;
    private DcMotorEx frontLeft;
    private DcMotorEx backLeft;
    private DcMotorEx screwLeft;
    private DcMotorEx screwRight;
    private Servo clawLeft;

    private Servo clawRight;

    /**
     * This function is executed when this Op Mode is selected from the Driver Station.
     */
    @Override
    public void runOpMode() {
        frontRight =  hardwareMap.get(DcMotorEx.class, "frontRight");
        backRight =   hardwareMap.get(DcMotorEx.class, "backRight");
        frontLeft =   hardwareMap.get(DcMotorEx.class, "frontLeft");
        backLeft =    hardwareMap.get(DcMotorEx.class, "backLeft");
        screwLeft =   hardwareMap.get(DcMotorEx.class, "screwLeft");
        screwRight =  hardwareMap.get(DcMotorEx.class, "screwRight");
        clawLeft = hardwareMap.get(Servo.class,"clawLeft");
        clawRight = hardwareMap.get(Servo.class,"clawRight");

        // Put initialization blocks here.
        frontRight.setDirection(DcMotor.Direction.FORWARD);
        backRight.setDirection(DcMotor.Direction.FORWARD);
        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        backLeft.setDirection(DcMotor.Direction.REVERSE);
        frontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        screwLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        screwRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        screwLeft.setDirection(DcMotor.Direction.FORWARD);
        screwRight.setDirection(DcMotor.Direction.FORWARD);
        screwLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        screwRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


        waitForStart();
        if (opModeIsActive()) {
            // Put run blocks here.
            while (opModeIsActive()) {
                // Put loop blocks here.
                mecanum(gamepad1.left_stick_y, gamepad1.left_stick_x, gamepad1.right_stick_x);
                Lift();
                claw();
                telemetry.addData("Left", clawLeft.getPosition());
                telemetry.addData("Right", clawRight.getPosition());
                telemetry.addData("Left Stick Y", gamepad1.left_stick_y);
                telemetry.addData("Right Stick X", gamepad1.right_stick_x);
                telemetry.update();
            }
        }
    }

    /**
     * Describe this function...
     */
    private void claw(){
    }
    private void Lift() {
        if (gamepad2.dpad_down) {
            screwLeft.setPower(1);
            screwRight.setPower(1);
        } else if (gamepad2.dpad_up) {
            screwLeft.setPower(-1);
            screwRight.setPower(-1);
        } else {
            screwLeft.setPower(0);
            screwRight.setPower(0);
        }
    }

    /**
     * Describe this function...
     */
    private void mecanum(double LSY, double LSX, double RSX){
        //RSX: + if forward, - if reverse: Set direction based 
        int Speed = 1600;
        double lx = Math.pow(LSY,3);
        double ly = Math.pow(LSY,3);
        double rx = Math.pow(RSX,3);
        if(LSX != 0 || LSY != 0 || RSX != 0){
            frontRight.setVelocity(Speed*(clip((ly)+lx,-1,1)+rx));
            backRight.setVelocity(Speed*(clip((ly)-lx,-1,1)+rx));
            frontLeft.setVelocity(Speed*(clip((ly)-lx,-1,1)-rx));
            backLeft.setVelocity(Speed*(clip((ly)+lx,-1,1)-rx));
        }
        else{
            frontLeft.setVelocity(0);
            backLeft.setVelocity(0);
            frontRight.setVelocity(0);
            backRight.setVelocity(0);
        }
    }
}
