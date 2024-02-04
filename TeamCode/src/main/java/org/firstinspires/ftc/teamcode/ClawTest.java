package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "Claw")
public class ClawTest extends LinearOpMode {
    private Servo clawLeft;
    private Servo clawRight;
    private Servo nodder;
    double power = .005;
    /**
     * This function is executed when this Op Mode is selected from the Driver Station.
     */
    @Override
    public void runOpMode() {
        TemplateJanx janx = new TemplateJanx(hardwareMap);
        janx.arminit("clawLeft","clawRight","nodder","armExtension","arm rotations");
        clawLeft  = janx.lc;
        clawRight = janx.rc;
        nodder    = janx.nod;
//        clawRight = hardwareMap.get(Servo.class,"clawRight");
//        nodder = hardwareMap.get(Servo.class,"vertical");
        waitForStart();
        if (opModeIsActive()) {
            clawLeft.setPosition(1);
            clawRight.setPosition(0);
            // Put run blocks here.
            //initialisations here
            while (opModeIsActive()) {
                claw(gamepad2.left_stick_x);
                arm();
                telemetry.addData("ResultLeft",clawLeft.getPosition());
                telemetry.addData("ResultRight",clawRight.getPosition());
                telemetry.update();
            }
        }
    }
    private void claw(double input){
//        //the claw nodder
//        if(gamepad2.left_stick_y>0){
//            nodder.setPosition(nodder.getPosition()+power);
//        }
//        else if(gamepad2.left_stick_y<0){
//            nodder.setPosition(nodder.getPosition()-power);
//        }
        //opens and closes claw
        telemetry.addData("input: ",input);
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
                // move to 0 degrees.
                //fully closed
                clawLeft.setPosition(0);
                clawRight.setPosition(1);
            } else if (gamepad2.x || gamepad2.b) {
                // move to 90 degrees.
                clawLeft.setPosition(0.5);
                clawRight.setPosition(0.5);
            } else if (gamepad2.a) {
                // move to 180 degrees.
                //fully open
                clawLeft.setPosition(1);
                clawRight.setPosition(0);
            }
        }
    }
    private void arm(){

    }
}
