package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

public class BlueLeft extends LinearOpMode {
    private Servo lc;
    private Servo rc;
    private Servo nodder;
    private DcMotorEx extender;

    private DcMotorEx rotater;
    private DcMotorEx frontRight;
    private DcMotorEx backRight;
    private DcMotorEx frontLeft;
    private DcMotorEx backLeft;
    private DcMotorEx turn;
    private DcMotorEx ext;
    @Override
    public void runOpMode() throws InterruptedException {
        TemplateJanx janx = new TemplateJanx(hardwareMap);
        waitForStart();
        if (opModeIsActive()) {
            // Put run blocks here.
            while (opModeIsActive()) {
                initialise();
                janx.forward(1);
            }
        }

    }
    private void initialise() {
        TemplateJanx janx = new TemplateJanx(hardwareMap);
        janx.wheelInit("frontRight", "backRight", "backLeft", "frontLeft");
        frontLeft = janx.fl;
        frontRight = janx.fr;
        backRight = janx.br;
        backLeft = janx.bl;
        janx.armInit("clawLeft", "clawRight", "nodder", "armExtension", "arm rotations");
        lc = janx.lc;
        rc = janx.rc;
        nodder = janx.nod;
        extender = janx.ext;
        rotater = janx.turn;
    }
}


