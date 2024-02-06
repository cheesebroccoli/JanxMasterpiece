package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;


@Autonomous
public class AutoTemplate extends LinearOpMode {
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
        waitForStart();
        if (opModeIsActive()) {
            // Put run blocks here.
            while (opModeIsActive()) {
                initialise();
                forward(2);
            }
        }

    }
    private void initialise(){
        frontRight = hardwareMap.get(DcMotorEx.class, "frontRight");
        backRight  = hardwareMap.get(DcMotorEx.class, "backRight");
        frontLeft  = hardwareMap.get(DcMotorEx.class, "frontLeft");
        backLeft   = hardwareMap.get(DcMotorEx.class, "backLeft");

        frontRight.setDirection(DcMotor.Direction.FORWARD);
        backRight.setDirection(DcMotor.Direction.FORWARD);
        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        backLeft.setDirection(DcMotor.Direction.REVERSE);
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
// janx.armInit("clawLeft","clawRight","nodder","armExtension","arm rotations");
        ext =       hardwareMap.get(DcMotorEx.class,"armExtension");
        turn =      hardwareMap.get(DcMotorEx.class,"arm rotations");
        nodder =    hardwareMap.get(Servo.class,"nodder");
        lc  =       hardwareMap.get(Servo.class, "clawLeft");
        rc  =       hardwareMap.get(Servo.class,"clawRight");

        ext.setDirection(DcMotor.Direction.FORWARD);
        ext.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        ext.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        ext.setPower(0);
        turn.setDirection(DcMotor.Direction.FORWARD);
        turn.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        turn.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        turn.setPower(0);


    }

    private void forward(long time){
        turnOn(1);
        sleep(time);
        turnOff();
    }

    private void backward(long time){
        turnOn(-1);
        sleep(time);
        turnOff();
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
        turnOff();

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

    public void turnOn(double strength){
        frontRight.setPower(strength);
        backRight.setPower(strength);
        backLeft.setPower(strength);
        frontLeft.setPower(strength);
    }
    public void turnOff(){
        frontRight.setPower(0);
        backLeft.setPower(0);
        backLeft.setPower(0);
        frontLeft.setPower(0);
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


