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
                telemetry.addData("arm", rotater.getCurrentPosition());
                telemetry.addData("nodder", nodder.getPosition());
                telemetry.addData("left",clawLeft.getPosition());
                telemetry.addData("right",clawRight.getPosition());
                telemetry.update();
            }
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
        rotater.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
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
        rotater.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }
    
}
