package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous
public class AutoTemplate extends LinearOpMode {
    private Servo clawLeft;
    private Servo clawRight;
    private Servo nodder;
    private DcMotorEx extender;

    private DcMotorEx rotater;
    private DcMotorEx frontRight;
    private DcMotorEx backRight;
    private DcMotorEx frontLeft;
    private DcMotorEx backLeft;
    private DcMotorEx armTurn;
    private DcMotorEx armExt;

//    private com.qualcomm.robotcore.hardware.HardwareMap HardwareMap;
    TemplateJanx robot = new TemplateJanx(hardwareMap);

    @Override
    public void runOpMode() throws InterruptedException {
        robot.wheelInit("frontRight","backRight","backLeft","frontLeft");
        robot.armInit("clawLeft","clawRight","nodder","armExtension","arm rotations");
        frontLeft =  robot.fl;
        frontRight = robot.fr;
        backRight =  robot.br;
        backLeft =   robot.bl;
        extender  =  robot.ext;
        rotater   =  robot.turn;
        clawLeft  =  robot.lc;
        clawRight =  robot.rc;
        nodder    =  robot.nod;
        armExt =     robot.ext;
        armTurn =    robot.turn;

        forward(10);

    }

    private void forward(long time){
        robot.turnOn(1);
        sleep(time);
        robot.turnOff();
    }

    private void backward(long time){
        robot.turnOn(-1);
        sleep(time);
        robot.turnOff();
    }
    private void Strife(String direction, long time){
        if(direction.equals("LEFT")) {
            frontRight.setPower(1);
            backRight.setPower(-1);
            backLeft.setPower(1);
            frontLeft.setPower(-1);
        }
        else if(direction.equals("RIGHT")){
            frontRight.setPower(-1);
            backRight.setPower(1);
            backLeft.setPower(1);
            frontLeft.setPower(-1);
        }
        sleep(time);
        robot.turnOff();

    }
    private void turn(String direction,double seconds){
        double left = 0;
        double right = 0;
        if(direction.equals("LEFT")){
            left = -1;
            right = 1;
        }
        else if(direction.equals("RIGHT")){
            left = 1;
            right =-1;
        }
        for(double i = 0; i<seconds; i++){
            frontRight.setPower(right);
            backRight.setPower(right);
            backLeft.setPower(left);
            frontLeft.setPower(left);
        }

    }

    private void arm(){

    }
    private void claw(String pos){
        if(pos.equals("CLOSE")){

        }
        else if(pos.equals("OPEN")){

        }
        else{

        }
    }


}
//PushbotAutoDriveByEncoder_Linear.java


