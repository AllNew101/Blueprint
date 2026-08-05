package org.firstinspires.ftc.teamcode.Opmode.Blueprint;

import static org.firstinspires.ftc.teamcode.pedroPathing.Tuning.follower;

import com.pedropathing.follower.Follower;
import com.qualcomm.robotcore.util.ElapsedTime;

public class Distance {
    ElapsedTime time;
    Follower follower;

    public double[] Red_Goal = {100 , 20 ,50}; // x,y,theta
    public double[] Blue_Goal = {100 , 20 ,50}; // x,y,theta

    public double distance,deltaX,deltaY;

    public void init (ElapsedTime elapsedTime){
        time = elapsedTime;
    }

    public double Pythagoras (double X,double Y,String Goal){
        if (Goal.equals("red")) {
            deltaX = Math.abs(X - Red_Goal[0]);
            deltaY = Math.abs(Y - Red_Goal[1]);
            distance = Math.sqrt(Math.pow(deltaX,2) + Math.pow(deltaY,2));
        }
        return distance;
    }

    public void Theta_cal (){

    }

    public void Goal_dis (){

    }
}
