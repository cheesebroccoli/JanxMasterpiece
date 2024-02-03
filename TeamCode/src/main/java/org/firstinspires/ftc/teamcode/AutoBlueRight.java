package org.firstinspires.ftc.teamcode;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import static java.lang.Thread.sleep;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous
public class AutoBlueRight extends LinearOpMode {
    TemplateJanx robot = new TemplateJanx();
    @Override
    public void runOpMode() throws InterruptedException {

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
            robot.fr.setPower(1);
            robot.br.setPower(-1);
            robot.bl.setPower(1);
            robot.fl.setPower(-1);
        }
        else if(direction.equals("RIGHT")){
            robot.fr.setPower(-1);
            robot.br.setPower(1);
            robot.bl.setPower(1);
            robot.fl.setPower(-1);
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
            robot.fr.setPower(right);
            robot.br.setPower(right);
            robot.bl.setPower(left);
            robot.fl.setPower(left);
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


