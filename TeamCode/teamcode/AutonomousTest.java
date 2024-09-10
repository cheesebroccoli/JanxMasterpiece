package org.firstinspires.ftc.teamcode;
//@Autonomous(name="AutonomousTest", group="Test")
//public class AutonomousTest extends LinearOpMode {
//    /* Declare OpMode members. */
//    TemplateJanx robot = new TemplateJanx(); // Use the hardware file
//    private ElapsedTime runtime = new ElapsedTime();
//    static final double COUNTS_PER_MOTOR_REV = 1440; // eg: TETRIX Motor Encoder
//    static final double DRIVE_GEAR_REDUCTION = 1.0; // This is < 1.0 if geared UP
//    static final double WHEEL_DIAMETER_INCHES = 4.0; // For figuring circumference
//    static final double COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV *
//            DRIVE_GEAR_REDUCTION) /
//            (WHEEL_DIAMETER_INCHES * 3.1415);
//    static final double DRIVE_SPEED = 0.6;
//    static final double TURN_SPEED = 0.5;
//
//    @Override
//    public void runOpMode() {
//        /*
//         * Initialize the drive system variables.
//         * The init() method of the hardware class does all the work here
//         */
//        robot.init("frontRight","backRight","backLeft","frontLeft", hardwareMap);
//// Send telemetry message to signify robot waiting;
//        telemetry.addData("Status", "Resetting Encoders"); //
//        telemetry.update();
//        robot.leftRearDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        robot.rightRearDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        robot.leftRearDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        robot.rightRearDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//// Send telemetry message to indicate successful Encoder reset
//        telemetry.addData("Path0", "Starting at %7d :%7d",
//                robot.leftRearDrive.getCurrentPosition(),
//                robot.rightRearDrive.getCurrentPosition());
//        telemetry.update();
//// Wait for the game to start (driver presses PLAY)
//        waitForStart();
////Drive by time
////power up the motors to 50% power (using a negative will make the wheel go backwards)
//        robot.leftRearDrive.setPower(0.5);
//        robot.rightRearDrive.setPower(0.5);
////Pause for 5 seconds
//        sleep(500);
////Turn off motor power
//        robot.leftRearDrive.setPower(0);
//        robot.rightRearDrive.setPower(0);
//// Drive by encoder
//// Note: Reverse movement is obtained by setting a negative distance (not speed)
//        encoderDrive(DRIVE_SPEED, 2, 2, 5.0); // S1: Forward 47 Inches with 5 Sec timeout
//        encoderDrive(TURN_SPEED, 2, -2, 4.0); // S2: Turn Right 12 Inches with 4 Sec timeout
//        encoderDrive(DRIVE_SPEED, -2, -2, 4.0); // S3: Reverse 24 Inches with 4 Sec timeout
//    }
//
//    /*
//     * Method to perfmorm a relative move, based on encoder counts.
//     * Encoders are not reset as the move is based on the current position.
//     * Move will stop if any of three conditions occur:
//     * 1) Move gets to the desired position
//     * 2) Move runs out of time
//     * 3) Driver stops the opmode running.
//     */
//    public void encoderDrive(double speed,
//                             double leftInches, double rightInches,
//                             double timeoutS) {
//        int newLeftTarget;
//        int newRightTarget;
//// Ensure that the opmode is still active
//        if (opModeIsActive()) {
//// Determine new target position, and pass to motor controller
//            newLeftTarget = robot.leftRearDrive.getCurrentPosition() + (int) (leftInches * COUNTS_PER_INCH);
//            newRightTarget = robot.rightRearDrive.getCurrentPosition() + (int) (rightInches *
//                    COUNTS_PER_INCH);
//            robot.leftRearDrive.setTargetPosition(newLeftTarget);
//            robot.rightRearDrive.setTargetPosition(newRightTarget);
//// Turn On RUN_TO_POSITION
//            robot.leftRearDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//            robot.rightRearDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//// reset the timeout time and start motion.
//            runtime.reset();
//            robot.leftRearDrive.setPower(Math.abs(speed));
//            robot.rightRearDrive.setPower(Math.abs(speed));
//// keep looping while we are still active, and there is time left, and both motors are running.
//// Note: We use (isBusy() && isBusy()) in the loop test, which means that when EITHER motor hits
//// its target position, the motion will stop. This is "safer" in the event that the robot will
//// always end the motion as soon as possible.
//// However, if you require that BOTH motors have finished their moves before the robot
//            //continues
//// onto the next step, use (isBusy() || isBusy()) in the loop test.
//            while (opModeIsActive() &&
//                    (runtime.seconds() < timeoutS) &&
//                    (robot.leftRearDrive.isBusy() && robot.rightRearDrive.isBusy())) {
//// Display it for the driver.
//                telemetry.addData("Path1", "Running to %7d :%7d", newLeftTarget, newRightTarget);
//                telemetry.addData("Path2", "Running at %7d :%7d",
//                        robot.leftRearDrive.getCurrentPosition(),
//                        robot.rightRearDrive.getCurrentPosition());
//                telemetry.update();
//            }
//// Stop all motion;
//            robot.leftRearDrive.setPower(0);
//            robot.rightRearDrive.setPower(0);
//// Turn off RUN_TO_POSITION
//            robot.leftRearDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//            robot.rightRearDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//// sleep(250); // optional pause after each move
//        }
//    }
//
//    public void driveToColor(double speed, double alphaValue, double timeOutS) {
//        telemetry.update();
//        sleep(2000);
////set power
//    }
//}