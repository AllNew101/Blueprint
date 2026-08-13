package org.firstinspires.ftc.teamcode.Opmode.Blueprint;

import com.acmerobotics.dashboard.config.Config;
import com.pedropathing.follower.Follower;
import com.qualcomm.robotcore.util.ElapsedTime;

@Config
public class Distance {
    ElapsedTime time;
    Follower follower;

    public double[] Red_Goal = {100 , 20 ,50}; // x,y,theta
    public double[] Blue_Goal = {100 , 20 ,50}; // x,y,theta
    public static double Max_angle = 120;
    public static double Min_angle = -120;

    public double distance,deltaX,deltaY,Target_Theta;
    public void init (ElapsedTime elapsedTime){
        time = elapsedTime;
    }

    public double[] Pythagoras (double X,double Y,double robot_theta,boolean Goal_red){
        if (Goal_red) {
            deltaX = Red_Goal[0] - X;// X_robot
            deltaY = Red_Goal[1] - Y;// Y_robot
            distance = Math.hypot(deltaX,deltaY);
        }
        else if (!Goal_red) {
            deltaX = Blue_Goal[0] - X;
            deltaY = Blue_Goal[1] - Y;
            distance = Math.hypot(deltaX,deltaY);
        }
        Target_Theta = Math.toDegrees(Math.atan2(deltaY,deltaX) - robot_theta);

        if (Target_Theta > Max_angle){Target_Theta = Max_angle;}
        else if (Target_Theta < Min_angle) {Target_Theta = Min_angle;}


        double[] pythagoras = {distance,Target_Theta};
        return pythagoras;
    }

}
