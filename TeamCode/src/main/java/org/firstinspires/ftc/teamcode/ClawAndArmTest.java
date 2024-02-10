package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "Claw")
public class ClawAndArmTest extends LinearOpMode {
    private Servo clawLeft;
    private Servo clawRight;
    private Servo nodder;
    private DcMotorEx extender;
    private DcMotorEx rotater;
    private DcMotorEx screwLeft;
    private DcMotorEx screwRight;

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
        janx.clawInit("clawLeft","clawRight","nodder");
        clawLeft  = hardwareMap.get(Servo.class,"clawLeft");
        clawRight = hardwareMap.get(Servo.class,"clawRight");
        nodder    = hardwareMap.get(Servo.class,"nodder");
        extender = janx.ext;
        rotater   = janx.turn;
        waitForStart();
        if (opModeIsActive()) {
            // Put run blocks here.
            //initialisations here
            while (opModeIsActive()) {
//                clawLeft.setPosition(1);
//                clawRight.setPosition(0);
                claw();
                arm();
                log_stuff();
                telemetry.addData("left",clawLeft.getPosition());
                telemetry.addData("right",clawRight.getPosition());
            }
        }
    }
    private void log_stuff() {
        telemetry.addData("arm", rotater.getCurrentPosition());
        telemetry.addData("nodder", nodder.getPosition());

    }
    private void lift(){
        screwLeft  = hardwareMap.get(DcMotorEx.class, "ScrewLeft");
        screwRight  = hardwareMap.get(DcMotorEx.class,"ScrewRight");
        screwLeft.setDirection(DcMotor.Direction.FORWARD);
        screwLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        screwLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        screwLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT); //should it be brake?
        screwLeft.setPower(0);
        screwRight.setDirection(DcMotor.Direction.FORWARD);
        screwRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        screwRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        screwRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT); //should it be brake?
        screwRight.setPower(0);

        if(gamepad2.left_trigger!=0){
            //up
           screwLeft.setPower(1);
           screwRight.setPower(1);
        }
        else if(gamepad2.right_trigger!=0){

            screwLeft.setPower(-1);
            screwRight.setPower(-1);
        }
        else{
            screwLeft.setPower(0);
            screwRight.setPower(0);
        }
    }

    /**claw- Gamepad2 left stick**/
    private void claw(){
        /**the claw nodder**/
        double y = .001;
        double x =gamepad2.left_stick_y;
        if(x>0){
            nodder.setPosition(nodder.getPosition()+y);
        }
        else if(x<0){
            nodder.setPosition(nodder.getPosition()-y);
        }
        //opens and closes claw
        if(gamepad2.left_bumper){
           clawLeft.setPosition(0);
            clawRight.setPosition(1);
//            clawLeft.setPosition(clawLeft.getPosition()+power);
//            clawRight.setPosition(clawRight.getPosition()-power);
//            if(Math.abs(clawLeft.getPosition())!=Math.abs(clawRight.getPosition())){
//                clawLeft.setPosition((clawLeft.getPosition()+clawRight.getPosition()/2);
//                clawRight.setPosition((clawLeft.getPosition()+clawRight.getPosition()/2);
//            }

        }
        if(gamepad2.right_bumper){
            clawLeft.setPosition(1);
            clawRight.setPosition(0);
//            clawLeft.setPosition(clawLeft.getPosition()-power);
//            clawRight.setPosition(clawRight.getPosition()+power);
//            if(Math.abs(clawLeft.getPosition())!=Math.abs(clawRight.getPosition())){
//                clawLeft.setPosition((clawLeft.getPosition()+clawRight.getPosition()/2);
//                clawRight.setPosition((clawLeft.getPosition()+clawRight.getPosition()/2);
//            }

        }
    }
    /**arm- Gamepad2 right stick**/
    //need to add pidf!
    private void arm(){
        rotater.setTargetPositionTolerance(10);
        /**Right stick y (extender)**/
        if(gamepad2.dpad_up){
            /**goes up**/
            extender.setPower(1);
        }
        else if(gamepad2.dpad_down){
            /**goes down**/
            extender.setPower(-1);
        }
        else{
            extender.setPower(0);
        }
        /**Right stick x (turn)**/
        if(gamepad2.right_stick_y>0){
            /**goes left?**/
            rotater.setPower(1);
            //rotater.setTargetPosition(rotater.getCurrentPosition()+5);
        }
        else if(gamepad2.right_stick_y<0){
            /**goes right?**/
            rotater.setPower(-1);
            //rotater.setTargetPosition(rotater.getCurrentPosition()-5);
        }
        else{
            rotater.setPower(0);
        }

    }
    
}
