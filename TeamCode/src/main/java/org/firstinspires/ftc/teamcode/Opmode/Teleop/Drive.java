package org.firstinspires.ftc.teamcode.Opmode.Teleop;

import com.acmerobotics.dashboard.config.Config;
import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.HeadingInterpolator;
import com.pedropathing.paths.Path;
import com.pedropathing.paths.PathChain;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Opmode.Blueprint.Distance;
import org.firstinspires.ftc.teamcode.Opmode.Blueprint.FSM_Lift;
import org.firstinspires.ftc.teamcode.Opmode.Blueprint.FSM_Turret;
import org.firstinspires.ftc.teamcode.Opmode.System.TelemetryX;
import org.firstinspires.ftc.teamcode.pedroPathing.Constants;
import java.util.function.Supplier;

@Config
@TeleOp
public class Drive extends OpMode {
    TelemetryX telemetryX;
    DcMotorEx FL,FR,BL,BR,intake;
    double x_joy,y_joy,rx_joy;
    boolean check_intake = false;
    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////

    //Class import
//    Drawing drawing;
//    Follower follower;
//    Supplier<PathChain> Auto_drive;
//    TelemetryX telemetryX;
//    ElapsedTime time;
//    FSM_Lift FSM_lift;
//    FSM_Turret FSM_turret;
//    Distance distance;
//    DcMotorEx turret;
//
//    public double target_turret, robot_goal_dis;
//    public boolean Goal_red = true;
//    public boolean check_turret = false;
//    public boolean check_tri = true;
//
//    private double[] pytha;
//    private boolean automatedDrive = false;
//    public static double[] multiplier = {1, 1, 0.5};
//    public static Pose startingPose = new Pose(72, -72, Math.toRadians(0));
//
//    private double Per_round = 537.7;
//    private double gear_motor = 39;
//    private double gear_turret = 89;

    @Override
    public void init() {
        FL = hardwareMap.get(DcMotorEx.class, "Front_L");
        FR = hardwareMap.get(DcMotorEx.class, "Front_R");
        BL = hardwareMap.get(DcMotorEx.class, "Back_L");
        BR = hardwareMap.get(DcMotorEx.class, "Back_R");
        intake = hardwareMap.get(DcMotorEx.class, "Intake");

        FL.setDirection(DcMotorSimple.Direction.REVERSE);
        BL.setDirection(DcMotorSimple.Direction.REVERSE);
        BR.setDirection(DcMotorSimple.Direction.REVERSE);
        intake.setDirection(DcMotorSimple.Direction.REVERSE);
        telemetryX = new TelemetryX();

        telemetryX.init(telemetry);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////

//        drawing = new Drawing();
//        time = new ElapsedTime();
//        FSM_lift = new FSM_Lift();
//        FSM_turret = new FSM_Turret();
//        distance = new Distance();
//        telemetryX = new TelemetryX();
//        turret = hardwareMap.get(DcMotorEx.class, "Turret");
//
//        follower = Constants.createFollower(hardwareMap);
//        follower.setStartingPose(startingPose);
//        follower.update();
//        time.reset();
//
////        FSM_lift.init(hardwareMap,time);
//        FSM_turret.init(hardwareMap, time);
//        distance.init(time);
//        telemetryX.init(telemetry);
//    }

    @Override
    public void start() {
//        follower.startTeleOpDrive();
    }

    @Override
    public void loop() {
        x_joy = gamepad1.left_stick_x;
        y_joy = -gamepad1.left_stick_y;
        rx_joy = gamepad1.right_stick_x;
        FL.setPower( y_joy + x_joy + rx_joy );
        FR.setPower( y_joy - x_joy - rx_joy );
        BL.setPower( y_joy - x_joy + rx_joy );
        BR.setPower( y_joy + x_joy - rx_joy );
        ////////////////////////////////////////////////////////////////////////////////////////////
        if (gamepad1.crossWasPressed()){check_intake = !check_intake; }
        if (check_intake){intake.setPower(1);}
        else if (!check_intake) {intake.setPower(0);}
        ////////////////////////////////////////////////////////////////////////////////////////////
        telemetryX.addData("X_joy",x_joy,2);
        telemetryX.addData("Y_joy",y_joy,2);
        telemetryX.addData("RX_joy",rx_joy,2);
        telemetryX.addData("Drive_motor","////////",2);
        telemetryX.addData("FL_wheel",FL.getPower(),2);
        telemetryX.addData("FR_wheel",FR.getPower(),2);
        telemetryX.addData("BL_wheel",BL.getPower(),2);
        telemetryX.addData("BR_wheel",BR.getPower(),2);
    }
    ////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////

//        follower.update();
////        FSM_lift.update_state();
//        pytha = distance.Pythagoras(follower.getPose().getX(), follower.getPose().getY(),follower.getPose().getHeading(),Goal_red);
//        target_turret = pytha[1];
//        robot_goal_dis = pytha[0];
//        FSM_turret.update_state(target_turret);

//        if (!automatedDrive) {
//            follower.setTeleOpDrive(
//                    -gamepad1.left_stick_y * multiplier[0],
//                    -gamepad1.left_stick_x * multiplier[1],
//                    -gamepad1.right_stick_x * multiplier[2],
//                    true // Robot Centric
//            );
//        }
////        if (gamepad1.circle){FSM_lift.Lift_command(FSM_Lift.Current_State.Up_max);}
//        if (gamepad1.triangle && check_tri){check_turret = !check_turret;  check_tri = false;}
//        else if (!gamepad1.triangle) {check_tri = true;}
//
//        if (check_turret) {FSM_turret.command(FSM_Turret.Current_State.Lock);}
//        else if (!check_turret) {FSM_turret.command(FSM_Turret.Current_State.Idle_state);}
//        if (gamepad1.optionsWasPressed()){Goal_red = !Goal_red;}

//        drawing.drawRobot(follower.getPose(), "red");
//        drawing.sendPacket();
//
//        telemetryX.addData("X_robot",follower.getPose().getX(),2);
//        telemetryX.addData("Y_robot",follower.getPose().getY(),2);
//        telemetryX.addData("Theta_robot",Math.toDegrees(follower.getPose().getHeading()),2);
//        telemetryX.addData("Turret_on",check_turret,2);
//        telemetryX.addData("Target_Theta",pytha[1],2);
//        telemetryX.addData("Turret_posi",convert_current_to_degree(turret.getCurrentPosition()),2);
//        telemetryX.update();

        // }

//    public double convert_current_to_degree(double motor_posi){
//        return (motor_posi / Per_round) * 360 * gear_motor / gear_turret;
//    }
}