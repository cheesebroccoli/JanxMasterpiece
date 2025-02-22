package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

//ftclib library
public class TemplateJanx {
    public DcMotorEx fr = null;
    public DcMotorEx br = null;
    public DcMotorEx fl = null;
    public DcMotorEx bl = null;

    public Servo lc = null;
    public Servo rc = null;
    public Servo nod = null;

    public DcMotorEx ext= null;
    public DcMotorEx turn = null;

    HardwareMap hwMap = null;


    //clockwise from front right
    //doubles as an initialisation
    public TemplateJanx(){
    }
    public void init(String frontRight, String backRight, String backleft, String frontLeft,String leftClaw, String rightClaw, String nodder, String armextend, String armTurn,  HardwareMap ahwMap) {
        //when you make this in teleop or autonomous, you can import hardware.DcMotor with the keyword hardwareMap
        hwMap = ahwMap;
        fr  = hwMap.get(DcMotorEx.class, frontRight);
        br  = hwMap.get(DcMotorEx.class, backRight);
        fl  = hwMap.get(DcMotorEx.class, frontLeft);
        bl  = hwMap.get(DcMotorEx.class, backleft);
        ext = hwMap.get(DcMotorEx.class,armextend);
        turn = hwMap.get(DcMotorEx.class,armTurn);
        nod = hwMap.get(Servo.class,nodder);
        lc  = hwMap.get(Servo.class, leftClaw);
        rc  = hwMap.get(Servo.class,rightClaw);

        ext.setDirection(DcMotor.Direction.FORWARD);
        ext.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        ext.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        ext.setPower(0);
        turn.setDirection(DcMotor.Direction.FORWARD);
        turn.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        turn.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        turn.setPower(0);
        fr.setDirection(DcMotor.Direction.FORWARD);
        br.setDirection(DcMotor.Direction.FORWARD);
        fl.setDirection(DcMotor.Direction.REVERSE);
        bl.setDirection(DcMotor.Direction.REVERSE);
        fr.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        br.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        fl.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        bl.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        br.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        fr.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        fl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        bl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT); //should it be brake?
        bl.setPower(0);
        fl.setPower(0);
        br.setPower(0);
        fr.setPower(0);
    }
    public void turnOn(double strength){
        fr.setPower(strength);
        br.setPower(strength);
        bl.setPower(strength);
        fl.setPower(strength);
    }
    public void turnOff(){
        fr.setPower(0);
        br.setPower(0);
        bl.setPower(0);
        fl.setPower(0);
    }

}
