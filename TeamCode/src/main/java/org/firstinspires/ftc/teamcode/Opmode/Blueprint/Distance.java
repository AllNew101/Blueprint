package org.firstinspires.ftc.teamcode.Opmode.Blueprint;

import com.acmerobotics.dashboard.config.Config;
import com.pedropathing.follower.Follower;
import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

@Config
public class Distance {
    ElapsedTime time;
    Follower follower;

    public double[] Red_Goal = {128.80923076923077 , -140.4923076923077 , 33.0}; // x,y,theta
    public double[] Blue_Goal = {123, -18 , 50.0}; // x,y,theta
    public static double Max_angle = 120;
    public static double Min_angle = -120;

    public double distance,deltaX,deltaY,Target_Theta,Theta,robot;
    public void init (ElapsedTime elapsedTime){
        time = elapsedTime;
    }

    public double[] Pythagoras (double X,double Y,double robot_theta,boolean Goal_red){
        if (Goal_red) {
            deltaX = Red_Goal[0] - X;// X_robot
            deltaY = Y - Red_Goal[1];// Y_robot
            distance = Math.hypot(deltaX,deltaY);// c^2 = a^2 + b^2
        }
        else if (!Goal_red) {
            deltaX = Blue_Goal[0] - X;
            deltaY = Blue_Goal[1] - Y;
            distance = Math.hypot(deltaX,deltaY);
        }
        Theta = Math.floor(Math.atan2(deltaY,deltaX) / Math.PI * 180);
        robot = robot_theta / Math.PI * 180;
        Target_Theta = AngleUnit.normalizeDegrees(Theta + robot);

        if (Target_Theta > Max_angle){Target_Theta = Max_angle;}
        else if (Target_Theta < Min_angle) {Target_Theta = Min_angle;}


        double[] pythagoras = {distance,Target_Theta};
        return pythagoras;
    }

}
