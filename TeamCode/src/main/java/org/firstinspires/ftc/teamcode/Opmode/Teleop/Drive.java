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
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Opmode.Blueprint.FSM_Lift;
import org.firstinspires.ftc.teamcode.pedroPathing.Constants;
import java.util.function.Supplier;

@Config
@TeleOp
public class Drive extends OpMode {
    //Class import
    private Drawing drawing;
    private Follower follower;
    private Supplier<PathChain> Auto_drive;
    ElapsedTime time;
    FSM_Lift FSM_lift;
    private boolean automatedDrive = false;
    public static double[] multiplier = {1, 1, 0.5};
    public static Pose startingPose = new Pose (72,72,Math.toRadians(-90));



    @Override
    public void init() {
        time = new ElapsedTime();
        FSM_lift = new FSM_Lift();

        follower = Constants.createFollower(hardwareMap);
        follower.setStartingPose(startingPose);
        follower.update();
        time.reset();
        time.startTime();


        FSM_lift.init(hardwareMap,time);
    }

    @Override
    public void start() {
    }

    @Override
    public void loop() {
        follower.update();
        FSM_lift.update_state();
        if (!automatedDrive) {
            follower.setTeleOpDrive(
                    -gamepad1.left_stick_y * multiplier[0],
                    -gamepad1.left_stick_x * multiplier[1],
                    -gamepad1.right_stick_x * multiplier[2],
                    true // Robot Centric
            );
        }
        if (gamepad1.circle){
            FSM_lift.Lift_command(FSM_Lift.Current_State.Up_max);
        }
}
}