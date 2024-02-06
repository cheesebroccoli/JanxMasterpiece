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

    /**
     * TO DO:
     * Strife
     * Set it to opMode
     */

    @Override
    public void runOpMode() {
        TemplateJanx janx = new TemplateJanx(hardwareMap);
        janx.wheelInit("frontRight","backRight","backLeft","frontLeft");
        frontLeft =  janx.fl;
        frontRight = janx.fr;
        backRight =  janx.br;
        backLeft =   janx.bl;

        waitForStart();
        if (opModeIsActive()) {
            // Put run blocks here.
            while (opModeIsActive()) {
                // Put loop blocks here.
                mecanum(gamepad1.left_stick_y, gamepad1.left_stick_x, gamepad1.right_stick_x);
                telemetry.addData("Left Stick Y", gamepad1.left_stick_y);
                telemetry.addData("Right Stick X", gamepad1.right_stick_x);
                telemetry.update();
            }
        }
    }

    private void mecanum(double LSY, double LSX, double RSX) {
        int Speed = 1600;
        double lx = Math.pow(LSY,3);
        double ly = -(Math.pow(LSY,3));
        double rx = Math.pow(RSX,3);
        if(LSX != 0 || LSY != 0 || RSX != 0){
            frontRight.setVelocity(Speed*(clip((ly)-lx,-1,1)+rx));
            frontLeft.setVelocity(Speed*(clip((ly)-lx,-1,1)-rx));
            backRight.setVelocity(Speed*(clip((ly)-lx,-1,1)+rx));
            backLeft.setVelocity(Speed*(clip((ly)-lx,-1,1)-rx));
        }
        else{
            frontLeft.setVelocity(0);
            backLeft.setVelocity(0);
            frontRight.setVelocity(0);
            backRight.setVelocity(0);
        }
    }
}

