package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotorEx.hardware.DcMotorEx
import com.qualcomm.robotcore.hardware.Servo
import com.qualcomm.robotcore.util.Range

class mecanumTest2 {

    private var frontRight: DcMotorEx? = null
    private var backRight: DcMotorEx? = null
    private var frontLeft: DcMotorEx? = null
    private var backLeft: DcMotorEx? = null
    private var screwLeft: DcMotorEx? = null
    private var screwRight: DcMotorEx? = null

    private var clawLeft: Servo? = null

    private var clawRight: Servo? = null

    private var arm: DcMotorEx? = null

    private val vertical: Servo? = null

    /**
     * This function is executed when this Op Mode is selected from the Driver Station.
     */
    fun runOpMode() {
        frontRight = hardwareMap.get<DcMotorEx>(DcMotorEx::class.java, "frontRight")
        backRight = hardwareMap.get<DcMotorEx>(DcMotorEx::class.java, "backRight")
        frontLeft = hardwareMap.get<DcMotorEx>(DcMotorEx::class.java, "frontLeft")
        backLeft = hardwareMap.get<DcMotorEx>(DcMotorEx::class.java, "backLeft")
        screwLeft = hardwareMap.get<DcMotorEx>(DcMotorEx::class.java, "screwLeft")
        screwRight = hardwareMap.get<DcMotorEx>(DcMotorEx::class.java, "screwRight")
        arm = hardwareMap.get<DcMotorEx>(DcMotorEx::class.java, "arm")
        frontRight = hardwareMap.get<DcMotorEx>(DcMotorEx::class.java, "frontRight")
        backRight = hardwareMap.get<DcMotorEx>(DcMotorEx::class.java, "backRight")
        frontLeft = hardwareMap.get<DcMotorEx>(DcMotorEx::class.java, "frontLeft")
        backLeft = hardwareMap.get<DcMotorEx>(DcMotorEx::class.java, "backLeft")
        screwLeft = hardwareMap.get<DcMotorEx>(DcMotorEx::class.java, "screwLeft")
        screwRight = hardwareMap.get<DcMotorEx>(DcMotorEx::class.java, "screwRight")
        clawLeft = hardwareMap.get<Servo>(Servo::class.java, "clawLeft")
        clawRight = hardwareMap.get<Servo>(Servo::class.java, "clawRight")

        // Put initialization blocks here.
        wheelsInit()
        armInit()
        waitForStart()
        if (opModeIsActive()) {
            while (opModeIsActive()) {
                // Put loop blocks here.
                mecanum(gamepad1.left_stick_y.toDouble(), gamepad1.left_stick_x.toDouble(), gamepad1.right_stick_x.toDouble())
                Lift()
                arm()
                telemetry.addData("Left Stick Y", gamepad1.left_stick_y)
                telemetry.addData("Right Stick X", gamepad1.right_stick_x)
                telemetry.update()
            }
        }
    }

    /**
     * Describe this function...
     */
    private fun wheelsInit() {
        frontRight!!.direction = DcMotor.Direction.FORWARD
        backRight!!.direction = DcMotor.Direction.FORWARD
        frontLeft!!.direction = DcMotor.Direction.REVERSE
        backLeft!!.direction = DcMotor.Direction.REVERSE
        frontRight!!.mode = DcMotor.RunMode.RUN_WITHOUT_ENCODER
        backRight!!.mode = DcMotor.RunMode.RUN_WITHOUT_ENCODER
        frontLeft!!.mode = DcMotor.RunMode.RUN_WITHOUT_ENCODER
        backLeft!!.mode = DcMotor.RunMode.RUN_WITHOUT_ENCODER
        backRight!!.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.FLOAT
        frontRight!!.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.FLOAT
        frontLeft!!.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.FLOAT
    }

