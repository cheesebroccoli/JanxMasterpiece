//package org.firstinspires.ftc.teamcode;
//import com.qualcomm.robotcore.hardware.DcMotor;
//import com.qualcomm.robotcore.hardware.HardwareMap;
//import com.qualcomm.robotcore.hardware.Servo;
//import com.qualcomm.robotcore.util.ElapsedTime;
//public class BasicBot
//{
//    /*T Public OpMode members. */
//    public DcMotor leftRearDrive = null;
//    public DcMotor rightRearDrive = null;
//    public Servo spike = null;
//    public static final double MID_SERVO = 0.5 ;
//    /* local OpMode members. */
//    HardwareMap hwMap = null;
//    private ElapsedTime period = new ElapsedTime();
//    /* Constructor */
//    public BasicBot(){
//    }
//    /* Initialize standard Hardware interfaces */
//    public void init(HardwareMap ahwMap) {
//// Save reference to Hardware map
//        hwMap = ahwMap;
//// Define and Initialize Motors
//        leftRearDrive = hwMap.get(DcMotor.class, "motor_rear_left");
//        rightRearDrive = hwMap.get(DcMotor.class, "motor_rear_right");
//        leftRearDrive.setDirection(DcMotor.Direction.REVERSE); // Set to REVERSE if using AndyMark
//        //motors
//        rightRearDrive.setDirection(DcMotor.Direction.FORWARD);// Set to FORWARD if using AndyMark
//        //motors
//        leftRearDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        rightRearDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//// Set all motors to zero power
//        leftRearDrive.setPower(0);
//        rightRearDrive.setPower(0);
//// Set all motors to run without encoders.
//// May want to use RUN_USING_ENCODERS if encoders are installed.
//        leftRearDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//        rightRearDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//// Define and initialize ALL installed servos.
//        spike = hwMap.get(Servo.class, "servo");
//        spike.setPosition(.2);
//    }
//}