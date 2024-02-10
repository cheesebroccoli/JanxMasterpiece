package org.firstinspires.ftc.teamcode;

import static java.lang.Thread.sleep;

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

    public DcMotorEx ext = null;
    public DcMotorEx turn = null;

    public DcMotorEx sl = null;
    public DcMotorEx sr = null;

    HardwareMap hwMap = null;


    //clockwise from front right
    public TemplateJanx(HardwareMap h) {
        hwMap = h;
    }

    public void wheelInit(String frontRight, String backRight, String backleft, String frontLeft) {
        //when you make this in teleop or autonomous, you can import hardware.DcMotor with the keyword hardwareMap
        fr = hwMap.get(DcMotorEx.class, frontRight);
        br = hwMap.get(DcMotorEx.class, backRight);
        fl = hwMap.get(DcMotorEx.class, frontLeft);
        bl = hwMap.get(DcMotorEx.class, backleft);

        fr.setDirection(DcMotor.Direction.FORWARD);
        br.setDirection(DcMotor.Direction.FORWARD);
        fl.setDirection(DcMotor.Direction.REVERSE);
        bl.setDirection(DcMotor.Direction.REVERSE);
        fr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        fr.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        br.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        br.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        fl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        fl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        bl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        br.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        fr.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        fl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        bl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT); //should it be brake?
        bl.setPower(0);
        fl.setPower(0);
        br.setPower(0);
        fr.setPower(0);
    }

    public void clawInit(String leftClaw, String rightClaw, String nodder) {
        nod = hwMap.get(Servo.class, nodder);
        lc = hwMap.get(Servo.class, leftClaw);
        rc = hwMap.get(Servo.class, rightClaw);
    }

    public void armInit(String armextend, String armTurn, String left, String right) {
        sl = hwMap.get(DcMotorEx.class, left);
        sr = hwMap.get(DcMotorEx.class, right);
//        ext = hwMap.get(DcMotorEx.class, armextend);
        turn = hwMap.get(DcMotorEx.class, armTurn);

//        ext.setDirection(DcMotor.Direction.FORWARD);
//        ext.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//        ext.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        ext.setPower(0);

        turn.setDirection(DcMotor.Direction.FORWARD);
        turn.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        turn.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        turn.setPower(0);

        sl.setDirection(DcMotor.Direction.FORWARD);
        sl.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        sl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        sl.setPower(0);

        sr.setDirection(DcMotor.Direction.FORWARD);
        sr.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        sr.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        sr.setPower(0);
    }

    public void forward(long time) throws InterruptedException {
        turnOn(1);
        sleep(time);
        turnOff();
    }

    public void backward(long time) throws InterruptedException {
        turnOn(-1);
        sleep(time);
        turnOff();
    }

    public void strife(String direction, long time) throws InterruptedException {
        if (direction.equals("LEFT")) {
            fr.setPower(1);
            br.setPower(-1);
            bl.setPower(1);
            fl.setPower(-1);
        } else if (direction.equals("RIGHT")) {
            fr.setPower(-1);
            br.setPower(1);
            bl.setPower(1);
            fl.setPower(-1);
        }
        sleep(time);
        turnOff();

    }

    public void turn(String direction, double seconds) {
        double left = 0;
        double right = 0;
        if (direction.equals("LEFT")) {
            left = -1;
            right = 1;
        } else if (direction.equals("RIGHT")) {
            left = 1;
            right = -1;
        }
        for (double i = 0; i < seconds; i++) {
            fr.setPower(right);
            br.setPower(right);
            bl.setPower(left);
            fl.setPower(left);
        }

    }

    private void turnOn(double strength) {
        fr.setPower(strength);
        br.setPower(strength);
        bl.setPower(strength);
        fl.setPower(strength);
    }

    private void turnOff() {
        fr.setPower(0);
        br.setPower(0);
        bl.setPower(0);
        fl.setPower(0);
    }

    public void claw(String pos) {
        if (pos.equals("CLOSE")) {
            lc.setPosition(0);
            rc.setPosition(1);
        } else if (pos.equals("OPEN")) {
            lc.setPosition(1);
            rc.setPosition(0);
        }
    }

}
