package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;
import static com.qualcomm.robotcore.util.Range.clip;


@TeleOp(name = "DriverControl")
public class teleop extends OpMode {
    private DcMotorEx frontRight;
    private DcMotorEx backRight;
    private DcMotorEx frontLeft;
    private DcMotorEx backLeft;
    private Servo clawLeft;
    private Servo clawRight;
    private Servo nodder;
    private DcMotorEx extender;

    private DcMotorEx rotater;

    private DcMotorEx screwRight;
    private DcMotorEx screwLeft;

    double power = .005;
    @Override
    public void init() {
        TemplateJanx janx = new TemplateJanx(hardwareMap);
        janx.wheelInit("frontRight","backRight","backLeft","frontLeft");
        frontLeft =  janx.fl;
        frontRight = janx.fr;
        backRight =  janx.br;
        backLeft =   janx.bl;
        janx.armInit("armExtension","arm rotations","ScrewLeft","ScrewRight");
        extender  = janx.ext;
        rotater   = janx.turn;
        screwRight = janx.sr;
        screwLeft = janx.sl;
        janx.clawInit("clawLeft","clawRight","nodder");
        clawLeft  = janx.lc;
        clawRight = janx.rc;
        nodder    = janx.nod;
    }

    @Override
    public  void start() {

    }

    @Override
    public void loop() {
        mecanum(gamepad1.left_stick_y, gamepad1.left_stick_x, gamepad1.right_stick_x);
        telemetry.addData("Y", gamepad1.left_stick_y);
        telemetry.addData("X", gamepad1.left_stick_x);
        telemetry.update();
        claw();
        arm();
        lift();
        telemetry.addData("inputActually",gamepad2.left_stick_y);
        telemetry.addData("NodderPos",nodder.getPosition());
        telemetry.update();    }
    @Override
    public void stop() {

    }
    private void mecanum(double LSY, double LSX, double RSX) {
        int Speed = 1600;
        double lx = Math.pow(LSX,3);
        double ly = -(Math.pow(LSY,3));
        double rx = Math.pow(RSX,3);
        //is RSX backwards? I may need to fix the canva
        if(LSX != 0 || LSY != 0 || RSX != 0){
            frontRight.setVelocity(Speed*(clip((ly)-lx,-1,1)-rx));
            frontLeft.setVelocity(Speed*(clip((ly)+lx,-1,1)+rx));
            backRight.setVelocity(Speed*(clip((ly)+lx,-1,1)-rx));
            backLeft.setVelocity(Speed*(clip((ly)-lx,-1,1)+rx));
        }
        else{
            frontLeft.setVelocity(0);
            backLeft.setVelocity(0);
            frontRight.setVelocity(0);
            backRight.setVelocity(0);
        }
    }
    private void arm() {
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
//            clawLeft.setPosition(0);
//            clawRight.setPosition(1);
            clawLeft.setPosition(clawLeft.getPosition()+power);
            clawRight.setPosition(clawRight.getPosition()-power);
            if(Math.abs(clawLeft.getPosition())!=Math.abs(clawRight.getPosition())){
                clawLeft.setPosition((clawLeft.getPosition()+clawRight.getPosition())/2);
                clawRight.setPosition((clawLeft.getPosition()+clawRight.getPosition())/2);
          }

        }
        if(gamepad2.right_bumper){
//            clawLeft.setPosition(1);
//            clawRight.setPosition(0);
            clawLeft.setPosition(clawLeft.getPosition()-power);
            clawRight.setPosition(clawRight.getPosition()+power);
            if(Math.abs(clawLeft.getPosition())!=Math.abs(clawRight.getPosition())){
                clawLeft.setPosition((clawLeft.getPosition()+clawRight.getPosition())/2);
                clawRight.setPosition((clawLeft.getPosition()+clawRight.getPosition())/2);
            }

        }
            if(gamepad2.y){
                clawLeft.setPosition(1);
                clawRight.setPosition(0);
            }
            else if(gamepad2.a){
                clawLeft.setPosition(0);
                clawRight.setPosition(1);
            }

       }
       private void lift(){
        if(gamepad1.y){
            screwLeft.setPower(1);
            screwRight.setPower(1);
        }
        else if(gamepad1.a){
            screwRight.setPower(-1);
            screwLeft.setPower(-1);
        }
        else{
            screwLeft.setPower(0);
            screwRight.setPower(0);
        }
       }

//    private void claw(double input){
//        /**the claw nodder**/
//        double y = .1;
//        double x = Math.pow(gamepad2.left_stick_y,3);
//        if(x>0){
//            nodder.setPosition(nodder.getPosition()+y);
//        }
//        else if(x<0){
//            nodder.setPosition(nodder.getPosition()-y);
//        }
//        //opens and closes claw
//        telemetry.addData("input: ",input);
//        /**input:gamepad2.left_stick_x, by the by**/
//        if(input>0){
//            //Left:open
//            clawLeft.setPosition(clawLeft.getPosition()-power);
//            clawRight.setPosition(clawRight.getPosition()+power);
//        }
//        else if(input<0){
//            //right:close
//            clawLeft.setPosition(clawLeft.getPosition()+power);
//            clawRight.setPosition(clawRight.getPosition()-power);
//        }
//        else
//        {
//        }
//    }


}
