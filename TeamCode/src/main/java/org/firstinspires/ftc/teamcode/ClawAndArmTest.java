package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

import java.lang.Math;

@TeleOp(name = "Claw")
public class ClawAndArmTest extends LinearOpMode {
    private Servo clawLeft;
    private Servo clawRight;
    private Servo nodder;
    private DcMotorEx extender;
    private DcMotorEx rotater;
    private DcMotorEx screwLeft;
    private DcMotorEx screwRight;

    private int tarPosition = 0;

    double power = .005;
    /**
     * TO DO:
     * Fix claw (wiring)
     * Get arm rotation to work
     * set nodder to be be less intense
     */
    @Override
    public void runOpMode() {
        TemplateJanx janx = new TemplateJanx(hardwareMap);
        janx.armInit("armExtension","arm rotations","ScrewLeft","ScrewRight");
        extender  = janx.ext;
        rotater   = janx.turn;
        screwRight = janx.sr;
        screwLeft = janx.sl;
        janx.clawInit("clawLeft","clawRight","nodder");
        clawLeft  = janx.lc;
        clawRight = janx.rc;
        nodder    = janx.nod;
//        clawRight = hardwareMap.get(Servo.class,"clawRight");
//        nodder = hardwareMap.get(Servo.class,"vertical");
        waitForStart();
        if (opModeIsActive()) {
            // Put run blocks here.
            //initialisations here
            while (opModeIsActive()) {
//                clawLeft.setPosition(1);
//                clawRight.setPosition(0);
                claw(gamepad2.left_stick_x);
                arm();
                lift();
                rotater.setPower(gamepad1.right_stick_y);
                telemetry.addData("current",rotater.getCurrentPosition());
                telemetry.addData("targer",rotater.getTargetPosition());
                telemetry.update();
            }
        }
    }
    private void lift(){
        if(gamepad2.right_bumper){
            //up
           screwLeft.setPower(1);
           screwRight.setPower(1);
        }
        else if(gamepad2.left_bumper){

            screwLeft.setPower(-1);
            screwRight.setPower(-1);
        }
        else{
            screwLeft.setPower(0);
            screwRight.setPower(0);
        }
    }

    /**claw- Gamepad2 left stick**/
    private void claw(double input){
        /**the claw nodder**/
        double y = .1;
        double x = Math.pow(gamepad2.left_stick_y,3);
        if(x>0){
            nodder.setPosition(nodder.getPosition()+y);
        }
        else if(x<0){
            nodder.setPosition(nodder.getPosition()-y);
        }
        //opens and closes claw
        telemetry.addData("input: ",input);
        /**input:gamepad2.left_stick_x, by the by**/
            if(input>0){
                //Left:open
              clawLeft.setPosition(clawLeft.getPosition()-power);
              clawRight.setPosition(clawRight.getPosition()+power);
            }
            else if(input<0){
                //right:close
                clawLeft.setPosition(clawLeft.getPosition()+power);
                clawRight.setPosition(clawRight.getPosition()-power);
            }
        else {
            //setpositions.
            if (gamepad2.y) {
                /** move to 0 degrees.
                //fully closed**/
                clawLeft.setPosition(0);
                clawRight.setPosition(1);
                nodder.setPosition(0);
            } else if (gamepad2.x || gamepad2.b) {
                /** move to 90 degrees.**/
                clawLeft.setPosition(0.5);
                clawRight.setPosition(0.5);
                nodder.setPosition(.5);
            } else if (gamepad2.a) {
                /** move to 180 degrees.
                //fully open**/
                clawLeft.setPosition(1);
                clawRight.setPosition(0);
                nodder.setPosition(1);
            }
        }
    }
    /**arm- Gamepad2 right stick**/
    //need to add pidf!
    int _lastPosition = 0;
    private void arm(){
        // for debug
        int _currentPosition = rotater.getCurrentPosition();
        if(_currentPosition != _lastPosition) telemetry.addData("rotaté", _currentPosition);
        _lastPosition = _currentPosition;
        /**Right stick y (extender)**/
        if(gamepad2.right_stick_y>0){
            /**goes up**/
            extender.setPower(1);
        }
        else if(gamepad2.right_stick_y<0){
            /**goes down**/
            extender.setPower(-1);
        }
        else{
            extender.setPower(0);
        }
        /**Right stick x (turn)**/
        rotater.setTargetPositionTolerance(10);
        if(gamepad2.dpad_up){
            /**goes left?**/
            //rotater.setTargetPosition(rotater.getTargetPosition()+35);
            rotater.setPower(1);//Math.min(1.2, 1 / (Math.pow(1.01, (Math.abs(rotater.getCurrentPosition())))))

        }
        else if(gamepad2.dpad_down){
            /**goes right?**/
            //rotater.setTargetPosition(rotater.getTargetPosition()-35);
            rotater.setPower(-1);//Math.min(1.2, 1 / (Math.pow(1.01, (Math.abs(rotater.getCurrentPosition())))))

        }
        else{
           // rotater.setTargetPosition(rotater.getTargetPosition());
            rotater.setPower(0);//Math.min(1.2, 1 / (Math.pow(1.01, (Math.abs(rotater.getCurrentPosition())))))

        }
        //rotater.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        //rotater.setPower(1);//Math.min(1.2, 1 / (Math.pow(1.01, (Math.abs(rotater.getCurrentPosition())))))
    }
}
