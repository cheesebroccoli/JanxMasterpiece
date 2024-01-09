package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;

import java.lang.annotation.Target;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import static com.qualcomm.robotcore.util.Range.clip;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
@TeleOp(name = "Claw")
public class ClawTest extends LinearOpMode {
    private Servo clawLeft;
    private Servo clawRight;

    double power = 5;
    /**
     * This function is executed when this Op Mode is selected from the Driver Station.
     */
    @Override
    public void runOpMode() {
        clawLeft = hardwareMap.get(Servo.class,"clawLeft");
        clawRight = hardwareMap.get(Servo.class,"clawRight");
        waitForStart();
        if (opModeIsActive()) {
            // Put run blocks here.
            //initialisations here
            while (opModeIsActive()) {
                claw();
                telemetry.addData("Left",clawLeft.getPosition());
                telemetry.addData("Right",clawRight.getPosition());
                telemetry.addData("stick:",gamepad2.left_stick_x);
                telemetry.update();
            }
        }
    }
    private void claw(){
        if(gamepad2.left_stick_x!=0){
            if(gamepad2.left_stick_x>0){
                //Left:open
              clawLeft.setPosition(clawLeft.getPosition()+power);
              clawRight.setPosition(clawLeft.getPosition()-power);
            }
            else if(gamepad2.left_stick_x<0){
                //right:close
                clawLeft.setPosition(clawLeft.getPosition()-power);
                clawRight.setPosition(clawLeft.getPosition()+power);
            }
        }
        else {
            if (gamepad2.y) {
                // move to 0 degrees.
                clawLeft.setPosition(0);
                clawRight.setPosition(1);
            } else if (gamepad2.x || gamepad2.b) {
                // move to 90 degrees.
                clawLeft.setPosition(0.5);
                clawRight.setPosition(0.5);
            } else if (gamepad2.a) {
                // move to 180 degrees.
                clawLeft.setPosition(1);
                clawRight.setPosition(0);
            }
        }
    }
}
