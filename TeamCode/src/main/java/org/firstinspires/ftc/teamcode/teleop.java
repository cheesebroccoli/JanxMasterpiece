package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

import static com.qualcomm.robotcore.util.Range.clip;


@TeleOp(name = "DriverControl")
public class teleop extends OpMode {
    private DcMotorEx frontRight;
    private DcMotorEx backRight;
    private DcMotorEx frontLeft;
    private DcMotorEx backLeft;
    private Servo clawLeft;
    private Servo clawRight;
    private Servo nodder;
    private DcMotorEx extender;

    private DcMotorEx rotator;

    private DcMotorEx screwRight;
    private DcMotorEx screwLeft;

    double power = .005;

    @Override
    public void init() {
        TemplateJanx janx = new TemplateJanx(hardwareMap);
        janx.wheelInit("frontRight", "backRight", "backLeft", "frontLeft");
        frontLeft = janx.fl;
        frontRight = janx.fr;
        backRight = janx.br;
        backLeft = janx.bl;
        janx.armInit("armExtension", "arm rotations", "ScrewLeft", "ScrewRight");
        extender = janx.ext;
        rotator = janx.turn;
        screwRight = janx.sr;
        screwLeft = janx.sl;

        janx.clawInit("clawLeft", "clawRight", "nodder");
        clawLeft = janx.lc;
        clawRight = janx.rc;
        nodder = janx.nod;
        rotator.setTargetPosition(rotator.getCurrentPosition());

    }

    @Override
    public void loop() {
        mecanum(gamepad1.left_stick_y, gamepad1.left_stick_x, gamepad1.right_stick_x);
        telemetry.addData("nod",nodder.getPosition());
        telemetry.addData("left",clawLeft.getPosition());
        telemetry.addData("right",clawRight.getPosition());
        telemetry.update();
        if (gamepad2.x) {
//          //open
            clawLeft.setPosition(1);
            clawRight.setPosition(1);
        }
        if (gamepad2.b) {
            //close
            clawLeft.setPosition(0);
            clawRight.setPosition(0);
        }
        claw();
        arm();
        lift();
        telemetry.update();
    }

    private void mecanum(double LSY, double LSX, double RSX) {
        int Speed = 1600;
        double lx = Math.pow(LSX, 3);
        double ly = -(Math.pow(LSY, 3));
        double rx = Math.pow(RSX, 3);
        //is RSX backwards? I may need to fix the canva
        if (LSX != 0 || LSY != 0 || RSX != 0) {
            frontRight.setVelocity(Speed * (clip((ly) - lx, -1, 1) - rx));
            frontLeft.setVelocity(Speed * (clip((ly) + lx, -1, 1) + rx));
            backRight.setVelocity(Speed * (clip((ly) + lx, -1, 1) - rx));
            backLeft.setVelocity(Speed * (clip((ly) - lx, -1, 1) + rx));
        } else {
            frontLeft.setVelocity(0);
            backLeft.setVelocity(0);
            frontRight.setVelocity(0);
            backRight.setVelocity(0);
        }
    }

    private void arm() {
        rotator.setTargetPositionTolerance(10);
//
        /* Right stick x (turn) */
        if (gamepad2.right_stick_y > 0) {
            /* goes left? */
//            rotator.setPower(1);
            rotator.setTargetPosition(rotator.getCurrentPosition()+5);
        } else if (gamepad2.right_stick_y < 0) {
            /* goes right? */
//            rotator.setPower(-1);
            rotator.setTargetPosition(rotator.getCurrentPosition()-5);
        }
        else{
            rotator.setTargetPosition(rotator.getCurrentPosition());
        }
        rotator.setPower(1);
//        else {
//            rotator.setPower(0);
        //}

    }

    private void claw() {
        /**the claw nodder**/
        double y = .001;
        if (gamepad2.left_stick_y> 0) {
            nodder.setPosition(nodder.getPosition() + y);
        } else if (gamepad2.left_stick_y < 0) {
            nodder.setPosition(nodder.getPosition() - y);
        }
        //opens and closes claw
        if (gamepad2.left_bumper) {
            clawLeft.setPosition(clawLeft.getPosition() - power);
            clawRight.setPosition(clawRight.getPosition() - power);
//            if (Math.abs(clawLeft.getPosition()) != Math.abs(clawRight.getPosition())) {
//                clawLeft.setPosition((clawLeft.getPosition() + clawRight.getPosition()) / 2);
//                clawRight.setPosition((clawLeft.getPosition() + clawRight.getPosition()) / 2);
//            }

        }
        if (gamepad2.right_bumper) {
            clawLeft.setPosition(clawLeft.getPosition() - power);
            clawRight.setPosition(clawRight.getPosition() - power);
//            if (Math.abs(clawLeft.getPosition()) != Math.abs(clawRight.getPosition())) {
//                clawLeft.setPosition((clawLeft.getPosition() + clawRight.getPosition()) / 2);
//                clawRight.setPosition((clawLeft.getPosition() + clawRight.getPosition()) / 2);
//            }

        }
        if (gamepad2.y) {
            clawLeft.setPosition(1);
            clawRight.setPosition(0);
        } else if (gamepad2.a) {
            clawLeft.setPosition(0);
            clawRight.setPosition(1);
        }

    }

    private void lift() {
        if (gamepad2.y) {
            screwLeft.setPower(1);
            screwRight.setPower(1);
        }
        else if (gamepad2.a) {
            screwLeft.setPower(-1);
            screwRight.setPower(-1);
        }
        else{
            screwLeft.setPower(0);
            screwRight.setPower(0);
        }
    }

}
