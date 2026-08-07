package org.firstinspires.ftc.teamcode.Opmode.Blueprint;

import com.pedropathing.follower.Follower;
import com.qualcomm.robotcore.util.ElapsedTime;

public class Distance {
    ElapsedTime time;
    Follower follower;

    public double[] Red_Goal = {100 , 20 ,50}; // x,y,theta
    public double[] Blue_Goal = {100 , 20 ,50}; // x,y,theta

    public double distance,deltaX,deltaY,Theta;
    public void init (ElapsedTime elapsedTime){
        time = elapsedTime;
    }

    public double[] Pythagoras (double X,double Y,double robot_theta,boolean Goal_red){
        if (Goal_red) {
            deltaX = Red_Goal[0] - X;
            deltaY = Red_Goal[1] - Y;
            distance = Math.hypot(deltaX,deltaY);
        }
        else if (!Goal_red) {
            deltaX = Blue_Goal[0] - X;
            deltaY = Blue_Goal[1] - Y;
            distance = Math.hypot(deltaX,deltaY);
        }
        Theta = Math.toDegrees(Math.atan2(deltaY,deltaX) - robot_theta);
        double[] pythagoras = {distance,Theta};
        return pythagoras;
    }

}