    private fun armInit() {
//        TemplateJanx janx = new TemplateJanx();
//        janx.init("frontRight","backRight","backLeft","frontLeft",hardwareMap);
        frontRight = hardwareMap.get<DcMotorEx>(DcMotorEx::class.java, "frontRight")
        backRight = hardwareMap.get<DcMotorEx>(DcMotorEx::class.java, "backRight")
        frontLeft = hardwareMap.get<DcMotorEx>(DcMotorEx::class.java, "frontLeft")
        backLeft = hardwareMap.get<DcMotorEx>(DcMotorEx::class.java, "backLeft")
        screwLeft = hardwareMap.get<DcMotorEx>(DcMotorEx::class.java, "screwLeft")
        screwRight = hardwareMap.get<DcMotorEx>(DcMotorEx::class.java, "screwRight")
        clawLeft = hardwareMap.get<Servo>(Servo::class.java, "clawLeft")
        clawRight = hardwareMap.get<Servo>(Servo::class.java, "clawRight")

        // Put initialization blocks here.
        frontRight!!.direction = DcMotor.Direction.FORWARD
        backRight!!.direction = DcMotor.Direction.FORWARD
        frontLeft!!.direction = DcMotor.Direction.REVERSE
        backLeft!!.direction = DcMotor.Direction.REVERSE
        frontRight!!.mode = DcMotor.RunMode.RUN_WITHOUT_ENCODER
        backRight!!.mode = DcMotor.RunMode.RUN_WITHOUT_ENCODER
        frontLeft!!.mode = DcMotor.RunMode.RUN_WITHOUT_ENCODER
        backLeft!!.mode = DcMotor.RunMode.RUN_WITHOUT_ENCODER
        backRight!!.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.FLOAT
        frontRight!!.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.FLOAT
        frontLeft!!.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.FLOAT
        backLeft!!.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.FLOAT
        screwLeft!!.mode = DcMotor.RunMode.RUN_WITHOUT_ENCODER
        screwRight!!.mode = DcMotor.RunMode.RUN_WITHOUT_ENCODER
        screwLeft!!.direction = DcMotor.Direction.FORWARD
        screwRight!!.direction = DcMotor.Direction.FORWARD
        screwLeft!!.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.BRAKE
        screwRight!!.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.BRAKE
        arm!!.mode = DcMotor.RunMode.RUN_WITHOUT_ENCODER
        arm!!.direction = DcMotor.Direction.FORWARD
        arm!!.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.BRAKE
    }

    private fun arm() {
        if (gamepad2.left_stick_y > 0) {
            arm!!.power = 1.0
        } else if (gamepad2.left_stick_y < 0) {
            arm!!.power = -1.0
        }
    }

    private fun Lift() {
        if (gamepad2.dpad_down) {
            screwLeft!!.power = 1.0
            screwRight!!.power = 1.0
        } else if (gamepad2.dpad_up) {
            screwLeft!!.power = -1.0
            screwRight!!.power = -1.0
        } else {
            screwLeft!!.power = 0.0
            screwRight!!.power = 0.0
        }
    }

    private fun mecanum(LSY: Double, LSX: Double, RSX: Double) {
        //RSX: + if forward, - if reverse: Set direction based
        val Speed = 1600
        val lx = Math.pow(LSY, 3.0)
        val ly = Math.pow(LSY, 3.0)
        val rx = Math.pow(RSX, 3.0)
        if (LSX != 0.0 || LSY != 0.0 || RSX != 0.0) {
            frontRight!!.velocity = Speed * (Range.clip(ly + lx, -1.0, 1.0) + rx)
            backRight!!.velocity = Speed * (Range.clip(ly - lx, -1.0, 1.0) + rx)
            frontLeft!!.velocity = Speed * (Range.clip(ly - lx, -1.0, 1.0) - rx)
            backLeft!!.velocity = Speed * (Range.clip(ly + lx, -1.0, 1.0) - rx)
        } else {
            frontLeft!!.velocity = 0.0
            backLeft!!.velocity = 0.0
            frontRight!!.velocity = 0.0
            backRight!!.velocity = 0.0
        }
    }
}
