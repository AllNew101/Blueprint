package org.firstinspires.ftc.teamcode.Opmode.Blueprint;

import com.acmerobotics.dashboard.config.Config;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

@Config
public class PID_Lift {
    public static double kp = 0 ;
    public static double ki = 0 ;
    public static double kd = 0 ;
    public static double kf = 0 ;
    public static double kg = 0 ;//gravity force

    private double previous_error, error, delta_error, integral, previous_time, delta_time, power_motor;
    private ElapsedTime time;

    public void init (ElapsedTime main_time){
        time = main_time;
        previous_time = time.seconds();
        previous_error = 0;
    }
    public double PIDF (double target_posi , double current_posi){
        delta_time = time.seconds() - previous_time;
        error = target_posi - current_posi;
        delta_error = error - previous_error;
        integral += error*delta_time;

        power_motor = (kp*error) + (ki*integral) + (kd*(delta_error/delta_time)) + (kf * Math.signum(error)) + kg;
        previous_time = time.seconds();
        previous_error = error;
        return power_motor;
    }
}