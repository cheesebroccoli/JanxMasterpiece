package org.firstinspires.ftc.teamcode;
import static com.qualcomm.robotcore.util.Range.clip;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;


public class TemplateJanx {
    private DcMotorEx fr = null;
    private DcMotorEx br = null;
    private DcMotorEx fl = null;
    private DcMotorEx bl = null;
    HardwareMap hwMap = null;

    //clockwise from front right
    //doubles as an initialisation
    public TemplateJanx() {
    }
    public void init(String frontRight, String backRight, String backleft, String frontLeft, HardwareMap ahwMap) {
        //when you make this in teleop or autonomous, you can import hardware.DcMotor with the keyword hardwareMap
        hwMap = ahwMap;
        fr = hwMap.get(DcMotorEx.class, frontRight);
        br = hwMap.get(DcMotorEx.class, backRight);
        fl = hwMap.get(DcMotorEx.class, frontLeft);
        bl = hwMap.get(DcMotorEx.class, backleft);
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
    private void movement(double LSY, double LSX, double RSX){
        //mecanum(gamepad1.left_stick_y, gamepad1.left_stick_x, gamepad1.right_stick_x);
        //RSX: + if forward, - if reverse: Set direction based
        int Speed = 1600;
        double lx = Math.pow(LSY,3);
        double ly = Math.pow(LSY,3);
        double rx = Math.pow(RSX,3);
        if(LSX != 0 || LSY != 0 || RSX != 0){
            fr.setVelocity(Speed*(clip((ly)+lx,-1,1)+rx));
            br.setVelocity(Speed*(clip(ly-lx,-1,1)+rx));
            fl.setVelocity(Speed*(clip(ly-lx,-1,1)-rx));
            bl.setVelocity(Speed*(clip((ly)+lx,-1,1)-rx));
        }
        else{
            fl.setVelocity(0);
            bl.setVelocity(0);
            fr.setVelocity(0);
            br.setVelocity(0);
        }
    }
}
